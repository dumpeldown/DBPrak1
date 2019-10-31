import java.io.IOException;
import java.util.ArrayList;

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

    static ArrayList<Artikel> getAllArtikel(){
        ArrayList<Artikel> alleArtikel = new ArrayList<>();
        String zeile = null;
        String[] gespaltet = new String[5];
        try{
            while((zeile = DatenVerwaltung.raf_dat.readLine()) != null){
                gespaltet = zeile.split(";");
                Artikel neuerArtikel = new Artikel(
                        Integer.parseInt(gespaltet[0]), gespaltet[1], gespaltet[2],
                        Double.parseDouble(gespaltet[3]), Integer.parseInt(gespaltet[4])
                );
                alleArtikel.add(neuerArtikel);
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return alleArtikel;
    }
}
