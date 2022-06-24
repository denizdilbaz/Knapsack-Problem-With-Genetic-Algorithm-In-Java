public class Main {
    public static void main(String args[]){
        Esya esya1 = new Esya("Su",6,30,1);
        Esya esya2 = new Esya("Kibrit",3,14,2);
        Esya esya3 = new Esya("Ekmek",4,16,3);
        Esya esya4 = new Esya("Fener",2,9,4);        
        Esya[] esyalar = {esya1,esya2,esya3,esya4};
        int populasyon_sayisi = 16;
        int elitism_sayisi = 3;
        int max_weight = 10;        
        CozumSinifi cozum = new CozumSinifi(max_weight,elitism_sayisi,populasyon_sayisi,esyalar);
        cozum.cozdur();
        System.out.println("---------------------------------------------");
        Esya esya5 = new Esya("Çadır",12,50,1);
        Esya esya6 = new Esya("Uyku Tulumu",7,42,2);
        Esya esya7 = new Esya("Mat",4,40,3);
        Esya esya8 = new Esya("Yemek",10,55,4);
        Esya esya9 = new Esya("Su",3,60,5);
        Esya esya10 = new Esya("Giysi",9,39,6);
        Esya esya11 = new Esya("Fener",3,36,7);
        Esya esya12 = new Esya("Dürbün",4,12,8);
        Esya esya13 = new Esya("Telsiz",3,38,9);
        Esya esya14 = new Esya("Pusula",2,40,10);
        Esya esya15 = new Esya("Sandalye",12,33,11);
        Esya esya16 = new Esya("Çakı",1,30,12);
        Esya esya17 = new Esya("Bilgisayar",5,10,13);
        Esya esya18 = new Esya("Kibrit",2,45,14);
        Esya esya19 = new Esya("Çöp Torbası",3,27,15);
        int max_weight2 = 40;
        int populasyon2 = 100;
        int elitzm2 = 5; 
        Esya[] esyalar2 = {esya5,esya6,esya7,esya8,esya9,esya10,esya11,esya12,esya13,esya14,esya15,esya16,esya17,esya18,esya19};
        CozumSinifi cozum2 = new CozumSinifi(max_weight2,elitzm2,populasyon2,esyalar2);
        cozum2.cozdur();
    }
}    
