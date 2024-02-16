import java.util.Random;

public class Oyun {
    BagliListe yilan;
    int elmaX;
    int elmaY;
    int bombaX;
    int bombaY;
    int satir;
    int sutun;
    int yon;
    int sayac;
    Random rastgele=new Random();

    public Oyun(int satir, int sutun) {
        this.satir = satir;
        this.sutun = sutun;
        this.yilan=new BagliListe();
        this.yon=1;
        this.sayac=5;
        yilanOlustur();
        elma();
        bomba();
    }

    private void yilanOlustur() { // ilk once yilanin basi icin rastgele x ve y koordinatlari olusturur
        int x= rastgele.nextInt(satir);
        int y= rastgele.nextInt(sutun);
        for(int i=0;i<5;i++){  // sonra bu x ve y konumuna diger dugumleri ekledi yani x ve y koordinatinda 5 dugum var
            yilan.basaEkle(new Node(x,y));
        }
        for (int i=0;i<5;i++){//dugumleri 1 yonu(yukari) olacak sekilde siraladi.
            haraket(1);
        }
    }

    public void elma(){// rastgele elma uretmek icin koordinat uretir
        elmaX= rastgele.nextInt(satir-2)+1;
        elmaY=rastgele.nextInt(sutun-2)+1;
    }
    public void elmaKontrol(){//yilanin basi elmanin koordinatina geldiyse elma yenmis olur yeni dugum eklenir ve yeni elma olusturulur.
        if(elmaX==yilan.head.x && elmaY==yilan.head.y){
            Node eleman=new Node(yilan.tail.x,yilan.tail.y);
            yilan.sonaEkle(eleman);
            sayac++;// sayac dugum sayisini tutar ve elma yedigi icin dugum 1 artar.
            elma();
        }
    }
    public void bomba(){// rastgele bomba uretmek icin koordinat uretir
        bombaX = rastgele.nextInt(satir-2)+1;
        bombaY = rastgele.nextInt(sutun-2)+1;
    }
    public void bombaKontrol(){//ucuncu dugum bombanin uzerine geldiyse ucuncu dugum silinir ve dugum sayisini tutan sayac 1 azalir

        if (bombaX==yilan.head.next.next.x && bombaY==yilan.head.next.next.y){
                yilan.ucuncuyuSil();
                sayac--;
                bomba();
        }
    }
    public void oyunDenetle(){//dugum sayisi 3 e dustuyse oyun biter veya

        if (sayac<=3){
            System.out.println("oyun bitti.");
            System.exit(0);
        }
        else if (!carpmaDenetle()) {//yilan kendine carparsa oyun biter
            System.out.println("oyun bitti.");
            System.exit(0);
        }



    }


    public void haraket(int byon){
        Node gezici = yilan.tail;// gezici yilanin kuyrugunu tutar
        while(gezici != yilan.head){//gezici head olana kadar listeyi gezer
            gezici.x = gezici.prev.x;
            gezici.y = gezici.prev.y;
            gezici = gezici.prev;
        }
        if (byon==1 && yon!=2){//yukari //byon kullanidan gelen yondur yon ise yilanin gittigi yonu tutar, ters yone gitmesini engeller cunku boyle yaparsak yilan kendi uzerinden gecmis olur
            yilan.head.x=yilan.head.x-1;//x koordinatini 1 azaltarak yukari gitmesini saglar
            yon=1;
        } else if (byon==2 && yon!=1) {//asagi
            yilan.head.x=yilan.head.x+1;//x koordinatini 1 arttirarak asagi gitmesini saglar
            yon=2;
        } else if (byon==3 && yon!=4) {//sag
            yilan.head.y=yilan.head.y+1;// y koordinatini 1 arttirarak saga gitmesini saglar
            yon=3;
        } else if (byon==4 && yon!=3) {//sol
            yilan.head.y=yilan.head.y-1;//y koordinatini 1 azaltarak sola gitmesini saglar
            yon=4;
        }
        kenariDenetle();//yilan her hareket ettiginde kenari denetle fonksiyonunu cagirir
        elmaKontrol();
        bombaKontrol();
        oyunDenetle();


    }
    public void kenariDenetle() {//
        Node gez = yilan.head;
        while (gez != null) {
            if (gez.x < 0) {// yilan yukari yonde duvardan ciktiginda kafasi artık alt duvar ve ayni hizadan cikar ve her hareketinde diger dugumler takip eder
                gez.x = satir - 1;
            } else if (gez.x >= satir) {//yilan asagi yonde duvardan ciktiginda kafasi artik ust duvardan ve ayni hizadan iceri girer
                gez.x = 0;
            }

            if (gez.y < 0) {//yilan sol yonde duvardan ciktiginda artık kafasi aynı hizada sag duvardan girer
                gez.y = sutun - 1;
            } else if (gez.y >= sutun) {// yilan sag yonde duvardan ciktiginda artik kafasi sol duvardan ve ayni hizadan girer
                gez.y = 0;
            }

            gez = gez.next; // Bir sonraki düğüme geçer
        }
    }

    public boolean carpmaDenetle(){
        Node gezici=yilan.head.next;//gezici ikinci dugumden baslar
        while(gezici!=null){
            if(yilan.head.x==gezici.x && yilan.head.y==gezici.y){//  ilk once birinci dugumle ikinci dugumun carpisma durumuna bakar ve carpma durumu varsa false dondurur
                return false;
            }
            gezici=gezici.next;//carpma yoksa gezici bir saga kayar
        }
        return true;// carpma yoksa ture dondurur
    }

   public void yazdir() {
       int[][] matris=new int[satir][sutun];//kullanicin girdigi satir ve sutundan matrisi olusturduk
       Node gezici=yilan.head.next;//ikinci dugumden baslar
       matris[yilan.head.x][yilan.head.y]=5;//yilanin kafasinin oldugu konumu matriste 5 attik
       while (gezici!=null){//diger dugumlerin konumunu matriste 1 olarak atar
           matris[gezici.x][gezici.y]=1;
           gezici=gezici.next;// bir sonraki dugume gecer
       }
       matris[elmaX][elmaY]=2;//elmanin oldugu konumu matriste 2 attik
       matris[bombaX][bombaY]=3;// bombanin oldugu konumu matriste 3 attik
       for (int i = 0; i < satir; i++) {// matrisi yazdirma kismi
           for (int j = 0; j < sutun; j++) {

               if (i == 0 || i == satir - 1) {
                   System.out.print("——");
               } else if (j == 0 ) {
                   System.out.print("┆  ");

               }else if (j == sutun - 1) {
                   System.out.print(" ┆");
               }   else {
                   System.out.print("  ");
               }


               if (matris[i][j]==5) {
                   System.out.print("☻");

               } else if (matris[i][j] == 2) {
                   System.out.print("⊙");

               }else if (matris[i][j] == 1) {
                   System.out.print("▅");
               } else if (matris[i][j] == 3) {
                   System.out.print("⊕");

               } else {
                   System.out.print(" ");
               }
           }
           System.out.println();
       }
   }

}
