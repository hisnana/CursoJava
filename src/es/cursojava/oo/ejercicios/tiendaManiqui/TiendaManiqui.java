package es.cursojava.oo.ejercicios.tiendaManiqui;

public class TiendaManiqui {
	private String nombre;
	private double precioTotal;


	public TiendaManiqui(String nombre) {
		this.nombre = nombre;
	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TiendaManiqui tienda = new TiendaManiqui("Tienda1");
		Maniqui[] maniquies=tienda.abrirTienda();
		tienda.vestirManiquis(maniquies);
		tienda.mostrarEscaparate(maniquies);
		tienda.cerrarTienda(maniquies);
	}
	
	public Maniqui[] abrirTienda() {
		//Donde se van a generar todos los objetos
		//+3 Objetos de tipo Maniqui (2 vestidos y 1 sin vestir)
		
		Boton boton1 = new Boton("Azul","Pequeño","Redondo");
		Boton boton2 = new Boton("Rosa","Grande","Rombo");
		Boton[] botones = {boton1,boton2};
		Pantalon pantalon1 = new Pantalon("Azul",29,"L",boton2);
		Camisa camisa1 = new Camisa("Blanca",12,"S",botones);
		Vestido vestido1 = new Vestido("Amarillo",20,"L");
		Maniqui maniqui1 = new Maniqui(pantalon1,camisa1);
		Maniqui maniqui2 = new Maniqui(vestido1);
		Maniqui maniqui3 = new Maniqui();
		Maniqui[] maniquies = {maniqui1,maniqui2,maniqui3};
		return maniquies;
		
	}

	public void vestirManiquis(Maniqui[] maniquies) {
		//vestir maniquies (solo vestirá aquellos que están completamente desnudos)
		for(Maniqui maniqui:maniquies) {
			if(maniqui.getCamisa()!=null||maniqui.getPantalon()!=null||maniqui.getVestido()!=null) {
				continue;
				
			}
			Boton boton3 = new Boton("Amarillo","Grande","Cuadrado");
			Boton[] botones = {boton3};
			Pantalon pantalon2 = new Pantalon("Blanco",33,"M",boton3);
			Camisa camisa2 = new Camisa("Negra",18,"M",botones);
			maniqui.vestir(pantalon2,camisa2);
		}
		
	}
	
	public void mostrarEscaparate(Maniqui[] maniquies) {
		//Mostrar la información de cada maniqui y el precio total de la ropa que llevan
		for (Maniqui maniqui : maniquies) {
			
			if (maniqui.getId()!=0) {
				System.out.println(maniqui.getId());
				
			}
			if (maniqui.getCamisa()!=null) {
				maniqui.getCamisa().mostrarInfo();;
				precioTotal+=maniqui.getCamisa().getPrecio();
			} else {
				System.out.println("No tiene camisa");
			}
			if(maniqui.getPantalon()!=null) {
				maniqui.getPantalon().mostrarInfo();
				precioTotal+=maniqui.getPantalon().getPrecio();
			} else {
				System.out.println("No tiene pantalon");
			}
			if (maniqui.getVestido()!=null) {
				maniqui.getVestido().mostrarInfo();
				precioTotal+=maniqui.getVestido().getPrecio();
			} else {
				System.out.println("No tiene vestido");
			}
			System.out.println("El precio total del escaparate es: "+precioTotal);
			
		}
		
	}
	public void cerrarTienda(Maniqui[] maniquies) {
		//Indicar que cierra la tienda y desvestir a los maniquies
		System.out.println("SE CIERRA LA TIENDA");
		for (Maniqui maniqui: maniquies) {
			maniqui.desvestir();
		}
		
	}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
}
