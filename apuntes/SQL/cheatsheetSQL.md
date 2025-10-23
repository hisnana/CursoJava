

FK es una restriccion.

# 🗒️ cheatsheet SQL— Clase 2025-10-23

### 🔑 FK (Foreign Key) — lo de hoy

* **Restricción** de **integridad referencial** (hijo → padre)
* **Por qué**: limita valores permitidos; evita filas **huérfanas**
* **ACID**: **Consistency (Consistencia)**

```sql
FOREIGN KEY (padre_id) REFERENCES padre(id) ON DELETE CASCADE
```

---

### 🧩 PK ↔ FK — 2 maneras (sintaxis)

1. **En `CREATE TABLE` (columna)**

```sql
CREATE TABLE hijo (
  id        NUMBER PRIMARY KEY,
  padre_id  NUMBER REFERENCES padre(id)
);
```

2. **Constraint nombrada (`CREATE`/`ALTER`)**

```sql
ALTER TABLE hijo
  ADD CONSTRAINT fk_hijo_padre
  FOREIGN KEY (padre_id) REFERENCES padre(id);
```

> Nota: relación N:M → **tabla puente** con **PK compuesta** (dos FKs).

---

### ❓ ¿Una FK tiene que ser única?

* **No.** Por defecto **NO** es única → permite **1:N**.
* Será única **solo** si añades `UNIQUE` a la columna FK → **1:1**.
* **NULL** permitido si la columna lo permite (los `NULL` no se validan contra la tabla padre).
* **Índice** en FK: **recomendado** por rendimiento; no obligatorio.
* **Multicolumna**: puede referir a PK/UNIQUE compuesto (mismo orden/columnas).

**Patrones**

```sql
-- 1:N (FK NO única)
CREATE TABLE hijo (
  id NUMBER PRIMARY KEY,
  padre_id NUMBER,
  CONSTRAINT fk_hijo_padre FOREIGN KEY (padre_id) REFERENCES padre(id)
);

-- 1:1 (FK única)
ALTER TABLE hijo ADD CONSTRAINT uq_hijo_padre UNIQUE (padre_id);
```

### 🔗 Relaciones entre tablas (cardinalidad)

| Tipo                  | Cardinalidad         | Implementación                                                       | Nota                              |
| --------------------- | -------------------- | -------------------------------------------------------------------- | --------------------------------- |
| **1:1**               | 1 ↔ 1                | **FK** con **UNIQUE** (o **FK = PK** en hija)                        | Separar módulos/seguridad         |
| **1:N**               | 1 ↔ ∞                | **FK** no única en la tabla “N”                                      | Más común                         |
| **N:M**               | ∞ ↔ ∞                | **Tabla puente** (A_B) con **2 FKs** y **PK compuesta** (A_id, B_id) | Atributos de la relación van aquí |
| **Jerárquica (self)** | ∞ ↔ ∞ en misma tabla | **FK** a sí misma (`parent_id`)                                      | Árbol/categorías                  |

**Participación (opcional/obligatoria)**

* **Opcional**: FK **nullable** (`NULL` permitido)
* **Obligatoria**: FK **NOT NULL**

**Identificativa vs no identificativa**

* **Identificativa**: PK de hija **incluye** la FK (parte de la clave)
* **No identificativa**: PK de hija **no** incluye la FK

**Cascadas típicas**

| Opción               | Efecto                      |
| -------------------- | --------------------------- |
| `ON DELETE CASCADE`  | borra hijos al borrar padre |
| `ON DELETE SET NULL` | pone FK a `NULL`            |
| `NO ACTION/RESTRICT` | impide borrar si hay hijos  |

**Patrones**

```sql
-- N:M con tabla puente
CREATE TABLE A_B (
  a_id NUMBER,
  b_id NUMBER,
  CONSTRAINT pk_ab PRIMARY KEY (a_id, b_id),
  CONSTRAINT fk_ab_a FOREIGN KEY (a_id) REFERENCES A(id),
  CONSTRAINT fk_ab_b FOREIGN KEY (b_id) REFERENCES B(id)
);
```

### 🔀 CROSS JOIN vs JOIN (rápido)

| Caso                           | Sintaxis                                | Condición de unión            | Resultado                           |
| ------------------------------ | --------------------------------------- | ----------------------------- | ----------------------------------- |
| **CROSS JOIN / sin condición** | `FROM A CROSS JOIN B` **o** `FROM A, B` | ninguna (`WHERE` solo filtra) | **producto cartesiano** = |A| × |B| |
| **INNER JOIN (ANSI)**          | `FROM A JOIN B ON A.id = B.id`          | `ON`/`USING` **obligatoria**  | solo coincidencias                  |
| **Coma + `WHERE` (SQL-89)**    | `FROM A, B WHERE A.id = B.id`           | condición en `WHERE`          | = `INNER JOIN`                      |

