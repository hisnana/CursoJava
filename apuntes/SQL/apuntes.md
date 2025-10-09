
## Bases de Datos

Las bases de datos que mas se utilizan son las relacionales.

Oracles, SQL server, Posgre son relacionales.

Aqui hay una lista de todos los tipos y sus ranking https://db-engines.com/en/ranking

SQL (Structured Query Language) es un lenguaje estándar para gestionar y manipular bases de datos relacionales y esto es lo que vamos a estudiar.

Nota: Quiza sea interesante profesionalmente ver Snowflake, relacional y no a la vez y en la nube. Cada vez mas usada.

Todas estas son SQL:
https://t.me/c/2813936991/332- Oracle Database    
- MySQL    
- PostgreSQL    
- Microsoft SQL Server    
- SQLite    
- MariaDB    
- IBM Db2    
- SAP HANA

En una base de datos relacional los datos se almacenan en tablas

| ID  | N1      | N2          | N3   | N4       |
| --- | ------- | ----------- | ---- | -------- |
| 0   | Nombre1 | Appellidos1 | DNI1 | 22/04/98 |
| 1   | Nombre2 | Appellidos3 | DNI2 | 23/09/89 |

# Bases de Datos Relacionales

## ¿Qué es una Base de Datos Relacional?

Una **base de datos relacional** es un sistema para almacenar y gestionar datos estructurados, donde la información se organiza en **tablas** que tienen filas y columnas. 

- Cada tabla representa una entidad o concepto (por ejemplo, `Clientes` o `Productos`).
- Las filas son registros individuales.
- Las columnas son atributos o campos de esos registros.
- Las tablas pueden relacionarse entre sí mediante claves primarias y foráneas, lo que permite mantener la integridad y coherencia de los datos.

Este modelo facilita consultas complejas mediante el lenguaje SQL (Structured Query Language).

---

## Tipos de Bases de Datos Relacionales

Existen varios sistemas de gestión de bases de datos relacionales (RDBMS), entre los más populares están:

| Sistema               | Características principales                                    |
|-----------------------|----------------------------------------------------------------|
| **MySQL**             | Muy popular, de código abierto, ampliamente usado en web       |
| **PostgreSQL**        | Open source, muy robusto, con soporte avanzado de SQL          |
| **Oracle Database**   | Comercial, muy utilizado en grandes empresas y entornos críticos|
| **Microsoft SQL Server** | Comercial, integración con productos Microsoft                |
| **SQLite**            | Ligero, embebido, ideal para aplicaciones móviles y escritorio |

---

## Ventajas de las Bases de Datos Relacionales

- **Estructura clara:** datos organizados en tablas.
- **Integridad:** uso de claves y restricciones.
- **Consultas complejas:** mediante SQL.
- **Escalabilidad y seguridad:** para grandes volúmenes de datos.

---

Cada vez que creas una tabla en una base de datos relacional, debes definir:

1. El nombre de la tabla — para identificarla.    
2. Las columnas (campos) — con un nombre para cada una.    
3. El tipo de dato de cada columna — para especificar qué tipo de información va a contener (números, texto, fechas, etc.).

Los registros son las filas de una tabla en una base de datos relacional.

Cada celda (la intersección de una fila y una columna) contiene un valor específico para ese campo dentro de ese registro.

Las restricciones en bases de datos son reglas que se aplican a las tablas para asegurar la integridad y validez de los datos.

Las bases de datos relacionales son poderosas porque permiten conectar tablas entre sí, lo que ayuda a reducir la duplicación de datos y mantener la información organizada y consistente.

Para relacionar la de clientes con contratos usamos foreign key y primary key.

| ContratoID | ClienteID | FechaInicio | FechaFin   | TipoContrato |
| ---------- | --------- | ----------- | ---------- | ------------ |
| 101        | 0         | 01/01/2023  | 31/12/2023 | Renovable    |
| 103        | 0         | 01/05/2024  | 30/04/2025 | Temporal     |
| 102        | 1         | 15/02/2023  | 14/02/2024 | Permanente   |
- ContratoID es la clave primaria de la tabla Contratos: identifica de forma única cada contrato.  
- ClienteID es la clave foránea en la tabla Contratos: referencia el ID de la tabla Clientes, estableciendo la relación entre contratos y clientes.

