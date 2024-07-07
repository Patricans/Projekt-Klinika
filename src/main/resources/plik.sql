SELECT * FROM users;
DELETE  FROM users;
DESCRIBE users;
UPDATE users SET status = 'ACTIVE';

INSERT INTO users (`email`,`password`, `first_name`,`last_name`,`pesel`,`role`,`status`)
values ('doktor@gmail.com','$2a$10$oVdiH2GiY7WtCXa35xU8r.AiD8LH0VxvSfHWKsNkzFgw.zQ4/ofca','Adam','Belka','124334524','DOCTOR','ACTIVE'),
('nurse@gmail.com','$2a$10$oVdiH2GiY7WtCXa35xU8r.AiD8LH0VxvSfHWKsNkzFgw.zQ4/ofca', 'Paulina', 'Przewala', '13875897', 'NURSE', 'ACTIVE'),
('admin@gmail.com', '$2a$10$oVdiH2GiY7WtCXa35xU8r.AiD8LH0VxvSfHWKsNkzFgw.zQ4/ofca','Admiral','Adminowicz','23825252','ADMIN', 'ACTIVE');

describe schedule;
SELECT * FROM schedule;
DELETE FROM schedule;
insert into schedule(personnel_id, start_date, end_date)
values
    (9, '2024-07-08 07:00', '2024-07-08 17:00'),
    (9, '2024-07-09 07:00', '2024-07-09 17:00'),
    (9, '2024-07-10 17:00', '2024-07-11 03:00'),
    (9, '2024-07-11 17:00', '2024-07-12 03:00');