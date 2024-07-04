package cl.usach.Model;

import cl.usach.Service.LineServiceImpl;
import cl.usach.Service.TrainServiceImpl;
import cl.usach.Util.Utililty;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    TrainServiceImpl trainService = new TrainServiceImpl();
    LineServiceImpl lineService =  new LineServiceImpl();
    Utililty util = new Utililty();

    /**
     *
     * @param trainList
     */
    public void addTrain(List<Train> trainList) {
        for (Train train : trainList) {
            if (trainService.isTrain(train))
                trains.add(train);
        }
    }

    /**
     *
     * @param train
     */
    public void addTrain(Train train) {
        if (trainService.isTrain(train))
            trains.add(train);

    }
    /**
     *
     * @param lineList
     */
    public void addLine(List<Line> lineList) {
        List<Integer> idList = lines.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
        for (Line line : lineList) {
            idList.add(line.getId());
            if (lineService.isLine(line) && util.isRepeatElement(idList))
                lines.add(line);
            else
                idList.remove(idList.size() - 1);
        }
    }

    /**
     *
     * @param line
     */
    public void addLine(Line line) {
        List<Integer> idList = lines.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
        idList.add(line.getId());
        if (lineService.isLine(line))
            lines.add(line);
        else
            idList.remove(idList.size() - 1);
    }

    /**
     *
     * @param driver
     */
    public void addDriver(List<Driver> driver) {
        drivers.addAll(driver);
    }

    /**
     *
     * @param driver
     */
    public void addDriver(Driver driver) {
        drivers.add(driver);
    }


}
