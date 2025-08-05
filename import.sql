CREATE DATABASE authgw;

USE authgw;

-- la contraseña es JjersaR5649
INSERT INTO authgw.user
(account_no_expired, account_no_locked, credential_no_expired, is_enabled, email, full_name, name, password)
VALUES(1, 1, 1, 1, 'ricardo@gmail.com', 'Ricardo', 'jersa', '$2a$10$L17iV.CYKxr40lqV57YyCeXJ6Wy657vYto.MXV0TSPKNIj7ZX4QSa');

insert into authgw.role(nombre) values ('ADMIN'), ('USER');

INSERT INTO authgw.usuarios_roles
(rol_id, usuario_id)
VALUES(2, 1);

INSERT INTO authgw.permisos
(nombre)
VALUES('CREAD'), ('READ'), ('UPDATE'), ('DELETE');

INSERT INTO authgw.roles_permisos
(permiso_id, rol_id)
VALUES(1, 2), (2, 2), (3, 2), (4, 2);
