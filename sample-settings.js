import { Log, UserManager} from "../../../../src";

Log.setLogger(console);
Log.setLevel(Log.INFO);

const url = window.location.origin + "/user-manager";

console.log("URL: ", url);

export const settings = {
    // authority: "https://demo.duendesoftware.com",
    authority: "http://localhost:9000",
    // client_id: "interactive.public",
    client_id: "client",
    client_secret: "secret",

    //client_id: 'interactive.public.short',
    redirect_uri: url + "/sample.html",
    // redirect_uri: "http://127.0.0.1:1234/user-manager/sample.html",
    // post_logout_redirect_uri: url + "/sample.html",
    response_type: "code",
    //response_mode: 'fragment',
    scope: "openid profile read",
    // scope: "openid profile api",
    //scope: 'openid profile api offline_access',

    // popup_redirect_uri: url + "/sample-popup-signin.html",
    // popup_post_logout_redirect_uri: url + "/sample-popup-signout.html",

    // silent_redirect_uri: url + "/sample-silent.html",
    // automaticSilentRenew: false,
    // validateSubOnSilentRenew: true,
    //silentRequestTimeout: 10000,

    loadUserInfo: false,

    monitorAnonymousSession: true,

    filterProtocolClaims: true,
    revokeAccessTokenOnSignout: true,

    metadata: {
        issuer: "http://localhost:9000",
        jwks_uri:
            "http://localhost:9000/.well-known/openid-configuration/jwks",
        authorization_endpoint:
            "http://localhost:9000/oauth2/authorize",
        token_endpoint: "http://localhost:9000/oauth2/token",
        userinfo_endpoint: "https://localhost:9000/userinfo",
        end_session_endpoint:
            "https://localhost:9000/endsession",

    },
};

// export const settings = {
//     authority: "http://localhost:1234/oidc",
//     client_id: "js.tokenmanager",
//     redirect_uri: url + "/sample.html",
//     post_logout_redirect_uri: url + "/sample.html",
//     response_type: "code",
//     scope: "openid email roles",

//     response_mode: "fragment",

//     popup_redirect_uri: url + "/sample-popup-signin.html",
//     popup_post_logout_redirect_uri: url + "/sample-popup-signout.html",

//     silent_redirect_uri: url + "/sample-silent.html",
//     automaticSilentRenew: true,

//     filterProtocolClaims: true
// };

// export const settings = {
//     authority: "http://localhost:9000",
//     client_id: "js.tokenmanager",
//     redirect_uri: url + "/sample.html",
//     post_logout_redirect_uri: url + "/sample.html",
//     response_type: "code",
//     scope: "openid email roles",

//     response_mode: "fragment",

//     popup_redirect_uri: url + "/sample-popup-signin.html",
//     popup_post_logout_redirect_uri: url + "/sample-popup-signout.html",

//     silent_redirect_uri: url + "/sample-silent.html",
//     automaticSilentRenew: true,

//     filterProtocolClaims: true
// };

// export const settings = {
//     // authority: "https://demo.duendesoftware.com",
//     authority: "http://localhost:9000",
//     // client_id: "interactive.public",
//     client_id: "client",
//     client_secret: "$2a$10$lcGI9Fp6GLfk7wjyOK0VqORQqMtsQRoC3J7i/V023SgQv9JZLZ01K",

//     //client_id: 'interactive.public.short',
//     // redirect_uri: url + "/sample.html",
//     redirect_uri: "http://127.0.0.1:1234/user-manager/sample.html",
//     // post_logout_redirect_uri: url + "/sample.html",
//     response_type: "code",
//     //response_mode: 'fragment',
//     scope: "openid read",
//     // scope: "openid profile api",
//     //scope: 'openid profile api offline_access',

//     // popup_redirect_uri: url + "/sample-popup-signin.html",
//     // popup_post_logout_redirect_uri: url + "/sample-popup-signout.html",

//     // silent_redirect_uri: url + "/sample-silent.html",
//     // automaticSilentRenew: false,
//     // validateSubOnSilentRenew: true,
//     //silentRequestTimeout: 10000,

//     loadUserInfo: true,

//     monitorAnonymousSession: true,

//     filterProtocolClaims: true,
//     revokeAccessTokenOnSignout: true,

