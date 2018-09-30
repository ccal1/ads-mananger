CREATE TABLE user (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  login TEXT,
  password TEXT
);

CREATE TABLE balance (
  user_id BIGINT PRIMARY KEY REFERENCES user(id),
  ammount DOUBLE PRECISION NOT NULL
);


CREATE TABLE campaign (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  visits_goal BIGINT NOT NULL,
  user_id BIGINT NOT NULL REFERENCES user(id),
  start_date DATE NOT NULL,
  end_date DATE NOT NULL
);

CREATE TABLE ad (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  campaign_id BIGINT NOT NULL REFERENCES campaign(id),
  image_url TEXT NOT NULL
)

CREATE TABLE place (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  campaign_id BIGINT NOT NULL REFERENCES campaign(id),
  lat DOUBLE PRECISION NOT NULL,
  lnt DOUBLE PRECISION NOT NULL,
  visit_count BIGINT NOT NULL DEFAULT 0
)