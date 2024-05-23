INSERT INTO user_Details (userId, userName,userEmail,userMono) VALUES
(1, 'Alice','alice.12@gmail.com',7894561230),
(2, 'Bob','haybob@gmail.com',71234569871),
(3, 'Charlie','charliechampin@gmail.com',6541239873);

-- Insert data into MobileApp table
INSERT INTO MobileApp (appId, appName, appType) VALUES
(1, 'ChatApp', 'Communication'),
(2, 'PhotoEditor', 'Photography'),
(3, 'WeatherTracker', 'Utility');

-- Insert data into UserMobileApp join table
INSERT INTO UserMobileApp (userId, appId) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3),
(3, 2),
(3, 3);