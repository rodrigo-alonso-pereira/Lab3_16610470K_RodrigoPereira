package cl.usach.Model;

import java.util.ArrayList;
import java.util.stream.Stream;

public class Train {

    private int id;
    private String trainMaker;
    private int speed;
    private int stationStayTime;
    private ArrayList<PassengerCar> carList;
    private DriverAssignment driverAssignment;

    public Train() {
    }

    public Train(int id, String trainMaker, int speed, int stationStayTime, ArrayList<PassengerCar> carList) {
        this.id = id;
        this.trainMaker = trainMaker;
        this.speed = speed;
        this.stationStayTime = stationStayTime;
        this.carList = carList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrainMaker() {
        return trainMaker;
    }

    public void setTrainMaker(String trainMaker) {
        this.trainMaker = trainMaker;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getStationStayTime() {
        return stationStayTime;
    }

    public void setStationStayTime(int stationStayTime) {
        this.stationStayTime = stationStayTime;
    }

    public ArrayList<PassengerCar> getCarList() {
        return carList;
    }

    public void setCarList(ArrayList<PassengerCar> carList) {
        this.carList = carList;
    }

    public DriverAssignment getDriverAssignment() {
        return driverAssignment;
    }

    public void setDriverAssignment(DriverAssignment driverAssignment) {
        this.driverAssignment = driverAssignment;
    }

    @Override
    public String toString() {
        return "Train_[" +
                "id=" + id +
                ", trainMaker='" + trainMaker + '\'' +
                ", speed=" + speed +
                ", stationStayTime=" + stationStayTime +
                ", carList=" + carList +
                ", driverAssignment=" + driverAssignment +
                ']';
    }

    // Metodos propios de train

    /**
     *
     * @param car
     */
    public void addCar(PassengerCar car, int position) {
        if (carList.size() >= position) {
            carList.add(position, car);
        } else
            System.out.println("[addCar] La posicion elejida [" + position + "] no es valida");
    }

    public int fetchCapacity() {
        return carList.stream().flatMap(e -> Stream.of(e.getPassengerCapacity())).reduce(0, Integer::sum);
    }
}
