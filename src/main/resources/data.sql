INSERT INTO privileges (id, privilege) VALUES
                                      (1,'WRITE_TICKET'),
                                      (2, 'READ_TICKET'),
                                      (3, 'DELETE_TICKET'),
                                      (4, 'UPDATE_TICKET');

INSERT INTO roles (id, role) VALUES
                             (1, 'ADMIN'),
                             (2, 'AGENT'),
                             (3, 'USER');

-- ADMIN (privileges 1, 2, 3, 4)
INSERT INTO role_privilege (role_id, privilege_id) VALUES
                                                       (1, 1),
                                                       (1, 2),
                                                       (1,3),
                                                       (1, 4);


-- AGENT (PRIVILEGES 1, 2, 3, 4)
INSERT INTO role_privilege (role_id, privilege_id) VALUES
                                                       (2, 1),
                                                       (2, 2),
                                                       (2,3),
                                                       (2, 4);

-- USER (privilege 1)
INSERT INTO role_privilege (role_id, privilege_id) VALUES (3, 1);


INSERT INTO users (id, name, email, username, password)
VALUES
    ('9de56bd7-a095-4b39-b01f-1e32b7f10a8e', 'Admin', 'admin@gmail.com', 'admin', '$2a$12$9mStvpnc0O6jhzoT8EEySeUFenJAyFAi8inLGqMALtkAr2.4HCa8i');


INSERT INTO user_role (user_id, role_id) VALUES
                                             ('9de56bd7-a095-4b39-b01f-1e32b7f10a8e', 1)