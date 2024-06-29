package cl.usach;

import cl.usach.Model.Line;
import cl.usach.Model.Section;
import cl.usach.Model.Station;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static cl.usach.Model.StationType.*;

public class Main {
    public static void main(String[] args) {

        //Datos station
        Station st0 = new Station(0, "San Alberto Hurtado", TERMINAL, 35);
        Station st1 = new Station(1, "USACH", COMBINACION, 30);
        Station st2 = new Station(2, "Estación Central", COMBINACION, 35);
        Station st3 = new Station(3, "ULA", REGULAR, 35);
        Station st4 = new Station(4, "República", REGULAR, 35);
        Station st5 = new Station(5, "Los Héroes", COMBINACION, 35);
        Station st6 = new Station(6, "La Moneda", REGULAR, 35);
        Station st7 = new Station(7, "U. de Chile", COMBINACION, 35);
        Station st8 = new Station(8, "Santa Lucia", TERMINAL, 35);

        //Datos de section
        Section s0 = new Section(st0, st1, 2, 50);
        Section s1 = new Section(st1, st2, 2.5, 55);
        Section s2 = new Section(st2, st3, 1.5, 30);
        Section s3 = new Section(st3, st4, 3, 45);
        Section s4 = new Section(st4, st5, 3, 45);
        Section s5 = new Section(st5, st6, 1.4, 50);
        Section s6 = new Section(st6, st7, 2, 40);
        Section s7 = new Section(st7, st8, 3, 20);

        //Datos para crear lines
        ArrayList<Section> sectionListLine0 = new ArrayList<>(Arrays.asList(s0, s1, s2, s3, s4, s5, s6, s7));
        Line line0 = new Line(0, "Línea 0", "UIC 60 ASCE", null);
        Line line1 = new Line(1, "Línea 1", "100 R.E.", sectionListLine0);

        //Testeo lineLength
        System.out.println(line1.lineLength()); //18.4
        System.out.println(line1.lineSectionLength("San Alberto Hurtado", "La Moneda")); //13.4
        System.out.println(line1.lineCost()); //335
        System.out.println(line1.lineSectionCost("San Alberto Hurtado", "La Moneda")); //275
    }
}