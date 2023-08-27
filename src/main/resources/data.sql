SELECT * FROM users;
SELECT * FROM authorities;
SELECT * FROM users_authorities;

INSERT INTO authorities(authority) VALUES('ROLE_USER');
INSERT INTO authorities(authority) VALUES('ROLE_ADMIN');
INSERT INTO authorities(authority) VALUES('ROLE_DEVELOPER');

INSERT INTO users(username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('Developer', '$2a$12$2yOChyhSuJm/naTBUjGZb.6d6mu1NsXS8XWRFousQfRTwzy0ZQtWW', true, true, true, true);
INSERT INTO users(username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('Admin', '$2a$12$2yOChyhSuJm/naTBUjGZb.6d6mu1NsXS8XWRFousQfRTwzy0ZQtWW', true, true, true, true);
INSERT INTO users(username, password, account_non_expired, account_non_locked, credentials_non_expired, enabled)
VALUES ('User', '$2a$12$2yOChyhSuJm/naTBUjGZb.6d6mu1NsXS8XWRFousQfRTwzy0ZQtWW', true, true, true, true);

INSERT INTO users_authorities(users_id, authorities_id) VALUES (1, 1);
INSERT INTO users_authorities(users_id, authorities_id) VALUES (1, 2);
INSERT INTO users_authorities(users_id, authorities_id) VALUES (1, 3);
INSERT INTO users_authorities(users_id, authorities_id) VALUES (2, 1);
INSERT INTO users_authorities(users_id, authorities_id) VALUES (2, 2);
INSERT INTO users_authorities(users_id, authorities_id) VALUES (3, 1);

SELECT * FROM client;

INSERT INTO client(id, authorization_grant_types, client_authentication_methods, client_id, client_id_issued_at, client_name,
client_secret, client_secret_expires_at, client_settings, redirect_uris, scopes, token_settings)
VALUES('abbc70f1-fb59-4b42-b1e4-c52fa0080bea', 'refresh_token,client_credentials,authorization_code', 'client_secret_basic',
'client', null, 'abbc70f1-fb59-4b42-b1e4-c52fa0080bea', '$2a$10$lcGI9Fp6GLfk7wjyOK0VqORQqMtsQRoC3J7i/V023SgQv9JZLZ01K', null,
'{"@class":"java.util.Collections$UnmodifiableMap","settings.client.require-proof-key":true,"settings.client.require-authorization-consent":true}',
'http://insomnia,http://127.0.0.1:8080/login/oauth2/code/client', 'read,openid,profile',
'{"@class":"java.util.Collections$UnmodifiableMap","settings.token.reuse-refresh-tokens":true,
"settings.token.id-token-signature-algorithm":["org.springframework.security.oauth2.jose.jws.SignatureAlgorithm","RS256"],
"settings.token.access-token-time-to-live":["java.time.Duration",86400.000000000],
"settings.token.access-token-format":{"@class":"org.springframework.security.oauth2.server.authorization.settings.OAuth2TokenFormat",
"value":"self-contained"},"settings.token.refresh-token-time-to-live":["java.time.Duration",3600.000000000],
"settings.token.authorization-code-time-to-live":["java.time.Duration",300.000000000]}');