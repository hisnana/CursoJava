set serveroutput on
--DECLARE
--	rTotal INTEGER := 0;
--    eTotal INTEGER := 0;
--BEGIN
--	DBMS_OUTPUT.PUT_LINE('Numero registros por tabla');
--    SELECT COUNT(*) INTO rTotal FROM REGIONS;
--    DBMS_OUTPUT.PUT_LINE('Regiones; ' ||rTotal);
--    DBMS_OUTPUT.PUT_LINE('Numero empleados');
--    SELECT COUNT(*) INTO eTotal FROM EMPLOYEES;
--    DBMS_OUTPUT.PUT_LINE('Regiones: '|| eTotal);
--END;

--DECLARE
--    TYPE tRegistro IS RECORD (
--    vTrabajo JOBS.JOB_TITLE%TYPE,
--    vTotal   INTEGER
--    );
--    rRegistro tRegistro;
--   vTotal   INTEGER := 0;
--   
--BEGIN
--   DBMS_OUTPUT.PUT_LINE('Numero registro por tabla');
--   SELECT COUNT(*) INTO vTotal FROM REGIONS;
--   DBMS_OUTPUT.PUT_LINE('Regiones: '||vTotal);
--   SELECT COUNT(*) INTO vTotal FROM EMPLOYEES;
--   DBMS_OUTPUT.PUT_LINE('Empleados: '||vTotal);
--   SELECT job_id, count(*) INTO rRegistro
--   FROM employees INNER JOIN jobs USING (job_id)
--   WHERE job_title='Programmer'
--   GROUP BY job_id;
--   DBMS_OUTPUT.PUT_LINE(rRegistro.vTrabajo||': '||rRegistro.vTotal);   
--END;

--DECLARE
--  -- SQL Developer te pedirá el valor de &texto (sin comillas).
--  -- q'[ ... ]' permite que el texto lleve comillas simples sin romper el script.
--  v_raw  VARCHAR2(4000) := q'[&texto]';
--    v_norm VARCHAR2(4000);
--  v_rev  VARCHAR2(4000);
--
--  FUNCTION reverse_str(p VARCHAR2) RETURN VARCHAR2 IS
--    r VARCHAR2(4000) := '';
--  BEGIN
--    FOR i IN REVERSE 1 .. LENGTH(p) LOOP
--      r := r || SUBSTR(p, i, 1);
--    END LOOP;
--    RETURN r;
--  END;
--  
--
--BEGIN
--  -- Minúsculas y quitamos todo lo no alfanumérico (espacios, signos, etc.)
--  v_norm := LOWER(REGEXP_REPLACE(v_raw, '[^[:alnum:]]', ''));
--  v_rev  := reverse_str(v_norm);
--
--  DBMS_OUTPUT.PUT_LINE('Texto original : ' || v_raw);
--  DBMS_OUTPUT.PUT_LINE('Normalizado    : ' || v_norm);
--  DBMS_OUTPUT.PUT_LINE('¿Palíndromo?   : ' ||
--    CASE WHEN v_norm = v_rev THEN 'SÍ' ELSE 'NO' END);
--END;
--/