**Notas**

* `JOIN` **sin `ON`** (ANSI) → **error**. Si usas coma **sin `WHERE`** → **CROSS JOIN** accidental (**cartesianazo**).
* Úsalo a propósito (combinaciones, calendario); si no, **pon siempre la condición**.

```sql
-- Si A tiene 3 filas y B 2 → CROSS JOIN devuelve 6 filas
SELECT * FROM A CROSS JOIN B;
```

### 🧪 Ejercicio hoy — 1:1 `EMPLEADO` ↔ `CREDENCIAL_ACCESO`

**Oracle SQL comentado (por qué de cada cosa)**

```sql
-- MODELO 1:1 ESTRICTO (PK COMPARTIDA)
CREATE TABLE empleado (
  employee_id NUMBER CONSTRAINT pk_empleado PRIMARY KEY, -- PK: identifica único empleado
  full_name   VARCHAR2(100) NOT NULL,                    -- NOT NULL: nombre requerido
  hired_on    DATE                                         -- fecha de alta (opcional)
);

CREATE TABLE credencial_acceso (
  employee_id NUMBER CONSTRAINT pk_credencial PRIMARY KEY, -- PK = FK: garantiza máx 1 credencial por empleado (1:1)
  rfid_code   VARCHAR2(32) CONSTRAINT uq_credencial_rfid UNIQUE, -- UNIQUE: la tarjeta no se comparte (valor irrepetible)
  issued_on   DATE,
  CONSTRAINT fk_credencial_empleado 
    FOREIGN KEY (employee_id) 
    REFERENCES empleado(employee_id)
    ON DELETE CASCADE -- al borrar empleado, se borra su credencial → consistencia (ACID: Consistency)
);
```

```sql
-- CARGA DE DATOS
-- 3 empleados (uno quedará SIN credencial para probar casos LEFT/anti-join)
INSERT INTO empleado (employee_id, full_name, hired_on) VALUES (1,'Ana López',   DATE '2023-03-01');
INSERT INTO empleado (employee_id, full_name, hired_on) VALUES (2,'Bruno Martín', DATE '2024-06-15');
INSERT INTO empleado (employee_id, full_name, hired_on) VALUES (3,'Carla Nieves', DATE '2025-01-10');

-- 2 credenciales (empleado 3 sin credencial)
INSERT INTO credencial_acceso (employee_id, rfid_code, issued_on) VALUES (1,'RFID-0001', DATE '2025-10-23');
INSERT INTO credencial_acceso (employee_id, rfid_code, issued_on) VALUES (2,'RFID-0002', DATE '2025-10-23');
```

```sql
-- CONSULTAS
-- a) Solo empleados CON credencial → INNER JOIN devuelve solo coincidencias
SELECT e.employee_id, e.full_name, c.rfid_code
FROM empleado e
JOIN credencial_acceso c ON c.employee_id = e.employee_id;

-- b) TODOS los empleados y su credencial si existe → LEFT JOIN conserva al padre
SELECT e.employee_id, e.full_name, c.rfid_code
FROM empleado e
LEFT JOIN credencial_acceso c ON c.employee_id = e.employee_id
ORDER BY e.employee_id;

-- c) Empleados SIN credencial → anti-join (LEFT JOIN + IS NULL en la FK de la hija)
SELECT e.employee_id, e.full_name
FROM empleado e
LEFT JOIN credencial_acceso c ON c.employee_id = e.employee_id
WHERE c.employee_id IS NULL;
```

```sql
-- d) PRUEBA DE UNICIDAD (RFID duplicado) → debe fallar por UNIQUE
INSERT INTO credencial_acceso (employee_id, rfid_code, issued_on)
VALUES (3,'RFID-0002', DATE '2025-10-23');
-- Esperado: ORA-00001: unique constraint (UQ_CREDENCIAL_RFID) violated

-- Extra 1: 2ª credencial para el MISMO empleado → falla por PK compartida
INSERT INTO credencial_acceso (employee_id, rfid_code, issued_on)
VALUES (1,'RFID-0003', DATE '2025-10-23');
-- Esperado: ORA-00001: unique constraint (PK_CREDENCIAL) violated

-- Extra 2: Borrado en cascada (coherencia automática)
DELETE FROM empleado WHERE employee_id = 2; -- borra también su credencial por ON DELETE CASCADE
```

