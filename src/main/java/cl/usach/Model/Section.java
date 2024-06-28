package cl.usach.Model;

public class Section {

    private Station point1;
    private Station point2;
    private double distance;
    private int cost;

    public Section(Station point1, Station point2, double distance, int cost) {
        this.point1 = point1;
        this.point2 = point2;
        this.distance = distance;
        this.cost = cost;
    }

    public Station getPoint1() {
        return point1;
    }

    public void setPoint1(Station point1) {
        this.point1 = point1;
    }

    public Station getPoint2() {
        return point2;
    }

    public void setPoint2(Station point2) {
        this.point2 = point2;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Section{" +
                "point1=" + point1 +
                ", point2=" + point2 +
                ", distance=" + distance +
                ", cost=" + cost +
                '}';
    }
}
