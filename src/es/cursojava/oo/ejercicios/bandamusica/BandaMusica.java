package es.cursojava.oo.ejercicios.bandamusica;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BandaMusica {
	private final static Logger log = LoggerFactory.getLogger("BandaMusica");
	
	public static void main(String [] args) {
		
		BandaMusica banda1 = new BandaMusica();
		Instrumento[] instrumentos=banda1.empezarConcierto();
		banda1.afinarInstrumentos(instrumentos,log);
		banda1.tocarInstrumentos(instrumentos, log);
		
		
	}
	
	public Instrumento[] empezarConcierto() {
		
		Guitarra guitarra1 = new Guitarra("Guitarra1","Acustica",false,6);
		GuitarraElectrica guitarra2 = new GuitarraElectrica("Guitar2","Electrica",false,40);
		Piano piano1 = new Piano("Piano1","De cola",false,7,"De cola");
		Tambor tambor1 = new Tambor("Tambor1","Tambor",false,"Carbono");
		Instrumento[] instrumentos = {guitarra1,guitarra2,piano1,tambor1};
		
		return instrumentos;
		
	}
	public void afinarInstrumentos(Instrumento[] instrumentos,Logger log) {
		
		for (Instrumento instrumento : instrumentos) {
			instrumento.afinar(instrumento,log);
		}
		
	}
	public void tocarInstrumentos(Instrumento[] instrumentos,Logger log) {
		
		for (Instrumento instrumento : instrumentos) {
			if(instrumento.getTipo().equalsIgnoreCase("Tambor")) {
				instrumento.aporrear(log);
			} else {
				instrumento.tocar(instrumento,log);
			}
			
		}
		for (Instrumento instrumento : instrumentos) {
			if(instrumento.afinar(instrumento,log)==false) {
				log.info("La banda suena mal");
			} 
			
		}
		
	}

}
