DROP TABLE IF EXISTS subscriber CASCADE;

CREATE TABLE subscriber (
  id BIGINT,
  name TEXT,
  age INTEGER,
  fbid VARCHAR(128),
  internalattr INTEGER
);

CREATE SEQUENCE subscriber_id_seq INCREMENT 1 MINVALUE 1 START 1 OWNED BY subscriber.id;

ALTER TABLE subscriber ALTER COLUMN id SET DEFAULT nextval('subscriber_id_seq');

ALTER TABLE subscriber ADD CONSTRAINT subscriber_id_pkey PRIMARY KEY(id);

INSERT 
  INTO subscriber(name, age, fbid, internalattr)
  VALUES('jianwu', 40, 'jian.wu@querydslexample.com', 10);