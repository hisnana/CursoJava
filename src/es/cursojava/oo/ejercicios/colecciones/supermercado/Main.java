package es.cursojava.oo.ejercicios.colecciones.supermercado;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import es.cursojava.oo.ejercicios.colecciones.supermercado.Alimentos.Alimento;
import es.cursojava.oo.ejercicios.colecciones.supermercado.Alimentos.Fruta;
import es.cursojava.oo.ejercicios.colecciones.supermercado.Alimentos.Lacteo;
import es.cursojava.oo.ejercicios.colecciones.supermercado.Alimentos.Verdura;
import es.cursojava.utils.MiLogger;

public class Main {
    public static void main(String[] args) {
    	
    	Scanner sc = new Scanner(System.in);
    	Alimento productoParaCarrito = null;
       
        // Crear supermercado y stock (con 2 categorías para ejemplo)
        Supermercado supermercado = new Supermercado("MiSuper");

        // Categoría 1: Frutas
        List<Alimento> frutas = new ArrayList<>();
        frutas.add(new Fruta("Plátanos", 3, 0.50,"Canarias"));
        frutas.add(new Fruta("Manzanas", 5, 0.30,"Banana"));

        // Categoría 2: Verduras
        List<Alimento> verduras = new ArrayList<>();
        verduras.add(new Verdura("Pimiento", 2, 0.80,"Italiano"));
        verduras.add(new Verdura("Cebolla", 8, 0.40,"Morada"));
        
        List<Alimento> lacteos = new ArrayList<>();
        lacteos.add(new Lacteo("Leche",5,0.98,"Entera"));
        lacteos.add(new Lacteo("Yogur",6,0.98,"Desnatado"));

        supermercado.getStockAlimentos().add(frutas);
        supermercado.getStockAlimentos().add(verduras);
        supermercado.getStockAlimentos().add(lacteos);

        // Crear clientes 
        ClienteSupermercado cliente1 = new ClienteSupermercado("Cliente1");
        ClienteSupermercado cliente2 = new ClienteSupermercado("Cliente2");
        supermercado.getClientes().add(cliente1);
        supermercado.getClientes().add(cliente2);

        // Para cada cliente
        for (ClienteSupermercado cliente : supermercado.getClientes()) {
            MiLogger.info("\nAtendiendo a " + cliente.getNombre());

            boolean continuar = true;
            while (continuar) {
                supermercado.mostrarStock();

                MiLogger.info("¿Qué producto desea comprar? (o 'salir' para terminar): ");
                String producto = sc.nextLine();
                MiLogger.info(producto);
                Alimento stockProducto = supermercado.buscarEnStock(producto);

                if (producto.equalsIgnoreCase("salir")) {
                    break;


                }

                
                if (stockProducto == null || stockProducto.getCantidad() == 0) {
                    MiLogger.info("Producto no disponible o agotado.");
                    continue;
                }

                MiLogger.info("¿Cuántas unidades quiere comprar?: ");
                int cantidad = Integer.parseInt(sc.nextLine());

                if (cantidad <= 0) {
                    MiLogger.info("Cantidad inválida.");
                    continue;
                }

                if (cantidad > stockProducto.getCantidad()) {
                    MiLogger.info("No hay suficiente stock. Solo quedan " + stockProducto.getCantidad() + " unidades.");
                    continue;
                }

                // Añadir al carrito
                
                if (stockProducto instanceof Fruta fruta) {
                    productoParaCarrito = new Fruta(fruta.getNombre(), cantidad,fruta.getPrecio(), fruta.getTipo());
                } else if (stockProducto instanceof Verdura verdura) {
                    productoParaCarrito = new Verdura(verdura.getNombre(), cantidad, verdura.getPrecio(), verdura.getTipo());
                } else if (stockProducto instanceof Lacteo lacteo) {
                    productoParaCarrito = new Lacteo(lacteo.getNombre(), cantidad, lacteo.getPrecio(), lacteo.getTipo());
                } else {
                    MiLogger.info("No existe el producto");
                }
 
                cliente.agregarAlCarrito(productoParaCarrito);

                // Actualizar stock
                stockProducto.setCantidad(stockProducto.getCantidad() - cantidad);

                MiLogger.info("¿Desea seguir comprando? (s/n): ");
                String resp = sc.nextLine();
                if (!resp.equalsIgnoreCase("s")) {
                    continuar = false;
                    System.out.printf("Total a pagar para %s: €%.2f\n", cliente.getNombre(), cliente.calcularTotal());
                }
            }
        }

        // Mostrar resumen y total a pagar
        MiLogger.info("\nResumen de compras y pagos:");
        for (ClienteSupermercado cliente : supermercado.getClientes()) {
            MiLogger.info(cliente.getNombre() + " compró:");
            for (Alimento a : cliente.getCarritoCompra()) {
                MiLogger.info("- " + a.getNombre() + " x " + a.getCantidad() + " unidades");
            }
            MiLogger.info(String.format("Total a pagar para %s: €%.2f\n", cliente.getNombre(), cliente.calcularTotal()));

        }

        sc.close();
        MiLogger.info("Resumen de compras de todos los clientes:");

        for (ClienteSupermercado cliente : supermercado.getClientes()) {
            MiLogger.info(cliente.getNombre() + " ha comprado:");

            for (Alimento alimento : cliente.getCarritoCompra()) {
                MiLogger.info(String.format("- %s x%d unidades (%.2f € cada unidad)",
                    alimento.getNombre(),
                    alimento.getCantidad(),
                    alimento.getPrecio()));
            }

            MiLogger.info(String.format("Total a pagar: €%.2f\n", cliente.calcularTotal()));
        }
    }
    


}