CREATE TABLE UserMobileApp (
    userId BIGINT NOT NULL,
    appId BIGINT NOT NULL,
    PRIMARY KEY (userId, appId),
    CONSTRAINT fk_ref_user FOREIGN KEY (userId) REFERENCES user_Details(userId),
    CONSTRAINT fk_ref_mobileapp FOREIGN KEY (appId) REFERENCES MobileApp(appId)
);