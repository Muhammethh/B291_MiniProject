package com.tpe.biletRezervasyonApp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Ticket {

    //4) mesafe(km), koltuk no, fiyat bilgisi, yolculuk tipi bilgilerini verelim

    public double distance;
    public String seatNo;
    public double price;
    public int typeNo;

    //Bilet fiyatını hesaplayalım

    public void getTotalPrice(int age) {

        int seat = Integer.parseInt(this.seatNo); //koltuk numarasını hesaplama yapabilmek icin int'e cevirdik
        double total = 0; //toplama çıkarma hesaplama işlemleri için bir kavanoz oluşturdum

        switch (this.typeNo) { //2 tipimiz var tek yön ve çift yön
            case 1: //tek yön
                if (seat % 3 == 0) { //tekli koltuk
                    total = this.distance * 1.2;
                } else {
                    total = this.distance * 1;
                }
                System.out.println("Toplam tutar: " + total); //İndirimsiz fiyatı yazdık
                break;
            case 2: // gidiş dönüş
                if (seat % 3 == 0) {
                    total = this.distance * 2.4;
                } else {
                    total = this.distance * 2;
                }
                System.out.println("Toplam tutar: " + total); //indirimsizfiyat
                //gidiş dönüş indirimi uygulamamız lazım
                total = total * 0.8; // bu da %80 almak demek
                System.out.println("Gidiş-Dönüş indirimi uygulandıktan sonra tutar: " + total);

                break;


        }
        //yaş indirimi yapmamız lazım
        if (age < 12) { //%50 indirim

            total = total / 2;
            System.out.println("12 yaş altı fiyat: " + total);
        } else if (age > 65) { //%30 indirim

            total = total * 0.7;
            System.out.println("65 yaş üstü fiyat: " + total);
        }
        this.price = total;


        //6) Bileti yazdıralım


    }

    //6) Bileti yazdıralım
    public void printTicket(Bus otobus) { //tarih, saat, mesafe, koltuk no, otobus plakası,firma,peron,güzergah

        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("hh:mm a  \n  dd.MM.yy");
        System.out.println("***************************************");
        System.out.println("-----------Bilet Detayı----------------");
        System.out.println("Otobüs Plakası           : " + otobus.numberPlate);
        System.out.println("Otobüs Firması           : " + otobus.firma);
        System.out.println("Peron Numarası           : " + otobus.peron);
        System.out.println("Mesafe                   : " + this.distance);
        System.out.println("Koltuk no                : " + this.seatNo);
        System.out.println("Yolculuk tipi            : " + (this.typeNo == 1 ? "Tekyön" : "Gidiş dönüş"));
        System.out.println("Toplam                   : " + price);
        System.out.println("              Keyifli Yolculuklar dileriz....       ");
        System.out.println(dtf.format(dateTime));
        System.out.println("***************************************");


    }


}
