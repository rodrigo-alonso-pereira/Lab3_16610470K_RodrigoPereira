package cl.usach.Model;

import cl.usach.Service.LineServiceImpl;
import cl.usach.Service.TrainServiceImpl;
import cl.usach.Util.Utililty;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Subway {

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

    // Metodos propios de Subway
    TrainServiceImpl trainService = new TrainServiceImpl();
    LineServiceImpl lineService = new LineServiceImpl();
    Utililty util = new Utililty();

    /**
     * @param trainList
     */
    public void addTrain(List<Train> trainList) {
        List<Integer> idList = trains.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
        for (Train train : trainList) {
            idList.add(train.getId());
            if (trainService.isTrain(train) && util.isRepeatElement(idList))
                trains.add(train);
            else
                idList.remove(idList.size() - 1);
        }
    }

    /**
     * @param train
     */
    public void addTrain(Train train) {
        List<Integer> idList = trains.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
        idList.add(train.getId());
        if (trainService.isTrain(train))
            trains.add(train);
        else
            idList.remove(idList.size() - 1);
    }

    /**
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
     * @param driver
     */
    public void addDriver(List<Driver> driver) {
        List<Integer> idList = drivers.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
        for (Driver d : driver) {
            idList.add(d.getId());
            if (util.isRepeatElement(idList))
                drivers.add(d);
            else
                idList.remove(idList.size() - 1);
        }
    }

    /**
     * @param driver
     */
    public void addDriver(Driver driver) {
        List<Integer> idList = drivers.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
        idList.add(driver.getId());
        if (util.isRepeatElement(idList))
            drivers.add(driver);
        else
            idList.remove(idList.size() - 1);
    }

    /**
     * @return
     */
    public String toString() {
        String lineString = "";
        String trainString = "";
        String driverString = "";

        for (Line line : lines) {
            lineString = lineString.concat(util.lineToString(line));
        }

        for (Train train : trains) {
            trainString = trainString.concat(util.trainToString(train));
        }

        for (Driver driver : drivers) {
            driverString = driverString.concat(util.driverToString(driver));
        }

        return "\n---Subway---\n" +
                "id = " + id + "," + '\n' +
                "name = " + name + "," + '\n' +
                "lines = [" + '\n' + lineString + "],\n" +
                "trains = [" + '\n' + trainString + "],\n" +
                "drivers = [" + '\n' + driverString + "]\n";
    }

    /**
     * @param train
     * @param line
     */
    public void assignTrainToLine(Train train, Line line) {
        // Evalua que line exista en subway
        Line verifiedTrain = lines.stream()
                .filter(e -> e.getId() == line.getId())
                .findFirst()
                .orElse(null);

        // Evalua que tren exista en subway
        Train verifiedLine = trains.stream()
                .filter(e -> e.getId() == train.getId())
                .findFirst()
                .orElse(null);

        if (verifiedTrain == null) {
            System.out.println("Line con id=" + line.getId() + " no encontrada");
            return;
        } else if (verifiedLine == null) {
            System.out.println("Train con id=" + train.getId() + " no encontrado");
            return;
        }

        // Obtiene lista de ids de trenes asignados
        List<Integer> idTrainList = lines.stream()
                .flatMap(e -> e.getTrains().stream())
                .map(Train::getId)
                .collect(Collectors.toList());

        // Evaluar que train no este asignado previamente en alguna linea
        if (!idTrainList.contains(train.getId()))
            verifiedTrain.addTrain(verifiedLine);
        else
            System.out.println("Train con id=" + train.getId() + " ya esta asignado");
    }

    public void assignDriverToTrain(Train train, Driver driver, Date departureTime, Station departureStation, Station arrivalStation) {
        // TODO: No hay evaluacion de que las estaciones ingresadas, existan realmente en la linea que fue asignado el tren.
        // Evalua que tren exista en subway
        Train verifiedTrain = trains.stream()
                .filter(e -> e.getId() == train.getId())
                .findFirst()
                .orElse(null);

        // Evalua que driver exista en subway
        Driver verifiedLine = drivers.stream()
                .filter(e -> e.getId() == driver.getId())
                .findFirst()
                .orElse(null);

        // TODO: Evaluar que driver no este asignado ya a un tren

        if (verifiedTrain == null) {
            System.out.println("Train con id=" + train.getId() + " no encontrado");
            return;
        } else if (verifiedLine == null) {
            System.out.println("Driver con id=" + driver.getId() + " no encontrado");
            return;
        } else if (!Objects.equals(train.getTrainMaker(), driver.getTrainMaker())) {
            System.out.println("TrainMaker incompatible entre train y driver");
            return;
        }

        DriverAssignment driverAssignment = new DriverAssignment(driver, departureTime, departureStation, arrivalStation);
        verifiedTrain.setDriverAssignment(driverAssignment);
    }

    public String whereIsTrain(int idTrain, Date time) {
        // Buscar el tren que esta asignado en alguna linea
        Train verifiedTrain = lines.stream()
                .flatMap(e -> e.getTrains().stream())
                .filter(e -> e.getId() == idTrain)
                .findFirst()
                .orElse(null);

        if (verifiedTrain == null) {
            return "Train con id=" + idTrain + " no encontrado";
        }

        // Busca las secciones que el tren va a recorrer
        List<Section> sectionList = lines.stream()
                .filter(e -> e.getTrains().stream().anyMatch(t -> t.getId() == idTrain))
                .findFirst()
                .map(Line::getSections)
                .orElse(null);

        System.out.println(sectionList);


        return "Train con id=" + idTrain + " encontrado";
    }
}
