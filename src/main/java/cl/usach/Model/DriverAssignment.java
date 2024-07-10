package cl.usach.Model;

import java.util.Date;

public class DriverAssignment {

    private Driver driver;
    private Date departureTime;
    private Station departureStation;
    private Station arrivalStation;

    public DriverAssignment(Driver driver, Date departureTime, Station departureStation, Station arrivalStation) {
        this.driver = driver;
        this.departureTime = departureTime;
        this.departureStation = departureStation;
        this.arrivalStation = arrivalStation;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Station getDepartureStation() {
        return departureStation;
    }

    public void setDepartureStation(Station departureStation) {
        this.departureStation = departureStation;
    }

    public Station getArrivalStation() {
        return arrivalStation;
    }

    public void setArrivalStation(Station arrivalStation) {
        this.arrivalStation = arrivalStation;
    }

    @Override
    public String toString() {
        return "TrainAssignment_[" +
                "driver=" + driver +
                ", departureTime=" + departureTime +
                ", departureStation=" + departureStation +
                ", arrivalStation=" + arrivalStation +
                ']';
    }
}
