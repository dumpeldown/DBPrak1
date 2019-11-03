import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Indexpaar implements Serializable, Comparable{
    public Indexpaar(int artnr, long offset){
        this.artnr = artnr;
        this.offset = offset;
    }
    private int artnr;
    private long offset;

    static void writeIndexPaar(Indexpaar zuschreiben) {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        try{
            fout = new FileOutputStream(DatenVerwaltung.artikel_idx, true);
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            oos = new ObjectOutputStream(fout);
        }catch(IOException e){
            e.printStackTrace();
        }

        try{
            oos.writeObject(zuschreiben);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    static ArrayList<Indexpaar> getIndexPaare(){
        ArrayList<Indexpaar> indexPaare = new ArrayList<>();
        String zeile = null;
        String[] gespaltet;
        Indexpaar neuesIndexpaar = null;
        int artnr = 0;
        long fp;
        try{
            DatenVerwaltung.raf_dat.seek(0);

        fp = DatenVerwaltung.raf_dat.getFilePointer();

        //solange machen, bis readLine() == null.
        while((zeile = DatenVerwaltung.raf_dat.readLine()) != null){
                gespaltet = zeile.split(";");
                artnr = Integer.parseInt(gespaltet[0]);
                neuesIndexpaar = new Indexpaar(artnr, fp);
                indexPaare.add(neuesIndexpaar);
                Collections.sort(indexPaare);
                fp = DatenVerwaltung.raf_dat.getFilePointer();
        }
        }catch(IOException e){
            e.printStackTrace();
        }
        return indexPaare;
    }


     static void writeMultiple(ArrayList<Indexpaar> alleIndexPaare){
        for(Indexpaar ip: alleIndexPaare){
            Indexpaar.writeIndexPaar(ip);
        }
    }

     static void showIndexPaare(ArrayList<Indexpaar> indexListe){
        System.out.println("Alle Indexpaare:\n");
        for(Indexpaar idp : indexListe){
            System.out.println("   "+idp.artnr+":"+idp.offset);
        }
        System.out.println("\n");
        searchIndexPaare(indexListe);
    }
     static void searchIndexPaare(ArrayList<Indexpaar> il){
        Scanner sc = new Scanner(System.in);
        long offset = -1;
        System.out.println("Geben Sie eine Artikelnummer ein..:\n");
        long searchnr = Long.parseLong(sc.nextLine());

        for(Indexpaar idp : il){
            if(idp.artnr == searchnr){
                offset = idp.offset;
            }
        }

        if(offset != -1){
            System.out.println("Suchergebnis:");
            Artikel result = Artikel.getArtikel(offset);
            result.ArtikelToPrint();
            System.out.println("\n");
        }
        else{
            System.out.println("Zu der eingegebenen Artikelnummer konnte kein Artikel in der Datenbank gefunden werden!");
        }

    }

    @Override
    public int compareTo(Object o){
        return Integer.compare(this.artnr, ((Indexpaar)o).artnr);
    }
}
