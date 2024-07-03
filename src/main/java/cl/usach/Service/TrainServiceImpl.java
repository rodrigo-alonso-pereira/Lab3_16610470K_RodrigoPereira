package cl.usach.Service;

import cl.usach.Model.Train;

public class TrainServiceImpl implements TrainService {

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
}