### Primary Key

- Unicidad:  
    Cada valor en la clave primaria debe ser único; no puede haber dos filas con el mismo valor.  
- No puede ser NULL:  
    La clave primaria siempre debe tener un valor válido; no acepta valores nulos.    
- Identifica de forma única cada registro:  
    Sirve para distinguir un registro de otro dentro de una tabla.

#### Restriccion

- PRIMARY KEY: Identifica de forma única cada registro en una tabla. No puede haber dos filas con el mismo valor en esta columna y no puede ser nula.


### Foreign Key

- Referencia a otra tabla:  
    Apunta a la Primary Key (o a una clave única) de otra tabla, estableciendo una relación entre ambas.
-  Mantiene la integridad referencial:  
    Garantiza que los valores en la columna de clave foránea existan en la tabla referenciada, evitando referencias “huérfanas”.

#### Restriccion

- FOREIGN KEY: Garantiza que un valor en una columna coincida con un valor existente en otra tabla, para mantener relaciones entre tablas.
## Principios ACID

ACID es un conjunto de propiedades que garantizan la fiabilidad y consistencia de las transacciones en bases de datos.

1. Atomicidad (Atomicity):  
    Toda la operación o conjunto de operaciones de una transacción se ejecutan por completo o no se ejecutan en absoluto. No hay estados intermedios.
   
    Ejemplo:  
    En un cajero automático, si quieres retirar dinero:
   
    - Se verifica saldo.        
    - Se debita el monto.        
    - Se registra la operación.  
        Si falla alguna de estas etapas (por ejemplo, no se puede registrar la operación), toda la transacción se cancela y no se debita dinero ni se entrega efectivo.        

2. Consistencia (Consistency):  
    La base de datos pasa de un estado válido a otro estado válido, respetando todas las reglas y restricciones (como integridad referencial, tipos de datos, claves únicas, etc.).
   
    Ejemplo:  
    Si tienes una tabla que no permite duplicados en el número de cuenta, al insertar una nueva transacción con un número repetido, la base de datos rechazará el cambio para mantener la consistencia.
   

3. Aislamiento (Isolation):

Las transacciones concurrentes se ejecutan como si fueran secuenciales, evitando que interfieran entre sí y generen resultados incorrectos.
   
    Ejemplo:  
    Dos personas intentan transferir dinero al mismo tiempo desde una misma cuenta. Gracias al aislamiento, cada transacción ve el estado de la cuenta sin interferencias, evitando que ambas retiren más dinero del disponible.
   

4. Durabilidad (Durability):  
    Una vez que una transacción es confirmada (commit), sus cambios se guardan de forma permanente, incluso si hay una falla en el sistema después.
   
    Ejemplo:  
    Después de que una transferencia bancaria se confirma, aunque el servidor se caiga o se reinicie, el cambio en el saldo permanecerá guardado sin pérdida.


Hay bases de datos que no tienen restricciones, como MongoDB.
### Ventajas de no tener restricciones

- Más flexibilidad para insertar datos sin reglas rígidas.    
- Inserciones más rápidas por menos validaciones.    
- Esquema fácil de modificar sin migraciones complejas.    
- Ideal para datos no estructurados o cambiantes.    

### Bases de datos NoSQL

- No tienen esquema fijo (schema-less), permiten datos variados.    
- Escalan fácilmente en muchos servidores (horizontalmente).    
- Modelos diversos: documentos, clave-valor, columnas, grafos.    
- Prioridad en velocidad y disponibilidad, a veces sacrificando consistencia inmediata.


# Otros Tipos de Bases de Datos

Además de las bases de datos relacionales, existen otros tipos de bases de datos que se adaptan a diferentes necesidades y estructuras de datos:

---

## 1. Bases de Datos No Relacionales (NoSQL)

Diseñadas para manejar grandes volúmenes de datos no estructurados o semi-estructurados. No usan tablas tradicionales ni SQL.

### Tipos principales:

