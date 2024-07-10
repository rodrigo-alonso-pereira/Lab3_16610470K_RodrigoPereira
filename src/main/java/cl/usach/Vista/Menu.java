package cl.usach.Vista;

import cl.usach.Model.*;
import cl.usach.Repository.TxtRepository;
import cl.usach.Util.Utililty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Menu {

    static Utililty util = new Utililty();
    static TxtRepository txtRepository = new TxtRepository();
    static Subway subway;
    static ArrayList<Station> stationList = new ArrayList<>();
    static ArrayList<Section> sectionList = new ArrayList<>();
    static ArrayList<Line> lineList = new ArrayList<>();
    static ArrayList<PassengerCar> passengerCarList = new ArrayList<>();
    static ArrayList<Train> trainList = new ArrayList<>();
    static ArrayList<Driver> driverList = new ArrayList<>();
    static DateFormat sdf = new SimpleDateFormat("hh:mm aa");

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
                        menuCase1();
                    case 2:
                        menuCase2();
                    case 3:
                        printSpecificMenu3();
                    case 4:
                        System.out.println("\nSaliendo de la red de metro... adios!\n");
                        System.exit(0);
                    default:
                        System.out.println("\n\n\n--------------OPCION NO VALIDA-------------\n");
                }
            } while (choice != MENU_EXIT_OPTION);
        } catch (Exception e) {
            System.out.println("[iniciarMenu] error: " + e.getMessage());
        }
    }

    private static void menuCase1() {
        try {
            Scanner input = new Scanner(System.in);
            int choiceCase1;
            do {
                printSpecificMenu1();
                choiceCase1 = input.nextInt();
                switch (choiceCase1) {
                    case 1:
                        subway = new Subway(0, "Metro de Santiago");
                        stationList = txtRepository.importStation();
                        sectionList = txtRepository.importSection();
                        lineList = txtRepository.importLine();
                        subway.addLine(lineList);
                        break;
                    case 2:
                        lineList.addAll(txtRepository.importCombinationLine());
                        subway.addLine(txtRepository.importCombinationLine());
                        break;
                    case 3:
                        passengerCarList = txtRepository.importPassengerCar();
                        trainList = txtRepository.importTrain();
                        subway.addTrain(trainList); // Se agregan lista de train a line
                        // Asignacion de trenes a lineas
                        subway.assignTrainToLine(util.findTrain(subway, 1), util.findLine(subway, 1));
                        subway.assignTrainToLine(util.findTrain(subway, 0), util.findLine(subway, 2));
                        subway.assignTrainToLine(util.findTrain(subway, 1), util.findLine(subway, 6)); // t1 ya esta asignado
                        subway.assignTrainToLine(util.findTrain(subway, 2), util.findLine(subway, 6)); // t2 no cumple con ser tren valido
                        subway.assignTrainToLine(util.findTrain(subway, 3), util.findLine(subway, 6));
                        subway.assignTrainToLine(util.findTrain(subway, 5), util.findLine(subway, 1));
                        subway.assignTrainToLine(util.findTrain(subway, 4), util.findLine(subway, 2));
                        break;
                    case 4:
                        driverList = txtRepository.importDriver();
                        subway.addDriver(driverList); // Se agregan drivers a subway
                        // Asignacion de driver a trenes
                        Date date1 = sdf.parse("01:30 pm");
                        Date date2 = sdf.parse("09:00 am");
                        Date date3 = sdf.parse("02:00 pm");
                        Date date4 = sdf.parse("06:00 am");
                        Date date5 = sdf.parse("10:00 pm");
                        subway.assignDriverToTrain(
                                util.findTrain(subway, 1),
                                util.findriver(subway, 1),
                                date1,
                                util.findStation(stationList, 10),
                                util.findStation(stationList, 8)); // No existe st10 en line 1
                        subway.assignDriverToTrain(
                                util.findTrain(subway, 1),
                                util.findriver(subway, 1),
                                date1,
                                util.findStation(stationList, 0),
                                util.findStation(stationList, 8));
                        subway.assignDriverToTrain(
                                util.findTrain(subway, 5),
                                util.findriver(subway, 3),
                                date2,
                                util.findStation(stationList, 0),
                                util.findStation(stationList, 8));
                        subway.assignDriverToTrain(
                                util.findTrain(subway, 3),
                                util.findriver(subway, 0),
                                date3,
                                util.findStation(stationList, 20),
                                util.findStation(stationList, 24));
                        subway.assignDriverToTrain(
                                util.findTrain(subway, 4),
                                util.findriver(subway, 4),
                                date4,
                                util.findStation(stationList, 10),
                                util.findStation(stationList, 19));
                        subway.assignDriverToTrain(
                                util.findTrain(subway, 5),
                                util.findriver(subway, 2),
                                date5,
                                util.findStation(stationList, 10),
                                util.findStation(stationList, 13)); // Incompatible trainMaker ALSTOM != CAF
                        break;
                    case 5:
                        System.out.println("\nVolviendo a menu general...\n");
                        iniciarMenu();
                        break;
                    default:
                        System.out.println("\n\n\n--------------OPCION NO VALIDA-------------\n");
                }
            } while (true);
        } catch (Exception e) {
            System.out.println("[iniciarMenu] error: " + e.getMessage());
        }
    }

    private static void menuCase2() {
        try {
            Scanner input = new Scanner(System.in);
            int choiceCase2;
            do {
                printSpecificMenu2();
                choiceCase2 = input.nextInt();
                switch (choiceCase2) {
                    case 1:
                        System.out.println(subway.toString());
                        break;
                    case 2:
                        System.out.println("\nVolviendo a menu general...\n");
                        iniciarMenu();
                        break;
                    default:
                        System.out.println("\n\n\n--------------OPCION NO VALIDA-------------\n");
                }
            } while (true);
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
        System.out.println("\n1. Creación de una línea de metro básica");
        System.out.println("2. Combinaciones entre estaciones entre Líneas");
        System.out.println("3. Definición de trenes con distintos número de carros");
        System.out.println("4. Conductores asignados a una Línea");
        System.out.println("5. Retorno al menú de Inicio");
        System.out.print("\nIngrese su opcion: ");
    }

    public static void printSpecificMenu2() {
        System.out.println("\n\n### Sistema Metro - Visualización del estado actual del sistema de metros ###");
        System.out.println("-----------------------------------------------------------------------------");
        System.out.println("\nDefiniciones estructurales de su sistema subido desde archivos");
        System.out.println("\n1. Desplegar en pantalla el estado actual de la red de metros.");
        System.out.println("2. Retorno al menú de Inicio");
        System.out.print("\nIngrese su opcion: ");
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
