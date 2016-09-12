DROP TABLE IF EXISTS bookings;
DROP TABLE IF EXISTS organization;

CREATE TABLE bookings (
  id         INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  first_name VARCHAR(5)       NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE organization (
  id     INT(10) UNSIGNED NOT NULL AUTO_INCREMENT,
  code   VARCHAR(255)     NOT NULL,
  name   VARCHAR(255)     NOT NULL,
  status VARCHAR(32)      NOT NULL DEFAULT 'active',
  PRIMARY KEY (id),
  UNIQUE KEY unique_code (code),
  KEY name_idx (name)
) ENGINE = InnoDB DEFAULT CHARSET = latin1;

INSERT INTO organization (code, name, status) VALUES ('NEVERMINED_THE_CODE', 'Russia', 'removed');
