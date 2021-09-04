DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS projects;

CREATE TABLE users (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  email VARCHAR(250) NOT NULL,
  first_name VARCHAR(250) NOT NULL,
  last_name VARCHAR(250) NOT NULL,
  password VARCHAR(250) DEFAULT NULL,
  temp_password VARCHAR(250) DEFAULT NULL
);

CREATE TABLE projects (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  type VARCHAR(250) NOT NULL,
  token_type VARCHAR(250) NOT NULL,
  ticker VARCHAR(250) DEFAULT NULL,
  stage VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  homepage VARCHAR(250) NOT NULL,
  twitter_handle VARCHAR(250) NOT NULL,
  telegram_handle VARCHAR(250) NOT NULL,
  youtube_handle VARCHAR(250) NOT NULL,
  facebook_handle VARCHAR(250) NOT NULL,
  discord_handle VARCHAR(250) NOT NULL
);

ALTER TABLE projects ADD COLUMN screenshot_url TYPE varchar(1000);

DROP TABLE IF EXISTS featuredprojects;
CREATE TABLE featuredprojects (
  id SERIAL  PRIMARY KEY,
  project_name VARCHAR(250),
  duration VARCHAR(250),
  start_date VARCHAR(250),
  end_date VARCHAR(250),
  promotion_type VARCHAR(250)
);