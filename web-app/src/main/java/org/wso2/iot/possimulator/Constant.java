package org.wso2.iot.possimulator;


public class Constant {
    public static final String MULTI_TENANT_OAUTH_TOKEN_PAYLOAD = "&grant_type=password&scope=perm:android:enroll"
            + " perm:android:wipe perm:android:ring perm:android:lock-devices perm:android:configure-vpn "
            + "perm:android:configure-wifi perm:android:enroll perm:android:uninstall-application "
            + "perm:android:manage-configuration perm:android:location perm:android:install-application "
            + "perm:android:mute perm:android:change-lock-code perm:android:blacklist-applications "
            + "perm:android:set-password-policy perm:android:encrypt-storage perm:android:clear-password "
            + "perm:android:enterprise-wipe perm:android:info perm:android:view-configuration "
            + "perm:android:upgrade-firmware perm:android:set-webclip perm:android:send-notification "
            + "perm:android:disenroll perm:android:update-application perm:android:unlock-devices "
            + "perm:android:control-camera perm:android:reboot perm:android:logcat appm:read appm:subscribe "
            + "perm:sign-csr perm:admin:devices:view perm:roles:add perm:roles:add-users perm:roles:update "
            +
            "perm:roles:permissions perm:roles:details perm:roles:view perm:roles:create-combined-role " +
            "perm:roles:delete "
            + "perm:dashboard:vulnerabilities perm:dashboard:non-compliant-count perm:dashboard:non-compliant "
            + "perm:dashboard:by-groups perm:dashboard:device-counts perm:dashboard:feature-non-compliant "
            +
            "perm:dashboard:count-overview perm:dashboard:filtered-count perm:dashboard:details perm:get-activity "
            +
            "perm:devices:delete perm:devices:applications perm:devices:effective-policy " +
            "perm:devices:compliance-data "
            +
            "perm:devices:features perm:devices:operations perm:devices:search perm:devices:details " +
            "perm:devices:update "
            + "perm:devices:view perm:view-configuration perm:manage-configuration perm:policies:remove "
            +
            "perm:policies:priorities perm:policies:deactivate perm:policies:get-policy-details " +
            "perm:policies:manage "
            + "perm:policies:activate perm:policies:update perm:policies:changes perm:policies:get-details "
            +
            "perm:users:add perm:users:details perm:users:count perm:users:delete perm:users:roles " +
            "perm:users:user-details "
            +
            "perm:users:credentials perm:users:search perm:users:is-exist perm:users:update " +
            "perm:users:send-invitation "
            + "perm:admin-users:view perm:groups:devices perm:groups:update perm:groups:add perm:groups:device "
            +
            "perm:groups:devices-count perm:groups:remove perm:groups:groups perm:groups:groups-view " +
            "perm:groups:share "
            +
            "perm:groups:count perm:groups:roles perm:groups:devices-remove perm:groups:devices-add " +
            "perm:groups:assign "
            +
            "perm:device-types:features perm:device-types:types perm:applications:install " +
            "perm:applications:uninstall "
            +
            "perm:admin-groups:count perm:admin-groups:view perm:notifications:mark-checked " +
            "perm:notifications:view "
            + "perm:admin:certificates:delete perm:admin:certificates:details perm:admin:certificates:view "
            + "perm:admin:certificates:add perm:admin:certificates:verify perm:ios:enroll perm:ios:view-device "
            +
            "perm:ios:apn perm:ios:ldap perm:ios:enterprise-app perm:ios:store-application " +
            "perm:ios:remove-application "
            + "perm:ios:app-list perm:ios:profile-list perm:ios:lock perm:ios:enterprise-wipe perm:ios:device-info "
            +
            "perm:ios:restriction perm:ios:email perm:ios:cellular perm:ios:applications perm:ios:wifi " +
            "perm:ios:ring "
            + "perm:ios:location perm:ios:notification perm:ios:airplay perm:ios:caldav perm:ios:cal-subscription "
            +
            "perm:ios:passcode-policy perm:ios:webclip perm:ios:vpn perm:ios:per-app-vpn " +
            "perm:ios:app-to-per-app-vpn "
            + "perm:ios:app-lock perm:ios:clear-passcode perm:ios:remove-profile perm:ios:get-restrictions "
            + "perm:ios:wipe-data perm:admin perm:android:applications perm:devicetype:deployment "
            + "perm:android-sense:enroll perm:admin:device-type perm:device-types:events "
            + "perm:device-types:events:view perm:device-types:types perm:device:enroll perm:device:disenroll "
            +
            "perm:device:modify perm:device:operations perm:device:publish-event perm:devices:operations " +
            "perm:devices:operations perm:firealarm:enroll ";

    public static final String topic = "carbon.super/POS/";


}
