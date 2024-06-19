package cl.usach.Service;

import cl.usach.Model.Line;

public interface LineService {

    int lineSectionLength(String StationName1, String StationName2);
    int lineSectionCost(String StationName1, String StationName2);
    boolean isLine(Line line);
}