//     // metadata: {
//     //     issuer: "http://localhost:9000",
//     //     jwks_uri:
//     //         "http://localhost:9000/.well-known/openid-configuration/jwks",
//     //     authorization_endpoint:
//     //         "http://localhost:9000/oauth2/authorize",
//     //     token_endpoint: "http://localhost:9000/oauth2/token",
//     //     userinfo_endpoint: "https://demo.duendesoftware.com/connect/userinfo",
//     //     end_session_endpoint:
//     //         "https://demo.duendesoftware.com/connect/endsession",
//     //     check_session_iframe:
//     //         "https://demo.duendesoftware.com/connect/checksession",
//     //     revocation_endpoint:
//     //         "https://demo.duendesoftware.com/connect/revocation",
//     //     introspection_endpoint:
//     //         "https://demo.duendesoftware.com/connect/introspect",
//     //     device_authorization_endpoint:
//     //         "https://demo.duendesoftware.com/connect/deviceauthorization",
//     //     frontchannel_logout_supported: true,
//     //     frontchannel_logout_session_supported: true,
//     //     backchannel_logout_supported: true,
//     //     backchannel_logout_session_supported: true,
//     //     scopes_supported: [
//     //         "openid",
//     //         "profile",
//     //         "email",
//     //         "api",
//     //         "api.scope1",
//     //         "api.scope2",
//     //         "scope2",
//     //         "policyserver.runtime",
//     //         "policyserver.management",
//     //         "offline_access",
//     //     ],
//     //     claims_supported: [
//     //         "sub",
//     //         "name",
//     //         "family_name",
//     //         "given_name",
//     //         "middle_name",
//     //         "nickname",
//     //         "preferred_username",
//     //         "profile",
//     //         "picture",
//     //         "website",
//     //         "gender",
//     //         "birthdate",
//     //         "zoneinfo",
//     //         "locale",
//     //         "updated_at",
//     //         "email",
//     //         "email_verified",
//     //     ],
//     //     grant_types_supported: [
//     //         "authorization_code",
//     //         "client_credentials",
//     //         "refresh_token",
//     //         "implicit",
//     //         "password",
//     //         "urn:ietf:params:oauth:grant-type:device_code",
//     //     ],
//     //     response_types_supported: [
//     //         "code",
//     //         "token",
//     //         "id_token",
//     //         "id_token token",
//     //         "code id_token",
//     //         "code token",
//     //         "code id_token token",
//     //     ],
//     //     response_modes_supported: ["form_post", "query", "fragment"],
//     //     token_endpoint_auth_methods_supported: [
//     //         "client_secret_basic",
//     //         "client_secret_post",
//     //     ],
//     //     id_token_signing_alg_values_supported: ["RS256"],
//     //     subject_types_supported: ["public"],
//     //     code_challenge_methods_supported: ["plain", "S256"],
//     //     request_parameter_supported: true,
//     // },
//     //metadataSeed: {"some_extra_data":"some_value"},
//     //signingKeys:[{"kty":"RSA","use":"sig","kid":"5CCAA03EDDE26D53104CC35D0D4B299C","e":"AQAB","n":"3fbgsZuL5Kp7HyliAznS6N0kTTAqApIzYqu0tORUk4T9m2f3uW5lDomNmwwPuZ3QDn0nwN3esx2NvZjL_g5DN407Pgl0ffHhARdtydJvdvNJIpW4CmyYGnI8H4ZdHtuW4wF8GbKadIGgwpI4UqcsHuPiWKARfWZMQfPKBT08SiIPwGncavlRRDgRVX1T94AgZE_fOTJ4Odko9RX9iNXghJIzJ_wEkY9GEkoHz5lQGdHYUplxOS6fcxL8j_N9urSBlnoYjPntBOwUfPsMoNcmIDXPARcq10miWTz8SHzUYRtsiSUMqimRJ9KdCucKcCmttB_p_EAWohJQDnav-Vqi3Q","alg":"RS256"}]
// };

// export const settings = {
//     authority: "http://localhost:1234/oidc",
//     client_id: "js.tokenmanager",
//     redirect_uri: url + "/sample.html",
//     post_logout_redirect_uri: url + "/sample.html",
//     response_type: "code",
//     scope: "openid email roles",

//     response_mode: "fragment",

//     popup_redirect_uri: url + "/sample-popup-signin.html",
//     popup_post_logout_redirect_uri: url + "/sample-popup-signout.html",

//     silent_redirect_uri: url + "/sample-silent.html",
//     automaticSilentRenew: true,

//     filterProtocolClaims: true
// };


console.log("Settings: ", settings);

export {
    Log,
    UserManager
};
