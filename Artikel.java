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
    private int artnr;
    private String artbez;
    private String mge;
    private double preis;
    private int steu;

     void ArtikelToPrint(){
        System.out.println(
                "Artikelbez: "+this.artbez+", ArtNr: "+ this.artnr+ ", Mge: "
                        + this.mge+", Preis: "+this.preis+" Steuer: "+ this.steu);
    }

     String ArtikelToString(){
        return
                (this.artnr+";"+ this.artbez+ ";"
                        + this.mge+";"+this.preis+";"+ this.steu);
    }

    static ArrayList<Artikel> getAllArtikel(){
        ArrayList<Artikel> alleArtikel = new ArrayList<>();
        String zeile;
        String[] gespaltet;

        try{
            DatenVerwaltung.raf_dat.seek(0);
            while((zeile = DatenVerwaltung.raf_dat.readLine()) != null){
                gespaltet = zeile.split(";");
                Artikel neuerArtikel = new Artikel(
                        Integer.parseInt(gespaltet[0]), gespaltet[1], gespaltet[2],
                        Double.parseDouble(gespaltet[3]), Integer.parseInt(gespaltet[4])
                );
                alleArtikel.add(neuerArtikel);
                neuerArtikel.ArtikelToString();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
        return alleArtikel;
    }

    static Artikel getArtikel(long offset){
        Artikel gefundenerArtikel = null;
        String zeile;
        String[] gespaltet;

        try{
            DatenVerwaltung.raf_dat.seek(offset);
            zeile = DatenVerwaltung.raf_dat.readLine();
            gespaltet = zeile.split(";");
            gefundenerArtikel = new Artikel(
                    Integer.parseInt(gespaltet[0]), gespaltet[1], gespaltet[2],
                    Double.parseDouble(gespaltet[3]), Integer.parseInt(gespaltet[4]));

        }catch(IOException e){
            e.printStackTrace();
        }
        return gefundenerArtikel;
    }

    static void writeArtikel(Artikel neu){
        try{
            DatenVerwaltung.raf_dat.writeBytes("\n"+neu.ArtikelToString());
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
