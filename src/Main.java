import java.util.Scanner;
//Beyza Nilufer Oskay 220303047
//Seymanur Altintas 220303007
public class Main {
    public static void main(String[] args) {
        Scanner klaye=new Scanner(System.in);
        System.out.println("satir:");
        int satir= klaye.nextInt();
        System.out.println("sutun:");
        int sutun= klaye.nextInt();
        Oyun o=new Oyun(satir,sutun);
        o.yazdir();
        while (true){
            System.out.println("1-yukari \n2-asagi \n3-sag \n4-sol");
            int yonn= klaye.nextInt();
            if (yonn>=1 && yonn<=4){
                o.haraket(yonn);
                o.yazdir();
            }else {
                System.out.println("gecerli sayi giriniz!!!");
            }

        }
    }

}