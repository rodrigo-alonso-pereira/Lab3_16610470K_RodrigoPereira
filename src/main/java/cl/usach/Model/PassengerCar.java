package cl.usach.Model;

public class PassengerCar {

    private int id;
    private int passengerCapacity;
    private String model;
    private String trainMaker;
    private CarType carType;

    public PassengerCar(int id, int passengerCapacity, String model, String trainMaker, CarType carType) {
        this.id = id;
        this.passengerCapacity = passengerCapacity;
        this.model = model;
        this.trainMaker = trainMaker;
        this.carType = carType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPassengerCapacity() {
        return passengerCapacity;
    }

    public void setPassengerCapacity(int passengerCapacity) {
        this.passengerCapacity = passengerCapacity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTrainMaker() {
        return trainMaker;
    }

    public void setTrainMaker(String trainMaker) {
        this.trainMaker = trainMaker;
    }

    public CarType getCarType() {
        return carType;
    }

    public void setCarType(CarType carType) {
        this.carType = carType;
    }

    @Override
    public String toString() {
        return "PassengerCar{" +
                "id=" + id +
                ", passengerCapacity=" + passengerCapacity +
                ", model='" + model + '\'' +
                ", trainMaker='" + trainMaker + '\'' +
                ", carType=" + carType +
                '}';
    }
}
