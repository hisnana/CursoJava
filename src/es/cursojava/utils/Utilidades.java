package es.cursojava.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Utilidades {
	private static final Scanner sc = new Scanner(System.in); // único Scanner

	//metodo calcularEdad al cual le pasamos un año y le devolvemos la edad apatir de ese año.
	
	public static int calcularEdad (int ano) {
		
		int añoActual = 2025;
		int resultado = añoActual - ano;
		return resultado;
		
	}
	
	public static void pintaMenu (String[] arrayStrings) {
		
		for (String palabra : arrayStrings) {
			System.out.println(palabra);
		}
		System.out.println("Introduce una opción");
		
	}
	public static void pintaMenu (String[] arrayStrings,String texto) {
		
		for (String palabra : arrayStrings) {
			System.out.println(palabra);
		}
		System.out.println(texto);
		
	}
	
	public static int pideDatoNumerico(String texto) {
		System.out.println(texto);
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		return num;
	}
	
	public static String pideDatoCadena(String texto){
		System.out.println(texto);
		Scanner scan = new Scanner(System.in);
		String dato= scan.nextLine();
		return dato;
		
	}
	
    public static String pedirDato(String prompt) {
        MiLogger.info(prompt);
        String s = sc.nextLine().trim();
        MiLogger.info("→ " + s);
        return s;
    }

    public static int pedirEntero(String prompt) {
        while (true) {
            MiLogger.info(prompt);
            String s =sc.nextLine().trim();
            try {
                int n = Integer.parseInt(s);
                MiLogger.info("→ " + n);
                return n;
            } catch (InputMismatchException | NumberFormatException e) {
                MiLogger.info("Número inválido. Intenta de nuevo.");
            }
        }
    }
    // Pregunta hasta recibir s/n válidos
    public static boolean pedirConfirmacion(String prompt) {
        while (true) {
            String r = pedirDato(prompt + " (s/n): ");
            String t = r.trim().toLowerCase();
            if (t.equals("s") || t.equals("si") || t.equals("sí")) return true;
            if (t.equals("n") || t.equals("no")) return false;
            MiLogger.info("Responde con 's' o 'n', por favor.");
        }
    }

	
}


