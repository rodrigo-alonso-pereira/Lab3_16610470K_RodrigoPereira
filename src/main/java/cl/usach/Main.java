package cl.usach;

import cl.usach.Model.*;
import cl.usach.Service.LineServiceImpl;
import cl.usach.Service.TrainServiceImpl;
import cl.usach.Util.Utililty;
import cl.usach.Vista.Menu;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Scanner;

import static cl.usach.Model.StationType.*;
import static cl.usach.Model.CarType.*;

public class Main {
    public static void main(String[] args) throws ParseException {
        /*
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

        Station st10 = new Station(10, "La Cisterna", StationType.TERMINAL, 35);
        Station st11 = new Station(11, "El Parron", COMBINACION, 30);
        Station st12 = new Station(12, "Lo Ovalle", COMBINACION, 35);
        Station st13 = new Station(13, "Ciudad del Niño", StationType.TERMINAL, 35);

        Station st20 = new Station(20, "Cerrillos", StationType.TERMINAL, 35);
        Station st21 = new Station(21, "Lo Valledor", COMBINACION, 35);
        Station st22 = new Station(22, "Pedro Aguirre Cerda", REGULAR, 35);
        Station st23 = new Station(23, "Franklin", StationType.TERMINAL, 35);

        // Datos de section
        Section s0 = new Section(st0, st1, 2, 50);
        Section s1 = new Section(st1, st2, 2.5, 55);
        Section s2 = new Section(st2, st3, 1.5, 30);
        Section s3 = new Section(st3, st4, 3, 45);
        Section s4 = new Section(st4, st5, 3, 45);
        Section s5 = new Section(st5, st6, 1.4, 50);
        Section s6 = new Section(st6, st7, 2, 40);
        Section s7 = new Section(st7, st8, 3, 20);

        Section s10 = new Section(st10, st11, 2, 50);
        Section s11 = new Section(st11, st12, 2.5, 55);
        Section s12 = new Section(st12, st13, 1.5, 30);

        Section s20 = new Section(st20, st21, 1.4, 50);
        Section s21 = new Section(st21, st22, 2, 40);
        Section s22 = new Section(st22, st23, 3, 20);

        // Datos para crear lines
        ArrayList<Section> sectionListLine0 = new ArrayList<>();
        ArrayList<Section> sectionListLine1 = new ArrayList<>(Arrays.asList(s0, s1, s2, s3, s4, s5, s6, s7));
        ArrayList<Section> sectionListLine2 = new ArrayList<>(Arrays.asList(s10, s11, s12));
        ArrayList<Section> sectionListLine6 = new ArrayList<>(Arrays.asList(s20, s21, s22));

        Line l0 = new Line(0, "Línea 0", "UIC 60 ASCE", sectionListLine0);
        Line l1 = new Line(1, "Línea 1", "100 R.E.", sectionListLine1);
        Line l2 = new Line(2, "Línea 2", "UIC 60 ASCE", sectionListLine2);
        Line l6 = new Line(6, "Línea 6", "100 R.E.", sectionListLine6);

        // Test de metodos de line
        System.out.println("lineLength: " + l1.lineLength()); //18.4
        System.out.println("lineSectionLength" + l1.lineSectionLength("San Alberto Hurtado", "La Moneda")); //13.4
        System.out.println("lineCost: " + l1.lineCost()); //335
        System.out.println("lineSectionCost: " + l1.lineSectionCost("San Alberto Hurtado", "La Moneda")); //275
        l0.lineAddSection(s0);
        System.out.println(l0.toString());
        System.out.println(l1.toString());

        LineServiceImpl lineService = new LineServiceImpl();
        System.out.println("isLine " + l0.getName() + ": " + lineService.isLine(l0)); //false
        System.out.println("isLine " + l1.getName() + ": "  + lineService.isLine(l1)); //true
        System.out.println("isLine " + l2.getName() + ": "  + lineService.isLine(l2)); //true
        System.out.println("isLine " + l6.getName() + ": "  + lineService.isLine(l6)); //true

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
        PassengerCar pc9 = new PassengerCar(9, 100, "NS-74", "CAF", CarType.TERMINAL);
        PassengerCar pc10 = new PassengerCar(10, 100, "NS-74", "CAF", CENTRAL);
        PassengerCar pc11 = new PassengerCar(11, 100, "NS-74", "CAF", CENTRAL);
        PassengerCar pc12 = new PassengerCar(12, 100, "NS-74", "CAF", CENTRAL);
        PassengerCar pc13 = new PassengerCar(13, 100, "NS-74", "CAF", CarType.TERMINAL);

        // Datos Train
        ArrayList<PassengerCar> passengerCarList0 = new ArrayList<>(Arrays.asList(pc0, pc1, pc2, pc3, pc4));
        ArrayList<PassengerCar> passengerCarList1 = new ArrayList<>(Arrays.asList(pc5, pc6, pc7, pc8));
        ArrayList<PassengerCar> passengerCarList2 = new ArrayList<>();
        ArrayList<PassengerCar> passengerCarList3 = new ArrayList<>(Arrays.asList(pc9, pc10, pc11, pc12, pc13));
        Train t0 = new Train(0, "CAF", 60, 60, passengerCarList0);
        Train t1 = new Train(1, "ALSTOM", 70, 120, passengerCarList1);
        Train t2 = new Train(2, "ALSTOM", 70, 90, passengerCarList2);
        Train t3 = new Train(3, "CAF", 80, 100, passengerCarList3);
        Train t4 = new Train(4, "CAF", 65, 70, passengerCarList0);
        Train t5 = new Train(5, "ALSTOM", 75, 50, passengerCarList1);

        // Test metodos train
        t2.addCar(pc6, 0);

        // Test metodos de TrainServiceImpl
        TrainServiceImpl trainService = new TrainServiceImpl();
        trainService.removeCar(t2, 0);
        System.out.println(t0.toString());
        System.out.println("isTrain tren de id " + t0.getId() + ": " + trainService.isTrain(t0)); //true
        System.out.println("isTrain tren de id " + t1.getId() + ": " + trainService.isTrain(t1)); // true
        System.out.println("isTrain tren de id " + t2.getId() + ": " + trainService.isTrain(t2)); // false
        System.out.println("isTrain tren de id " + t3.getId() + ": " + trainService.isTrain(t3)); // true

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

        // Pruebas de metodos propios de subway
        ArrayList<Train> listTrain = new ArrayList<>(Arrays.asList(t0, t1, t2, t4, t5));
        sw0.addTrain(listTrain);
        sw0.addTrain(t3);
        sw0.addTrain(t0); //Fue asignado previamente

        ArrayList<Line> lineList = new ArrayList<>(Arrays.asList(l0, l1));
        sw0.addLine(lineList);
        sw0.addLine(l1);
        sw0.addLine(l2);
        sw0.addLine(l6);

        ArrayList<Driver> driverList = new ArrayList<>(Arrays.asList(d0, d1, d2));
        sw0.addDriver(driverList);
        sw0.addDriver(d1);
        sw0.addDriver(d3);
        sw0.addDriver(d4);

        sw0.assignTrainToLine(t1, l1);
        sw0.assignTrainToLine(t0, l2);
        sw0.assignTrainToLine(t1, l6); // t1 ya esta asignado
        sw0.assignTrainToLine(t2, l6);
        sw0.assignTrainToLine(t3, l6);
        sw0.assignTrainToLine(t5, l1);
        sw0.assignTrainToLine(t4, l2);

        DateFormat sdf = new SimpleDateFormat("hh:mm aa");
        Date date1 = sdf.parse("01:30 pm");
        Date date2 = sdf.parse("09:00 am");
        Date date3 = sdf.parse("02:00 pm");
        Date date4 = sdf.parse("06:00 am");
        Date date5 = sdf.parse("10:00 pm");
        sw0.assignDriverToTrain(t1, d1, date1, st10, st8); // No existe st10 en line 1
        sw0.assignDriverToTrain(t1, d1, date1, st0, st8);
        sw0.assignDriverToTrain(t5, d3, date2, st2, st7);
        sw0.assignDriverToTrain(t3, d0, date3, st20, st23);
        sw0.assignDriverToTrain(t4, d4, date4, st10, st13);
        sw0.assignDriverToTrain(t5, d2, date5, st10, st13); // Incompatible trainMaker

        // Pruebas toString
        //System.out.println(sw0.toString());

        // Prueba de whereIsTrain
        Date date11 = sdf.parse("09:11 am");
        Date date12 = sdf.parse("02:02 pm");
        Date date13 = sdf.parse("06:05 am");
        Date date14 = sdf.parse("01:00 pm");
        System.out.println(sw0.whereIsTrain(5, date11)); // Linea 1
        System.out.println(sw0.whereIsTrain(3, date12)); // Linea 2
        System.out.println(sw0.whereIsTrain(4, date13)); // Linea 6
        System.out.println(sw0.whereIsTrain(1, date14)); // error por fecha mal ingresada

        System.out.println(sw0.trainPath(5, date11));
        */
        try {
            Menu.iniciarMenu();
        } catch (Exception e) {
            System.out.println("[Main] error: " + e.getMessage());
        }


    }
}