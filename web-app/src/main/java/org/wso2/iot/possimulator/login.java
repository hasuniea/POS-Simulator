package org.wso2.iot.possimulator;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;


@WebServlet(name = "/login")
public class login extends HttpServlet {

    private static final Log log = LogFactory.getLog(login.class);
    static final String ADMIN_USERNAME = "admin";
    static final String ADMIN_PASSWORD = "admin";

    public static final String ATTR_USER_NAME = "userName";
    public static final String ATTR_ACCESS_TOKEN = "accessToken";
    public static final String ATTR_REFRESH_TOKEN = "refreshToken";
    public static final String ATTR_ENCODED_CLIENT_APP = "encodedClientApp";

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        req.setAttribute("username", req.getParameter("username"));
        req.setAttribute("password", req.getParameter("password"));

        HttpPost apiRegEndpoint = new HttpPost("http://localhost:8280/api-application-registration/register");
        apiRegEndpoint.setHeader("Authorization",
                "Basic " + new String(Base64.encodeBase64((userName + ":" + password).getBytes())));
        apiRegEndpoint.setHeader("Content-Type", ContentType.APPLICATION_JSON.toString());
        String jsonStr = "{ \"applicationName\":\"app_123456\", \"isAllowedToAllDomains\":false, \"tags\":[ \"android\", \"device_management\" ], \"isMappingAnExistingOAuthApp\":false }";
        StringEntity apiRegPayload = new StringEntity(jsonStr, ContentType.APPLICATION_JSON);
        apiRegEndpoint.setEntity(apiRegPayload);

        String clientAppResult;
        try {
            clientAppResult = executePost(apiRegEndpoint);
        } catch (ConnectException e) {
            log.error("Cannot connect to api registration endpoint: " + apiRegEndpoint);
            res.sendError(500, "Internal Server Error, Cannot connect to api registration endpoint");
            return;
        }
        if (clientAppResult != null) {
            JSONParser jsonParser = new JSONParser();
            try {
                JSONObject jClientAppResult = (JSONObject) jsonParser.parse(clientAppResult);

                String clientId = jClientAppResult.get("client_id").toString();

                String clientSecret = jClientAppResult.get("client_secret").toString();
                String encodedClientApp = new String(Base64.encodeBase64((clientId + ":" + clientSecret).getBytes()));

                HttpPost tokenEndpoint = new HttpPost("http://localhost:8280/token");

                tokenEndpoint.setHeader("Authorization",
                        "Basic " + encodedClientApp);
                tokenEndpoint.setHeader("Content-Type", ContentType.APPLICATION_FORM_URLENCODED.toString());

                StringEntity tokenEPPayload = new StringEntity(
                        "grant_type=password&username=" + ADMIN_USERNAME + "&password=" + ADMIN_PASSWORD +
                                "&scope=" + Constant.MULTI_TENANT_OAUTH_TOKEN_PAYLOAD,
                        ContentType.APPLICATION_FORM_URLENCODED);

                tokenEndpoint.setEntity(tokenEPPayload);
                String tokenResult;
                try {
                    tokenResult = executePost(tokenEndpoint);
                } catch (ConnectException e) {
                    log.error("Cannot connect to token endpoint: " + tokenEndpoint);
                    res.sendError(500, "Internal Server Error, Cannot connect to token endpoint");
                    return;
                }
                JSONObject jTokenResult = (JSONObject) jsonParser.parse(tokenResult);
                String refreshToken = jTokenResult.get("refresh_token").toString();
                String accessToken = jTokenResult.get("access_token").toString();
                String scope = jTokenResult.get("scope").toString();

                HttpSession session = req.getSession(false);
                if (session == null) session = req.getSession(true);
                session.setAttribute(ATTR_ACCESS_TOKEN, accessToken);
                req.setAttribute(ATTR_ACCESS_TOKEN, accessToken);
                session.setAttribute(ATTR_REFRESH_TOKEN, refreshToken);
                session.setAttribute(ATTR_ENCODED_CLIENT_APP, encodedClientApp);
                session.setAttribute(ATTR_USER_NAME, userName);
                log.debug("Access Token retrieved with scopes: " + scope);

            } catch (ParseException e) {
                sendFailureRedirect(req, res);
            }

            req.getRequestDispatcher("/welcome.jsp").forward(req, res);
        }

    }

    private String executePost(HttpPost post) throws IOException {
        CloseableHttpClient client = null;
        try {
            client = getHTTPClient();
        } catch (LoginException e) {
            return null;
        }
        HttpResponse response = client.execute(post);
        System.out.println("Response Code : "
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));

        StringBuilder result = new StringBuilder();
        String line = "";
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        return result.toString();
    }

    private void sendFailureRedirect(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String referer = req.getHeader("referer");
        String redirect = (referer == null || referer.isEmpty()) ? req.getRequestURI() : referer;
        if (redirect.contains("?")) {
            redirect += "&status=fail";
        } else {
            redirect += "?status=fail";
        }
        resp.sendRedirect(redirect);
    }

    private CloseableHttpClient getHTTPClient() throws LoginException {
        SSLContextBuilder builder = new SSLContextBuilder();
        try {
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                    builder.build());
            return HttpClients.custom().setSSLSocketFactory(
                    sslsf).build();
        } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
            log.error(e.getMessage(), e);
            throw new LoginException("Error occurred while retrieving http client", e);
        }
    }
}
