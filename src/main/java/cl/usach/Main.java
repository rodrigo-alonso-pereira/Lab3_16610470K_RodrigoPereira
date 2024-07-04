package cl.usach;

import cl.usach.Model.*;
import cl.usach.Service.LineServiceImpl;
import cl.usach.Service.TrainServiceImpl;
import cl.usach.Util.Utililty;

import java.util.ArrayList;
import java.util.Arrays;

import static cl.usach.Model.StationType.*;
import static cl.usach.Model.CarType.*;

public class Main {
    public static void main(String[] args) {

        // Datos station
        Station st0 = new Station(0, "San Alberto Hurtado", StationType.TERMINAL, 35);
        Station st1 = new Station(1, "USACH", COMBINACION, 30);
        Station st2 = new Station(2, "Estación Central", COMBINACION, 35);
        Station st3 = new Station(3, "ULA", REGULAR, 35);
        Station st4 = new Station(4, "República", REGULAR, 35);
        Station st5 = new Station(5, "Los Héroes", COMBINACION, 35);
        Station st6 = new Station(6, "La Moneda", REGULAR, 35);
        Station st7 = new Station(7, "U. de Chile", COMBINACION, 35);
        Station st8 = new Station(8, "Santa Lucia", StationType.TERMINAL, 35);

        // Datos de section
        Section s0 = new Section(st0, st1, 2, 50);
        Section s1 = new Section(st1, st2, 2.5, 55);
        Section s2 = new Section(st2, st3, 1.5, 30);
        Section s3 = new Section(st3, st4, 3, 45);
        Section s4 = new Section(st4, st5, 3, 45);
        Section s5 = new Section(st5, st6, 1.4, 50);
        Section s6 = new Section(st6, st7, 2, 40);
        Section s7 = new Section(st7, st8, 3, 20);

        // Datos para crear lines
        ArrayList<Section> sectionListLine0 = new ArrayList<>();
        ArrayList<Section> sectionListLine1 = new ArrayList<>(Arrays.asList(s0, s1, s2, s3, s4, s5, s6, s7));
        Line l0 = new Line(0, "Línea 0", "UIC 60 ASCE", sectionListLine0);
        Line l1 = new Line(1, "Línea 1", "100 R.E.", sectionListLine1);

        // Test de metodos de line
        System.out.println(l1.lineLength()); //18.4
        System.out.println(l1.lineSectionLength("San Alberto Hurtado", "La Moneda")); //13.4
        System.out.println(l1.lineCost()); //335
        System.out.println(l1.lineSectionCost("San Alberto Hurtado", "La Moneda")); //275
        l0.lineAddSection(s0);
        System.out.println(l0.toString());
        System.out.println(l1.toString());

        LineServiceImpl lineService = new LineServiceImpl();
        System.out.println("isLine:" + lineService.isLine(l1));

        // Datos PassengerCar
        PassengerCar pc0 = new PassengerCar(0, 90, "NS-74", "CAF", CarType.TERMINAL);
        PassengerCar pc1 = new PassengerCar(1, 100, "NS-74", "CAF", CENTRAL);
        PassengerCar pc2 = new PassengerCar(2, 150, "NS-74", "CAF", CENTRAL);
        PassengerCar pc3 = new PassengerCar(3, 90, "NS-74", "CAF", CENTRAL);
        PassengerCar pc4 = new PassengerCar(4, 100, "NS-74", "CAF", CarType.TERMINAL);
        PassengerCar pc5 = new PassengerCar(5, 100, "AS-2014", "ALSTOM", CarType.TERMINAL);
        PassengerCar pc6 = new PassengerCar(6, 80, "AS-2014", "ALSTOM", CENTRAL);
        PassengerCar pc7 = new PassengerCar(7, 150, "AS-2014", "ALSTOM", CENTRAL);
        PassengerCar pc8 = new PassengerCar(8, 120, "AS-2014", "ALSTOM", CarType.TERMINAL);

        // Datos Train
        ArrayList<PassengerCar> passengerCarList0 = new ArrayList<>(Arrays.asList(pc0, pc1, pc2, pc3, pc4));
        ArrayList<PassengerCar> passengerCarList1 = new ArrayList<>(Arrays.asList(pc5, pc6, pc7, pc8));
        ArrayList<PassengerCar> passengerCarList2 = new ArrayList<>();
        Train t0 = new Train(0, "CAF", 60, 60, passengerCarList0);
        Train t1 = new Train(1, "ALSTOM", 70, 120, passengerCarList1);
        Train t2 = new Train(2, "ALSTOM", 70, 90, passengerCarList2);

        // Test metodos train
        t2.addCar(pc6, 0);

        // Test metodos de TrainServiceImpl
        TrainServiceImpl trainService = new TrainServiceImpl();
        trainService.removeCar(t2, 0);
        System.out.println(t0.toString());
        System.out.println("isTrain:" + trainService.isTrain(t2));

        System.out.println("Capacidad Tren: " + t2.fetchCapacity());

        // Datos Driver
        Driver d0 = new Driver(0, "Eren Yeager", "CAF");
        Driver d1 = new Driver(1, "Oliver Atom", "ALSTOM");
        Driver d2 = new Driver(2, "Kakaroto", "CAF");
        Driver d3 = new Driver(3, "Levy Ackerman", "ALSTOM");
        Driver d4 = new Driver(4, "Hanamichi Sakuragi", "CAF");
        Driver d5 = new Driver(5, "Monkey D. Luffy", "ALSTOM");

        // Datos subway
        Subway sw0 = new Subway(0, "Metro Santiago");
        Subway sw1 = new Subway(1, "Metro Valparaiso");
        Subway sw2 = new Subway(2, "Metro Concepcion");

        ArrayList<Train> listTrain = new ArrayList<>(Arrays.asList(t0, t1));
        sw0.addTrain(listTrain);
        sw0.addTrain(t2);

        ArrayList<Line> lineList = new ArrayList<>(Arrays.asList(l0, l1));
        sw0.addLine(l1);
        sw0.addLine(lineList);

        ArrayList<Driver> driverList = new ArrayList<>(Arrays.asList(d0, d1, d2));
        ArrayList<Driver> driverList2 = new ArrayList<>(Arrays.asList(d3, d4));
        sw0.addDriver(driverList);
        sw0.addDriver(driverList2);
        sw0.addDriver(d5);

        System.out.println("SUBWAY -> " + sw0.toString());

        // Pruebas toString
        Utililty util = new Utililty();
        System.out.println(sw0.toString2());

    }
}