public class Artikel{
    public Artikel(int artnr, String artbez, String mge, double preis, int steu){
        this.artnr = artnr;
        this.artbez = artbez;
        this.mge = mge;
        this.preis = preis;
        this.steu = steu;
    }
    int artnr;
    String artbez;
    String mge;
    double preis;
    int steu;

    public void ArtikelToString(){
        System.out.println(
                "Artikelbez: "+this.artbez+", ArtNr: "+ this.artnr+ ", Mge: "
                        + this.mge+", Preis: "+this.preis+" Steuer: "+ this.steu);
    }
}
