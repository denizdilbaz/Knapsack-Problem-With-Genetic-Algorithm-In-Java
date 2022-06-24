public class Esya {
    String esyaIsmi;
    int weight;
    int value;
    boolean durum;
    int id;
    public Esya(String esyaIsmi,int weight, int value,int id){
        this.weight = weight;
        this.value = value ;
        this.id = id;
        this.esyaIsmi = esyaIsmi;
    }
    public void setBit(){
        durum = true;
    }
    public boolean getBit(){
        return durum;
    }
    public void resetBit(){
        durum = false;
    }
}