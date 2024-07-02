package cl.usach.Service;

import cl.usach.Model.Train;

public class TrainServiceImpl implements TrainService {

    @Override
    public void removeCar(Train train, int position) {
        train.getCarList().remove(position);
    }
}
