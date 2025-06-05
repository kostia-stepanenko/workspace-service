CREATE TABLE PROJECT
(
id                      INT             AUTO_INCREMENT,
compartment_id          INT             NOT NULL,
tenant_id               INT             AUTO_INCREMENT,
display_name            VARCHAR(200)    NOT NULL,
short_name              VARCHAR(20)     NOT NULL,
description             VARCHAR(4000),
lifecycle_state         VARCHAR(64)     NOT NULL CHECK(lifecycle_state IN('CREATING','ACTIVE','DELETING','DELETED','FAILED')),
config_status           VARCHAR(32)     DEFAULT 'MISSED' CHECK(config_status IN('MISSED', 'CONFIGURED')),
stores                  CLOB,
groups                  CLOB,
protected               BOOlEAN         DEFAULT TRUE,
vetted                  BOOlEAN         DEFAULT FALSE,
version                 BIGINT,
PRIMARY KEY (id)
);
--NOTE: for "version" field i use BIGINT since there is no LONG in H2,
--but it also means that we can't denote the version as for example 1.2.5,
--we can only use 125 or switch BIGINT to VARCHAR