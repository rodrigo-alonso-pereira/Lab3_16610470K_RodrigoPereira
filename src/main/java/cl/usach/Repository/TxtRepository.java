package cl.usach.Repository;

import cl.usach.Model.*;
import cl.usach.Util.Utililty;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;

public class TxtRepository extends Repository {
    final String pathStation = "src/main/java/resources/db/station.txt";
    final String pathSection = "src/main/java/resources/db/section.txt";
    final String pathLine = "src/main/java/resources/db/line.txt";
    final String pathCombinationLine = "src/main/java/resources/db/combinationLine.txt";
    final String pathPassengerCar = "src/main/java/resources/db/passengerCar.txt";
    final String pathTrain = "src/main/java/resources/db/train.txt";
    final String pathDriver = "src/main/java/resources/db/driver.txt";
    static ArrayList<Station> stations;
    static ArrayList<Section> sections;
    static ArrayList<Line> lines;
    static ArrayList<PassengerCar> passengerCars;
    static ArrayList<Train> trains;
    static ArrayList<Driver> drivers;
    static Utililty util = new Utililty();

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Station> importStation() {
        String[] arrayData;
        stations = new ArrayList<>();
        try {
            File archivo = new File(pathStation);
            if (archivo.exists()) {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String data = br.readLine();

                while (data != null) {
                    Station station = new Station();
                    arrayData = data.split(",");
                    station.setId(Integer.parseInt(arrayData[0]));
                    station.setName(arrayData[1]);
                    station.setStationType(StationType.valueOf(arrayData[2]));
                    station.setStopTime(Integer.parseInt(arrayData[3]));
                    stations.add(station);
                    data = br.readLine();
                }
                System.out.println("[importStation] Stations cargados correctamente");
                br.close();
            } else {
                System.out.println("[importStation] Archivo no encontrado");
            }
        } catch (Exception e) {
            System.out.println("[importStation] error: " + e.getMessage());
        }
        return stations;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Section> importSection() {
        String[] arrayData;
        sections = new ArrayList<>();
        try {
            File archivo = new File(pathSection);
            if (archivo.exists()) {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String data = br.readLine();

                while (data != null) {
                    Section section = new Section();
                    arrayData = data.split(",");
                    section.setPoint1(util.findStation(stations, Integer.parseInt(arrayData[0])));
                    section.setPoint2(util.findStation(stations, Integer.parseInt(arrayData[1])));
                    section.setDistance(Double.parseDouble(arrayData[2]));
                    section.setCost(Integer.parseInt(arrayData[3]));
                    sections.add(section);
                    data = br.readLine();
                }
                System.out.println("[importSection] Sections cargados correctamente");
                br.close();
            } else {
                System.out.println("[importSection] Archivo no encontrado");
            }
        } catch (Exception e) {
            System.out.println("[importSection] error: " + e.getMessage());
        }
        return sections;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Line> importLine() {
        String[] arrayData;
        lines = new ArrayList<>();
        ArrayList<Section> sectionListLine0 = new ArrayList<>();
        ArrayList<Section> sectionListLine1 = new ArrayList<>(Arrays.asList(sections.get(0), sections.get(1), sections.get(2),
                sections.get(3), sections.get(4), sections.get(5), sections.get(6), sections.get(7)));
        try {
            File archivo = new File(pathLine);
            if (archivo.exists()) {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String data = br.readLine();
                while (data != null) {
                    Line line = new Line();
                    arrayData = data.split(",");
                    line.setId(Integer.parseInt(arrayData[0]));
                    line.setName(arrayData[1]);
                    line.setRailType(arrayData[2]);
                    switch (arrayData[3]) {
                        case "sectionListLine0":
                            line.setSections(sectionListLine0);
                            break;
                        case "sectionListLine1":
                            line.setSections(sectionListLine1);
                            break;
                    }
                    lines.add(line);
                    data = br.readLine();
                }
                System.out.println("[importLine] Lines cargados correctamente");
                br.close();
            } else {
                System.out.println("[importLine] Archivo no encontrado");
            }
        } catch (Exception e) {
            System.out.println("[importLine] error: " + e.getMessage());
        }
        return lines;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Line> importCombinationLine() {
        String[] arrayData;
        lines = new ArrayList<>();
        ArrayList<Section> sectionListLine2 = new ArrayList<>(Arrays.asList(sections.get(8), sections.get(9),
                sections.get(10), sections.get(11), sections.get(12), sections.get(13), sections.get(14),
                sections.get(15),sections.get(16)));
        ArrayList<Section> sectionListLine6 = new ArrayList<>(Arrays.asList(sections.get(17), sections.get(18),
                sections.get(19), sections.get(20)));
        try {
            File archivo = new File(pathCombinationLine);
            if (archivo.exists()) {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String data = br.readLine();
                while (data != null) {
                    Line line = new Line();
                    arrayData = data.split(",");
                    line.setId(Integer.parseInt(arrayData[0]));
                    line.setName(arrayData[1]);
                    line.setRailType(arrayData[2]);
                    switch (arrayData[3]) {
                        case "sectionListLine2":
                            line.setSections(sectionListLine2);
                            break;
                        case "sectionListLine6":
                            line.setSections(sectionListLine6);
                            break;
                    }
                    lines.add(line);
                    data = br.readLine();
                }
                System.out.println("[importCombinationLine] CombinationsLines cargados correctamente");
                br.close();
            } else {
                System.out.println("[importCombinationLine] Archivo no encontrado");
            }
        } catch (Exception e) {
            System.out.println("[importCombinationLine] error: " + e.getMessage());
        }
        return lines;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<PassengerCar> importPassengerCar() {
        String[] arrayData;
        passengerCars = new ArrayList<>();
        try {
            File archivo = new File(pathPassengerCar);
            if (archivo.exists()) {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String data = br.readLine();

                while (data != null) {
                    PassengerCar passengerCar = new PassengerCar();
                    arrayData = data.split(",");
                    passengerCar.setId(Integer.parseInt(arrayData[0]));
                    passengerCar.setPassengerCapacity(Integer.parseInt(arrayData[1]));
                    passengerCar.setModel(arrayData[2]);
                    passengerCar.setTrainMaker(arrayData[3]);
                    passengerCar.setCarType(CarType.valueOf(arrayData[4]));
                    passengerCars.add(passengerCar);
                    data = br.readLine();
                }
                System.out.println("[importPassengerCar] PassengerCars cargados correctamente");
                br.close();
            } else {
                System.out.println("[importPassengerCar] Archivo no encontrado");
            }
        } catch (Exception e) {
            System.out.println("[importPassengerCar] error: " + e.getMessage());
        }
        return passengerCars;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Train> importTrain() {
        String[] arrayData;
        trains = new ArrayList<>();
        ArrayList<PassengerCar> passengerCarList0 = new ArrayList<>(Arrays.asList(passengerCars.get(0),
                passengerCars.get(1), passengerCars.get(2), passengerCars.get(3), passengerCars.get(4)));
        ArrayList<PassengerCar> passengerCarList1 = new ArrayList<>(Arrays.asList(passengerCars.get(5),
                passengerCars.get(6), passengerCars.get(7), passengerCars.get(8)));
        ArrayList<PassengerCar> passengerCarList2 = new ArrayList<>();
        ArrayList<PassengerCar> passengerCarList3 = new ArrayList<>(Arrays.asList(passengerCars.get(9),
                passengerCars.get(10), passengerCars.get(11), passengerCars.get(12), passengerCars.get(13)));
        try {
            File archivo = new File(pathTrain);
            if (archivo.exists()) {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String data = br.readLine();

                while (data != null) {
                    Train train = new Train();
                    arrayData = data.split(",");
                    train.setId(Integer.parseInt(arrayData[0]));
                    train.setTrainMaker(arrayData[1]);
                    train.setSpeed(Integer.parseInt(arrayData[2]));
                    train.setStationStayTime(Integer.parseInt(arrayData[3]));
                    switch (arrayData[4]) {
                        case "passengerCarList0":
                            train.setCarList(passengerCarList0);
                            break;
                        case "passengerCarList1":
                            train.setCarList(passengerCarList1);
                            break;
                        case "passengerCarList2":
                            train.setCarList(passengerCarList2);
                            break;
                        case "passengerCarList3":
                            train.setCarList(passengerCarList3);
                            break;
                    }
                    trains.add(train);
                    data = br.readLine();
                }
                System.out.println("[importTrain] Trains cargados correctamente");
                br.close();
            } else {
                System.out.println("[importTrain] Archivo no encontrado");
            }
        } catch (Exception e) {
            System.out.println("[importTrain] error: " + e.getMessage());
        }
        return trains;
    }

    /**
     *
     * @return
     */
    @Override
    public ArrayList<Driver> importDriver() {
        String[] arrayData;
        drivers = new ArrayList<>();
        try {
            File archivo = new File(pathDriver);
            if (archivo.exists()) {
                FileReader fr = new FileReader(archivo);
                BufferedReader br = new BufferedReader(fr);
                String data = br.readLine();

                while (data != null) {
                    Driver driver = new Driver();
                    arrayData = data.split(",");
                    driver.setId(Integer.parseInt(arrayData[0]));
                    driver.setName(arrayData[1]);
                    driver.setTrainMaker(arrayData[2]);
                    drivers.add(driver);
                    data = br.readLine();
                }
                System.out.println("[importDriver] Drivers cargados correctamente");
                br.close();
            } else {
                System.out.println("[importDriver] Archivo no encontrado");
            }
        } catch (Exception e) {
            System.out.println("[importDriver] error: " + e.getMessage());
        }
        return drivers;
    }
}
