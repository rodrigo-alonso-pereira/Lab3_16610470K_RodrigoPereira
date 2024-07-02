package cl.usach.Model;

import java.util.List;

public class Train {

    private int id;
    private String trainMaker;
    private int speed;
    private int stationStayTime;
    private List<PassengerCar> carList;

    public Train(int id, String trainMaker, int speed, int stationStayTime, List<PassengerCar> carList) {
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

    public List<PassengerCar> getCarList() {
        return carList;
    }

    public void setCarList(List<PassengerCar> carList) {
        this.carList = carList;
    }

    @Override
    public String toString() {
        return "Train{" +
                "id=" + id +
                ", trainMaker='" + trainMaker + '\'' +
                ", speed=" + speed +
                ", stationStayTime=" + stationStayTime +
                ", carList=" + carList +
                '}';
    }
}