```sql
-- RESET RÁPIDO (opcional, para rehacer el ejercicio)
DROP TABLE credencial_acceso CASCADE CONSTRAINTS;
DROP TABLE empleado CASCADE CONSTRAINTS;
```

### 🧹 ON DELETE CASCADE — por qué y dónde

| Pregunta                         | Resumen                                                                                                                                          |
| -------------------------------- | ------------------------------------------------------------------------------------------------------------------------------------------------ |
| ¿Qué es?                         | Acción de **FK** que define qué pasa al borrar el **padre**.                                                                                     |
| ¿Dónde va?                       | En la **FK de la tabla hija** (no en la PK del padre): `FOREIGN KEY (...) REFERENCES padre(pk) ON DELETE CASCADE`.                               |
| ¿Por qué aquí?                   | Cada **FK** decide su política (distintas hijas pueden elegir **CASCADE**, **SET NULL** o bloquear). La **PK** del padre **no** define acciones. |
| ¿Para qué** en este ejercicio**? | Si se borra un `EMPLEADO`, su `CREDENCIAL_ACCESO` se borra sola → sin huérfanos (ACID **Consistency**).                                          |
| ¿Sin CASCADE?                    | El `DELETE` del padre falla con **ORA‑02292** (child record found).                                                                              |
| Alternativas                     | `ON DELETE SET NULL` (opcionalidad) · `NO ACTION/RESTRICT` (bloquea). *(Oracle admite CASCADE/SET NULL; no `ON UPDATE CASCADE`)*                 |
| Rendimiento                      | **Indexa la FK** para borrados del padre más rápidos.                                                                                            |

```sql
-- Correcto (en la FK de la hija):
ALTER TABLE credencial_acceso ADD CONSTRAINT fk_credencial_empleado
  FOREIGN KEY (employee_id)
  REFERENCES empleado(employee_id)
  ON DELETE CASCADE;

-- Incorrecto (intento en la PK del padre):
-- CREATE TABLE empleado (
--   employee_id NUMBER PRIMARY KEY ON DELETE CASCADE -- ❌ No válido en Oracle
-- );
```

### 🤔 “Quiero eliminar el RFID, ¿por qué se pone en la FK?”

* `ON DELETE CASCADE` borra **la fila hija completa**, no una columna.
* `RFID_CODE` vive en `credencial_acceso` → al borrar **esa fila**, el **RFID desaparece**.
* La acción se define en la **FK** porque es la **relación** la que reacciona al `DELETE` del **padre**.
* Con **PK compartida** (`employee_id` = PK y FK) **no puedes `SET NULL`** (una PK no admite NULL) ⇒ **CASCADE** es lo correcto para “borras empleado → desaparece su credencial”.

**Alternativa si quisieras conservar la credencial “sin dueño”**

```sql
-- Modelo distinto (no PK compartida): permite ON DELETE SET NULL
CREATE TABLE credencial (
  id NUMBER PRIMARY KEY,
  rfid_code VARCHAR2(32) UNIQUE NOT NULL
);
CREATE TABLE asignacion_credencial (
  credencial_id NUMBER PRIMARY KEY REFERENCES credencial(id),
  employee_id   NUMBER UNIQUE NULL REFERENCES empleado(employee_id) ON DELETE SET NULL
);
```

### ✍️ Ejemplos de hoy

```sql
-- pega aquí las consultas de clase
```

### 🔑 PK — reglas rápidas

* **Siempre única** y **`NOT NULL`** (implícito). Oracle crea/usa **índice único**.
* **Solo 1 PK por tabla.** Puede ser **compuesta** `(c1, c2, ...)`.
* **UNIQUE ≠ PK**: `UNIQUE` permite **`NULL`** (varios `NULL`); la **PK no**. Puedes tener **varias UNIQUE**.
* **FK** puede apuntar a **PK** o a una **UNIQUE** (mismo conjunto/orden de columnas).
* **Consejo**: nombra las constraints (`pk_tabla`, `uq_tabla_col`, `fk_hija_padre`) para depurar mejor.

```sql
-- Ejemplo PK compuesta (y solo hay 1 PK en la tabla)
CREATE TABLE ejemplo (
  a NUMBER,
  b NUMBER,
  CONSTRAINT pk_ejemplo PRIMARY KEY (a,b)
);
-- UNIQUE adicional (posibles varias UNIQUE)
ALTER TABLE ejemplo ADD CONSTRAINT uq_ejemplo_b UNIQUE (b);
```
