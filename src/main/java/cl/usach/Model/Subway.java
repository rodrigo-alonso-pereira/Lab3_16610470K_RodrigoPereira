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
        List<Integer> idLineList = trains.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
        for (Train train : trainList) {
            idLineList.add(train.getId());
            if (trainService.isTrain(train) && util.isRepeatElement(idLineList)) {
                trains.add(train);
                System.out.println("Train de id=" + train.getId() + " fue asignado a subway " + name);
            } else {
                idLineList.remove(idLineList.size() - 1);
                System.out.println("Train de id=" + train.getId() + " ya fue asignado previamente o no cumple con condiciones para ser un tren valido");
            }
        }
    }

    /**
     * @param train
     */
    public void addTrain(Train train) {
        try {
            List<Integer> idTrainList = trains.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
            idTrainList.add(train.getId());
            if (trainService.isTrain(train) && util.isRepeatElement(idTrainList)) {
                trains.add(train);
                System.out.println("Train de id=" + train.getId() + " fue asignado a subway " + name);
            } else {
                idTrainList.remove(idTrainList.size() - 1);
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
            List<Integer> idLineList = lines.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
            for (Line line : lineList) {
                idLineList.add(line.getId());
                if (lineService.isLine(line) && util.isRepeatElement(idLineList)) {
                    lines.add(line);
                    System.out.println("Line de id=" + line.getId() + " fue asignado a subway " + name);
                } else {
                    idLineList.remove(idLineList.size() - 1);
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
            List<Integer> idLineList = lines.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
            idLineList.add(line.getId());
            if (lineService.isLine(line) && util.isRepeatElement(idLineList)) {
                lines.add(line);
                System.out.println("Line de id=" + line.getId() + " fue asignado a subway " + name);
            } else {
                idLineList.remove(idLineList.size() - 1);
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
            List<Integer> idDriverList = drivers.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
            for (Driver d : driver) {
                idDriverList.add(d.getId());
                if (util.isRepeatElement(idDriverList)) {
                    drivers.add(d);
                    System.out.println("Driver de id=" + d.getId() + " fue asignado a subway " + name);
                } else {
                    idDriverList.remove(idDriverList.size() - 1);
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
            List<Integer> idDriverList = drivers.stream().flatMap(e -> Stream.of(e.getId())).collect(Collectors.toList());
            idDriverList.add(driver.getId());
            if (util.isRepeatElement(idDriverList)) {
                drivers.add(driver);
                System.out.println("Driver de id=" + driver.getId() + " fue asignado a subway " + name);
            } else {
                idDriverList.remove(idDriverList.size() - 1);
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
            Line verifiedLine = lines.stream()
                    .filter(e -> e.getId() == line.getId())
                    .findFirst()
                    .orElse(null);

            // Evalua que tren exista en subway
            Train verifiedTrain = trains.stream()
                    .filter(e -> e.getId() == train.getId())
                    .findFirst()
                    .orElse(null);

            if (verifiedLine == null) {
                System.out.println("Line con id=" + line.getId() + " no encontrada");
                return;
            } else if (verifiedTrain == null) {
                System.out.println("Train con id=" + train.getId() + " no encontrado");
                return;
            }

            // Obtiene lista de ids de trenes asignados
            List<Integer> assignedTrainIdList = lines.stream()
                    .flatMap(e -> e.getTrains().stream())
                    .map(Train::getId)
                    .collect(Collectors.toList());

            // Evaluar que train no este asignado previamente en alguna linea
            if (!assignedTrainIdList.contains(train.getId())) {
                verifiedLine.addTrain(verifiedTrain);
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
            Driver verifiedDriver = drivers.stream()
                    .filter(e -> e.getId() == driver.getId())
                    .findFirst()
                    .orElse(null);

            // Busca id de drivers asigndados a trenes
            List<Integer> assignerDriverIdList = lines.stream()
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
            else if (verifiedDriver == null) // La linea existe en subway?
                System.out.println("Driver con id=" + driver.getId() + " no encontrado");
            else if (!Objects.equals(train.getTrainMaker(), driver.getTrainMaker())) // Trainmaker es igual en train y driver?
                System.out.println("TrainMaker incompatible entre train de id= " + train.getId() + " y driver de id=" + driver.getId());
            else if (assignerDriverIdList.contains(driver.getId())) // Driver fue asignado previamente?
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
        var iAmHere = "No se donde estoy =(";
        var lineName = "";
        try {
            // Evalua que line exista en subway
            lineName = lines.stream()
                    .filter(l -> l.getTrains().stream().anyMatch(t -> t.getId() == idTrain))
                    .findFirst()
                    .map(Line::getName)
                    .orElse(null);
            Station currentStation = calculateWhereIsTrain(idTrain, time);

            if (lineName == null) {
                System.out.println("La linea no tiene nombre asignado");
            } else if (currentStation == null) {
                System.out.println("No se pudo encontrar la estacion donde va el tren");
            } else {
                iAmHere = "El tren va en la estacion '" + currentStation.getName() + "' de la linea " + lineName;
            }

        } catch (Exception e) {
            System.out.println("[whereIsTrain] error: " + e.getMessage());
        }
        return iAmHere;
    }

    public Station calculateWhereIsTrain(int idTrain, Date time) {
        try {
            // Busca que el tren que esta asignado en alguna linea
            Train verifiedTrain = lines.stream()
                    .flatMap(e -> e.getTrains().stream())
                    .filter(e -> e.getId() == idTrain)
                    .findFirst()
                    .orElse(null);

            // Busca las secciones que el tren va a recorrer
            List<Section> sectionList = lines.stream()
                    .filter(l -> l.getTrains().stream().anyMatch(t -> t.getId() == idTrain))
                    .findFirst()
                    .map(Line::getSections)
                    .orElse(null);

            // Verifica que train haya sido encontrado en alguna linea
            if (verifiedTrain == null || verifiedTrain.getDriverAssignment() == null) {
                System.out.println("Train con id=" + idTrain + " no encontrado");
            } else if (sectionList == null) {
                System.out.println("La linea no tiene secciones asignadas");
            } else if (util.getTotalTime(verifiedTrain.getDriverAssignment().getDepartureTime(), time) == 0) {
                System.out.println("Ingrese de forma correcta el tiempo a consultar");
            } else { // Si paso las verificaciones entrega la station
                long totalTime = util.getTotalTime(verifiedTrain.getDriverAssignment().getDepartureTime(), time);
                return util.trainMove(sectionList, totalTime, verifiedTrain);
            }
        } catch (Exception e) {
            System.out.println("[calculateWhereIsTrain] error: " + e.getMessage());
        }
        return null;
    }

    public List<Station> trainPath(int idTrain, Date time) {
        Station currentStation = calculateWhereIsTrain(idTrain, time);
        List<Section> sectionList = lines.stream()
                .filter(l -> l.getTrains().stream().anyMatch(t -> t.getId() == idTrain))
                .findFirst()
                .map(Line::getSections)
                .orElse(null);
        List<Station> stationList = util.getStationList(sectionList);

        List<Station> finalStationList = new ArrayList<>();
        var flag = false;
        for (Station station : stationList) {
            if (station.getId() == currentStation.getId() || flag) {
                flag = true;
                finalStationList.add(station);
            }
        }
        return finalStationList;
    }
}
