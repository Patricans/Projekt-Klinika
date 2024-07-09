SELECT * FROM users;
DELETE  FROM users;
DESCRIBE users;
UPDATE users SET status = 'ACTIVE';

INSERT INTO users (`email`,`password`, `first_name`,`last_name`,`pesel`,`role`,`status`)
values ('doktor@gmail.com','$2a$10$oVdiH2GiY7WtCXa35xU8r.AiD8LH0VxvSfHWKsNkzFgw.zQ4/ofca','Adam','Belka','124334524','DOCTOR','ACTIVE'),
       ('nurse@gmail.com','$2a$10$oVdiH2GiY7WtCXa35xU8r.AiD8LH0VxvSfHWKsNkzFgw.zQ4/ofca', 'Paulina', 'Przewala', '13875897', 'NURSE', 'ACTIVE'),
       ('admin@gmail.com', '$2a$10$oVdiH2GiY7WtCXa35xU8r.AiD8LH0VxvSfHWKsNkzFgw.zQ4/ofca','Admiral','Adminowicz','23825252','ADMIN', 'ACTIVE');

INSERT INTO users (`email`,`password`, `first_name`,`last_name`,`pesel`,`role`,`status`) values
    ('patient@gmail.com', '$2a$10$oVdiH2GiY7WtCXa35xU8r.AiD8LH0VxvSfHWKsNkzFgw.zQ4/ofca','BP','NFN','23825252','PATIENT', 'ACTIVE');
describe schedule;
SELECT * FROM schedule;
DELETE FROM schedule;
insert into schedule(personnel_id, start_date, end_date)
values
    (9, '2024-07-08 07:00', '2024-07-08 17:00'),
    (9, '2024-07-09 07:00', '2024-07-09 17:00'),
    (9, '2024-07-10 17:00', '2024-07-11 03:00'),
    (9, '2024-07-11 17:00', '2024-07-12 03:00');

insert into schedule(personnel_id, start_date, end_date)
values
    ((SELECT id FROM users WHERE role='DOCTOR' limit 1), '2024-07-08 07:00', '2024-07-08 17:00'),
    ((SELECT id FROM users WHERE role='DOCTOR' limit 1), '2024-07-09 07:00', '2024-07-09 17:00'),
    ((SELECT id FROM users WHERE role='DOCTOR' limit 1), '2024-07-10 17:00', '2024-07-11 03:00'),
    ((SELECT id FROM users WHERE role='DOCTOR' limit 1), '2024-07-11 17:00', '2024-07-12 03:00');

describe visits;
describe e_receipt;
ALTER TABLE e_receipt ADD COLUMN visit_id INT NULL;
alter table e_receipt add FOREIGN KEY (visit_id) references visits(id);

describe drugs;
describe receipt_drugs;

alter table receipt_drugs add column ereceipt_id int not null;
alter table receipt_drugs add foreign key (ereceipt_id) references e_receipt(id)

update users set password = (SELECT password from users where id = 1 limit 1) where id != 1;

select * from users;

insert into visits(patient_id, doctor_id, start_date, duration, doctor_notes, cancel)
values
    ((select id from users where role = 'PATIENT' limit 1), (select id from users where role = 'DOCTOR' limit 1), '2024-10-10 12:13',90,'Pacjent ma pic 4 monsterki dziennie', null),
    ((select id from users where role = 'PATIENT' limit 1), (select id from users where role = 'DOCTOR' limit 1), '2024-10-12 15:11',15,null, null);

alter table e_receipt change id id int auto_increment not null;
alter table receipt_drugs change id id int auto_increment not null;
alter table drugs change id id int auto_increment not null;
select * from visits;

insert into e_receipt(doctor_id, patient_id, date, visit_id) values
    ((select id from users where role = 'DOCTOR' limit 1),(select id from users where role = 'PATIENT' limit 1), '2024-10-10 12:15', 2);

select * from drugs;
insert into drugs(name, vendor, stock) VALUES('Monster Doctor', 'Monster Energy', 555);

insert into receipt_drugs(drug_id, amount, expiration_date, bought, last_purchased, ereceipt_id) VALUES
    (1, 5, '2024-11-10 12:00', 1, null, 1);

select * from receipt_drugs;
select * from e_receipt;