| Tipo           | Descripción                                     | Ejemplos populares          |
|----------------|------------------------------------------------|----------------------------|
| **Documentales** | Almacenan datos en documentos tipo JSON o BSON | MongoDB, CouchDB           |
| **Clave-Valor**  | Almacenan pares clave-valor simples            | Redis, DynamoDB            |
| **Columnas**     | Almacenan datos en columnas en lugar de filas  | Apache Cassandra, HBase    |
| **Grafos**       | Modelan datos en nodos y relaciones (grafos)   | Neo4j, Amazon Neptune      |

---

## 2. Bases de Datos en Memoria

Almacenan datos principalmente en la memoria RAM para un acceso ultrarrápido.

- Ejemplo: Redis, Memcached

---

## 3. Bases de Datos Orientadas a Objetos

Almacenan datos como objetos, similar a la programación orientada a objetos.

- Ejemplo: db4o, ObjectDB

---

## 4. Bases de Datos Distribuidas

Distribuyen los datos en múltiples servidores para escalabilidad y alta disponibilidad.

- Ejemplo: Google Spanner, Apache Cassandra

---

## 5. Bases de Datos en la Nube

Servicios de bases de datos gestionados y escalables en la nube.

- Ejemplo: Amazon RDS, Google Cloud SQL, Azure SQL Database

---

## Resumen rápido

| Tipo                  | Uso principal                                  |
|-----------------------|------------------------------------------------|
| Relacional            | Datos estructurados, integridad y consultas SQL |
| NoSQL                 | Datos no estructurados, escalabilidad horizontal |
| En memoria            | Acceso ultra rápido, caching                    |
| Orientada a objetos   | Modelado orientado a objetos                     |
| Distribuida           | Alta disponibilidad y escalabilidad             |
| En la nube            | Servicios gestionados y escalables               |

---
25/09/2025

drop table "GETAFE"."USUARIOS"  


Ejercicio 1
=============================================
Crear la tabla usuarios:

Columnas:
    id: Entero, clave primaria.
    nombre: Cadena de caracteres (VARCHAR(50)), no nula.
    email: Cadena de caracteres (VARCHAR(100)), no nula y única.

Ejercicio 1 bis
=============================================
Crear la tabla usuarios:

Columnas:
    id: Entero, clave primaria y autoincrementable.
    nombre: Cadena de caracteres (VARCHAR(50)), no nula.
    email: Cadena de caracteres (VARCHAR(100)), no nula y única.
    fecha_registro: Fecha, con valor por defecto (Fecha actual)


CREATE TABLE usuarios (
    id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,--AUTOINCREMENTADO
    nombre VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    fecha_registro DATE DEFAULT CURRENT_DATE
);

Modificar la tabla para añadir una columna: Agregar la columna telefono de tipo VARCHAR(15) que permita valores nulos.

ALTER TABLE usuarios
ADD telefono VARCHAR2(15);


Modificar el tipo de datos de la columna nombre: Cambiar la definición de nombre a VARCHAR(100).

ALTER TABLE usuarios
MODIFY COLUMN nombre VARCHAR(100) NOT NULL;

Ejercicio 2
=============================================
1. Creación de la tabla productos
Requisitos:
Campos y restricciones:
    id: Entero, clave primaria y autoincrementable.
    nombre: Cadena de caracteres (VARCHAR(100)), no nulo.
    descripcion: Texto (TEXT), puede ser nulo.
    precio: Número decimal (DECIMAL(8,2)), no nulo y debe ser mayor o igual a 0.
    stock: Entero, no nulo y con valor por defecto 0.
    fecha_registro: Fecha y hora (DATETIME), con valor por defecto la fecha y hora actual.
    descuento: Número entero, que representa el porcentaje de descuento (entre 0 y 100).
    (Se usará una restricción CHECK para validar este rango.)
    estado: Cadena de caracteres (ENUM) que solo puede tener los valores 'activo' o 'inactivo', con valor por defecto 'activo'.


CREATE TABLE productos (
    id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(100) NOT NULL,
    descripcion VARCHAR2(4000) NULL,
    precio NUMBER(8,2) NOT NULL CHECK (precio >= 0),
    stock NUMBER DEFAULT 0 NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    descuento NUMBER CHECK (descuento BETWEEN 0 AND 100),
    estado VARCHAR2(10) DEFAULT 'activo' CHECK (estado IN ('activo', 'inactivo'))
);

