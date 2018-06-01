CREATE TABLE `user`(
  `id`    VARCHAR(40) PRIMARY KEY,
  `email` VARCHAR(40) DEFAULT NULL,
  `uname` VARCHAR(10) DEFAULT NULL,
  `fname` VARCHAR(40) DEFAULT NULL,
  `lname` VARCHAR(40) DEFAULT NULL,
  `pass`  VARCHAR(40) DEFAULT NULL,
  `cpass` VARCHAR(40) DEFAULT NULL
) ENGINE = InnoDB;

CREATE TABLE `user_roles`(
  `id` VARCHAR(40) PRIMARY KEY,
  `user_id` VARCHAR(40) DEFAULT NULL,
  `role_name` VARCHAR(10) DEFAULT NULL,

  CONSTRAINT user_fk
  FOREIGN KEY (user_id) REFERENCES user (id)
) ENGINE = InnoDB;

CREATE INDEX user_fk_idx
  ON user_roles (user_id);