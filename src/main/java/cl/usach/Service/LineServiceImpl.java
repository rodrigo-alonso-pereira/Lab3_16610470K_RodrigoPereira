package cl.usach.Service;

import cl.usach.Model.Line;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class LineServiceImpl implements LineService {

    @Override
    public boolean isLine(Line line) {
        /*List<Integer> idList = line.getSections().stream()
                .flatMap(Collection::stream)
                .map(ob->(Integer)ob)
                .collect(Collectors.toList());*/
        return false;
    }
}
