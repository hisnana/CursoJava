package es.cursojava.oo.ejercicios.tiendaManiqui;

public class Maniqui {
	private int id;
	private Pantalon pantalon;
	private Camisa camisa;
	private Vestido vestido;
	public Maniqui() {
		
	}
	public Maniqui(Pantalon pantalon, Camisa camisa) {
		if(vestido!=null) {
			System.out.println("Ya tiene un vestido puesto");
		} else {
			this.pantalon = pantalon;
			this.camisa = camisa;
		}
		
	}
	public Maniqui(Vestido vestido) {
		if(pantalon!=null&&camisa!=null) {
			System.out.println("Ya está vestido con camisa y/o pantalón");
		} else {
			
			this.vestido = vestido;
		}
		
	}
	public void vestir(Pantalon pantalon,Camisa camisa) {
		if(this.camisa==null||this.pantalon==null&&this.vestido==null) {
			this.pantalon = pantalon;
			this.camisa = camisa;
		} 
		
		
	}
	public void desvestir() {
		this.pantalon = null;
		this.camisa = null;
		this.vestido = null;
		
		
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Pantalon getPantalon() {
		return pantalon;
	}
	public void setPantalon(Pantalon pantalon) {
		this.pantalon = pantalon;
	}
	public Camisa getCamisa() {
		return camisa;
	}
	public void setCamisa(Camisa camisa) {
		this.camisa = camisa;
	}
	public Vestido getVestido() {
		return vestido;
	}
	public void setVestido(Vestido vestido) {
		this.vestido = vestido;
	}
	
	public void mostrarInfo() {
		System.out.println("El maniqui con id: "+id);
		System.out.println("Tiene pantalón: ");
		pantalon.mostrarInfo();
		System.out.println("Tiene camisa:");
		camisa.mostrarInfo();
		System.out.println("Tiene vestido:");
		vestido.mostrarInfo();
	}
	

}
