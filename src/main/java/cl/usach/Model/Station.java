package cl.usach.Model;

public class Station {
    private int id;
    private String name;
    // TODO: Crear ENUM de stationType
    private String stationType;
    private int stopTime;

    public Station(int id, String name, String stationType, int stopTime) {
        this.id = id;
        this.name = name;
        this.stationType = stationType;
        this.stopTime = stopTime;
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

    public String getStationType() {
        return stationType;
    }

    public void setStationType(String stationType) {
        this.stationType = stationType;
    }

    public int getStopTime() {
        return stopTime;
    }

    public void setStopTime(int stopTime) {
        this.stopTime = stopTime;
    }

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", stationType='" + stationType + '\'' +
                ", stopTime=" + stopTime +
                '}';
    }
}