- `id NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY`  
  Para tener un identificador único que se autoincremente automáticamente, sin necesidad de insertar manualmente.

- `nombre VARCHAR2(100) NOT NULL`  
  Nombre del producto, obligatorio y con un límite razonable de 100 caracteres.

- `descripcion CLOB`  
  Texto largo para describir el producto, ya que puede ser muy extenso (más de 4000 caracteres).

- `precio NUMBER(8,2) NOT NULL CHECK (precio >= 0)`  
  Precio con dos decimales, obligatorio y nunca negativo (por eso la restricción CHECK).

- `stock NUMBER DEFAULT 0 NOT NULL`  
  Cantidad disponible, con valor por defecto 0 y obligatorio.

- `fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP`  
  Fecha y hora de registro, con valor por defecto la fecha actual.

fecha_registro DATE DEFAULT SYSDATE; --valor por defecto fecha

- `descuento NUMBER CHECK (descuento BETWEEN 0 AND 100)`  
  Porcentaje de descuento, validado para estar entre 0 y 100.

- `estado VARCHAR2(10) DEFAULT 'activo' CHECK (estado IN ('activo', 'inactivo'))`  
  Estado del producto, solo puede ser 'activo' o 'inactivo', con 'activo' por defecto.



2. Modificación de la tabla productos
a) Añadir nuevas columnas
Agregar la columna categoria:
Tipo VARCHAR(50), no nulo.

Agregar la columna proveedor:
Tipo VARCHAR(100), que permita nulos.

ALTER TABLE productos
ADD categoria VARCHAR2(50) NOT NULL;

ALTER TABLE productos
ADD proveedor VARCHAR2(100);

b) Modificar el tipo de datos de la columna descripcion
Cambiar el tipo de datos de descripcion de TEXT a VARCHAR(500) para limitar la longitud a 500 caracteres.

ALTER TABLE productos
MODIFY descripcion VARCHAR2(500);


3. Eliminación de la tabla productos
Una vez realizados los ejercicios y pruebas, se eliminará la tabla.

DROP TABLE productos;

30/09/2025

# Resumen rápido de tipos de comandos SQL

| Tipo | Comandos principales                    |
|-------|----------------------------------------|
| DDL   | CREATE, ALTER, DROP, TRUNCATE          |
| DML   | SELECT, INSERT, UPDATE, DELETE         |
| DCL   | GRANT, REVOKE                         |
| TCL   | COMMIT, ROLLBACK, SAVEPOINT, SET TRANSACTION |

------------------------------


# BBDD
## Modos típicos de incluir datos auditables en tablas SQL Oracle

> [!CAUTION]  El profesor solo nombro que había métodos típicos para ello, pero no lo explicó.

En entornos donde es importante mantener trazabilidad (por ejemplo, saber quién creó o modificó un dato, y cuándo), se suele implementar auditoría directamente en las tablas o mediante estructuras adicionales. A continuación, se detallan las opciones más comunes.

---

### 1. Campos de auditoría en cada tabla

Consiste en agregar columnas de control directamente en las tablas principales de datos.

Campos comunes:

| Campo        | Descripción                                       |
| ------------ | ------------------------------------------------- |
| CREATED_AT | Fecha y hora de creación del registro             |
| CREATED_BY | Usuario que creó el registro                      |
| UPDATED_AT | Fecha y hora de la última modificación            |
| UPDATED_BY | Usuario que hizo la última modificación           |
| DELETED_AT | Fecha de eliminación lógica (opcional)            |
| DELETED_BY | Usuario que eliminó lógicamente (opcional)        |
| IS_DELETED | Bandera para marcar eliminación lógica (opcional) |

Ventajas:

- Fácil de implementar
    
- Accesible directamente desde las consultas normales
    
- Compatible con frameworks ORM
    

Desventajas:

- Solo registra la última acción
    
- No guarda historial detallado de cambios
    

---

### 2. Tablas de auditoría o histórico (Change Log)

Se crean tablas separadas, normalmente con el mismo diseño que la tabla original, más campos adicionales para el seguimiento de acciones.

Campos adicionales comunes:

