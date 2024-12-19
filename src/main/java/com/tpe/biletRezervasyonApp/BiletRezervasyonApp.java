package com.tpe.biletRezervasyonApp;


/*
        project: Bilet Rezervasyon ve Bilet Fiyatı Hesaplama Uygulaması

        1-Uygulama mesafe ve kurallara göre otobüs bileti fiyatı hesaplar sonuç olarak bilet bilgisini verir
        2- Kullanıcıdan     mesafe (KM),
        yolcu yaşı ,
        yolculuk tipi (Tek Yön, Gidiş-Dönüş)
        ve koltuk no bilgilerini alınır.
        NOT: Koltuk numaraları 1-32 aralığında olmalıdır.)
        Kullanıcıdan alınan değerler geçerli (mesafe ve yaş değerleri pozitif sayı, yolculuk tipi ise 1 veya 2) olmalıdır.
        Aksi halde kullanıcıya "Hatalı Veri Girdiniz !" şeklinde bir uyarı verilmelidir.

        3-Fiyat hesaplama kuralları:
        -Mesafe başına ücret:
        Tek yön: 1 Lira / km       Çift Yön(Gidiş-Dönüş): 2 Lira/km
        -Tekli Koltuk ücreti:
        Koltuk no 3 veya 3 ün katı ise fiyat %20 daha fazladır(Tek yön: 1.2 Lira/km, Çift Yön:2.4 Lira/km).
        -İlk olarak seferin mesafe, yön ve koltuk no bilgisine göre fiyatı hesaplanır,
        sonrasında koşullara göre aşağıdaki indirimler uygulanır ;
        i)-Çift Yön indirimi:
        "Yolculuk Tipi" gidiş dönüş seçilmiş ise son bilet fiyatı üzerinden %20 indirim uygulanır.
        ii)-Yaş indirimi:
        Kişi 12 yaşından küçükse son bilet fiyatı üzerinden %50 indirim uygulanır.
        Kişi 65 yaşından büyük ise son bilet fiyatı üzerinden %30 indirim uygulanır.

        */


import java.util.Scanner;

public class BiletRezervasyonApp {

    public static void main(String[] args) {


        //1 bilet rez için otobüse ihtiyacımızvar
        Bus bus = new Bus("44ee201", "TechPro", "Peron 2");

        //2) bilet objesini oluşturalım
        Ticket ticket = new Ticket();
        //7) uygulamayı başlatan bir method oluşturalım
        start(bus, ticket);

    }

    private static void start(Bus bus, Ticket ticket) {
        Scanner input = new Scanner(System.in); // kullanıcıdan bilgi almak için
        int select;
        do {
            //8-Kullanıcıdan bilgileri alalım
            System.out.println("--Bilet Rezervasyon Uygulamasına Hoşgeldiniz--");
            System.out.println("Lütfen yaşınızı giriniz");
            int age = input.nextInt();


            System.out.println("Lütfen gidilicek mesafe bilgisini KM olarak giriniz");
            double distance = input.nextDouble();

            System.out.println("Lütfen yolculuık tpini seciniz");
            System.out.println("1. Tek Yön");
            System.out.println("2. Gidiş Dönüş");
            int type = input.nextInt();


            System.out.println("Lütfen koltuk No Seciniz : ");
            System.out.println("Tekli koltuk Ücreti %20 daha fazladır.");
            System.out.println(bus.seats);//Mevcut tüm koltukları yazdıralım
            String seat = input.next();

            boolean isReserved = !bus.seats.contains(seat);
            if (isReserved) {
                System.out.println("Seçilen koltuk rezervedir");
            }

            //Girilen değerler geçerli mi?

            boolean isOkey = age > 0 && distance > 0 && (type == 1 || type == 2) && !isReserved;
            if (isOkey) {
                //Girilen değerler geçerli ise koltuk no listeden silinebilir

                bus.seats.remove(seat);

                //bileti oluşturalım
                ticket.distance = distance;
                ticket.seatNo = seat;
                ticket.typeNo = type;
                ticket.getTotalPrice(age);
                //bileti yazdıralım
                ticket.printTicket(bus);

            } else {
                System.out.println("Hatalı veri girildi");
            }
            System.out.println("Çıkış için 0 a basın");
            select = input.nextInt();
        } while (select != 0);


    }

}
