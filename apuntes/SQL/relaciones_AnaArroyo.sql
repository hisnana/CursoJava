
--EJERCICIO RESUELTO ANA ARROYO PLZA

--Enunciado (Relación 1 a 1)
--Contexto. En tu empresa, cada EMPLEADO puede (o no) tener exactamente una CREDENCIAL_ACCESO para entrar al edificio (tarjeta RFID). Si existe credencial, es única para ese empleado y no puede compartirse
--Objetivo. Modelar y consultar una relación 1:1 estricta entre EMPLEADO y CREDENCIAL_ACCESO usando Oracle XE.

--Tareas:
--Modelo de datos
--Crea la tabla EMPLEADO con: EMPLOYEE_ID (PK), FULL_NAME, HIRED_ON.
CREATE TABLE empleado (
employee_id NUMBER CONSTRAINT pk_empleado PRIMARY KEY,
full_name VARCHAR2(100) NOT NULL,
hired_on DATE
);
--
--Crea la tabla CREDENCIAL_ACCESO con:
--Patrón 1:1 estricto de PK compartida: EMPLOYEE_ID será PK y a la vez FK a EMPLEADO(EMPLOYEE_ID).
--Campos: RFID_CODE (único), ISSUED_ON es una fecha.
--Si se borra un empleado, su credencial debe borrarse automáticamente.
--Carga de datos

CREATE TABLE credencial_acceso (
employee_id NUMBER CONSTRAINT pk_credencial PRIMARY KEY, -- PK = FK: garantiza máx 1 credencial por empleado (1:1)
rfid_code VARCHAR2(32) CONSTRAINT uq_credencial_rfid UNIQUE, -- UNIQUE: la tarjeta no se comparte (valor irrepetible)
issued_on DATE,
CONSTRAINT fk_credencial_empleado FOREIGN KEY (employee_id) REFERENCES empleado(employee_id)
ON DELETE CASCADE -- al borrar empleado, se borra su credencial → consistencia (ACID: Consistency)
);
--
--Inserta 3 empleados.
INSERT INTO empleado (employee_id, full_name, hired_on) VALUES (1,'Ana López', DATE '2023-03-01');
INSERT INTO empleado (employee_id, full_name, hired_on) VALUES (2,'Bruno Martín', DATE '2024-06-15');
INSERT INTO empleado (employee_id, full_name, hired_on) VALUES (3,'Carla Nieves', DATE '2025-01-10');
--Asigna credencial a 2 de ellos; deja 1 sin credencial.
INSERT INTO credencial_acceso (employee_id, rfid_code, issued_on) VALUES (1,'RFID-0001', DATE '2025-10-23');
INSERT INTO credencial_acceso (employee_id, rfid_code, issued_on) VALUES (2,'RFID-0002', DATE '2025-10-23');
--Consultas
--a) Lista solo los empleados con credencial
SELECT e.employee_id, e.full_name, c.rfid_code
FROM empleado e JOIN credencial_acceso c 
ON c.employee_id = e.employee_id;
--b) Lista todos los empleados y su credencial si existe 
SELECT e.employee_id, e.full_name, c.rfid_code
FROM empleado e LEFT JOIN credencial_acceso c 
ON c.employee_id = e.employee_id
ORDER BY e.employee_id;
--c) Empleados sin credencial 
SELECT e.employee_id, e.full_name, c.rfid_code
FROM empleado e LEFT JOIN credencial_acceso c 
ON c.employee_id = e.employee_id
WHERE c.rfid_code IS NULL;
--d) Comprobación de unicidad: intenta insertar una credencial con un RFID_CODE duplicado y observa el error de restricción.

INSERT INTO credencial_acceso (employee_id, rfid_code, issued_on) VALUES (3,'RFID-0001', DATE '2025-10-23');
--Resultado:ORA-00001: restricción única (GETAFE.UQ_CREDENCIAL_RFID) violada.
