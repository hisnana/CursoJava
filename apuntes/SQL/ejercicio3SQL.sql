--------------------------------------------------------------------------------
-- PASO 1: Diseño y DDL inicial con generación automática de id_producto y codigo_sku
--------------------------------------------------------------------------------

-- Crear secuencia para autogenerar el ID del producto
CREATE SEQUENCE seq_productos_ana
START WITH 1 INCREMENT BY 1 NOCACHE;

-- Crear secuencia para autogenerar el código SKU secuencial
CREATE SEQUENCE seq_sku
START WITH 1 INCREMENT BY 1 NOCACHE;

-- Crear tabla productos_ana con restricciones y valores por defecto
CREATE TABLE productos_ana (
    id_producto NUMBER PRIMARY KEY,                              -- ID autogenerado y clave primaria
    nombre VARCHAR2(200) NOT NULL,                               -- Nombre del producto, obligatorio, ampliado para SEO
    categoria VARCHAR2(20) NOT NULL CHECK (                     -- Categoría, obligatoria y validada
        categoria IN ('TECNOLOGIA', 'HOGAR', 'DEPORTE')
    ),
    precio NUMBER(10,2) DEFAULT 0 CHECK (precio >= 0),          -- Precio, >= 0, por defecto 0
    stock NUMBER DEFAULT 0 NOT NULL CHECK (stock >= 0),         -- Stock, no nulo, por defecto 0
    fecha_alta DATE DEFAULT SYSDATE NOT NULL,                   -- Fecha de alta, por defecto fecha sistema
    estado VARCHAR2(10) DEFAULT 'ACTIVO' CHECK (                -- Estado, solo ACTIVO o INACTIVO, por defecto ACTIVO
        estado IN ('ACTIVO', 'INACTIVO')
    ),
    codigo_sku VARCHAR2(50) UNIQUE,                             -- Código SKU único, se genera automáticamente si no se indica
    usuario_alta VARCHAR2(30) DEFAULT USER NOT NULL,            -- Usuario que crea el registro, por defecto USER de Oracle
    tipo_iva NUMBER DEFAULT 21 CHECK (tipo_iva BETWEEN 0 AND 100) -- Tipo de IVA, % entero, por defecto 21
);

-- Trigger para autogenerar id_producto y codigo_sku antes de insertar un producto
CREATE OR REPLACE TRIGGER trg_productos_ana_bi
BEFORE INSERT ON productos_ana
FOR EACH ROW
DECLARE
    v_prefijo VARCHAR2(1);
    v_num NUMBER;
BEGIN
    -- Generar id_producto si no se pasa
    IF :NEW.id_producto IS NULL THEN
        SELECT seq_productos_ana.NEXTVAL INTO :NEW.id_producto FROM dual;
    END IF;

    -- Generar codigo_sku automáticamente si no se pasa
    IF :NEW.codigo_sku IS NULL THEN
        -- Elegir prefijo según categoría
        CASE :NEW.categoria
            WHEN 'TECNOLOGIA' THEN v_prefijo := 'T';
            WHEN 'HOGAR' THEN v_prefijo := 'H';
            WHEN 'DEPORTE' THEN v_prefijo := 'D';
            ELSE v_prefijo := 'X'; -- fallback por si categoría es distinta
        END CASE;

        -- Obtener siguiente valor secuencial para SKU
        SELECT seq_sku.NEXTVAL INTO v_num FROM dual;

        -- Construir codigo_sku con formato: SKU-[prefijo][número 3 dígitos con ceros a la izquierda]
        :NEW.codigo_sku := 'SKU-' || v_prefijo || TO_CHAR(v_num, 'FM000');
    END IF;
END;
/

--------------------------------------------------------------------------------
-- PASO 2: Carga inicial de 5 productos de ejemplo
--------------------------------------------------------------------------------

-- Producto TECNOLOGIA con fecha de alta especificada
INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado)
VALUES ('Smartphone X10', 'TECNOLOGIA', 499.99, 15, TO_DATE('01/09/2025','DD/MM/YYYY'), 'ACTIVO');

-- Producto TECNOLOGIA con datos por defecto (fecha_alta, estado, codigo_sku generados automáticamente)
INSERT INTO productos_ana (nombre, categoria, precio, stock)
VALUES ('Portátil Pro', 'TECNOLOGIA', 899.99, 10);

-- Producto HOGAR con stock > 0
INSERT INTO productos_ana (nombre, categoria, precio, stock)
VALUES ('Aspiradora Robot', 'HOGAR', 299.50, 5);

-- Producto HOGAR con stock = 0 (para pruebas posteriores)
INSERT INTO productos_ana (nombre, categoria, precio, stock)
VALUES ('Cafetera Express', 'HOGAR', 120.00, 0);

-- Producto DEPORTE con precio 0 y estado INACTIVO
INSERT INTO productos_ana (nombre, categoria, precio, stock, estado)
VALUES ('Balón de fútbol', 'DEPORTE', 0, 20, 'INACTIVO');

-- Intento de insertar producto con precio negativo para probar la restricción y error

INSERT INTO productos_ana (nombre, categoria, precio, stock)
VALUES ('Producto Erróneo', 'TECNOLOGIA', -10, 5);

/

--------------------------------------------------------------------------------
-- PASO 3: Mantenimiento de catálogo (UPDATE)
--------------------------------------------------------------------------------

-- Aumentamos el precio un 5% multiplicando por 1.05 (100% + 5%)
-- Usamos ROUND(..., 2) para redondear a 2 decimales, típico para precios en euros
UPDATE productos_ana
SET precio = ROUND(precio * 1.05, 2)
WHERE categoria = 'TECNOLOGIA';

-- Marcar como INACTIVO los productos con stock = 0
UPDATE productos_ana
SET estado = 'INACTIVO'
WHERE stock = 0;

-- Normalizar las fechas de alta al primer día del mes
UPDATE productos_ana
SET fecha_alta = TRUNC(fecha_alta, 'MM');

--------------------------------------------------------------------------------
-- PASO 4: Depuración (DELETE)
--------------------------------------------------------------------------------

-- Eliminar productos INACTIVO con stock = 0 (no visibles y sin rotación)
DELETE FROM productos_ana
WHERE estado = 'INACTIVO' AND stock = 0;

--------------------------------------------------------------------------------
-- PASO 5: Consultas de verificación
--------------------------------------------------------------------------------

-- Mostrar los 3 productos con mayor precio (descendente)
SELECT *
FROM productos_ana
ORDER BY precio DESC
FETCH FIRST 3 ROWS ONLY;
