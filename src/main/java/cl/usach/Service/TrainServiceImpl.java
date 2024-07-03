package cl.usach.Service;

import cl.usach.Model.Train;
import cl.usach.Util.Utililty;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TrainServiceImpl implements TrainService {
    Utililty util = new Utililty();

    /**
     *
     * @param train
     * @param position
     */
    // TODO: Verificar que position exista antes de eliminar
    @Override
    public void removeCar(Train train, int position) {
        train.getCarList().remove(position);
    }

    /**
     *
     * @param train
     * @return
     */
    @Override
    public boolean isTrain(Train train) {
        // Obtiene lista de id de PassengerCar
        List<Integer> idList =  train.getCarList().stream().flatMap(p -> Stream.of(p.getId())).collect(Collectors.toList());

        return util.isValidtrainMaker(train.getCarList(), train.getTrainMaker())
                && util.isRepeatElement(idList)
                && !(train.getCarList().isEmpty() || train.getCarList() == null)
                && util.isValidTrainFormat(train.getCarList());
    }
}
