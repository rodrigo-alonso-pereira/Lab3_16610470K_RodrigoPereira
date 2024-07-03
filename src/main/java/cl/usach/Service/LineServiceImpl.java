package cl.usach.Service;

import cl.usach.Model.Line;
import cl.usach.Model.Section;
import cl.usach.Model.Station;
import cl.usach.Util.Utililty;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LineServiceImpl implements LineService {

    Utililty util = new Utililty();

    /**
     *
     * @param line
     * @return
     */
    @Override
    public boolean isLine(Line line) {
        // Obtiene lista de id
        List<Integer> idList = Stream.concat( // Se une dos stream
                line.getSections().stream()
                        .flatMap(section -> Stream.of(section.getPoint1().getId())), //Obtiene id's de point1
                line.getSections().stream()
                        .reduce((first, second) -> second) // Obtener la última sección
                        .stream()
                        .map(section -> section.getPoint2().getId())
        ).collect(Collectors.toList());

        // Obtiene lista de name
        List<String> nameList = Stream.concat(
                line.getSections().stream()
                        .flatMap(section -> Stream.of(section.getPoint1().getName())),
                line.getSections().stream()
                        .reduce((first, second) -> second)
                        .stream()
                        .map(section -> section.getPoint2().getName())
        ).collect(Collectors.toList());

         // Obtiene lista de Station
        List<Station> stationList = Stream.concat(
                line.getSections().stream()
                        .flatMap(section -> Stream.of(section.getPoint1())),
                line.getSections().stream()
                        .reduce((first, second) -> second)
                        .stream()
                        .map(Section::getPoint2)
        ).collect(Collectors.toList());

        return util.isRepeatElement(idList) // Evaluar si id's estan repetidos
                && util.isRepeatElement(nameList) // Evalua si name's estan repetidos
                && util.isSectionCommunicates(line.getSections()) // Evalua si las secciones estan comunicadas
                && util.isTerminal(stationList); //Evalua si linea tiene forma minima T-T
    }
}
