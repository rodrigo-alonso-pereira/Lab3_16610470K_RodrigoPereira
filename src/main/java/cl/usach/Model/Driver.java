package cl.usach.Model;

public class Driver {

    private int id;
    private String name;
    private String trainMaker;

    public Driver(int id, String name, String trainMaker) {
        this.id = id;
        this.name = name;
        this.trainMaker = trainMaker;
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

    public String getTrainMaker() {
        return trainMaker;
    }

    public void setTrainMaker(String trainMaker) {
        this.trainMaker = trainMaker;
    }

    @Override
    public String toString() {
        return "Driver{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", trainMaker='" + trainMaker + '\'' +
                '}';
    }
}
