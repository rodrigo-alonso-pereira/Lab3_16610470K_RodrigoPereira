package cl.usach.Vista;

import java.util.Scanner;

public class Menu {

    // Metodos propios de menu
    public static void iniciarMenu() {
        try {
            Scanner input = new Scanner(System.in);
            final int MENU_EXIT_OPTION = 5;
            int choice;
            do {
                printGeneralMenu();
                choice = input.nextInt();
                switch (choice) {
                    case 1:
                        printSpecificMenu1();
                        break;
                    case 2:
                        printSpecificMenu2();
                        //sw0.toString();
                        break;
                    case 3:
                        printSpecificMenu3();
                        break;
                    case 4:
                        System.out.println("\nSaliendo del programa... adios!\n");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\n\n\n--------------OPCION NO VALIDA-------------\n");
                        break;
                }
            } while (choice != MENU_EXIT_OPTION);
        } catch (Exception e) {
            System.out.println("[iniciarMenu] error: " + e.getMessage());
        }
    }

    public static void printGeneralMenu() {
        System.out.println("\n\n### Sistema Metro - Inicio ###");
        System.out.println("------------------------------");
        System.out.println("\nOpciones de creación de la red de metro y simulación de ejecución");
        System.out.println("\n1. Cargar información del sistema de metro");
        System.out.println("2. Visualizar estado actual del sistema de metro");
        System.out.println("3. Interactuar con el sistema de metro");
        System.out.println("4. Salir del programa");
        System.out.print("\nIngrese su opcion: ");
    }

    public static void printSpecificMenu1() {
        System.out.println("\n\n### Sistema Metro - Cargar información del sistema de metro ###");
        System.out.println("---------------------------------------------------------------");
        System.out.println("\nDefiniciones estructurales de su sistema subido desde archivos");
        System.out.println("\n1. Creación de una línea de metro básica (cargar archivo lineas.txt)");
        System.out.println("2. Combinaciones entre estaciones entre Líneas (cargar archivo combinaciones.txt)");
        System.out.println("3. Definición de trenes con distintos número de carros (cargar archivo trenes.txt)");
        System.out.println("4. Conductores asignados a una Línea (cargar archivo conductores.txt)");
        System.out.println("5. Retorno al menú de Inicio");
        System.out.print("\nIngrese su opcion: ");
    }

    public static void printSpecificMenu2() {
        System.out.println("\n\n### Sistema Metro - Visualización del estado actual del sistema de metros ###");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nDefiniciones estructurales de su sistema subido desde archivos");
        System.out.println("\n1. Desplegar en pantalla el estado actual de la red de metros.");
        System.out.println("2. Retorno al menú de Inicio");
        System.out.println("\nIngrese su opcion: ");
    }

    public static void printSpecificMenu3() {
        System.out.println("\n\n### Sistema Metro - Interactuar con el sistema de metros ###");
        System.out.println("------------------------------------------------------------");
        System.out.println("\nA través de las siguientes opciones usted puede interactuar con la red de metros " +
                "cargada previamente por archivos de texto.");
        System.out.println("\n1. lineLength: obtener el largo total de una línea.");
        System.out.println("2. lineSectionLength: determinar el tracto entre una estación origen y final.");
        System.out.println("3. lineCost: determinar el costo total de recorrer una línea.");
        System.out.println("4. lineSectionCost: determinar el costo de un trayecto entre estación origen y final.");
        System.out.println("5. isLine: verificar si una línea cumple con las restricciones especificadas.");
        System.out.println("6. Train - addCar: añade un carro de pasajeros a un tren en la posición establecida.");
        System.out.println("7. Train - removeCar: remueve un carro de pasajeros de un tren en la posición establecida");
        System.out.println("8. Train - isTrain: verifica si un tren cumple con las especificaciones de los carros de pasajeros.");
        System.out.println("9. Train - fetchCapacity: entrega la capacidad máxima de pasajeros de un tren.");
        System.out.println("10. Subway - whereIsTrain: determina la ubicación de un tren a partir de una hora indicada del día.");
        System.out.println("11. Subway - trainPath: armar el recorrido del tren a partir de una hora especificada y que " +
                "retorna la lista de estaciones futuras por recorrer.");
        System.out.print("\nIngrese su opcion: ");
    }
}
