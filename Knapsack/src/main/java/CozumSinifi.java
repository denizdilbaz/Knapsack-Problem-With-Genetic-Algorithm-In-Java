
public class CozumSinifi {
    
    int max_weight;
    int elitism_sayisi;
    int populasyon_sayisi;
    Esya[] esyalar;
    public CozumSinifi(int max_weight,int elitism_sayisi,int populasyon_sayisi,Esya[] esyalar){
        this.max_weight = max_weight;
        this.elitism_sayisi = elitism_sayisi;
        this.populasyon_sayisi = populasyon_sayisi;
        this.esyalar = new Esya[esyalar.length];
        this.esyalar = esyalar;        
    }
    
    public void cozdur(){
        GA ga1 = new GA(max_weight,elitism_sayisi,populasyon_sayisi,esyalar.length);
        ga1.esyalariAl(esyalar);
        ga1.nesilOlustur();        
        int nesil_sayisi = ga1.butunnesiller.size();
        int[] ilk_kromozom = ga1.butunnesiller.get(nesil_sayisi -1 )[0];
        int ilk_kromozom_fv = ga1.FVHesapla(ilk_kromozom);
        int hedefFV = ga1.HedefFVDegeriBulma();
        System.out.println("Olabilecek En Yuksek Value " + hedefFV);
        while(ilk_kromozom_fv != hedefFV){
            ga1.Caprazlama();
            nesil_sayisi = ga1.butunnesiller.size();
            ilk_kromozom = ga1.butunnesiller.get(nesil_sayisi-1)[0];
            ilk_kromozom_fv = ga1.FVHesapla(ilk_kromozom);
        }              
        System.out.println("Canta Dolduruldu: ");
        for (int i = 0 ; i < ilk_kromozom.length;i++){
            if (ilk_kromozom[i] == 1){
                System.out.println(esyalar[i].esyaIsmi + " Onemi: " + esyalar[i].value);
                esyalar[i].getBit();
            }
            else {
                esyalar[i].resetBit();
            }
        }
        System.out.println();
        System.out.println("Deger: " +ga1.FVHesapla(ilk_kromozom));
        System.out.println("Bulunan Nesil: " + ga1.kacNesilde());
    }
}
