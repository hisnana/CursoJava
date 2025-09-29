package es.cursojava.oo.ejercicios.limpiar;

public class Main {

	public void main(String[] args) {
		// TODO Auto-generated method stub
		Mueble mesa1 = new Mesa();
		Mueble silla1 = new Silla();
		Verdura lechuga1 = new Lechuga("Murcia","escarola");
		Verdura judias1 = new JudiasVerdes("Aragón",true);
		Limpiable[] limpiables = {mesa1,silla1,lechuga1,judias1};
		
		desinfectar(limpiables);
		
	}
	
	private static void desinfectar(Limpiable[] limpiables) {
		
		for (Limpiable limpiable : limpiables) {
			if (limpiable instanceof Verdura) {
				((Verdura) limpiable).desinfectar();
			}
		}
		
	}

}
