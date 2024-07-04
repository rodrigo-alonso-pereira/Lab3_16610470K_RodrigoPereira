package cl.usach.Model;

import java.util.ArrayList;
import java.util.List;

public class Subway{

    private int id;
    private String name;
    private List<Line> lines;
    private List<Train> trains;
    private List<Driver> drivers;

    public Subway(int id, String name) {
        this.id = id;
        this.name = name;
        this.lines = new ArrayList<>();
        this.trains = new ArrayList<>();
        this.drivers = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Line> getLines() {
        return lines;
    }

    public void setLines(List<Line> lines) {
        this.lines = lines;
    }

    public List<Train> getTrains() {
        return trains;
    }

    public void setTrains(List<Train> trains) {
        this.trains = trains;
    }

    public List<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<Driver> drivers) {
        this.drivers = drivers;
    }

    @Override
    public String toString() {
        return "Subway{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lines=" + lines +
                ", trains=" + trains +
                ", drivers=" + drivers +
                '}';
    }

    // Metodos propios de Subway
    public void addTrain(List<Train> trainList) {
        trains.addAll(trainList);
    }

    public void addTrain(Train train) {
        trains.add(train);
    }
}
