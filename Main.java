import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Main{
    //TODO eventuell _idx bei jedem Programmneustart löschen, weil sonst wird immer nur angehängt...
    public static final String artikel_dat = "C:\\Users\\Jurek\\eclipse-workspace\\DBPrak1\\src\\ARTIKEL.DAT";
    public static final String artikel_idx = "C:\\Users\\Jurek\\eclipse-workspace\\DBPrak1\\src\\ARTIKEL.IDX";
    public static RandomAccessFile raf_dat;
    public static RandomAccessFile raf_idx;

    public static void main(String[] args){
        //Initialisierung der RandomAccessFiles
        ArrayList<Indexpaar> alleIndexPaare = new ArrayList<>();
        try{
            raf_dat = new RandomAccessFile(artikel_dat, "rw");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            raf_idx = new RandomAccessFile(artikel_idx, "rw");
        }catch(FileNotFoundException e){
            e.printStackTrace();
        }
        //Passiert bei jedem programmstart
        init(alleIndexPaare);

        //Menüführung.
        //a) erfassen neuer Datensätze
        //B) alle datensätze anzeigen
        //C) indexliste anzeigen, suchnummer eingeben,(lineare Suche)  wenn ex: --> fp mit seek() in _dat auf den
            // offset aus _idx inhalt von datensatz anzeigen
            //wenn artnr nicht ex: --> meldung anzeigen. JOptionPane.
        //D) Bei ende des programms: bei neuen Datensätzen -->_idx neu pflegen.
        // setzen.
    }


    //B) alle datensätze anzeigen:
        /*
        System.out.println("1. Indexpaar ausgegeben:!!!");
        System.out.println(alleIndexPaare.get(0).offset+":"+alleIndexPaare.get(0).artnr);
        System.out.println("2. Indexpaar ausgegeben:!!!");
        System.out.println(alleIndexPaare.get(1).offset+":"+alleIndexPaare.get(1).artnr);
        Indexpaar.writeMultiple(alleIndexPaare);
         */


    public static void init(ArrayList<Indexpaar> alleIndexPaare){
        try{
            alleIndexPaare = datToList();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    public static ArrayList<Indexpaar> datToList() throws IOException{
        ArrayList<Indexpaar> indexPaare = new ArrayList<>();
        String zeile = null;
        String[] gespaltet = new String[5];
        Indexpaar neuesIndexpaar = null;
        long fp;
        try{
            raf_dat.seek(0);
        }catch(IOException e){
            e.printStackTrace();
        }
        fp = raf_dat.getFilePointer();
        //TODO solange machen, bis readLine() == null.
        while((zeile = raf_dat.readLine()) != null){
            try{
                System.out.println(fp+":\n");
                System.out.println(zeile);
                gespaltet = zeile.split(";");
                Artikel neuerArtikel = new Artikel(
                        Integer.parseInt(gespaltet[0]), gespaltet[1], gespaltet[2],
                        Double.parseDouble(gespaltet[3]), Integer.parseInt(gespaltet[4])
                );
                neuerArtikel.ArtikelToString();
                neuesIndexpaar = new Indexpaar(neuerArtikel.artnr, fp);
                indexPaare.add(neuesIndexpaar);
                fp = raf_dat.getFilePointer();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return indexPaare;
    }


    public static void seekArtNrIDX(int artnr){
        //TODO wie sucht man in der .IDX?
        //TODO Serialisiert aus .idx auslesen, dann in jedem object die artnr suchen.

    }
}