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
    
}
