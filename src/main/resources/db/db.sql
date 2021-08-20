DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id SERIAL  PRIMARY KEY,
  email VARCHAR(250) NOT NULL,
  email_confirmed VARCHAR(250) DEFAULT 'false',
  password VARCHAR(250) DEFAULT NULL
);

DROP TABLE IF EXISTS projects;
CREATE TABLE projects (
  id SERIAL  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  type VARCHAR(250) NOT NULL,
  ticker VARCHAR(250),
  stage VARCHAR(250),
  description VARCHAR(3000),
  short_description VARCHAR(250),
  homepage VARCHAR(250),
  whitepaper_url VARCHAR(250),
  youtube_embed_id VARCHAR(250),
  twitter_handle VARCHAR(250),
  telegram_handle VARCHAR(250),
  youtube_handle VARCHAR(250),
  facebook_handle VARCHAR(250),
  discord_handle VARCHAR(250),
  github_link VARCHAR(250),
  gitlab_link VARCHAR(250),
  reddit VARCHAR(250),
  image_url VARCHAR(250),
  
  token_type VARCHAR(250),
  total_supply VARCHAR(250),
  circulating_supply VARCHAR(250),
  token_distribution_link VARCHAR(250),
  policy_id VARCHAR(250),

  owner_email VARCHAR(250),
  release_date VARCHAR(250);
  created_date DATE,
  updated_date DATE,
  verified VARCHAR(250)
);

DROP TABLE IF EXISTS projectteam;
CREATE TABLE projectteam (
  id SERIAL  PRIMARY KEY,
  project_name VARCHAR(250),
  member_name VARCHAR(250) NOT NULL,
  position VARCHAR(250),
  twitter VARCHAR(250),
  linkin VARCHAR(250),
  github VARCHAR(250) ,
  img VARCHAR(250)
);

DROP TABLE IF EXISTS projectsales;
CREATE TABLE projectsales (
  id SERIAL  PRIMARY KEY,
  project_name VARCHAR(250),
  sale_details_link VARCHAR(250),
  upcoming_sale VARCHAR(250),
  sale_start_date VARCHAR(250),
  sale_end_date VARCHAR(250),
  sale_token_price VARCHAR(250),
  token_distribution_detail VARCHAR(250),
  accepted_funding VARCHAR(250)
);

DROP TABLE IF EXISTS projectviews;
CREATE TABLE projectviews (
  id SERIAL  PRIMARY KEY,
  project_name VARCHAR(250),
  month_name VARCHAR(250),
  view_count INT
);

DROP TABLE IF EXISTS featuredprojects;
CREATE TABLE featuredprojects (
  id SERIAL  PRIMARY KEY,
  project_name VARCHAR(250),
  duration VARCHAR(250),
  start_date VARCHAR(250),
  end_date VARCHAR(250),
  promotion_type VARCHAR(250)
);