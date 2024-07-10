package cl.usach.Util;

import cl.usach.Model.*;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Utililty {

    /**
     * @param list
     * @return
     */
    public boolean isRepeatElement(List<?> list) {
        if (list == null || list.isEmpty()) {
            return false;
        } else
            return list.stream().allMatch(new HashSet<>()::add);

    }

    /**
     * @param sectionList
     * @return
     */
    public boolean isSectionCommunicates(List<Section> sectionList) {
        var name = sectionList.get(0).getPoint2().getName();
        var flag = true;
        for (int i = 1; i < sectionList.size(); i++) {
            if (!sectionList.get(i).getPoint1().getName().equals(name)) {
                flag = false;
                break;
            }
            name = sectionList.get(i).getPoint2().getName();
        }
        return flag;
    }

    /**
     * @param stationList
     * @return
     */
    public boolean isTerminal(List<Station> stationList) {
        Station firstStation = stationList.get(0);
        Station secondStation = stationList.get(stationList.size() - 1);
        return firstStation.getStationType().equals(secondStation.getStationType());
    }

    /**
     * @param passengerCarList
     * @param trainMaker
     * @return
     */
    public boolean isValidtrainMaker(List<PassengerCar> passengerCarList, String trainMaker) {
        if (passengerCarList == null || passengerCarList.isEmpty() || trainMaker == null) {
            return false;
        } else
            return passengerCarList.stream().flatMap(e -> Stream.of(e.getTrainMaker())).distinct().count() <= 1;
    }

    /**
     * @param passengerCarList
     * @return
     */
    public boolean isValidTrainFormat(List<PassengerCar> passengerCarList) {
        if (passengerCarList == null || passengerCarList.isEmpty() || passengerCarList.size() < 2) {
            return false;
        } else {
            return passengerCarList.get(0).getCarType().equals(CarType.TERMINAL)
                    && passengerCarList.get(passengerCarList.size() - 1).getCarType().equals(CarType.TERMINAL)
                    && passengerCarList.subList(1, passengerCarList.size() - 1).stream().allMatch(e -> e.getCarType().equals(CarType.CENTRAL));
        }
    }

    public String stationToString(Station station) {
        return "[" +
                "id=" + station.getId() +
                ", name=" + station.getName() +
                ", stationType=" + station.getStationType() +
                ", stopTime=" + station.getStopTime() +
                "]";
    }

    public String sectionToString(Section section) {
        return "   [" +
                "station1 = " + stationToString(section.getPoint1()) +
                ", station2 = " + stationToString(section.getPoint2()) +
                ", distance = " + section.getDistance() +
                ", cost = " + section.getCost() + "],\n";
    }

    public String lineToString(Line line) {
        String sectionsString = "";
        String trainsString = "";
        if (line.getSections() != null) {
            for (Section section : line.getSections()) {
                sectionsString = sectionsString.concat(sectionToString(section));
            }
        }

        if (line.getTrains() != null) {
            for (Train train : line.getTrains()) {
                trainsString = trainsString.concat(trainToString(train));
            }
        }

        return " [id = " + line.getId() +
                ", name = " + line.getName() +
                ", railType = " + line.getRailType() + ",\n" +
                "  sections = [" + '\n' + sectionsString + "  ]," + '\n' +
                "  trains = [" + "\n  " + trainsString + "  ]," + '\n';
    }

    public String passengerCarToString(PassengerCar passengerCar) {
        return "    [" +
                "id=" + passengerCar.getId() +
                ", passengerCapacity=" + passengerCar.getPassengerCapacity() +
                ", model=" + passengerCar.getModel() +
                ", trainMaker=" + passengerCar.getTrainMaker() +
                ", carType=" + passengerCar.getCarType() + "],\n";
    }

    public String trainToString(Train train) {
        String passengerCarsString = "";
        for (PassengerCar passengerCar : train.getCarList()) {
            passengerCarsString = passengerCarsString.concat(passengerCarToString(passengerCar));
        }

        return " [id = " + train.getId() +
                ", trainMaker = " + train.getTrainMaker() +
                ", speed = " + train.getSpeed() +
                ", stationStayTime = " + train.getStationStayTime() + ",\n" +
                "   carList = [" + '\n' + passengerCarsString + "   ]," + '\n' +
                "   driverAssignment = " + ((train.getDriverAssignment() != null) ? "[" + driverAssignmentToString(train.getDriverAssignment()) + "]" : "[ ]") + '\n';
    }

    public String driverToString(Driver driver) {
        return " [" +
                "id = " + driver.getId() +
                ", name=" + driver.getName() +
                ", trainMaker=" + driver.getTrainMaker() + "],\n";
    }

    public String driverAssignmentToString(DriverAssignment driverAssignment) {
        return '\n' +
                "    driver = " + driverToString(driverAssignment.getDriver()) +
                "    departureTime = " + driverAssignment.getDepartureTime() + ",\n" +
                "    departureStation = " + stationToString(driverAssignment.getDepartureStation()) + ",\n" +
                "    arrivalStation = " + stationToString(driverAssignment.getArrivalStation());
    }

    public boolean stationBelongs(Station station, Train train, List<Line> lines) {
        return lines.stream()
                .flatMap(l -> l.getTrains().stream()
                        .filter(t -> t.getId() == train.getId())
                        .flatMap(t -> l.getSections().stream())
                )
                .anyMatch(s -> s.getPoint1().getId() == station.getId() || s.getPoint2().getId() == station.getId());
    }

    public double speedToMs(float speedKh) {
        return speedKh / 3.6;
    }

    public List<Station> getStationList(List<Section> sectionList) {
        return Stream.concat(
                sectionList.stream()
                        .flatMap(section -> Stream.of(section.getPoint1())),
                sectionList.stream()
                        .reduce((first, second) -> second)
                        .stream()
                        .map(Section::getPoint2)
        ).collect(Collectors.toList());
    }

    public long getTotalTime(Date start, Date end) {
        if (start.after(end)) // Verifica que Date end este despues de Date start
            return 0;
        else
            return (end.getTime() - start.getTime()) / 1000;
    }

    public Station trainMove(List<Section> sectionList, Long totalTime, Train train) {
        for (Section section : sectionList) {
            //System.out.println("totalTime en station " + section.getPoint1().getName() + ": " + totalTime + " sec");
            if (totalTime >= section.getPoint1().getStopTime()) { // Evaluar que tiempo total sea mayor que tiempo en estacion
                totalTime -= section.getPoint1().getStopTime(); // Restar tiempo de espera en estacion
                //System.out.println("totalTime despues de esperar " + section.getPoint1().getStopTime() + " sec en station " + section.getPoint1().getName() + ": " + totalTime + " sec");
                var timeNextStation = (section.getDistance() * 1000) / speedToMs(train.getSpeed()); // Calcular tiempo proxima estacion
                if (totalTime >= timeNextStation) { // Evaluar que el tiempo total sea mayor que tiempo de avance a siguiente estacion
                    totalTime -= (long) timeNextStation; // Restar tiempo de espera en estacion
                    //System.out.println("totalTime despues de demorar " + timeNextStation + " sec en avanzar a station " + section.getPoint2().getName() + ": " + totalTime + " sec");
                } else
                    return section.getPoint1();
            } else
                return section.getPoint1();

        }
        return sectionList.get(sectionList.size() - 1).getPoint2(); // Si termino y queda tiempo, retorna estacion final de linea
    }

    public Station findStation(List<Station> stations, int stationId) {
        Station station = stations.stream().filter(e -> e.getId() == stationId).findFirst().orElse(null);
        if (station != null) {
            return station;
        } else
            System.out.println("[findStation] Station de id=" + stationId + " no encontrado");
        return null;
    }

    public Train findTrain(Subway subway, int trainId) {
        Train train = subway.getTrains().stream().filter(e -> e.getId() == trainId).findFirst().orElse(null);
        if (train != null) {
            return train;
        } else
            System.out.println("[findTrain] Train de id=" + trainId + " no encontrado");
        return null;
    }

    public Line findLine(Subway subway, int lineId) {
        Line line = subway.getLines().stream().filter(e -> e.getId() == lineId).findFirst().orElse(null);
        if (line != null) {
            return line;
        } else
            System.out.println("[findLine] Line de id=" + lineId + " no encontrado");
        return null;
    }

    public Line findLineByStations(Subway subway, String stationName1, String stationName2) {
        for (Line line : subway.getLines()) {
            for (Section section : line.getSections()) {
                if (Objects.equals(section.getPoint1().getName(), stationName1) || Objects.equals(section.getPoint2().getName(), stationName1)) {
                    for (Section section2 : line.getSections()) {
                        if (Objects.equals(section2.getPoint1().getName(), stationName2) || Objects.equals(section2.getPoint2().getName(), stationName2))
                            return line;
                    }
                }
            }
        }
        System.out.println("[findLineByStation] No se encontraron estaciones con nombre '" + stationName1 + "' y '" + stationName2 + "' en ninguna linea asignada a subway '" + subway.getName() + "'" );
        return null;
    }

    public Driver findDriver(Subway subway, int driverId) {
        Driver driver = subway.getDrivers().stream().filter(e -> e.getId() == driverId).findFirst().orElse(null);
        if (driver != null) {
            return driver;
        } else
            System.out.println("[findLine] Line de id=" + driverId + " no encontrado");
        return null;
    }

    public PassengerCar findPassengerCar(List<PassengerCar> passengerCarList, int passengerCarId) {
        PassengerCar passengerCar = passengerCarList.stream().filter(p -> p.getId() == passengerCarId).findFirst().orElse(null);
        if (passengerCar != null) {
            return passengerCar;
        } else
            System.out.println("[findPassengerCar] PassengerCar de id=" + passengerCarId + " no encontrado");
        return null;
    }

    public boolean isValidTimeFormat(String date) {
        // Expresión regular para validar el formato "hh:mm am/pm"
        String timePattern = "^(0[0-9]|1[0-2]):([0-5][0-9])\\s?(am|pm)$";

        Pattern pattern = Pattern.compile(timePattern, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(date);

        return matcher.matches(); // true si coincido, false si no
    }


}
