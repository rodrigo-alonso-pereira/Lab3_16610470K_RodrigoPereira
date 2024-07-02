package cl.usach;

import cl.usach.Model.*;
import cl.usach.Service.LineServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;

import static cl.usach.Model.StationType.*;
import static cl.usach.Model.CarType.*;
import static cl.usach.Model.StationType.TERMINAL;

public class Main {
    public static void main(String[] args) {

        // Datos station
        Station st0 = new Station(0, "San Alberto Hurtado", TERMINAL, 35);
        Station st1 = new Station(1, "USACH", COMBINACION, 30);
        Station st2 = new Station(2, "Estación Central", COMBINACION, 35);
        Station st3 = new Station(3, "ULA", REGULAR, 35);
        Station st4 = new Station(4, "República", REGULAR, 35);
        Station st5 = new Station(5, "Los Héroes", COMBINACION, 35);
        Station st6 = new Station(6, "La Moneda", REGULAR, 35);
        Station st7 = new Station(7, "U. de Chile", COMBINACION, 35);
        Station st8 = new Station(8, "Santa Lucia", TERMINAL, 35);

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
        Line line0 = new Line(0, "Línea 0", "UIC 60 ASCE", sectionListLine0);
        Line line1 = new Line(1, "Línea 1", "100 R.E.", sectionListLine1);

        // Test de metodos de line
        System.out.println(line1.lineLength()); //18.4
        System.out.println(line1.lineSectionLength("San Alberto Hurtado", "La Moneda")); //13.4
        System.out.println(line1.lineCost()); //335
        System.out.println(line1.lineSectionCost("San Alberto Hurtado", "La Moneda")); //275
        line0.lineAddSection(s0);
        System.out.println(line0.toString());
        System.out.println(line1.toString());

        LineServiceImpl lineService = new LineServiceImpl();
        System.out.println(lineService.isLine(line1));

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
        System.out.println(t2.toString());
    }
}