--DECLARE
--  -- SQL Developer mostrará un popup para &texto
--  v_raw        VARCHAR2(32767) := q'[&texto]';
--
--  v_norm       VARCHAR2(32767);  -- texto normalizado
--  v_len        PLS_INTEGER;
--  v_mid        PLS_INTEGER;
--  v_left       VARCHAR2(32767);
--  v_right      VARCHAR2(32767);
--  v_right_rev  VARCHAR2(32767);
--  v_es_par_txt VARCHAR2(10);
--  v_resultado  VARCHAR2(10);
--
--  -- Función para invertir una cadena
--  FUNCTION reverse_str(p VARCHAR2) RETURN VARCHAR2 IS
--    r VARCHAR2(32767) := '';
--  BEGIN
--    FOR i IN REVERSE 1 .. LENGTH(p) LOOP
--      r := r || SUBSTR(p, i, 1);
--    END LOOP;
--    RETURN r;
--  END;
--BEGIN
--  -- NORMALIZACIÓN:
--  -- 1) minusculas
--  -- 2) quitar todo lo NO alfanumérico (espacios, signos, etc.)
--  v_norm := LOWER(REGEXP_REPLACE(v_raw, '[^[:alnum:]]', ''));
--  -- 3) (opcional) quitar tildes comunes en español
--  v_norm := TRANSLATE(v_norm, 'áéíóúüñ', 'aeiouun');
--
--  -- Longitud y par/impar
--  v_len        := LENGTH(v_norm);
--  v_es_par_txt := CASE WHEN MOD(v_len, 2) = 0 THEN 'par' ELSE 'impar' END;
--
--  -- Partición según paridad
--  IF MOD(v_len, 2) = 0 THEN
--    -- Ej.: ABCDEF -> izq=ABC, dcha=DEF
--    v_mid   := v_len/2;
--    v_left  := SUBSTR(v_norm, 1, v_mid);
--    v_right := SUBSTR(v_norm, v_mid + 1);
--  ELSE
--    -- Ej.: ABCDE -> centro=C, izq=AB, dcha=DE
--    v_mid   := (v_len + 1)/2;
--    v_left  := SUBSTR(v_norm, 1, v_mid - 1);
--    v_right := SUBSTR(v_norm, v_mid + 1);
--  END IF;
--
--  -- Invertimos la derecha y comparamos con la izquierda
--  v_right_rev := reverse_str(v_right);
--  v_resultado := CASE WHEN v_left = v_right_rev THEN 'SI' ELSE 'NO' END;
--
--  -- Salida
--  DBMS_OUTPUT.PUT_LINE('Texto        : ' || v_raw);
--  DBMS_OUTPUT.PUT_LINE('Normalizado  : ' || v_norm);
--  DBMS_OUTPUT.PUT_LINE('Longitud     : ' || v_len || ' (' || v_es_par_txt || ')');
--  IF MOD(v_len, 2) = 1 THEN
--    DBMS_OUTPUT.PUT_LINE('Centro       : pos ' || v_mid || ' = ''' || SUBSTR(v_norm, v_mid, 1) || '''');
--  END IF;
--  DBMS_OUTPUT.PUT_LINE('Mitad izq.   : ' || v_left);
--  DBMS_OUTPUT.PUT_LINE('Mitad dcha.  : ' || v_right);
--  DBMS_OUTPUT.PUT_LINE('Dcha invert. : ' || v_right_rev);
--  DBMS_OUTPUT.PUT_LINE('¿Es palindromo? ' || v_resultado);
--END;
--/

CREATE OR REPLACE PROCEDURE detector_palindromo(p_frase IN VARCHAR2)
AS
  vFraseSinEspacios  VARCHAR2(32767);
  vLongitud          PLS_INTEGER;
  vMitad             PLS_INTEGER;
  vPrimeraMitad      VARCHAR2(32767);
  vSegundaMitad      VARCHAR2(32767);
  vSegundaInv        VARCHAR2(32767);
  vPalindromo        VARCHAR2(2) := 'NO';

  -- Invertir cadena
  FUNCTION reverse_str(p VARCHAR2) RETURN VARCHAR2 IS
    r VARCHAR2(32767) := '';
  BEGIN
    FOR i IN REVERSE 1 .. LENGTH(p) LOOP
      r := r || SUBSTR(p, i, 1);
    END LOOP;
    RETURN r;
  END;
BEGIN
  -- quitar espacios y pasar a minúsculas
  vFraseSinEspacios := LOWER(REPLACE(p_frase, ' ', ''));
  vLongitud := LENGTH(vFraseSinEspacios);
  vMitad    := TRUNC(vLongitud / 2);

  -- partir en dos mitades (ignorando el carácter central si es impar)
  vPrimeraMitad := SUBSTR(vFraseSinEspacios, 1, vMitad);
  vSegundaMitad := SUBSTR(vFraseSinEspacios, -vMitad);

  -- invertir segunda mitad y comparar
  vSegundaInv := reverse_str(vSegundaMitad);
  IF vPrimeraMitad = vSegundaInv THEN
    vPalindromo := 'SI';
  END IF;

  DBMS_OUTPUT.PUT_LINE(vPalindromo || ' es un palindromo.');
END;
/



