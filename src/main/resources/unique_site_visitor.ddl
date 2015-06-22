CREATE TABLE IF NOT EXISTS unique_site_visitor
(
   visitorId VARCHAR   NOT NULL,
   siteId    INTEGER   NOT NULL,
   visitTs   BIGINT
   CONSTRAINT my_pk  PRIMARY KEY (visitorId, siteId)
)