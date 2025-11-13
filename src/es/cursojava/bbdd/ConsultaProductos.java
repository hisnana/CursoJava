package es.cursojava.bbdd;

import es.cursojava.utils.UtilidadesBD;

import java.math.BigDecimal;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ConsultaProductos {

    private static final String SQL =
        "SELECT ID, NOMBRE, CATEGORIA, PRECIO, STOCK, FECHA_ALTA, ESTADO, CODIGO_SKU, CREADO_POR, IVA " +
        "FROM TB_PRODUCTOS_ANA";

//    public static void main(String[] args) {
//        List<Producto> productos = consultaProductos();
//        System.out.println("Cantidad productos: " + productos.size());
//        for (Producto p : productos) {
//            System.out.println(p); // toString() de Lombok
//        }
//    }

    /** Devuelve la lista de productos leídos de la BD y los añade a la lista productos */
    public static List<Producto> consultaProductos() {
        List<Producto> productos = new ArrayList<>();

        try (Connection con = UtilidadesBD.crearConexion();
             PreparedStatement ps = con.prepareStatement(SQL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                productos.add(mapProducto(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
    }

    /** Mapea la fila actual del ResultSet a un Producto. */
    private static Producto mapProducto(ResultSet rs) throws SQLException {
        Long id           = rs.getLong("ID");                 // NUMBER -> Long
        String nombre     = rs.getString("NOMBRE");
        String categoria  = rs.getString("CATEGORIA");
        BigDecimal precio = rs.getBigDecimal("PRECIO");       // NUMBER(8,2) -> BigDecimal
        int stock         = rs.getInt("STOCK");               // NOT NULL (default 0)
        // JDBC 4.2: el driver Oracle permite leer LocalDate directamente
        LocalDate fechaAlta = rs.getObject("FECHA_ALTA", LocalDate.class);
        if (fechaAlta == null) {
            // Fallback por si algún driver antiguo devolviera null:
            Date d = rs.getDate("FECHA_ALTA");
            if (d != null) fechaAlta = d.toLocalDate();
        }
        String estado     = getNullable(rs, "ESTADO");
        String sku        = getNullable(rs, "CODIGO_SKU");
        String creadoPor  = getNullable(rs, "CREADO_POR");
        int iva           = rs.getInt("IVA");                 // NOT NULL (default 21)

        // Builder de Lombok (o new Producto(...) si prefieres el constructor)
        return Producto.builder()
                .id(id)
                .nombre(nombre)
                .categoria(categoria)
                .precio(precio)
                .stock(stock)
                .fechaAlta(fechaAlta)
                .estado(estado)
                .codigoSku(sku)
                .creadoPor(creadoPor)
                .iva(iva)
                .build();
    }

    /** Lee String que puede venir NULL sin lanzar NPE. */
    private static String getNullable(ResultSet rs, String col) throws SQLException {
        String v = rs.getString(col);
        return rs.wasNull() ? null : v;
    }
}
