CREATE TABLE user
(
  id    INT AUTO_INCREMENT
    PRIMARY KEY,
  email VARCHAR(40) NULL,
  uname VARCHAR(10) NULL,
  fname VARCHAR(40) null,
  lname VARCHAR(40) null,
  pass VARCHAR(40) null,
  cpass VARCHAR(40) null
)
  ENGINE = InnoDB;

CREATE TABLE user_roles
(
  id        INT AUTO_INCREMENT
    PRIMARY KEY,
  usr_sur   INT         NULL,
  role_name VARCHAR(10) NULL,
  CONSTRAINT user_fk
  FOREIGN KEY (usr_sur) REFERENCES user (id)
)
  ENGINE = InnoDB;

CREATE INDEX user_fk_idx
  ON user_roles (usr_sur);