- AUDIT_ACTION (INSERT, UPDATE, DELETE)
    
- AUDIT_TIMESTAMP
    
- AUDIT_USER
    
- AUDIT_SESSION_ID
    
- Otros metadatos relevantes
    

Implementación típica:

- Triggers en la tabla principal que insertan en la tabla de auditoría ante cada cambio
    
- También puede hacerse desde la lógica de negocio en la aplicación
    

Ventajas:

- Permite guardar todo el historial de cambios
    
- Útil para auditorías y trazabilidad detallada
    

Desventajas:

- Requiere más espacio en disco
    
- Mayor complejidad en mantenimiento y consultas
    

---

### 3. Uso de Triggers

Los triggers permiten automatizar la actualización de campos de auditoría o la inserción en tablas de auditoría.

Ejemplo 1: Actualizar campos de auditoría en la tabla original
CREATE OR REPLACE TRIGGER trg_before_update_cliente
BEFORE UPDATE ON clientes
FOR EACH ROW
BEGIN
  :NEW.updated_at := SYSDATE;
  :NEW.updated_by := SYS_CONTEXT('USERENV', 'SESSION_USER');
END;

Ejemplo 2: Insertar en tabla de auditoría
CREATE OR REPLACE TRIGGER trg_audit_cliente
AFTER INSERT OR UPDATE OR DELETE ON clientes
FOR EACH ROW
BEGIN
  INSERT INTO clientes_aud (
    id, nombre, email, audit_action, audit_timestamp, audit_user
  ) VALUES (
    COALESCE(:OLD.id, :NEW.id),
    COALESCE(:OLD.nombre, :NEW.nombre),
    COALESCE(:OLD.email, :NEW.email),
    CASE
      WHEN INSERTING THEN 'INSERT'
      WHEN UPDATING THEN 'UPDATE'
      WHEN DELETING THEN 'DELETE'
    END,
    SYSDATE,
    SYS_CONTEXT('USERENV', 'SESSION_USER')
  );
END;

---

### 4. Uso de ORA_ROWSCN y Flashback

Oracle permite utilizar funciones internas para ver versiones anteriores de registros o identificar cuándo fue la última modificación.

- ORA_ROWSCN: Pseudocolumna que indica el SCN (System Change Number) de la última modificación
    
- Flashback Query: Permite consultar cómo estaba una tabla en un momento pasado, si el UNDO lo permite
    

Ventajas:

- No requiere cambiar las tablas
    
- Útil para recuperación y análisis temporal
    

Desventajas:

- Requiere configuración avanzada
    
- Limitado por la retención de UNDO
    

---

### 5. Auditoría nativa de Oracle (DB-level)

Oracle ofrece mecanismos de auditoría avanzados a nivel de base de datos:

- Standard Auditing
    
- Fine-Grained Auditing (FGA)
    
- Oracle Audit Vault
    

Ventajas:

- No requiere modificar tablas de negocio


- Cumple normativas exigentes (por ejemplo, seguridad financiera o sanitaria)
    

Desventajas:

- Puede requerir licencias adicionales
    
- Configuración y monitoreo más complejos
    

---

### Recomendación práctica habitual

En la mayoría de proyectos empresariales se recomienda:

1. Agregar campos de auditoría (`created_at`, updated_at, etc.) en cada tabla
    
2. Si se requiere historial completo, usar triggers y una tabla de auditoría asociada
    
3. Para entornos con requerimientos regulatorios altos, considerar Flashback o herramientas de auditoría de Oracle
    

---

## Orden de cláusulas en una consulta SELECT en Oracle SQL

---

### 1. Orden de escritura (sintaxis SQL)
SELECT columnas
FROM tabla
WHERE condiciones
GROUP BY columnas
HAVING condiciones_de_grupo
ORDER BY columnas;

---

### 2. Orden lógico de ejecución

|Paso|Cláusula|Descripción|
|---|---|---|
|1|`FROM`|Se obtienen las tablas o vistas base|
|2|`WHERE`|Se filtran las filas según condiciones|
|3|`GROUP BY`|Se agrupan las filas filtradas|
|4|`HAVING`|Se filtran grupos según condiciones de grupo|
|5|`SELECT`|Se proyectan columnas y funciones (agregación)|
|6|`ORDER BY`|Se ordenan las filas resultantes|

