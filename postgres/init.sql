CREATE TABLE balance (
  user_email TEXT PRIMARY KEY,
  amount DOUBLE PRECISION NOT NULL
);

CREATE TABLE campaign (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  visits_goal BIGINT NOT NULL,
  user_email TEXT NOT NULL,
  start_date DATE NOT NULL,
  end_date DATE NOT NULL,
  cpv DOUBLE PRECISION NOT NULL
);

CREATE TABLE ad (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  campaign_id BIGINT NOT NULL REFERENCES campaign(id),
  image_url TEXT NOT NULL
);

CREATE TABLE place (
  id BIGSERIAL PRIMARY KEY NOT NULL,
  campaign_id BIGINT NOT NULL REFERENCES campaign(id),
  lat DOUBLE PRECISION NOT NULL,
  lng DOUBLE PRECISION NOT NULL,
  visit_count BIGINT NOT NULL DEFAULT 0
);

INSERT INTO balance values ('divino', 100000000);
INSERT INTO balance values ('inloco', 100000000);

INSERT INTO balance values ('auth.com.inloco', 100000000);