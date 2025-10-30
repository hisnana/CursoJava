-- Próximo aniversario de contratación por empleado (HR.EMPLOYEES)
SELECT
  e.employee_id,
  e.first_name || ' ' || e.last_name         AS empleado,
  TRUNC(e.hire_date)                         AS fecha_contratacion,
  CASE
    WHEN anniv_this_year >= TRUNC(SYSDATE)
      THEN anniv_this_year                   -- hoy o futuro en este año
    ELSE ADD_MONTHS(anniv_this_year, 12)     -- ya pasó; el del año que viene
  END                                        AS proximo_cumple,
FROM (
  SELECT e.*,
         -- “Mismo mes/día pero en el año actual”
         TRUNC(
           ADD_MONTHS(
             e.hire_date,
             12 * (EXTRACT(YEAR FROM TRUNC(SYSDATE)) - EXTRACT(YEAR FROM e.hire_date))
           )
         ) AS anniv_this_year
  FROM hr.employees e
) e
ORDER BY proximo_cumple, empleado;
