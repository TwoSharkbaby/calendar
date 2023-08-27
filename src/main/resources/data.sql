INSERT INTO user(createdDate, lastModifiedDate, username, password, nickname, email, role)
VALUES(now(), now(), "user1", "$2a$10$uR2ocBzEavetMPastFPeR.sgbiTTfyJ253CK5RBL5rrzXH2dk.wye", "user1", "us@er.com", "ROLE_USER");
INSERT INTO user(createdDate, lastModifiedDate, username, password, nickname, email, role)
VALUES(now(), now(), "manager", "$2a$10$F5LMmXW4Lw5jxCtWmeDJb.krcguMX3IUYIRfpQjNefPAdBxRRqzaS", "manager", "mana@ger.com", "ROLE_MANAGER");
INSERT INTO user(createdDate, lastModifiedDate, username, password, nickname, email, role)
VALUES(now(), now(), "admin", "$2a$10$Q1op8r76KUVwdXx9aFMj6uhPZmjhpJL8Aonu7I4rVmUltlDdV9/LC", "admin", "ad@min.com", "ROLE_ADMIN");

INSERT INTO resources(createdDate, lastModifiedDate, createdBy, lastModifiedBy, urlName, role)
VALUES(now(), now(), "kim", "kim", "/try/user", "ROLE_USER");
INSERT INTO resources(createdDate, lastModifiedDate, createdBy, lastModifiedBy, urlName, role)
VALUES(now(), now(), "kim", "kim", "/try/manager", "ROLE_MANAGER");
INSERT INTO resources(createdDate, lastModifiedDate, createdBy, lastModifiedBy, urlName, role)
VALUES(now(), now(), "kim", "kim", "/try/admin", "ROLE_ADMIN");