---ANA ARROYO PLAZA
----EJERCICIO SELECTS
guardar todos estos datos
=================================================
INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku, tipo_iva)
VALUES ('Smartphone 128GB', 'TECNOLOGIA', 499.00, 30, TO_DATE('05/09/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-T-003', 21);

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku, tipo_iva)
VALUES ('Tablet 10"', 'TECNOLOGIA', 199.99, 0, TO_DATE('20/07/2025','DD/MM/YYYY'), 'INACTIVO', 'SKU-T-004', 21);

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Monitor 27" 2K', 'TECNOLOGIA', 279.00, 7, SYSDATE, 'ACTIVO', 'SKU-T-005');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Teclado mecánico', 'TECNOLOGIA', 89.90, 15, TO_DATE('12/08/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-T-006');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Cámara deportiva 4K', 'TECNOLOGIA', 149.00, 10, TO_DATE('28/05/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-T-007');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Auriculares gaming', 'TECNOLOGIA', 69.00, 25, TO_DATE('03/10/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-T-008');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Smartwatch', 'TECNOLOGIA', 139.00, 14, TO_DATE('18/06/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-T-009');

-- HOGAR
INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Robot aspirador', 'HOGAR', 229.00, 5, TO_DATE('03/06/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-H-003');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Batidora de vaso', 'HOGAR', 45.00, 18, SYSDATE, 'ACTIVO', 'SKU-H-004');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Funda de almohada', 'HOGAR', 9.99, 0, TO_DATE('11/07/2025','DD/MM/YYYY'), 'INACTIVO', 'SKU-H-005');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku, tipo_iva)
VALUES ('Silla ergonómica', 'HOGAR', 159.00, 8, TO_DATE('22/04/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-H-006', 21);

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Cafetera espresso', 'HOGAR', 129.00, 6, TO_DATE('09/09/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-H-007');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Freidora de aire', 'HOGAR', 99.00, 0, TO_DATE('30/08/2025','DD/MM/YYYY'), 'INACTIVO', 'SKU-H-008');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Lámpara LED escritorio', 'HOGAR', 22.90, 40, TO_DATE('05/05/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-H-009');

