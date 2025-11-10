package es.cursojava.bbdd;

import java.sql.*;
import java.util.Properties;

public class ConexionBBDD {
  public static void main(String[] args) throws Exception {
    String url = "jdbc:oracle:thin:@//localhost:1521/XEPDB1"; 
    Properties p = new Properties();
    p.put("user", "NANA");
    p.put("password", "Nana_2025!");

    try (Connection cn = DriverManager.getConnection(url, p)) {
      System.out.println("OK " + cn.getMetaData().getDatabaseProductVersion());
      System.out.println("Driver " + oracle.jdbc.OracleDriver.getDriverVersion());
    } catch (SQLException e) {
      System.err.println("SQLState=" + e.getSQLState() +
                         " Error=" + e.getErrorCode() +
                         " Msg=" + e.getMessage());
    }
  }
}