---

### 3. Ejemplo completo
SELECT departamento_id, COUNT(*) AS total_empleados
FROM empleados
WHERE salario > 2000
GROUP BY departamento_id
HAVING COUNT(*) > 5
ORDER BY total_empleados DESC;

- Primero se filtran empleados con salario > 2000 (`WHERE`).
    
- Luego se agrupan por departamento (`GROUP BY`).
    
- Después se filtran departamentos con más de 5 empleados (`HAVING`).
    
- Se selecciona el departamento y el conteo (`SELECT`).
    
- Finalmente, se ordenan los resultados por cantidad descendente (`ORDER BY`).

## ROUND

Se utiliza para redondear números o fechas. Aquí tienes la sintaxis y ejemplos claros, todos envueltos con el formato que necesitas para Obsidian:

---

### 1. Redondeo de Números

### Sintaxis:
ROUND(número [, decimales])

- número: valor numérico que deseas redondear.
    
- decimales: número de decimales a los que quieres redondear (opcional). Si se omite, redondea al entero más cercano.
    

### Ejemplos:
-- Redondea a 2 decimales
SELECT ROUND(123.4567, 2) AS resultado FROM dual;
-- Resultado: 123.46

-- Redondea a 0 decimales (entero más cercano)
SELECT ROUND(123.5) AS resultado FROM dual;
-- Resultado: 124

-- Redondea a -1 (decena más cercana)
SELECT ROUND(123.4567, -1) AS resultado FROM dual;
-- Resultado: 120


---

### 2. Redondeo de Fechas

Cuando se usa con fechas, ROUND redondea al período más cercano según la unidad indicada.

### Sintaxis:
ROUND(fecha, 'unidad')

- fecha: valor de tipo DATE
    
- unidad: puede ser 'YEAR', 'MONTH', 'DAY', etc.
    

#### Ejemplos:
-- Redondea al primer día del mes más cercano
SELECT ROUND(TO_DATE('15-SEP-2023', 'DD-MON-YYYY'), 'MONTH') AS resultado FROM dual;
-- Resultado: 01-OCT-2023

-- Redondea al domingo o lunes más cercano según NLS_TERRITORY
SELECT ROUND(TO_DATE('04-OCT-2023', 'DD-MON-YYYY'), 'W') AS resultado FROM dual;


---

## Debezium

> [!CAUTION]  El profesor solo lo nombró, pero no lo explicó.
### ¿Qué es Debezium?

Debezium es una plataforma open-source que escucha los logs de transacciones de bases de datos (como MySQL, PostgreSQL, SQL Server, Oracle, MongoDB) y transmite esos cambios a Kafka topics, permitiendo construir aplicaciones _event-driven_ basadas en los cambios en las bases de datos.

---

### Arquitectura básica

[Base de datos] -> [Debezium Connector] -> [Kafka Topic] -> [Consumer]

1. La base de datos produce cambios (INSERT, UPDATE, DELETE).
    
2. Debezium lee el log de transacciones (binlog, WAL, etc.).
    
3. Esos cambios se publican como eventos en Kafka.
    
4. Tus servicios pueden consumir esos eventos desde Kafka.
    

---

### Requisitos generales

- Apache Kafka en ejecución
    
- Apache Kafka Connect (puede usar el de Confluent o standalone)
    
- Debezium Connector (MySQL, PostgreSQL, SQL Server, Oracle, MongoDB, etc.)
    
- Base de datos configurada para permitir CDC (según el tipo)


---

## COUNT 

### 1.Contar todas las filas (incluye nulos)
SELECT COUNT(*) FROM empleados;

- Cuenta todas las filas de la tabla, incluyendo aquellas con valores nulos.
    
- Útil para saber el total de registros.
    

---

### 2. Contar valores no nulos de una columna
SELECT COUNT(salario) FROM empleados;

- Cuenta solo las filas donde salario no es nulo.
    
- Útil cuando quieres contar solo datos válidos.
    

---

### 3. Contar valores distintos (únicos)
SELECT COUNT(DISTINCT departamento_id) FROM empleados;

