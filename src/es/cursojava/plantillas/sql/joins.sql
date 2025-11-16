-- 1) INNER JOIN: solo filas con relación en ambas tablas.
SELECT c.NOMBRE, p.ID_PEDIDO, p.IMPORTE
FROM   CLIENTE c
JOIN   PEDIDO  p ON p.ID_CLIENTE = c.ID_CLIENTE;

-- CUÁNDO: relación obligatoria; quieres "intersección".

-- 2) LEFT JOIN: todos los CLIENTE y sus PEDIDOS si existen.
SELECT c.NOMBRE, p.ID_PEDIDO, p.ESTADO
FROM   CLIENTE c
LEFT   JOIN PEDIDO p ON p.ID_CLIENTE = c.ID_CLIENTE
ORDER  BY c.NOMBRE;

-- CUÁNDO: entidad principal a la izquierda; no perder clientes sin pedidos.

-- 3) RIGHT JOIN: todos los PEDIDOS aunque no hubiera CLIENTE.
-- (Con FK y ON DELETE CASCADE no debería haber huérfanos; es solo demostrativo.)
SELECT c.NOMBRE, p.ID_PEDIDO
FROM   CLIENTE c
RIGHT  JOIN PEDIDO p ON p.ID_CLIENTE = c.ID_CLIENTE;

-- 4) FULL OUTER JOIN: todo de ambos lados (coincidan o no).
SELECT c.NOMBRE, p.ID_PEDIDO
FROM   CLIENTE c
FULL   OUTER JOIN PEDIDO p ON p.ID_CLIENTE = c.ID_CLIENTE;

-- CUÁNDO: auditorías/comparaciones entre datasets.

-- 5) CROSS JOIN: producto cartesiano (todas las combinaciones).
SELECT c.NOMBRE, p.ID_PEDIDO
FROM   CLIENTE c
CROSS  JOIN PEDIDO p;

-- CUÁNDO: generar combinaciones; ⚠️ puede explotar en tamaño.

-- 6) SELF JOIN: tabla consigo misma (ej. referidor → referido).
SELECT hijo.NOMBRE   AS REFERIDO,
       padre.NOMBRE  AS REFERIDOR
FROM   CLIENTE hijo
LEFT   JOIN CLIENTE padre ON hijo.ID_REFERIDOR = padre.ID_CLIENTE;

-- 7) JOINS en cadena (3 tablas)
SELECT c.NOMBRE, p.ID_PEDIDO, l.SKU, l.CANTIDAD, l.PRECIO
FROM   CLIENTE c
JOIN   PEDIDO  p ON p.ID_CLIENTE = c.ID_CLIENTE
JOIN   LINEA_PEDIDO l ON l.ID_PEDIDO = p.ID_PEDIDO;

-- 8) SEMI JOIN (EXISTS): clientes con al menos un pedido
SELECT c.ID_CLIENTE, c.NOMBRE
FROM   CLIENTE c
WHERE  EXISTS (SELECT 1 FROM PEDIDO p WHERE p.ID_CLIENTE = c.ID_CLIENTE);

-- 9) ANTI JOIN (NOT EXISTS): clientes sin pedidos
SELECT c.ID_CLIENTE, c.NOMBRE
FROM   CLIENTE c
WHERE  NOT EXISTS (SELECT 1 FROM PEDIDO p WHERE p.ID_CLIENTE = c.ID_CLIENTE);

-- 💡 NOTAS:
-- - Evita NATURAL JOIN (fragilidad por nombres de columnas).
-- - Prefiere ANSI JOIN (ON ...) frente al estilo antiguo con (+).
-- - Una FK es una RESTRICCIÓN: protege Consistency (ACID) evitando huérfanos.
