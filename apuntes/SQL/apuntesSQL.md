

# Bases de Datos — Resumen



> Guía breve y corregida sobre bases de datos (énfasis en **SQL**/Oracle)---



## 🧩 Qué es una BBDD relacional

Las bases de datos relacionales se usan cuando se necesita mantener integridad y relaciones lógicas entre conjuntos de datos, como en aplicaciones empresariales, financieras o de gestión donde los vínculos entre tablas (por ejemplo, clientes y pedidos) son esenciales. Son ideales cuando se requiere consistencia, transacciones seguras y un modelo estructurado que facilite consultas complejas.



Una **base de datos relacional** almacena información en **tablas** (filas y columnas) y se manipula mediante ****SQL** (Structured Query Language)**.



- Cada **tabla** representa una entidad (por ejemplo `Clientes`, `Productos`).

- Las **filas** son registros individuales.

- Las **columnas** son atributos o campos.

- Las tablas pueden relacionarse mediante **claves primarias** y **foráneas**.



**Ejemplo de tabla:**



| id | nombre | apellidos | dni | fecha_nac |

|----|----------|------------|------|------------|

| 0 | Nombre1 | Apellidos1 | DNI1 | 1998-04-22 |

| 1 | Nombre2 | Apellidos2 | DNI2 | 1989-09-23 |



---



## 🗄️ SGBD más usados



Los sistemas de gestión de bases de datos más comunes son relacionales:



- **Oracle Database**

- **PostgreSQL**

- **MySQL**

- **Microsoft **SQL**** Server****

- **SQLite**

- **MariaDB**

- ****IBM** Db2**

- ****SAP** **HANA****