- Devuelve el número de departamentos únicos entre los empleados.
    

---

### 4. Usar COUNT con GROUP BY
SELECT departamento_id, COUNT(*) AS total_empleados
FROM empleados
GROUP BY departamento_id;

- Cuenta empleados por departamento.
    
- Agrupa los resultados según departamento_id.
    

---

### 5. Combinar con condiciones (`WHERE`)
SELECT COUNT(*) FROM empleados
WHERE salario > 3000;

- Cuenta solo los empleados con salario mayor a 3000.
    

---

### 6. Ejemplo completo
SELECT
  COUNT(*) AS total,
  COUNT(salario) AS con_salario,
  COUNT(DISTINCT departamento_id) AS departamentos_unicos
FROM empleados;


---

## Alias en Oracle SQL

### 1. Alias para columnas
SELECT nombre AS empleado_nombre
FROM empleados;

- empleado_nombre es un alias para la columna nombre.
    
- El uso de AS es opcional en Oracle:
    
SELECT nombre empleado_nombre FROM empleados;

Ambos funcionan igual.

---

### 2. Alias con espacios o caracteres especiales

Si el alias contiene espacios, se deben usar comillas dobles:
SELECT nombre empleado_nombre FROM empleados;

---

### 3. Alias para expresiones o funciones
SELECT salario * 12 AS salario_anual
FROM empleados;

- Asigna un nombre al resultado de una expresión.
    

---

### 4. Alias para funciones de agregación
SELECT COUNT(*) AS total_empleados
FROM empleados;

- Facilita la lectura de resultados agregados.
    

---

### 5. Alias para tablas
SELECT e.nombre, d.nombre AS departamento
FROM empleados e
JOIN departamentos d ON e.departamento_id = d.id;

- e y d son alias para las tablas empleados y departamentos.
    
- Esto permite escribir consultas más concisas.
    

---

### 6. Alias dentro de subconsultas
SELECT *
FROM (
  SELECT nombre, salario * 12 AS salario_anual
  FROM empleados
) emp_con_sueldo;

- Se da un alias (`emp_con_sueldo`) a la subconsulta para poder referenciarla.
    

---

### Notas importantes

- El alias no cambia el nombre real en la base de datos.
    
- Es útil en reportes, vistas, y para mejorar la claridad del código SQL.
    
- Aunque AS es opcional, se recomienda usarlo para mayor claridad.
    

---

## Uso de BETWEEN en Oracle SQL

BETWEEN se utiliza para comparar si un valor está dentro de un rango, incluyendo los valores extremos.

---

### Sintaxis
valor BETWEEN mínimo AND máximo

- Equivale a:
    
       valor >= mínimo AND valor <= máximo
        

---

### 1. Ejemplo con números
SELECT * FROM empleados
WHERE salario BETWEEN 3000 AND 5000;

- Devuelve los empleados cuyo salario está entre 3000 y 5000, inclusive.
    

---

### 2. Ejemplo con fechas
SELECT * FROM pedidos
WHERE fecha_pedido BETWEEN TO_DATE('01-01-2023', 'DD-MM-YYYY')
                      AND TO_DATE('31-12-2023', 'DD-MM-YYYY');

- Devuelve pedidos realizados en el año 2023.
    
- Las fechas límites sí se incluyen.
    

---

### 3. Ejemplo con texto (valores alfabéticos)
SELECT * FROM productos
WHERE nombre BETWEEN 'A' AND 'M';

- Devuelve productos cuyos nombres comienzan entre la letra A y la M.
    
- Oracle hace la comparación alfabética (según collation).
    

---

### 4. Uso con NOT BETWEEN
SELECT * FROM empleados
WHERE salario NOT BETWEEN 3000 AND 5000;

- Devuelve empleados cuyo salario no esté dentro del rango especificado.
    

---

### 5. Equivalencia lógica

Este ejemplo:
SELECT * FROM empleados
WHERE salario BETWEEN 3000 AND 5000;

Es equivalente a:
SELECT * FROM empleados
WHERE salario >= 3000 AND salario <= 5000;

---

### Notas

- BETWEEN incluye los valores límite (es decir, es **inclusivo**).
    
