-- begin SAAS_PAYMENT_METHOD
create table SAAS_PAYMENT_METHOD (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    --
    NAME varchar(255) not null,
    DESCRIPTION varchar(255),
    --
    primary key (ID)
)^
-- end SAAS_PAYMENT_METHOD
-- begin SAAS_CUSTOMER
create table SAAS_CUSTOMER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    CLIENT integer not null,
    --
    NAME varchar(255) not null,
    --
    primary key (ID)
)^
-- end SAAS_CUSTOMER
-- begin SAAS_ORDER
create table SAAS_ORDER (
    ID uuid,
    VERSION integer not null,
    CREATE_TS timestamp,
    CREATED_BY varchar(50),
    UPDATE_TS timestamp,
    UPDATED_BY varchar(50),
    DELETE_TS timestamp,
    DELETED_BY varchar(50),
    CLIENT integer not null,
    --
    NUM varchar(255) not null,
    DATE_ date,
    CUSTOMER_ID uuid,
    PAYMENT_METHOD_ID uuid,
    --
    primary key (ID)
)^
-- end SAAS_ORDER
