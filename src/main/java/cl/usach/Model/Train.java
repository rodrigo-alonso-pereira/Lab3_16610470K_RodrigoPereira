package cl.usach.Model;

import java.util.ArrayList;
public class Train {

    private int id;
    private String trainMaker;
    private int speed;
    private int stationStayTime;
    private ArrayList<PassengerCar> carList;

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

    // Metodos propios de train

    /**
     *
     * @param car
     */
    // TODO: Revisar las excepciones cuando posicion esta fuera del tamaño de lista
    public void addCar(PassengerCar car, int position) {
        carList.add(position, car);
    }
}
