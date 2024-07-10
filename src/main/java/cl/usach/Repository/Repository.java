package cl.usach.Repository;

import cl.usach.Model.*;

import java.util.ArrayList;

public abstract class Repository {

    // Metodos abstractos
    public abstract ArrayList<Station> importStation();
    public abstract ArrayList<Section> importSection();
    public abstract ArrayList<Line> importLine ();
    public abstract ArrayList<Line> importCombinationLine ();
    public abstract ArrayList<PassengerCar> importPassengerCar();
    public abstract ArrayList<Train> importTrain();
}
