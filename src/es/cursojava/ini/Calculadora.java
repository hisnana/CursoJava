package es.cursojava.ini;

import java.util.Arrays;

public class Calculadora {

	public static void main(String[] args) {
		

        if (args.length < 3) {
            System.err.println("Error: Debes ingresar al menos 3 argumentos.");
            System.out.println("Uso: java calculadora operacion arg2 arg3 [arg4 ...]");
            System.exit(1);  
        } else {
        	int num = 0;
        	int resultado = Integer.parseInt(args[1]);
        	
        	String operador = args[0].toLowerCase();
        	System.out.println(Arrays.toString(args));
            for (int i = 2; i < args.length; i++) {
            	
               num = Integer.parseInt(args[i]);
               
               
               
               if (operador.equals("suma")) {
               	
               	resultado+= num;
               	
               } else if (operador.equals("resta")) {
               	
               	resultado-= num;
               	
               } else if (operador.equals("multiplicacion")) {
               	
               	resultado*= num;
               	
               } else if (operador.equals("division")) {
               	
               	resultado%= num;
               } else {
            	   
            	   System.out.println("No es un operador válido");
            	   System.exit(1);
               }
               
              
           }
   		
           System.out.println("El resultado final es "+resultado);
        	
        	
        	
        	
        	
        }

        	

		
	}

}
