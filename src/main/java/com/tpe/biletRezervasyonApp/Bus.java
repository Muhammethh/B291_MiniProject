package com.tpe.biletRezervasyonApp;

import java.util.ArrayList;
import java.util.List;

public class Bus {

    //2-plaka ve koltuk no
    public String numberPlate; //plaka
    public List<String> seats = new ArrayList<>(); //koltuk numaraları

    //Firma ismi ve peron no
    public String firma;
    public String peron;

    public Bus(String plate,String firma,String peron){

        this.numberPlate=plate;
        this.firma = firma;
        this.peron = peron;

        for (int i = 1; i < 33 ; i++) {

            this.seats.add(String.valueOf(i));

        }

    }

}
