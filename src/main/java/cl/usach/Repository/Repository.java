package cl.usach.Repository;

import cl.usach.Model.Line;
import cl.usach.Model.Section;
import cl.usach.Model.Station;

import java.util.ArrayList;

public abstract class Repository {

    // Metodos abstractos
    public abstract ArrayList<Station> importStation();
    public abstract ArrayList<Section> importSection();
    public abstract ArrayList<Line> importLine ();
}
