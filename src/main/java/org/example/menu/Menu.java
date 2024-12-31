package org.example.menu;

import org.example.entidades.Conversion;
import org.example.servicios.ConversorService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Menu {
    public static void mostrarMenu() {
        //Imprimo el menú
        System.out.println("**************************************************************************");
        System.out.println("¡Bienvenidos al conversor de monedas! 👋");
        System.out.println("\nElije la conversión que deseas realizar: \n");
        System.out.println("""
                1) Dólar ==> Peso Argentino
                2) Peso Argentino ==> Dólar
                3) Dólar ==> Real brasileño
                4) Real brasileño ==> Dólar
                5) Dólar ==> Peso colombiano
                6) Peso colombiano ==> Dólar
                7) Salir
                """);

        Scanner entrada = new Scanner(System.in);
        int opcion = 0;
        while (true){
            try{
                System.out.println("Seleccione una opción válida: ");
                opcion = entrada.nextInt();

                if (opcion>0 && opcion<8){
                    break;
                } else{
                    System.err.println("Entrada inválida. Por favor ingrese un número entre el 1 y el 7");
                }
            } catch (InputMismatchException e) {
                System.err.println("Entrada inválida. Por favor ingrese un número");
                entrada.next();
            }
        }

        String monedas = obtenerMonedas(opcion);

        //limpio la entrada para el nuevo dato
        entrada.nextLine();
        float cantidad = 0;
        while (true){
            try {
                System.out.println("\nIngrese la cantidad que desea convertir 💸: ");
                cantidad = Float.parseFloat(entrada.nextLine().replace(",", "."));
                if (cantidad>0){
                    break;
                } else {
                    System.err.println("Entrada inválida. Por favor ingrese una cantidad mayor a cero");
                }
            } catch (NumberFormatException e) {
                System.err.println("Entrada inválida. Por favor ingrese un número");
            }
        }

        Conversion conversion = ConversorService.consumoApi(monedas, cantidad);

        //MOSTRAR RESULTADO
        System.out.println("\nEl valor de "+cantidad+" ["+ conversion.getMonedaOrigen()+ "] corresponde al valor final de ==> "+conversion.getResultado()+" ["+conversion.getMonedaDestino()+"]");
        mostrarMenu();
    }

    public static String obtenerMonedas(int opcion){
        String monedas= "";
        switch (opcion){
            case 1:
                monedas = "USD/ARS";
                break;
            case 2:
                monedas = "ARS/USD";
                break;
            case 3:
                monedas = "USD/BRL";
                break;
            case 4:
                monedas = "BRL/USD";
                break;
            case 5:
                monedas = "USD/COP";
                break;
            case 6:
                monedas = "COP/USD";
                break;
            case 7:
                System.out.println("\nMuchas gracias por usar el conversor de monedas!! 😊👋");
                System.exit(0);
        }
        return monedas;
    }

}
