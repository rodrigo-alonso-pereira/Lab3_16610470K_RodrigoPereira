package cl.usach.Util;

import cl.usach.Model.Section;
import cl.usach.Model.Station;
import cl.usach.Model.StationType;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;
import static cl.usach.Model.StationType.*;

public class Utililty {

    public boolean isRepeatElement(List<?> list) {
        return list.stream().allMatch(new HashSet<>()::add);
    }

    public boolean isSectionCommunicates(List<Section> sectionList) {
        var name = sectionList.get(0).getPoint2().getName();
        var flag = true;
        for (int i = 1; i < sectionList.size(); i++) {
            if(!sectionList.get(i).getPoint1().getName().equals(name)){
                flag = false;
                break;
            }
            name = sectionList.get(i).getPoint2().getName();
        }
        return flag;
    }

    public boolean isTerminal(List<Station> stationList) {
        Station firstStation = stationList.get(0);
        Station secondStation = stationList.get(stationList.size() - 1);
        return firstStation.getStationType().equals(secondStation.getStationType());
    }
    
}
