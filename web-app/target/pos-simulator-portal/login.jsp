
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>

    <link href="css/bootstrap.min.css" rel="stylesheet"/>
    <link href="css/material-icons.css" rel="stylesheet" />
    <link href="css/font-awesome.min.css" rel="stylesheet">
    <link rel="stylesheet" href="css/material-kit.css"/>
</head>
<body class="signup-page">
<nav class="navbar navbar-transparent navbar-absolute">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <a class="navbar-brand"><h4><strong>POS </strong>DEVICE</h4></a>
        </div>
    </div>
</nav>
<div class="wrapper">
    <div class="header header-filter" style="background-image: url('images/login_bg2.jpg'); background-size: cover; background-position: top center;">
        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
                    <div class="card card-signup">
                        <form class="form" method="post" action="login">
                            <div class="header header-primary text-center">
                                <h5>The Point of Sales for Retail</h5>
                                <div class="social-line">
                                </div>
                            </div>
                            <p class="text-divider">Enter credentials to login</p>
                            <div class="content">
                                <% if ("fail".equals(request.getParameter("status"))) { %>
                                <div class="alert alert-dismissible alert-danger">
                                    <button type="button" class="close" data-dismiss="alert">&times;</button>
                                    <h4>Login Failed!</h4>
                                    <p>Please verify username and password again.</p>
                                </div>
                                <% } %>
                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">email</i>
										</span>
                                    <div class="form-group label-floating">
                                        <label class="control-label">Username</label>
                                        <input type="text" name="username" class="form-control">
                                    </div>
                                </div>

                                <div class="input-group">
										<span class="input-group-addon">
											<i class="material-icons">lock_outline</i>
										</span>
                                    <div class="form-group label-floating">
                                        <label class="control-label">Password</label>
                                        <input type="password" name="password" class="form-control">
                                    </div>
                                </div>

                                <% if (request.getParameter("ret") != null) { %>
                                <input type="hidden" name="ret" value="<%=request.getParameter("ret")%>" />
                                <% } %>
                                <% if (request.getParameter("q") != null) { %>
                                <input type="hidden" name="q" value="<%=request.getParameter("q")%>" />
                                <% } %>
                            </div>
                            <div class="footer text-center">
                                <input type="submit" class="btn btn-simple btn-primary btn-lg" value="Login" />
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>

</body>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/material.min.js" type="text/javascript"></script>
<script src="js/nouislider.min.js" type="text/javascript"></script>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/material-kit.js" type="text/javascript"></script>
<script src="js/jquery.min.js" type="text/javascript"></script>
<script src="js/bootstrap.min.js" type="text/javascript"></script>
<script src="js/material.min.js" type="text/javascript"></script>
<script src="js/nouislider.min.js" type="text/javascript"></script>
<script src="js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="js/material-kit.js" type="text/javascript"></script>
<script src="js/bootstrap-notify.js" type="text/javascript"></script>
<script src="js/material-dashboard.js" type="text/javascript"></script>
</html>
