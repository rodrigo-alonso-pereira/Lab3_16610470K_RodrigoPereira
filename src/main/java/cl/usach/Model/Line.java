package cl.usach.Model;

import cl.usach.Model.*;
import java.util.ArrayList;
import java.util.Objects;

public class Line {

    private int id;
    private String name;
    private String railType;
    private ArrayList<Section> sections;
    private ArrayList<Train> trains;

    public Line() {
        this.trains = new ArrayList<>();
    }

    public Line(int id, String name, String railType, ArrayList<Section> sections) {
        this.id = id;
        this.name = name;
        this.railType = railType;
        this.sections = sections;
        this.trains = new ArrayList<>();
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

    public String getRailType() {
        return railType;
    }

    public void setRailType(String railType) {
        this.railType = railType;
    }

    public ArrayList<Section> getSections() {
        return sections;
    }

    public void setSections(ArrayList<Section> sections) {
        this.sections = sections;
    }

    public ArrayList<Train> getTrains() {
        return trains;
    }

    public void setTrains(ArrayList<Train> trains) {
        this.trains = trains;
    }

    public void addTrain(Train train) {
        trains.add(train);
    }

    @Override
    public String toString() {
        return "Line_[" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", railType='" + railType + '\'' +
                ", sections=" + sections +
                ", trains=" + trains +
                ']';
    }

    // Metodos Propios Line

    /**
     *
     * @return
     */
    //TODO: Averiguar si es total distancia o total estaciones
    public double lineLength() {
        double length = 0;
        for (Section section : sections) {
            length += section.getDistance();
        }
        return length;
        //return  sections.size();
    }

    /**
     *
     * @param stationName1
     * @param stationName2
     * @return
     */
    public double lineSectionLength(String stationName1, String stationName2) {
        var flag = false;
        var distance = 0.0;
        for (Section section : sections) {
            if (Objects.equals(section.getPoint2().getName(), stationName2)) {
                flag = false;
                distance += section.getDistance();
            } else if (Objects.equals(section.getPoint1().getName(), stationName1) || flag) {
                flag = true;
                distance += section.getDistance();
            }
        }
        return distance;
    }

    /**
     *
     * @return
     */
    public int lineCost() {
        int cost = 0;
        for (Section section : sections) {
            cost += section.getCost();
        }
        return cost;
    }

    /**
     *
     * @param StationName1
     * @param StationName2
     * @return
     */
    public int lineSectionCost(String StationName1, String StationName2) {
        var flag = false;
        var cost = 0;
        for (Section section : sections) {
            if (Objects.equals(section.getPoint2().getName(), StationName2)) {
                flag = false;
                cost += section.getCost();
            } else if (Objects.equals(section.getPoint1().getName(), StationName1) || flag) {
                flag = true;
                cost += section.getCost();
            }
        }
        return cost;
    }

    /**
     *
     * @param section
     */
    public void lineAddSection(Section section) {
        sections.add(section);
    }

}
