CREATE TABLE IF NOT EXISTS visitor_history
(
   visitor_id VARCHAR   NOT NULL,
   site_id    INT       NOT NULL,
   visit_ts   BIGINT    NOT NULL
   CONSTRAINT my_pk  PRIMARY KEY (visitor_id, site_id));