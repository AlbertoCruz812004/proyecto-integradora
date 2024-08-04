-- Active: 1720622037025@@127.0.0.1@3306@db_integradora
INSERT INTO permisos(nombre_permiso)
VALUES ('CREATE'),
       ('DELETE'),
       ('UPDATE');
       
INSERT INTO roles(nombre_rol)
VALUES ('ADMIN'),
       ('USER'),
       ('INVITED'),
       ('DEVELOPER');

INSERT INTO pertenece(id_rol, id_permisos)
VALUES (4, 1),
       (4, 2),
       (4, 3),
       (4, 4);

INSERT INTO usuario (email, password, is_enable, account_no_expired, account_no_locked, credential_no_expired) 
VALUES('santiago', '$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6', true, true, true, true),
      ('daniel', '$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6', true, true, true, true),
      ('andrea', '$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6', true, true, true, true),
      ('anyi', '$2a$10$cMY29RPYoIHMJSuwRfoD3eQxU1J5Rww4VnNOUOAEPqCBshkNfrEf6', true, true, true, true);

INSERT INTO usuario_rol(id_usuario, id_rol)
VALUES (1,1),
       (2,2),
       (3,3),
       (4,4);