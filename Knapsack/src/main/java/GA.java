import java.util.ArrayList;
import java.util.Random;

public class GA {
    int maxWeight;
    int esyaSayisi;
    int populasyon;
    int elitizm_sayisi;
    Esya[] tumEsyalar = new Esya[esyaSayisi];
    int[] kume;
    double mutasyonOlasiligi = 0.20;
    double caprazlamaOlasiligi = 0.9;
    ArrayList<int [][]> butunnesiller = new ArrayList<int[][]>();
    public GA(int maxWeight,int elitizm_sayisi,int populasyon,int esyaSayisi){
        this.maxWeight = maxWeight ;
        this.elitizm_sayisi = elitizm_sayisi ;
        this.populasyon = populasyon ;
        this.esyaSayisi = esyaSayisi ;
        kume = new int[esyaSayisi];
        for (int i = 0 ; i<kume.length ; i++){
            kume[i] = i+1 ;
        }
    }   
    
    public void esyalariAl(Esya[] Esyalar){
        tumEsyalar = Esyalar.clone();
    }    
    
    public ArrayList<ArrayList> AltKumeleriBul(){
        ArrayList<ArrayList> altkumeler = new ArrayList<>();            
        int n = kume.length;
        int kume_sayisi = (int) Math.pow(2, n);
        for (int i = 0; i < kume_sayisi; i++) {
            ArrayList<Integer> altkume = new ArrayList<>();
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) != 0) {
                    altkume.add(kume[j]);
                }
            }
            altkumeler.add(altkume);
        }
        return altkumeler;    
    }
    
    public int HedefFVDegeriBulma(){
        ArrayList<ArrayList> altKumeler = new ArrayList<ArrayList>();
        altKumeler = AltKumeleriBul();
        int enYuksek = 0;
        for (int i = 1; i< altKumeler.size();i++){            // i = 1 cunku bos kumeyi atliyoruz.
            ArrayList<Integer> altKume = altKumeler.get(i);
            int toplamAgirlik = 0 ;
            int toplamDeger = 0 ;
            for (int j = 0 ; j< altKume.size() ; j++ ){
                int a = altKume.get(j);
                Esya esya = tumEsyalar[a-1];
                toplamAgirlik += esya.weight;
                toplamDeger += esya.value;
            }
            if (toplamAgirlik <= maxWeight && enYuksek < toplamDeger){
                enYuksek = toplamDeger;
            }
        }
        return enYuksek;
    }
    /*                   DYNAMİC PROGRAMMİNG TARGET FV VALUE
    public int Hedef(){
        int[][] V = new int[tumEsyalar.length+1][maxWeight+1];
        for (int z = 0 ; z<tumEsyalar.length+1;z++){
            V[z][0] = 0 ;
        }
        for (int a = 0 ; a<maxWeight+1;a++){
            V[0][a] = 0 ;
        }
        for (int i = 1; i<tumEsyalar.length+1;i++){
            for (int j = 1 ; j <maxWeight+1;j++){
                if (j- tumEsyalar[i-1].weight > 0){
                    V[i][j] = Math.max(V[i-1][j],V[i-1][j-tumEsyalar[i-1].weight] +tumEsyalar[i-1].value );
                }
                else {
                    V[i][j] = V[i-1][j];
                }
            }
        }
        return V[tumEsyalar.length][maxWeight];
    }
    */
    public int GenAta(){
        Random rand = new Random();
        int sayi = rand.nextInt(2);
        return sayi;
    }
    public int FVHesapla(int[] kromozom){         
        int FV = 0;                                
        int totalWeight = 0 ;
        for (int i = 0 ; i < kromozom.length; i++ ){      
            if(kromozom[i] == 1){
                Esya urun = tumEsyalar[i];
                FV += urun.value;
                totalWeight += urun.weight;
            }
        }
        if (totalWeight > maxWeight){
            return 0;
        }
        else {
            return FV;
        }                                          
    }
    
    public void nesilOlustur(){
        int[][] nesil = new int[populasyon][] ;
        for (int i = 0 ; i<populasyon; i++){
            int[] kromozom = new int[esyaSayisi];
            for (int j = 0 ; j<esyaSayisi; j++){
                int xd = GenAta();
                kromozom[j] = xd;
            }
            nesil[i] = kromozom;
        }
        butunnesiller.add(nesil);
        SonNesilSirala();
    }
    public void SonNesilSirala(){
        int nesil_sayisi = butunnesiller.size();                    
        int [][] son_nesil = butunnesiller.get(nesil_sayisi -1 );  
        for (int i = 1; i < son_nesil.length;i++  ){                
            int value = FVHesapla(son_nesil[i]);                    
            int[] eleman = son_nesil[i];                          
            int j = i-1;                                            
            while (j >= 0 && FVHesapla(son_nesil[j]) < value){      
                son_nesil[j+1] = son_nesil[j];                       
                j = j-1;
            }
            son_nesil[j+1] = eleman;            
        }
        butunnesiller.remove(nesil_sayisi - 1);
        butunnesiller.add(son_nesil);
      
    }
    
    public int randomKesmebitis(){
        Random r = new Random();
        int bitis = r.nextInt(esyaSayisi);
        return bitis;
    }
    public int randomKesmebaslangic(){
        Random r = new Random();
        int baslangic = r.nextInt(esyaSayisi);
        return baslangic;
    }
    public int RandomKromozom(){
        Random r = new Random();
        int kromozom = r.nextInt(populasyon);
        return kromozom;
    }
    public float MutasyonOlasiligi(){
        Random r = new Random();
        float mutasyonOlasilik = r.nextFloat();
        return mutasyonOlasilik;
    }
    public float CaprazlamaOlasiligi(){
        Random r = new Random();
        float caprazlamaOlasilik = r.nextFloat();
        return caprazlamaOlasilik;
    }
    public int kacNesilde(){
        return butunnesiller.size();
    }
    
    public void Caprazlama(){
        int[][] yeni_nesil = new int[populasyon][] ;                            
        int nesil_sayisi = butunnesiller.size();                          
        int[][] son_nesil = butunnesiller.get(nesil_sayisi -1 );        
        float caprazlamaOlasilik = CaprazlamaOlasiligi();
        if (caprazlamaOlasilik < caprazlamaOlasiligi){
            for (int k = 0 ; k<elitizm_sayisi;k++){                                       
                yeni_nesil[k]=son_nesil[k]; 
            }
            for(int d = elitizm_sayisi; d < populasyon; d++ ){                                
                int baslangic = randomKesmebaslangic();                      
                int bitis = randomKesmebitis();
                if (bitis < baslangic){                                       
                    int a = bitis;
                    bitis = baslangic;
                    baslangic = a;
                }            
            
                int ilk_kromozom_konum =  RandomKromozom();                   
                int ikinci_kromozom_konum =  RandomKromozom(); 
        
                int[] ilk_kromozom = son_nesil[ilk_kromozom_konum];         
                int[] ikinci_kromozom = son_nesil[ikinci_kromozom_konum];
            
                int[] ilk_cocuk = ilk_kromozom.clone() ;                    
                for (int i = baslangic; i<bitis+1;i++){                    
                    ilk_cocuk[i]=ikinci_kromozom[i];                
                }
                int[] ikinci_cocuk = ikinci_kromozom.clone();               
                for (int i = baslangic; i<bitis+1;i++){
                    ikinci_cocuk[i]=ilk_kromozom[i];
                }
                int ilk_cocuk_fv = FVHesapla(ilk_cocuk);
                int ikinci_cocuk_fv = FVHesapla(ikinci_cocuk);
            
            
                if (ilk_cocuk_fv > ikinci_cocuk_fv){
                    ilk_cocuk = MutasyonYap(ilk_cocuk);               
                    yeni_nesil[d] = ilk_cocuk;
                }
                else{
                    ikinci_cocuk = MutasyonYap(ikinci_cocuk);
                    yeni_nesil[d] = ikinci_cocuk;
                }
            }       
        }
        else{
            yeni_nesil = son_nesil;
        }
        butunnesiller.add(yeni_nesil);                                  
        SonNesilSirala();                                               
        
    }
    public int[] MutasyonYap(int [] kromozom){
        float cark = MutasyonOlasiligi();
        if (cark < mutasyonOlasiligi){        
            int mutasyon = GenAta();
            Random r = new Random();
            int sayi = r.nextInt(esyaSayisi);
            kromozom[sayi] = mutasyon;
        }
        return kromozom;
    }
    
    
    
    
    
    
    
}
