CREATE SEQUENCE users_seq;
CREATE TABLE users (
  id INTEGER PRIMARY KEY NOT NULL DEFAULT nextval('users_seq'),
  name TEXT NOT NULL,
  email TEXT NOT NULL
);
