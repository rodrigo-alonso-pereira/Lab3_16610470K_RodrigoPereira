package cl.usach.Service;

import cl.usach.Model.Train;

public interface TrainService {

    void removeCar(Train train, int position);
    boolean isTrain(Train train);
}
