SET TRANSACTION READ WRITE;     -- o SERIALIZABLE (aislamiento más estricto)
SAVEPOINT p1;
UPDATE ...;
-- si va mal:
ROLLBACK TO p1;
COMMIT;
