drop table if exists oauth_client_details;
create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove VARCHAR(255)
) ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

drop table if exists oauth_client_token;
create table oauth_client_token (
  token_id VARCHAR(255),
/*  token LONG VARBINARY, */
  token BLOB,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;

drop table if exists oauth_access_token;
create table oauth_access_token (
  token_id VARCHAR(255),
/*  token LONG VARBINARY, */
  token BLOB,
  authentication_id VARCHAR(255) PRIMARY KEY,
  user_name VARCHAR(255),
  client_id VARCHAR(255),
/*  authentication LONG VARBINARY,*/
  authentication BLOB,
  refresh_token VARCHAR(255)
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
 
drop table if exists oauth_refresh_token;
create table oauth_refresh_token (
  token_id VARCHAR(255),
/*  token LONG VARBINARY, */
  token BLOB,
/*  authentication LONG VARBINARY,*/
  authentication BLOB
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
 
drop table if exists oauth_code;
create table oauth_code (
  code VARCHAR(255), 
/*  authentication LONG VARBINARY*/
  authentication BLOB
)ENGINE=InnoDB DEFAULT CHARSET=UTF8MB4;
 

drop table if exists oauth_approvals;
create table oauth_approvals (
    user_id VARCHAR(255),
    client_id VARCHAR(255),
    scope VARCHAR(255),
    status VARCHAR(10),
    expires_at TIMESTAMP default CURRENT_TIMESTAMP,
    last_modified_at TIMESTAMP default CURRENT_TIMESTAMP
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