> 🔗 Ranking actualizado: [db-engines.com/en/ranking](https://db-engines.com/en/ranking)

> 💡 *Tip profesional:* **Snowflake** es un sistema híbrido (relacional y analítico en la nube).



---



## ⚙️ Ventajas de las BBDD relacionales



- **Estructura clara:** datos organizados en tablas.

- **Integridad:** uso de claves y restricciones.

- **Consultas complejas:** mediante **SQL**.

- **Seguridad y escalabilidad:** ideal para entornos empresariales.



---



## 🔑 Claves y restricciones



### Primary Key (clave primaria)



- Identifica de forma **única** cada registro.

- No puede ser `**NULL**`.



### Foreign Key (clave foránea)



- **Referencia** una PK o columna `**UNIQUE**` de otra tabla.

- Mantiene la **integridad referencial** (evita valores huérfanos).



**Ejemplo de relación `Contratos` ↔ `Clientes`:**



| contrato_id | cliente_id | fecha_inicio | fecha_fin | tipo |

|--------------|-------------|---------------|-------------|-------------|

| 101 | 0 | 2023-01-01 | 2023-12-31 | Renovable |

| 103 | 0 | 2024-05-01 | 2025-04-30 | Temporal |

| 102 | 1 | 2023-02-15 | 2024-02-14 | Permanente |



---



## ⚖️ Propiedades ACID



Las transacciones en bases de datos deben cumplir las propiedades **ACID**:



| Propiedad | Descripción | Ejemplo |

|------------|--------------|---------|

| **Atomicidad** | Todo o nada: una transacción se completa completamente o se cancela. | Si falla un paso en un retiro bancario, se revierte todo. |

| **Consistencia** | Mantiene la validez de las reglas y restricciones. | No permite duplicar un número de cuenta único. |

| **Aislamiento** | Las transacciones concurrentes no interfieren entre sí. | Dos usuarios no pueden gastar el mismo saldo simultáneamente. |

| **Durabilidad** | Una vez confirmada (COMMIT), la transacción se guarda permanentemente. | Si el sistema cae tras un `COMMIT`, los cambios persisten. |



---



## 🧮 Bases de datos NoSQL

Esta sección se ha movido justo después de la descripción de las bases de datos relacionales para facilitar la comparación entre ambos modelos y resaltar sus diferencias clave.



- Sin esquema fijo (*schema-less*).

- Escalan **horizontalmente** (varios servidores).

- Flexibles para datos no estructurados.



**Modelos comunes:**



| Tipo | Descripción | Ejemplos |

|------|--------------|----------|

| Documentales | Guardan documentos JSON/BSON | MongoDB, CouchDB |

| Clave-valor | Pares simples clave-valor | Redis, DynamoDB |

| Columnas | Datos por columnas | Cassandra, HBase |

| Grafos | Nodos y relaciones | Neo4j, Amazon Neptune |



---



## ☁️ Otros tipos de bases de datos



| Tipo | Uso principal | Ejemplos |

|------|----------------|----------|

| **En memoria** | Acceso ultra rápido | Redis, Memcached |

| **Orientadas a objetos** | Modelado OO | ObjectDB, db4o |

| **Distribuidas** | Escalabilidad y disponibilidad | Google Spanner, Cassandra |

| **En la nube** | Servicios gestionados | Amazon RDS, Cloud SQL, Azure SQL |



---



## 🧠 Resumen de comandos SQL

En esta sección se resumen los principales grupos de comandos SQL con una breve explicación para evitar repeticiones y mejorar la claridad:





| Tipo | Comandos principales |

|------|-----------------------|

| **DDL** | `CREATE`, `ALTER`, `DROP`, `TRUNCATE` |

| **DML** | `SELECT`, `INSERT`, `UPDATE`, `DELETE` |

| **DCL** | `GRANT`, `REVOKE` |

| **TCL** | `COMMIT`, `ROLLBACK`, `SAVEPOINT`, `SET TRANSACTION` |



---

# BBDD

## Foreign Key

Una Foreign Key es una restricción que se usa para asegurar la integridad referencial entre dos tablas. Esto significa que un valor en una columna (o conjunto de columnas) debe existir previamente en otra tabla.

### Conceptos clave

- La Foreign Key en una tabla hija apunta a la Primary Key o a una columna única en la tabla padre.
    
- Evita que se inserten valores en la tabla hija que no existan en la tabla padre.
    
- También controla el comportamiento al borrar o actualizar registros (acciones ON DELETE y `ON UPDATE`).

### Sintaxis para crear una Foreign Key

#### Cuando creas una tabla:
CREATE TABLE hija (
    id NUMBER PRIMARY KEY,
    padre_id NUMBER,
    CONSTRAINT fk_padre FOREIGN KEY (padre_id) REFERENCES padre(id)
);

#### Añadir una Foreign Key a una tabla existente
ALTER TABLE hija

> Luis:
ADD CONSTRAINT fk_padre FOREIGN KEY (padre_id) REFERENCES padre(id);

####  Opciones comunes en Foreign Key

- ON DELETE CASCADE: Si borras un registro en la tabla padre, los registros relacionados en la tabla hija también se borran automáticamente.
ALTER TABLE hija
ADD CONSTRAINT fk_padre FOREIGN KEY (padre_id) REFERENCES padre(id)
ON DELETE CASCADE;

ON DELETE SET NULL: Si borras un registro en la tabla padre, la columna padre_id en la tabla hija se pone en NULL.

### Ejemplo completo
CREATE TABLE padre (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR2(50)
);

CREATE TABLE hija (
    id NUMBER PRIMARY KEY,
    padre_id NUMBER,
    CONSTRAINT fk_padre FOREIGN KEY (padre_id) REFERENCES padre(id) ON DELETE CASCADE
);


## Relaciones en Bases de Datos: 1:1 y 1:M

### 1:1 (Uno a Uno)

- Cada registro en la tabla A está relacionado con exactamente un registro en la tabla B, y viceversa.
    
- Ejemplo: Una persona y su pasaporte (una persona tiene un pasaporte, un pasaporte pertenece a una persona).

#### Cómo implementarlo

- Se usa una clave foránea única en alguna de las tablas.
    
- Por ejemplo, en Oracle:
CREATE TABLE persona (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR2(50)
);

CREATE TABLE pasaporte (
    id NUMBER PRIMARY KEY,
    persona_id NUMBER UNIQUE,  -- clave única para asegurar 1:1
    numero VARCHAR2(20),
    CONSTRAINT fk_persona FOREIGN KEY (persona_id) REFERENCES persona(id)
);

UNIQUE en persona_id garantiza que un pasaporte solo puede asociarse a una persona, y cada persona a un solo pasaporte.

### 1:M (Uno a Muchos)

- Un registro en la tabla A puede estar relacionado con varios registros en la tabla B, pero cada registro en B está relacionado con uno solo en A.
    
- Ejemplo: Un autor y sus libros (un autor puede tener muchos libros, pero cada libro tiene un solo autor).

#### Cómo implementarlo

- La tabla “muchos” contiene la clave foránea que apunta a la tabla “uno”.
    
- Por ejemplo:
CREATE TABLE autor (
    id NUMBER PRIMARY KEY,
    nombre VARCHAR2(50)
);

CREATE TABLE libro (
    id NUMBER PRIMARY KEY,
    autor_id NUMBER,
    titulo VARCHAR2(100),
    CONSTRAINT fk_autor FOREIGN KEY (autor_id) REFERENCES autor(id)
);

---

### Resumen

|Relación|Descripción|Implementación Oracle|
|---|---|---|
|1:1|Un registro relacionado a otro único|Foreign Key con restricción UNIQUE|
|1:M|Un registro relacionado a muchos|Foreign Key en tabla “muchos”|



## 🧾 Auditoría en Oracle



### 1. Campos de auditoría en tablas

Columnas típicas:

- `created_at`, `created_by`

- `updated_at`, `updated_by`

- `deleted_at`, `deleted_by`, `is_deleted`



✅ Ventaja: simple

❌ Desventaja: solo guarda el último cambio.



---



### 2. Tablas histórico (log)

Se guarda una copia de cada cambio con metadatos:

- `AUDIT_ACTION`, `AUDIT_TIMESTAMP`, `AUDIT_USER`, etc.

✅ Guarda todo el historial

❌ Más espacio y mantenimiento.



---



### 3. Triggers de auditoría



Ejemplo de actualización automática:

```sql

**CREATE** OR **REPLACE** **TRIGGER** trg_before_update_cliente

**BEFORE** **UPDATE** ON clientes

# FOR EACH ROW

**BEGIN**

:**NEW**.updated_at := **SYSDATE**;

:**NEW**.updated_by := SYS_CONTEXT('**USERENV**', 'SESSION_USER');

**END**;

/

````



Ejemplo de inserción en tabla histórica:



```sql

**CREATE** OR **REPLACE** **TRIGGER** trg_audit_cliente

**AFTER** **INSERT** OR **UPDATE** OR **DELETE** ON clientes

# FOR EACH ROW

**BEGIN**

**INSERT** **INTO** clientes_aud (

id, nombre, email, audit_action, audit_timestamp, audit_user

)

**VALUES** (

**COALESCE**(:**OLD**.id, :**NEW**.id),

**COALESCE**(:**OLD**.nombre, :**NEW**.nombre),

**COALESCE**(:**OLD**.email, :**NEW**.email),

**CASE**

**WHEN** **INSERTING** **THEN** '**INSERT**'

**WHEN** **UPDATING** **THEN** '**UPDATE**'

**WHEN** **DELETING** **THEN** '**DELETE**'

**END**,

**SYSDATE**,

SYS_CONTEXT('**USERENV**', 'SESSION_USER')

);

**END**;

/

```



---



## 🔍 Orden lógico de ejecución en SELECT



### Orden de escritura



```sql

**SELECT** columnas

**FROM** tabla

**WHERE** condiciones

**GROUP** BY columnas

**HAVING** condiciones_de_grupo

**ORDER** BY columnas;

```



### Orden de ejecución lógico



| Paso | Cláusula | Descripción |

| ---- | ---------- | ---------------------- |

| 1 | `FROM` | Tablas origen |

| 2 | `WHERE` | Filtros de filas |

| 3 | `GROUP BY` | Agrupación |

| 4 | `HAVING` | Filtros de grupos |

| 5 | `SELECT` | Columnas o expresiones |

| 6 | `ORDER BY` | Orden final |



Ejemplo:



```sql

**SELECT** departamento_id, **COUNT**(*) AS total_empleados

**FROM** empleados

**WHERE** salario > **2000**

**GROUP** BY departamento_id

**HAVING** **COUNT**(*) > 5

**ORDER** BY total_empleados **DESC**;

```



---



## 🧰 Funciones y operadores útiles



### `ROUND`



```sql

**SELECT** **ROUND**(**123**.**4567**, 2) **FROM** dual; -- **123**.46

**SELECT** **ROUND**(**123**.5) **FROM** dual; -- **124**

**SELECT** **ROUND**(**DATE** '**2023**-09-15', '**MONTH**') **FROM** dual; -- 01-**OCT**-**2023**

```



### `COUNT`



```sql

**SELECT** **COUNT**(*) **FROM** empleados;

**SELECT** **COUNT**(salario) **FROM** empleados;

**SELECT** **COUNT**(**DISTINCT** departamento_id) **FROM** empleados;

```



### `ALIAS`



```sql

**SELECT** nombre AS empleado_nombre **FROM** empleados;

**SELECT** e.nombre, d.nombre AS departamento

**FROM** empleados e **JOIN** departamentos d ON e.departamento_id = d.id;

```



### `BETWEEN`



```sql

**SELECT** * **FROM** empleados **WHERE** salario **BETWEEN** **3000** **AND** **5000**;

**SELECT** * **FROM** pedidos **WHERE** fecha_pedido **BETWEEN** **DATE** '**2023**-01-01' **AND** **DATE** '**2023**-12-31';

```



### `LIKE`



```sql

**SELECT** * **FROM** empleados **WHERE** nombre **LIKE** 'J%'; -- empieza por J

**SELECT** * **FROM** empleados **WHERE** nombre **LIKE** '%ez'; -- termina en ez

**SELECT** * **FROM** empleados **WHERE** nombre **LIKE** '_ara'; -- un carácter + *ara*

**SELECT** * **FROM** empleados **WHERE** **UPPER**(nombre) **LIKE** '**JUAN**%'; -- insensible a mayúsculas

```



### `NVL`



```sql

**SELECT** nombre, **NVL**(comision, 0) AS comision_final **FROM** empleados;

**SELECT** nombre, salario + **NVL**(bono, 0) AS ingreso_total **FROM** empleados;

```



---



## 📚 Recursos recomendados

A continuación se listan recursos útiles con una breve descripción de su propósito y tipo de usuario o proyecto recomendado.



- 📈 [db-engines.com/en/ranking](https://db-engines.com/en/ranking) — Ranking actualizado de bases de datos.

- ⚙️ [Debezium](https://debezium.io) — Plataforma *open source* para captura de cambios (**CDC**) desde **BBDD** hacia Kafka.



---

# Opciones gratuitas para aprender Oracle SQL

## 1. [Oracle Dev Gym](https://devgym.oracle.com) 🎯  
Gamificado (quizzes, workouts y clases). 100% gratis.  
Incluye el bootcamp **“Databases for Developers: Foundations”**.  
✅ Recomendado

---

## 2. [Oracle Live SQL](https://livesql.oracle.com) 💻  
Entorno online de Oracle Database con tutoriales y editor en el navegador.  
Gratis (requiere cuenta de oracle.com).  
✅ Recomendado

---

## 3. [SQLZoo - pista Oracle](https://sqlzoo.net) 🧩  
Ejercicios y quizzes interactivos, gratis.  
✅ Recomendado

---

## 4. [SQL Murder Mystery](https://mystery.knightlab.com/) 🕵️  
Juego tipo “cluedo” en SQL, gratis y con repositorio abierto para practicar.  
✅ Recomendado

---

## 5. [Oracle Academy](https://academy.oracle.com) 🎓  
Gratis con membresía institucional (escuelas/universidades).  
Para autoestudio individual, suele ser más directo empezar con **Dev Gym** + **Live SQL**.  
✅ Recomendado



# Resumen en Markdown — *Bases de datos relacionales (Oracle)*

## 1) Qué cubre el PDF

* **Conceptos básicos**: tablas/filas/columnas, **PK/FK**, tipos de relaciones (1:1, 1:N, N:M), **normalización** (1NF–3NF), y consultas SQL. 
* **SQL por categorías**: **DML** (SELECT/INSERT/UPDATE/DELETE), **DDL** (CREATE/ALTER/DROP/TRUNCATE), **DCL** (GRANT/REVOKE), con ejemplos en Oracle XE.
* **Tipos de datos (Oracle)**: numéricos (`NUMBER`, `INTEGER`, `FLOAT`, `BINARY_FLOAT/DOUBLE`), caracteres (`CHAR`, `VARCHAR2`, NCHAR/NVARCHAR2), **fecha/hora** (`DATE`, `TIMESTAMP`, zonas horarias, `INTERVAL`), y **LOB/Especiales** (`CLOB/BLOB/NCLOB/BFILE`, `RAW`, `ROWID/UROWID`).
* **DDL práctico**: crear/modificar tablas, añadir/modificar/eliminar columnas, **TRUNCATE** vs **DROP**. 
* **Constraints**: `PRIMARY KEY` (incluida compuesta), `FOREIGN KEY` (+`ON DELETE CASCADE`), `NOT NULL`, `UNIQUE`, `CHECK`, `DEFAULT`, e **`IDENTITY`** (12c+) con alternativa mediante **`SEQUENCE`**.

---

## 2) Conceptos clave (muy breve)

* **Relaciones**:

  * 1:1, 1:N y N:M (esta última se modela con **tabla intermedia**). 
* **Normalización**:

  * **1NF** (sin grupos repetitivos), **2NF** (sin dependencias parciales), **3NF** (sin dependencias transitivas). 
* **Índices**: aceleran búsquedas en columnas consultadas frecuentemente (p. ej. `Nombre`). 

---

## 3) SQL por categorías (Oracle XE)

**DML** – Manipulan **datos**:

```sql
-- SELECT / INSERT / UPDATE / DELETE
SELECT * FROM Clientes;
INSERT INTO Clientes (ID_Cliente, Nombre) VALUES (1, 'Ana');
UPDATE Clientes SET Nombre = 'Ana G.' WHERE ID_Cliente = 1;
DELETE FROM Clientes WHERE ID_Cliente = 1;
```

**DDL** – Definen/modifican **estructura**:

```sql
-- CREATE / ALTER / DROP / TRUNCATE
CREATE TABLE Clientes (ID_Cliente NUMBER PRIMARY KEY, Nombre VARCHAR2(50));
ALTER TABLE Clientes ADD Telefono VARCHAR2(20);
TRUNCATE TABLE Clientes;  -- borra datos, mantiene estructura (no reversible)
DROP TABLE Clientes;      -- elimina la tabla y su estructura
```

**DCL** – **Permisos y seguridad**:

```sql
GRANT SELECT, INSERT ON Clientes TO usuario1;
REVOKE INSERT ON Clientes FROM usuario1;
```



---

## 4) Tipos de datos (selección rápida)

* **Números**: `NUMBER(p,s)`, `INTEGER`, `FLOAT(p)`, `BINARY_FLOAT/DOUBLE`. 
* **Texto**: `CHAR(n)`, `VARCHAR2(n)`, `NCHAR/NVARCHAR2`. 
* **Fecha/Hora**: `DATE`, `TIMESTAMP(n)`, `TIMESTAMP WITH TIME ZONE/LOCAL TIME ZONE`, `INTERVAL YEAR TO MONTH` / `INTERVAL DAY TO SECOND`. 
* **LOB**: `CLOB`, `BLOB`, `NCLOB`, `BFILE`; **Especiales**: `RAW(n)`, `ROWID`, `UROWID`. 

---

## 5) Constraints esenciales (con ejemplos)

* **PRIMARY KEY** (única y no nula), incluida **compuesta**:

```sql
CREATE TABLE asistencia (
  empleado_id NUMBER(5),
  fecha       DATE,
  PRIMARY KEY (empleado_id, fecha)
);
```



* **FOREIGN KEY** con **ON DELETE CASCADE**:

```sql
CREATE TABLE empleados (
  id NUMBER(5) PRIMARY KEY,
  nombre VARCHAR2(50) NOT NULL,
  departamento_id NUMBER(3),
  CONSTRAINT fk_departamento
    FOREIGN KEY (departamento_id)
    REFERENCES departamentos(id)
    ON DELETE CASCADE
);
```



* **NOT NULL / UNIQUE / CHECK / DEFAULT** (reglas de validez y valores por defecto). 

* **IDENTITY (12c+)** y alternativa con **SEQUENCE** (versiones antiguas):

```sql
-- 12c+: IDENTITY
CREATE TABLE clientes (
  id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
  nombre VARCHAR2(100) NOT NULL
);

-- Con SEQUENCE (p.ej., XE 11g)
CREATE SEQUENCE clientes_seq START WITH 1 INCREMENT BY 1;
INSERT INTO clientes (id, nombre) VALUES (clientes_seq.NEXTVAL, 'Juan Pérez');
```



---

## 6) Operaciones de mantenimiento

* **ALTER**: añadir/modificar/eliminar columnas (p. ej., ampliar precisión de `salario`). 
* **TRUNCATE vs DELETE**: `TRUNCATE` borra **todas** las filas sin registro por fila y **no es reversible**; `DELETE` sí registra y puede revertirse antes de `COMMIT`. 

---

### TL;DR

El PDF resume **fundamentos relacionales**, **SQL en Oracle por categorías**, **tipos de datos**, **DDL práctico** y **constraints** (incluyendo `IDENTITY` en 12c+) con ejemplos. Si dominas PK/FK, normalización 1–3NF, DML/DDL/DCL, tipos de datos y restricciones, tendrás cubierto lo esencial para un examen de **Oracle SQL**.