-- DEPORTE
INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Mancuernas 5kg', 'DEPORTE', 35.00, 20, TO_DATE('14/07/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-D-002');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Esterilla de yoga', 'DEPORTE', 19.95, 0, TO_DATE('21/06/2025','DD/MM/YYYY'), 'INACTIVO', 'SKU-D-003');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku, tipo_iva)
VALUES ('Zapatillas running', 'DEPORTE', 79.00, 12, TO_DATE('02/09/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-D-004', 21);

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Bicicleta de spinning', 'DEPORTE', 499.00, 3, TO_DATE('17/03/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-D-005');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Balón de baloncesto', 'DEPORTE', 24.50, 50, TO_DATE('12/10/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-D-006');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Cinta de correr', 'DEPORTE', 699.00, 2, TO_DATE('08/01/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-D-007');

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku, tipo_iva)
VALUES ('Comba de velocidad', 'DEPORTE', 12.99, 35, TO_DATE('25/08/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-D-008', 21);

INSERT INTO productos_ana (nombre, categoria, precio, stock, fecha_alta, estado, codigo_sku)
VALUES ('Rodillo foam', 'DEPORTE', 16.50, 9, TO_DATE('19/05/2025','DD/MM/YYYY'), 'ACTIVO', 'SKU-D-009');


## 1) Listado básico

Enunciado: Muestra todas las columnas de todos los productos_ana.

SELECT * FROM productos_ana;

## 2) Proyección y ordenación

Enunciado: Lista `nombre`, `categoria` y `precio`, ordenado alfabéticamente por `nombre`.

SELECT nombre, categoria, precio
FROM productos_ana
ORDER BY nombre ASC;


## 3) Filtro simple

Enunciado: Muestra los productos_ana de la categoría TECNOLOGIA con `precio` entre 100 y 300 euros.

SELECT nombre, categoria, precio
FROM productos_ana
WHERE categoria = 'TECNOLOGIA'
  AND precio BETWEEN 100 AND 300;

## 4) Nulos, defaults y condiciones

Enunciado: Obtén los productos_ana con stock = 0 (rotación nula) y muestra `id`, `nombre`, `estado`.

SELECT id_producto, nombre, estado, stock
FROM productos_ana
WHERE stock = 0;

## 5) Funciones sobre fechas

Enunciado: Muestra los productos_ana dados de alta en septiembre de 2025.

SELECT *
FROM productos_ana
WHERE fecha_alta BETWEEN TO_DATE('01-09-2025', 'DD-MM-YYYY')
                     AND TO_DATE('30-09-2025', 'DD-MM-YYYY');


## 6) Agregación por categoría

Enunciado: Muestra cuántos productos_ana hay por `categoria`.

SELECT categoria, COUNT(*) AS cantidad_productos
FROM productos_ana
GROUP BY categoria;


## 7) Métricas de negocio

Enunciado: Para cada `categoria`, calcula precio medio, precio máx y unidades totales en stock.
SELECT
  categoria,
  ROUND(AVG(precio), 2) AS precio_medio,
  MAX(precio) AS precio_maximo,
  SUM(stock) AS stock_total
FROM productos_ana
GROUP BY categoria;



## 8) Top-N

Enunciado: Devuelve los 5 productos_ana más caros (id, nombre, categoria, precio).

SELECT 
  categoria,
  AVG(precio) AS precio_medio,
  MAX(precio) AS precio_maximo,
  SUM(stock) AS unidades_en_stock
FROM productos_ana
GROUP BY categoria;


## 9) Cálculo de PVP con tipo_iva

Enunciado: Muestra `nombre`, `precio` y el PVP (precio con tipo_iva), redondeado a 2 decimales.

SELECT
  nombre,
  precio,
  ROUND(precio * (1 + tipo_iva / 100), 2) AS pvp
FROM productos_ana;


## 10) Búsqueda por texto
Enunciado: Encuentra productos_ana cuyo nombre contenga la palabra “cafe”.

SELECT *
FROM productos_ana
WHERE LOWER(nombre) LIKE '%cafe%';


## 11) Media de precio por categoría (solo TECNOLOGIA y HOGAR, activos)

Enunciado: Para las categorías TECNOLOGIA y HOGAR con `estado = 'ACTIVO'`, muestra precio medio y nº de productos_ana. Solo devuelve categorías cuya media sea > 100€. Ordena por media desc.
SELECT 
  categoria,
  ROUND(AVG(precio),2) AS precio_medio,
  COUNT(*) AS numero_productos
FROM productos_ana
WHERE categoria IN ('TECNOLOGIA', 'HOGAR')
  AND estado = 'ACTIVO'
GROUP BY categoria
HAVING AVG(precio) > 100
ORDER BY precio_medio DESC;


## 12) Stock por mes para (ago, sep, oct) 2025 en categorías seleccionadas

Enunciado: Para HOGAR y DEPORTE, en los meses agosto, septiembre, octubre de 2025, agrupa por mes y categoría, muestra stock total. Devuelve solo grupos con stock ≥ 20. Ordena por mes asc, stock desc.

SELECT
  TO_CHAR(fecha_alta, 'YYYY-MM') AS mes,
  categoria,
  SUM(stock) AS stock_total
FROM productos_ana
WHERE categoria IN ('HOGAR', 'DEPORTE')
  AND fecha_alta BETWEEN TO_DATE('01-08-2025', 'DD-MM-YYYY') AND TO_DATE('31-10-2025', 'DD-MM-YYYY')
GROUP BY TO_CHAR(fecha_alta, 'YYYY-MM'), categoria
HAVING SUM(stock) >= 20
ORDER BY mes ASC, stock_total DESC;


## 13) tipo_iva y categoría con masa crítica


SELECT
  tipo_iva,
  categoria,
  COUNT(*) AS numero_productos,
  MAX(precio) AS precio_maximo
FROM productos_ana
WHERE tipo_iva IN (21, 10)
  AND categoria IN ('TECNOLOGIA', 'HOGAR', 'DEPORTE')
GROUP BY tipo_iva, categoria
HAVING COUNT(*) >= 3
ORDER BY tipo_iva ASC, numero_productos DESC;


## 14) Catálogo filtrado por nombres concretos

Enunciado: Toma solo los productos_ana cuyo nombre esté en la lista
`('Auriculares BT','Auriculares gaming','Cafetera espresso','Freidora de aire','Smartwatch')`.
Agrupa por categoría y estado y muestra precio medio y stock total. Devuelve grupos con precio medio > 50. Ordena por precio medio desc.

SELECT
  categoria,
  estado,
  AVG(precio) AS precio_medio,
  SUM(stock) AS stock_total
FROM productos_ana
WHERE nombre IN (
  'Auriculares BT',
  'Auriculares gaming',
  'Cafetera espresso',
  'Freidora de aire',
  'Smartwatch'
)
GROUP BY categoria, estado
HAVING AVG(precio) > 50
ORDER BY precio_medio DESC;


## 15) Control de “rotación cero” dentro de categorías

Enunciado: Sobre HOGAR y DEPORTE, agrupa por categoría y estado y calcula cuántos tienen stock = 0 y el precio medio. Devuelve solo grupos con al menos 1 producto con stock 0 y precio medio ≥ 20. Ordena por categoría, estado.


SELECT
  categoria,
  estado,
  COUNT(CASE WHEN stock = 0 THEN 1 END) AS productos_sin_stock,
  AVG(precio) AS precio_medio
FROM productos_ana
WHERE categoria IN ('HOGAR', 'DEPORTE')
GROUP BY categoria, estado
HAVING COUNT(CASE WHEN stock = 0 THEN 1 END) >= 1
   AND AVG(precio) >= 20
ORDER BY categoria ASC, estado ASC;
