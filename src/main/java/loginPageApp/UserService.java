package loginPageApp;

import java.util.*;

public class UserService {

    private List<String> mails = new ArrayList<>();
    private List<String> password = new ArrayList<>();

    //3-Ad soyad, mail, ve şifre bilgilerinialarak kayıt edicez
    public void register() {
        Scanner input = new Scanner(System.in);

        System.out.println("Lütfen Ad soyad giriniz");
        String name = input.nextLine(); //kontrolleri local variable üzerinden yapıyoruz

        //-4 geçerli bir email girene kadar dönen bir döngü
        String email;
        boolean isValid;
        do {

            System.out.println("Lütfen mail adresinizi giriniz");
            email = input.nextLine(); //boşluk kontrolü yapabilmek için line ile aldık

            isValid = validateEmail(email);

            if (this.mails.contains(email)){
                System.out.println("Bu mail halihazırda kayıtlıdır");
                isValid=false;
            }

        } while (!isValid);//geçerli değer girene kadar dönecek

        String password;
        boolean isValidPassword;

        do {

            System.out.println("şifrenizi oluşturunuz");
            password=input.nextLine();

            isValidPassword = validatePassword(password); // validatepassword metotu ödev

        }while (!isValidPassword);

        //6-User objesi oluşturup kayıt işlemini tamamlayalım

        User user = new User(name,email,password);
        this.mails.add(user.email);
        this.password.add(user.password);
        System.out.println("Tebrikler kayıt işlemi başarılıdır");
        System.out.println("Email ve şifreniz ile sisteme giriş yapabilirsiniz");

    }

    public void login(){
        Scanner input = new Scanner(System.in);
        System.out.println("Email adresinizi giriniz");
        String email = input.nextLine();

        boolean isExistEmail = this.mails.contains(email);

        if (isExistEmail){
            //kullanıcının kaydı vardır şifreyi kontrol ederiz
            int sayaç=3;
            while (sayaç>0){
                System.out.println("Şifrenizi giriniz");
                String passw = input.nextLine();
                //Şifre ile mial aynı indexte mi
                int index = this.mails.indexOf(email);
                if (this.password.get(index).equals(passw)){
                    System.out.println("Sisteme başarılı girş yapıldı");
                    break;
                }else {
                    sayaç--;
                    System.out.println("Şİfrenizi eksik yada hatalı girdiniz tekrar deneyiniz \n " +
                            "kalan deneme hakkı " + sayaç);
                }

            }

        }else {
            System.out.println("Sisteme kayıtlı kullanıcı bulunamadı");
            System.out.println("Eğer sisteme kayıtlı olduğunuzu düşünüyorsanız, bilgilerinizi kontrol ediniz");
        }
    }


    private boolean validatePassword(String password) {

       /* boşluk içermemeli
        : en az 6 karakter olmalı
        : en az bir tane küçük harf içermeli
        : en az bir tane büyük harf içermeli
        : en az bir tane rakam içermeli
        : en az bir tane sembol içermeli*/

        /*boolean isValidPassword;
        boolean space = password.contains(" ");
        boolean lenght = password.length() > 6;
        boolean lowerCaseControl = password.replaceAll("[A-Z0-9-p{Punct}]", "").length() == 0;*/



        return true;
    }


    private boolean validateEmail(String email) {
        boolean isValid;//en son return edecegim deger
        boolean space = email.contains(" ");
        boolean containsAt = email.contains("@");

        if (space) {
            System.out.println("Email boşluk karakteri iceremez!!!");
            isValid = false;
        } else if (!containsAt) {

            System.out.println("Email @ icermek zorundadır");
            isValid = false;
        } else {//alican@gmail.com
            String firstPart = email.split("@")[0];
            String secondPart = email.split("@")[1];
            //büyük-küçük harf,rakam yada -._ sembolleri dışında karakter var mı?
            int notValidCharLength = firstPart.replaceAll("[a-zA-Z0-9-._]", "").length();
            boolean checkStart = notValidCharLength == 0;//yukardakiler dısında karakter yoktur
            boolean checkEnd = secondPart.equals("gmail.com")
                    || secondPart.equals("yahoo.com")
                    || secondPart.equals("hotmail.com");
            if (!checkStart) {
                System.out.println("Email büyük-küçük harf,rakam yada -._ sembolleri dışında karakter iceremez");
            }
            if (!checkEnd) {
                System.out.println("Email gmail.com,hotmail.com veya yahoo.com ile bitmelidir");
            }
            isValid = checkStart && checkEnd;

            if (!isValid) {
                System.out.println("Geçersiz email, tekrar giriniz!!!");
            }

        }
        return isValid;

    }
    //valiadatepassword metodunu oluşturalım ODEV


}
