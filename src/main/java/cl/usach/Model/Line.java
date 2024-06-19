package cl.usach.Model;

import java.util.ArrayList;

public class Line {

    private int id;
    private String name;
    private String railType;
    private ArrayList<Section> sections = new ArrayList<>();

    public Line(int id, String name, String railType, ArrayList<Section> sections) {
        this.id = id;
        this.name = name;
        this.railType = railType;
        this.sections = sections;
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

    // Metodos Propios Line

    /**
     *
     * @return
     */
    public int lineLength() {
        int length = 0;
        for (Section section : sections) {
            length += section.getDistance();
        }
        return  length;
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
     * @param section
     */
    public void lineAddSection(Section section) {
        sections.add(section);
    }

    @Override
    public String toString() {
        return "Line{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", railType='" + railType + '\'' +
                ", sections=" + sections +
                '}';
    }
}