- Se puede usar con números, fechas y texto.

- Evita usar BETWEEN si los valores mínimo y máximo están invertidos, ya que puede no devolver resultados.

---

## Uso de LIKE en Oracle SQL

El operador LIKE se utiliza para realizar búsquedas por patrones en columnas de texto. Es útil cuando no se conoce el valor exacto, pero sí parte de él.

---

### Sintaxis básica
SELECT * FROM empleados
WHERE nombre LIKE 'J%';

- Devuelve todos los empleados cuyo nombre comienza por la letra J.
    

---

### Comodines disponibles

|Comodín|Significado|Ejemplo|
|---|---|---|
|`%`|Cualquier cantidad de caracteres (incluyendo 0)|`'A%'` → comienza con A|
|`_`|Exactamente un solo carácter|`'A_'` → A seguido de un carácter|

---

### 1. Usar % (cualquier número de caracteres)
SELECT * FROM empleados
WHERE nombre LIKE '%ez';

- Encuentra nombres que terminan en ez, como “Pérez”, “Gómez”.
    

---

### 2. Usar _ (un solo carácter)
SELECT * FROM empleados
WHERE nombre LIKE '_ara';

- Devuelve registros como “Sara”, “Lara”, “Kara”, etc.
    
- La primera letra puede ser cualquiera, seguido de “ara”.
    

---

### 3. Combinación de % y _
SELECT * FROM empleados
WHERE nombre LIKE '_a%';

- Encuentra nombres donde la segunda letra es a, como “Sara”, “Maria”, “David”.
    

---

### 4. Escape de caracteres especiales

Cuando quieres buscar los comodines % o _ como caracteres literales, debes usar la cláusula ESCAPE:
SELECT * FROM productos
WHERE descripcion LIKE '10\%%' ESCAPE '\';

- Busca descripciones que contienen el símbolo % como texto, no como comodín.
    

---

### 5. Comparaciones con mayúsculas y minúsculas

En Oracle, LIKE es sensible a mayúsculas por defecto:
SELECT * FROM empleados
WHERE nombre LIKE 'juan'; -- No encontrará 'Juan'

Para hacer una búsqueda insensible al caso, puedes usar UPPER o LOWER:
SELECT * FROM empleados
WHERE UPPER(nombre) LIKE 'JUAN';

---

### 6. Alternativa más avanzada: REGEXP_LIKE

Cuando necesitas patrones más complejos (como expresiones regulares), puedes usar REGEXP_LIKE:
SELECT * FROM empleados
WHERE REGEXP_LIKE(nombre, '^J.*z$');

- Encuentra nombres que comienzan con J y terminan con z.
    

---

### Ejemplos de patrones comunes

|Búsqueda deseada|Ejemplo con `LIKE`|
|---|---|
|Empieza por “A”|`'A%'`|
|Termina en “z”|`'%z'`|
|Contiene “mar”|`'%mar%'`|
|Segunda letra es “a”|`'_a%'`|
|Cuatro letras exactas|`'____'`|

---

## Función NVL en Oracle SQL

NVL se utiliza para reemplazar valores NULL por un valor predeterminado que tú especifiques.

---

### Sintaxis
NVL(expresión, valor_reemplazo)

- Si expresión es NULL, devuelve valor_reemplazo.
    
- Si expresión no es NULL, devuelve el valor de expresión.
    

---

### 1. Ejemplo básico
SELECT nombre, NVL(comision, 0) AS comision_final
FROM empleados;

- Si comision es NULL, muestra 0 en su lugar.
    

---

### 2. Uso con cadenas
SELECT nombre, NVL(direccion, 'No especificada') AS direccion_final
FROM empleados;

- Si direccion es NULL, muestra 'No especificada'.
    

---

### 3. En cálculos
SELECT nombre, salario + NVL(bono, 0) AS ingreso_total
FROM empleados;

- Suma el salario más el bono (considerando el bono como 0 si es NULL).
    

---

### Notas

- NVL solo permite dos parámetros.
    
- El tipo de dato de valor_reemplazo debe ser compatible con el de expresión.
    
- Alternativas modernas: COALESCE (acepta múltiples valores) y CASE.
    

---










