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
            if (trainService.isTrain(train) && util.isRepeatElement(idList)) {
                trains.add(train);
                System.out.println("Train de id=" + train.getId() + " fue asignado a subway " + name);
            } else {
                idList.remove(idList.size() - 1);
                System.out.println("Train de id=" + train.getId() + " ya fue asignado previamente o no cumple con condiciones para ser un tren valido");
            }
        }
    }

    /**
     * @param train
     */
    public void addTrain(Train train) {
        try {
            List<Integer> idList = trains.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
            idList.add(train.getId());
            if (trainService.isTrain(train) && util.isRepeatElement(idList)) {
                trains.add(train);
                System.out.println("Train de id=" + train.getId() + " fue asignado a subway " + name);
            } else {
                idList.remove(idList.size() - 1);
                System.out.println("Train de id=" + train.getId() + " ya fue asignado previamente o no cumple con condiciones para ser un tren valido");
            }
        } catch (Exception e) {
            System.out.println("[addTrain] error: " + e.getMessage());
        }
    }

    /**
     * @param lineList
     */
    public void addLine(List<Line> lineList) {
        try {
            List<Integer> idList = lines.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
            for (Line line : lineList) {
                idList.add(line.getId());
                if (lineService.isLine(line) && util.isRepeatElement(idList)) {
                    lines.add(line);
                    System.out.println("Line de id=" + line.getId() + " fue asignado a subway " + name);
                } else {
                    idList.remove(idList.size() - 1);
                    System.out.println("Line de id=" + line.getId() + " ya fue asignado previamente o no cumple con condiciones para ser un line valido");
                }
            }
        } catch (Exception e) {
            System.out.println("[addLine] error: " + e.getMessage());
        }
    }

    /**
     * @param line
     */
    public void addLine(Line line) {
        try {
            List<Integer> idList = lines.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
            idList.add(line.getId());
            if (lineService.isLine(line) && util.isRepeatElement(idList)) {
                lines.add(line);
                System.out.println("Line de id=" + line.getId() + " fue asignado a subway " + name);
            } else {
                idList.remove(idList.size() - 1);
                System.out.println("Line de id=" + line.getId() + " ya fue asignado previamente o no cumple con condiciones para ser un line valido");
            }
        } catch (Exception e) {
            System.out.println("[addLine] error: " + e.getMessage());
        }
    }

    /**
     * @param driver
     */
    public void addDriver(List<Driver> driver) {
        try {
            List<Integer> idList = drivers.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
            for (Driver d : driver) {
                idList.add(d.getId());
                if (util.isRepeatElement(idList)) {
                    drivers.add(d);
                    System.out.println("Driver de id=" + d.getId() + " fue asignado a subway " + name);
                } else {
                    idList.remove(idList.size() - 1);
                    System.out.println("Driver de id=" + d.getId() + " ya fue asignado previamente");
                }
            }
        } catch (Exception e) {
            System.out.println("[addDriver] error: " + e.getMessage());
        }
    }

    /**
     * @param driver
     */
    public void addDriver(Driver driver) {
        try {
            List<Integer> idList = drivers.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
            idList.add(driver.getId());
            if (util.isRepeatElement(idList)) {
                drivers.add(driver);
                System.out.println("Driver de id=" + driver.getId() + " fue asignado a subway " + name);
            } else {
                idList.remove(idList.size() - 1);
                System.out.println("Driver de id=" + driver.getId() + " ya fue asignado previamente");
            }
        } catch (Exception e) {
            System.out.println("[addDriver] error: " + e.getMessage());
        }
    }

    /**
     * @return
     */
    public String toString() {
        String lineString = null;
        String trainString = null;
        String driverString = null;
        try {
            lineString = "";
            trainString = "";
            driverString = "";

            for (Line line : lines) {
                lineString = lineString.concat(util.lineToString(line));
            }

            for (Train train : trains) {
                trainString = trainString.concat(util.trainToString(train));
            }

            for (Driver driver : drivers) {
                driverString = driverString.concat(util.driverToString(driver));
            }

        } catch (Exception e) {
            System.out.println("[toString] error: " + e.getMessage());
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
        try {
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
            if (!idTrainList.contains(train.getId())) {
                verifiedTrain.addTrain(verifiedLine);
                System.out.println("Train id id=" + train.getId() + " fue asignado a line de id=" + line.getId());
            } else
                System.out.println("Train con id=" + train.getId() + " ya esta asignado");
        } catch (Exception e) {
            System.out.println("[assignTrainToLine] error: " + e.getMessage());
        }
    }

    public void assignDriverToTrain(Train train, Driver driver, Date departureTime, Station departureStation, Station arrivalStation) {
        try {
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

            // Busca id de drivers asignados a trenes
            List<Integer> idDriver = lines.stream()
                    .flatMap(l -> l.getTrains().stream())
                    .map(Train::getDriverAssignment)
                    .filter(Objects::nonNull)
                    .map(DriverAssignment::getDriver)
                    .filter(Objects::nonNull)
                    .map(Driver::getId)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());

            // Entrega resultados de evaluaciones ahteriores
            if (verifiedTrain == null) // El tren existe en subway?
                System.out.println("Train con id=" + train.getId() + " no encontrado");
            else if (verifiedLine == null) // La linea existe en subway?
                System.out.println("Driver con id=" + driver.getId() + " no encontrado");
            else if (!Objects.equals(train.getTrainMaker(), driver.getTrainMaker())) // Trainmaker es igual en train y driver?
                System.out.println("TrainMaker incompatible entre train de id= " + train.getId() + " y driver de id=" + driver.getId());
            else if (idDriver.contains(driver.getId())) // Driver fue asignado previamente?
                System.out.println("Driver con id=" + driver.getId() + " ya esta asignado");
            else if (!util.stationBelongs(departureStation, train, lines)) // Estacion de salida existe en la linea asignada al tren?
                System.out.println("Estacion " + departureStation.getName() + " no existe en la linea en cual esta asignado el tren de id=" + train.getId());
            else if (!util.stationBelongs(arrivalStation, train, lines)) // Estacion de llegada existe en la linea asignada al tren?
                System.out.println("Estacion " + arrivalStation.getName() + " no existe en la linea en cual esta asignado el tren de id=" + train.getId());
            else { // Si lo anterio es validad, se crea y asigna driverAssignment a Train
                DriverAssignment driverAssignment = new DriverAssignment(driver, departureTime, departureStation, arrivalStation);
                verifiedTrain.setDriverAssignment(driverAssignment);
                System.out.println("Driver de id=" + driver.getId() + " fue asignado a train de id=" + train.getId());
            }
        } catch (Exception e) {
            System.out.println("[assignDriverToTrain] error: " + e.getMessage());
        }
    }

    public String whereIsTrain(int idTrain, Date time) {
        try {
            // Busca que el tren que esta asignado en alguna linea
            Train verifiedTrain = lines.stream()
                    .flatMap(e -> e.getTrains().stream())
                    .filter(e -> e.getId() == idTrain)
                    .findFirst()
                    .orElse(null);

            // Verifica que train haya sido encontrado en alguna linea
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

        } catch (Exception e) {
            System.out.println("[whereIsTrain] error: " + e.getMessage());
        }
        return "Train con id=" + idTrain + " encontrado";
    }
}
