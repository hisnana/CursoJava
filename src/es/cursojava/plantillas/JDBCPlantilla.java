package es.cursojava.plantillas;

import java.sql.*;

import es.cursojava.utils.MiLogger;

public class JDBCPlantilla {
    public static void demo() {
        // Ajusta a tu instancia: ":XE" o "XEPDB1"
        String url  = "jdbc:oracle:thin:@localhost:1521/XEPDB1";
        String user = "HR";
        String pass = "hr";
        String sql = "SELECT first_name FROM employees WHERE employee_id = ?";
//        //String sql = """
//        	    CREATE TABLE TB_PRODUCTOS (
//        	        ID      NUMBER(10),
//        	        NOMBRE  VARCHAR2(100) NOT NULL,
//        	        PRECIO  NUMBER(8,2) CHECK (PRECIO >= 0),
//        	        CONSTRAINT PK_TB_PRODUCTOS PRIMARY KEY (ID)
//        	    )
//        	""";
        
        //Para la creación de la tabla
//        try (Connection conn = obtenerConexion();
//        	     Statement st = conn.createStatement()) {
//
//        	    st.executeUpdate(sql);
//        	    conn.commit(); // si estás en transacción manual
//
//        	} catch (SQLException e) {
//        	    conn.rollback(); // si procede
//        	    e.printStackTrace();
//        	}


        try (Connection con = DriverManager.getConnection(url, user, pass);
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, 100);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) System.out.println(rs.getString(1));
            }
            MiLogger.info("Consulta JDBC OK");
        } catch (SQLException e) {
            MiLogger.errorf("Fallo JDBC", e);
        }
    }
}
