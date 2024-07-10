package cl.usach.Vista;

import cl.usach.Model.*;
import cl.usach.Repository.TxtRepository;
import cl.usach.Service.LineServiceImpl;
import cl.usach.Service.TrainServiceImpl;
import cl.usach.Util.Utililty;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Menu {

    static Utililty util = new Utililty();
    static TxtRepository txtRepository = new TxtRepository();
    static LineServiceImpl lineService = new LineServiceImpl();
    static TrainServiceImpl trainService = new TrainServiceImpl();
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
        Scanner input = new Scanner(System.in);
        try {
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
                        menuCase3();
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
        input.close();
    }

    private static void menuCase1() {
        Scanner input = new Scanner(System.in);
        try {
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
                                util.findDriver(subway, 1),
                                date1,
                                util.findStation(stationList, 10),
                                util.findStation(stationList, 8)); // No existe st10 en line 1
                        subway.assignDriverToTrain(
                                util.findTrain(subway, 1),
                                util.findDriver(subway, 1),
                                date1,
                                util.findStation(stationList, 0),
                                util.findStation(stationList, 8));
                        subway.assignDriverToTrain(
                                util.findTrain(subway, 5),
                                util.findDriver(subway, 3),
                                date2,
                                util.findStation(stationList, 0),
                                util.findStation(stationList, 8));
                        subway.assignDriverToTrain(
                                util.findTrain(subway, 3),
                                util.findDriver(subway, 0),
                                date3,
                                util.findStation(stationList, 20),
                                util.findStation(stationList, 24));
                        subway.assignDriverToTrain(
                                util.findTrain(subway, 4),
                                util.findDriver(subway, 4),
                                date4,
                                util.findStation(stationList, 10),
                                util.findStation(stationList, 19));
                        subway.assignDriverToTrain(
                                util.findTrain(subway, 5),
                                util.findDriver(subway, 2),
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
        input.close();
    }

    private static void menuCase2() {
        Scanner input = new Scanner(System.in);
        try {
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
        input.close();
    }

    private static void menuCase3() {
        Scanner inputI = new Scanner(System.in); // Input para numeros
        Scanner inputS = new Scanner(System.in); // Input para Strings
        try {
            int choiceCase3;
            do {
                printSpecificMenu3();
                choiceCase3 = inputI.nextInt();
                switch (choiceCase3) {
                    case 1:
                        try {
                            int lineId;
                            System.out.println("\n---------------LARGO LINEA----------------\n");
                            System.out.println("Lista de Lineas disponibles:");
                            for (Line line : subway.getLines()) {
                                System.out.println("-> " + line.toString());
                            }
                            System.out.print("\nIngresa el id (number) de la linea que desea consultar su largo: ");
                            lineId = inputI.nextInt();
                            Line line = util.findLine(subway, lineId);
                            if (line != null)
                                // lineId = 1, largo = 18.4
                                System.out.println("El largo de la linea de id=" + lineId + " es: " + line.lineLength() + " [km]");
                            else
                                System.out.println("Vuelva a intentarlo");
                        } catch (Exception e) {
                            System.out.println("[opcion3.lineLength] error: " + e.getMessage());
                        }
                        break;
                    case 2:
                        try {
                            String station1Name;
                            String station2Name;
                            System.out.println("\n---------------LARGO LINEA ENTRE ESTACIONES----------------\n");
                            System.out.println("Lista de Lineas disponibles:");
                            for (Line line : subway.getLines()) {
                                System.out.println("-> " + line.toString());
                            }
                            System.out.print("\nIngresa el nombre de la estacion inicial: ");
                            station1Name = inputS.nextLine().trim();
                            System.out.print("\nIngresa el nombre de la estacion final: ");
                            station2Name = inputS.nextLine().trim();
                            Line line = util.findLineByStations(subway, station1Name, station2Name);
                            // station1Name = USACH, station2Name = La Moneda -> largo = 11.4
                            System.out.println("El largo de la linea entre la estacion '" + station1Name + "' y estacion '"
                                    + station2Name + "' es: " + line.lineSectionLength(station1Name, station2Name) + " [km]");
                        } catch (Exception e) {
                            System.out.println("[opcion3.lineLength] error: " + e.getMessage());
                        }
                        break;
                    case 3:
                        try {
                            int lineId;
                            System.out.println("\n---------------COSTO LINEA----------------\n");
                            System.out.println("Lista de Lineas disponibles:");
                            for (Line line : subway.getLines()) {
                                System.out.println("-> " + line.toString());
                            }
                            System.out.print("\nIngresa el id (number) de la linea que desea consultar su costo: ");
                            lineId = inputI.nextInt();
                            Line line = util.findLine(subway, lineId);
                            if (line != null)
                                // lineId = 1, costo = 335
                                System.out.println("El costo de la linea de id=" + lineId + " es: " + line.lineCost() + "K USD");
                            else
                                System.out.println("Vuelva a intentarlo");
                        } catch (Exception e) {
                            System.out.println("[opcion3.lineCost] error: " + e.getMessage());
                        }
                        break;
                    case 4:
                        try {
                            String station1Name;
                            String station2Name;
                            System.out.println("\n---------------COSTO LINEA ENTRE ESTACIONES----------------\n");
                            System.out.println("Lista de Lineas disponibles:");
                            for (Line line : subway.getLines()) {
                                System.out.println("-> " + line.toString());
                            }
                            System.out.print("\nIngresa el nombre de la estacion inicial: ");
                            station1Name = inputS.nextLine().trim();
                            System.out.print("\nIngresa el nombre de la estacion final: ");
                            station2Name = inputS.nextLine().trim();
                            Line line = util.findLineByStations(subway, station1Name, station2Name);
                            // station1Name = USACH, station2Name = La Moneda -> costo = 225
                            System.out.println("El costo de la linea entre la estacion '" + station1Name + "' y estacion '"
                                    + station2Name + "' es: " + line.lineSectionCost(station1Name, station2Name) + "K USD");
                        } catch (Exception e) {
                            System.out.println("[opcion3.lineSectionCost] error: " + e.getMessage());
                        }
                        break;
                    case 5:
                        try {
                            int lineId;
                            System.out.println("\n---------------¿ES LINEA?----------------\n");
                            System.out.println("Lista de Lineas disponibles:");
                            for (Line line : subway.getLines()) {
                                System.out.println("-> " + line.toString());
                            }
                            System.out.print("\nIngresa el id (number) de la linea que desea evaluar: ");
                            lineId = inputI.nextInt();
                            Line line = util.findLine(subway, lineId);
                            if (line != null)
                                // lineId = 1, true
                                System.out.println("La linea de id=" + lineId + (lineService.isLine(line)? " es una linea valida" : "no es una linea valida"));
                            else
                                System.out.println("Vuelva a intentarlo");
                        } catch (Exception e) {
                            System.out.println("[opcion3.isLine] error: " + e.getMessage());
                        }
                        break;
                    case 6:
                        try {
                            int passengerCarId;
                            int trainId;
                            int position;
                            System.out.println("\n---------------AGREGAR CARRO A TREN----------------\n");
                            System.out.println("Lista de Carros disponibles:");
                            for (PassengerCar passengerCar : passengerCarList) {
                                System.out.println("-> " + passengerCar.toString());
                            }
                            System.out.print("\nIngresa el id (number) del carro que desea agregar: ");
                            passengerCarId = inputI.nextInt();
                            PassengerCar passengerCar = util.findPassengerCar(passengerCarList, passengerCarId);

                            System.out.println("\nLista de Trenes disponibles:");
                            for (Train train : trainList) {
                                System.out.println("-> " + train.toString());
                            }
                            System.out.print("\nIngresa el id (number) del tren al cual desea agregar el carro: ");
                            trainId = inputI.nextInt();
                            Train train = util.findTrain(subway, trainId);

                            System.out.print("\nIngrese la posicion donde desea ser agregado el carro: ");
                            position = inputI.nextInt();

                            if (passengerCar != null || train != null) {
                                train.addCar(passengerCar, position);
                                System.out.println("Se agrego con exito el carro de id=" + passengerCarId
                                        + " en la posicion [" + position + "]" + " a el tren de id=" + trainId);
                            } else
                                System.out.println("Vuelva a intentarlo");
                        } catch (Exception e) {
                            System.out.println("[opcion3.addCar] error: " + e.getMessage());
                        }
                        break;
                    case 7:
                        try {
                            int trainId;
                            int position;
                            System.out.println("\n---------------REMOVER CARRO A TREN----------------\n");

                            System.out.println("Lista de Trenes disponibles:");
                            for (Train train : trainList) {
                                System.out.println("-> " + train.toString());
                            }
                            System.out.print("\nIngresa el id (number) del tren al cual desea remocer el carro: ");
                            trainId = inputI.nextInt();
                            Train train = util.findTrain(subway, trainId);

                            System.out.print("\nIngrese la posicion donde desea eliminar el carro: ");
                            position = inputI.nextInt();

                            if (train != null) {
                                trainService.removeCar(train, position);
                                System.out.println("Se elimino con exito el carro en la posicion [" + position + "]"
                                        + " a el tren de id=" + trainId);
                            } else
                                System.out.println("Vuelva a intentarlo");
                        } catch (Exception e) {
                            System.out.println("[opcion3.removeCar] error: " + e.getMessage());
                        }
                        break;
                    case 8:
                        try {
                            int trainId;
                            System.out.println("\n---------------¿ES TREN?----------------\n");
                            System.out.println("Lista de Trenes disponibles:");
                            for (Train train : trainList) {
                                System.out.println("-> " + train.toString());
                            }
                            System.out.print("\nIngresa el id (number) del tren que desea evaluar: ");
                            trainId = inputI.nextInt();
                            Train train = util.findTrain(subway, trainId);
                            if (train != null)
                                // trainId = 0, true
                                System.out.println("El tren de id=" + trainId + (trainService.isTrain(train)? " es un tren valido" : " no es un tren valido"));
                            else
                                System.out.println("Vuelva a intentarlo");
                        } catch (Exception e) {
                            System.out.println("[opcion3.isTrain] error: " + e.getMessage());
                        }
                        break;
                    case 9:
                        try {
                            int trainId;
                            System.out.println("\n---------------CAPACIDAD DEL TREN----------------\n");
                            System.out.println("Lista de Trenes disponibles:");
                            for (Train train : trainList) {
                                System.out.println("-> " + train.toString());
                            }
                            System.out.print("\nIngresa el id (number) del tren que desea obtener su capacidad: ");
                            trainId = inputI.nextInt();
                            Train train = util.findTrain(subway, trainId);
                            if (train != null)
                                // trainId = 0 -> capacidad = 530
                                System.out.println("El tren de id=" + trainId + " tiene una capacidad total de: " + train.fetchCapacity() + " pasajeros");
                            else
                                System.out.println("Vuelva a intentarlo");
                        } catch (Exception e) {
                            System.out.println("[opcion3.fetchCapacity] error: " + e.getMessage());
                        }
                        break;
                    case 10:
                        try {
                            int trainId;
                            String date;
                            System.out.println("\n---------------¿DONDE ESTA EL TREN?----------------\n");
                            System.out.println("Lista de Trenes disponibles:");
                            for (Train train : trainList) {
                                System.out.println("-> " + train.toString());
                            }
                            System.out.print("\nIngresa el id (number) del tren que desea saber su ubicacion: ");
                            trainId = inputI.nextInt();
                            var flag = true;

                            do {
                                System.out.print("\nIngresa la hora de consulta (en formato [hh:mm am/pm] 0 <= hh <= 12) " +
                                        "para el tren de id=" + trainId + " : ");
                                date = inputS.nextLine();
                                if (util.isValidTimeFormat(date)) {
                                    flag = false;
                                } else {
                                    System.out.println("Fecha ingresada "+ date + " no valida, vuelva a intentarlo");
                                }
                            } while (flag);
                            Date dateFormat = sdf.parse(date);
                            //trainId = 1 , hora = 01:40 pm -> Republica
                            System.out.println(subway.whereIsTrain(trainId, dateFormat));
                        } catch (Exception e) {
                            System.out.println("[opcion3.whereIsTrain] error: " + e.getMessage());
                        }
                        break;
                    case 11:
                        try {
                            int trainId;
                            String date;
                            System.out.println("\n---------------¿CUAL ES EL CAMINO FUTURO DEL TREN?----------------\n");
                            System.out.println("Lista de Trenes disponibles:");
                            for (Train train : trainList) {
                                System.out.println("-> " + train.toString());
                            }
                            System.out.print("\nIngresa el id (number) del tren que desea saber su ubicacion: ");
                            trainId = inputI.nextInt();
                            var flag = true;

                            do {
                                System.out.print("\nIngresa la hora de consulta (en formato [hh:mm am/pm] 0 <= hh <= 12) " +
                                        "para el tren de id=" + trainId + " : ");
                                date = inputS.nextLine();
                                if (util.isValidTimeFormat(date)) {
                                    flag = false;
                                } else {
                                    System.out.println("Fecha ingresada "+ date + " no valida, vuelva a intentarlo");
                                }
                            } while (flag);
                            Date dateFormat = sdf.parse(date);
                            //trainId = 1 , hora = 01:40 pm -> Republica
                            System.out.println("Las estaciones que tiene que recorrer el tren de id=" + trainId + " desde las " + date + " son:");
                            for (Station station : subway.trainPath(trainId, dateFormat)) {
                                System.out.print(" -> " + station.getName());
                            }
                        } catch (Exception e) {
                            System.out.println("[opcion3.trainPath] error: " + e.getMessage());
                        }
                        break;
                    case 12:
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
        inputI.close();
        inputS.close();
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
        System.out.println("12. Retorno al menú de Inicio");
        System.out.print("\nIngrese su opcion: ");
    }
}
