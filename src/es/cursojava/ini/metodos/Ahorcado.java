package es.cursojava.ini.metodos;

import java.util.*;

public class Ahorcado {

    static String[] sujetos = {
        "El gato", "La niña", "Mi amigo", "Un hombre", "La policía", "El perro",
        "La profesora", "Un payaso", "El médico", "La madre", "El robot", "Mi vecino"
    };

    static String[] verbos = {
        "come", "pinta", "busca", "rompe", "salta", "canta",
        "lava", "lee", "mueve", "atrapa", "conduce", "enciende"
    };

    static String[] complementos = {
        "una manzana", "la casa", "el coche", "una canción", "el jardín", "la calle",
        "el balón", "un sombrero", "la ventana", "la lámpara", "el periódico", "el fuego"
    };

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Random rand = new Random();

        // Generar una frase aleatoria
        String frase = sujetos[rand.nextInt(sujetos.length)] + " "
                     + verbos[rand.nextInt(verbos.length)] + " "
                     + complementos[rand.nextInt(complementos.length)];

        frase = frase.toLowerCase();

        // Preparar estado inicial
        char[] estado = new char[frase.length()];
        for (int i = 0; i < frase.length(); i++) {
            if (frase.charAt(i) == ' ') {
                estado[i] = ' '; // Dejar espacios visibles
            } else {
                estado[i] = '_';
            }
        }

        int intentosRestantes = 6;
        Set<Character> letrasUsadas = new HashSet<>();

        System.out.println("Bienvenido al juego del ahorcado! Tienes " + intentosRestantes + " intentos.");

        while (intentosRestantes > 0 && new String(estado).contains("_")) {
            System.out.println("\nFrase: " + new String(estado));
            System.out.println("Letras usadas: " + letrasUsadas);
            System.out.print("Introduce una letra: ");
            String entrada = scan.nextLine().toLowerCase();

            if (entrada.length() != 1 || !Character.isLetter(entrada.charAt(0))) {
                System.out.println("Entrada no válida. Introduce solo una letra.");
                continue;
            }

            char letra = entrada.charAt(0);

            if (letrasUsadas.contains(letra)) {
                System.out.println("Ya has usado esa letra.");
                continue;
            }

            letrasUsadas.add(letra);
            boolean acierto = false;

            for (int i = 0; i < frase.length(); i++) {
                if (frase.charAt(i) == letra) {
                    estado[i] = letra;
                    acierto = true;
                }
            }

            if (!acierto) {
                intentosRestantes--;
                System.out.println("Letra incorrecta. Te quedan " + intentosRestantes + " intentos.");
            } else {
                System.out.println("¡Bien! Has acertado una letra.");
            }
        }

        if (!new String(estado).contains("_")) {
            System.out.println("\n¡Felicidades! Has adivinado la frase: " + frase);
        } else {
            System.out.println("\nHas perdido. La frase era: " + frase);
        }

        scan.close();
    }
}

