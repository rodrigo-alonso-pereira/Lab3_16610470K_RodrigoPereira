package cl.usach;

import cl.usach.Model.Station;

import static cl.usach.Model.StationType.*;

public class Main {
    public static void main(String[] args) {

        Station st0 = new Station(0, "San Alberto Hurtado", TERMINAL, 35);
        Station st1 = new Station(1, "USACH", COMBINACION, 30);
        Station st2 = new Station(2, "Estación Central", COMBINACION, 35);
        Station st3 = new Station(3, "ULA", REGULAR, 35);
        Station st4 = new Station(4, "República", REGULAR, 35);
        Station st5 = new Station(5, "Los Héroes", COMBINACION, 35);
        Station st6 = new Station(6, "La Moneda", REGULAR, 35);
        Station st7 = new Station(7, "U. de Chile", COMBINACION, 35);
        Station st8 = new Station(8, "Santa Lucia", TERMINAL, 35);
        System.out.println(st8.toString());
    }
}