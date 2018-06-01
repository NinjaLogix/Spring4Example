create table `user_details` (
  `id` varchar(40) primary key,
  `user_id` varchar(40) default null,
  `acct_enbl` boolean default null,
  `acct_non_exp` boolean default null,
  `creds_non_exp` boolean default null,
  `acct_non_lked` boolean default null,

  constraint details_fk
  foreign key (user_id) references user (id)
) ENGINE = InnoDB;

create index detail_fk_idx
  on user_details (user_id);