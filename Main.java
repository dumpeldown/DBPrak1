import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    //TODO eventuell _idx bei jedem Programmneustart löschen, weil sonst wird immer nur angehängt...
    private static final String artikel_dat = "C:\\Users\\Jurek\\eclipse-workspace\\DBPrak1\\src\\ARTIKEL.DAT";
    private static final String artikel_idx = "C:\\Users\\Jurek\\eclipse-workspace\\DBPrak1\\src\\ARTIKEL.IDX";
    private static RandomAccessFile raf_dat;
    private static RandomAccessFile raf_idx;
    private static Scanner sc = new Scanner(System.in);

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
        alleIndexPaare = init(alleIndexPaare);
        int auswahl = -1;
        while(auswahl != 4){
            System.out.println(
                    "1) Erfassen eines neuen Datensatzes.\n" +
                    "2) Alle Datensatze aus artikel.dat anzeigen.\n" +
                    "3) ISAM-Indexliste anzeigen und Datensatz anhand der Artikel-Nummer suchen.\n" +
                    "4) ISAM-Indexliste persistieren und das Program beenden.\n");
            try{
                auswahl = Integer.parseInt(sc.nextLine());
                if(auswahl < 1 || auswahl > 4) throw new NumberFormatException();
            }catch(NumberFormatException e){
                System.out.println("Bitte eine Nummer zwischen 1 und 4 eingeben.");
            }

            switch(auswahl){
                //case 1: datensatzErfassen();
                        //break;
                case 2: Main.showDatensaetze();
                        break;
                case 3: Indexpaar.showIndexPaare(alleIndexPaare);
                        break;
                case 4: Indexpaar.writeMultiple(alleIndexPaare);
                        break;
            }
        }

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


    public static ArrayList<Indexpaar> init(ArrayList<Indexpaar> alleIndexPaare){
        try{
            alleIndexPaare = datToList();
        }catch(IOException e){
            e.printStackTrace();
        }
        return alleIndexPaare;
    }
    public static ArrayList<Indexpaar> datToList() throws IOException{
        ArrayList<Indexpaar> indexPaare = new ArrayList<>();
        String zeile = null;
        String[] gespaltet = new String[5];
        Indexpaar neuesIndexpaar = null;
        int artnr = 0;
        long fp;
        try{
            raf_dat.seek(0);
        }catch(IOException e){
            e.printStackTrace();
        }
        fp = raf_dat.getFilePointer();
        //solange machen, bis readLine() == null.
        while((zeile = raf_dat.readLine()) != null){
            try{
                gespaltet = zeile.split(";");
                artnr = Integer.parseInt(gespaltet[0]);
                neuesIndexpaar = new Indexpaar(artnr, fp);
                indexPaare.add(neuesIndexpaar);
                fp = raf_dat.getFilePointer();
            }catch(IOException e){
                e.printStackTrace();
            }
        }
        return indexPaare;
    }

    public static ArrayList<Artikel> showDatensaetze(){
        String zeile = null;
        String[] gespaltet = new String[5];
        ArrayList<Artikel> alleArtikel = new ArrayList<>();
        try{
            while((zeile = raf_dat.readLine()) != null){
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

    public static void seekArtNrIDX(int artnr){
        //TODO wie sucht man in der .IDX?
        //TODO Serialisiert aus .idx auslesen, dann in jedem object die artnr suchen.

    }
}
