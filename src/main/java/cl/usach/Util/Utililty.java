package cl.usach.Util;

import cl.usach.Model.*;

import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Utililty {

    /**
     *
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
     *
     * @param sectionList
     * @return
     */
    public boolean isSectionCommunicates(List<Section> sectionList) {
        var name = sectionList.get(0).getPoint2().getName();
        var flag = true;
        for (int i = 1; i < sectionList.size(); i++) {
            if(!sectionList.get(i).getPoint1().getName().equals(name)){
                flag = false;
                break;
            }
            name = sectionList.get(i).getPoint2().getName();
        }
        return flag;
    }

    /**
     *
     * @param stationList
     * @return
     */
    public boolean isTerminal(List<Station> stationList) {
        Station firstStation = stationList.get(0);
        Station secondStation = stationList.get(stationList.size() - 1);
        return firstStation.getStationType().equals(secondStation.getStationType());
    }

    /**
     *
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
     *
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
            return  "[" +
                    "id=" +  String.valueOf(station.getId()) +
                    ", name=" + station.getName() +
                    ", stationType=" + station.getStationType() +
                    ", stopTime=" + String.valueOf(station.getStopTime()) + "]";
    }

    public String sectionToString(Section section) {
        return "   [" +
                    "station1 = " +  stationToString(section.getPoint1()) +
                    ", station2 = " + stationToString(section.getPoint2()) +
                    ", distance = " + section.getDistance() +
                    ", cost = " + section.getCost() + "],\n";
    }

    public String lineToString(Line line) {
        String sectionsString = "";
        String trainsString = "";
        for (Section section : line.getSections()) {
            sectionsString = sectionsString.concat(sectionToString(section));
        }
        for (Train train : line.getTrains()) {
            trainsString = trainsString.concat(trainToString(train));
        }

        return " [id = " +  line.getId() +
                ", name = " + line.getName() +
                ", railType = " + line.getRailType() + ",\n" +
                "  sections = [" + '\n' + sectionsString + "  ]," + '\n' +
                "  trains = [" + "\n  " + trainsString + "  ]," + '\n';
    }

    public String passengerCarToString(PassengerCar passengerCar) {
        return "    [" +
                "id=" +  passengerCar.getId() +
                ", passengerCapacity=" + passengerCar.getPassengerCapacity() +
                ", model=" + passengerCar.getModel() +
                ", trainMaker=" + passengerCar.getTrainMaker() +
                ", carType=" + passengerCar.getCarType() + "],\n";
    }

    public String trainToString(Train train) {
        String passengerCarsString= "";
        for (PassengerCar passengerCar : train.getCarList()) {
            passengerCarsString = passengerCarsString.concat(passengerCarToString(passengerCar));
        }

        return " [id = " +  train.getId() +
                ", trainMaker = " + train.getTrainMaker() +
                ", speed = " + train.getSpeed() +
                ", stationStayTime = " + train.getStationStayTime() + ",\n" +
                "   carList = [" + '\n' + passengerCarsString + "   ]," + '\n' +
                "   driverAssignment = " + ((train.getDriverAssignment() != null) ? "[" + driverAssignmentToString(train.getDriverAssignment()) + "]" : "[ ]") + '\n';
    }

    public String driverToString(Driver driver) {
        return  " [" +
                "id = " + driver.getId() +
                ", name=" + driver.getName() +
                ", trainMaker=" + driver.getTrainMaker() + "],\n";
    }

    public String driverAssignmentToString(DriverAssignment driverAssignment) {
        return '\n' +
                "    driver = " +  driverToString(driverAssignment.getDriver()) +
                "    departureTime = " + driverAssignment.getDepartureTime()+ ",\n" +
                "    departureStation = " + stationToString(driverAssignment.getDepartureStation())+ ",\n" +
                "    arrivalStation = " + stationToString(driverAssignment.getArrivalStation());
    }
    
}
