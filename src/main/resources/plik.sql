SELECT * FROM users;
DELETE  FROM users;
DESCRIBE users;
UPDATE users SET status = 'ACTIVE';

INSERT INTO users (`email`,`password`, `first_name`,`last_name`,`pesel`,`role`,`status`)
values ('doktor@gmail.com','$2a$10$oVdiH2GiY7WtCXa35xU8r.AiD8LH0VxvSfHWKsNkzFgw.zQ4/ofca','Adam','Belka','124334524','DOCTOR','ACTIVE'),
('nurse@gmail.com','$2a$10$oVdiH2GiY7WtCXa35xU8r.AiD8LH0VxvSfHWKsNkzFgw.zQ4/ofca', 'Paulina', 'Przewala', '13875897', 'NURSE', 'ACTIVE'),
('admin@gmail.com', '$2a$10$oVdiH2GiY7WtCXa35xU8r.AiD8LH0VxvSfHWKsNkzFgw.zQ4/ofca','Admiral','Adminowicz','23825252','ADMIN', 'ACTIVE');