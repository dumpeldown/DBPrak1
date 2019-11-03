import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Scanner;

public class DatenVerwaltung{
    static final String artikel_dat = "src\\ARTIKEL.DAT";
    static final String artikel_idx = "src\\ARTIKEL.IDX";
    static RandomAccessFile raf_dat;
    static RandomAccessFile raf_idx;
    static ArrayList<Indexpaar> alleIndexPaare = new ArrayList<>();
    static ArrayList<Artikel> alleArtikel = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    static void initFiles(){
        //Initialisierung der RandomAccessFiles
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
    }
    static void initIndexPaare(){
        alleIndexPaare = Indexpaar.getIndexPaare();
    }

    public static void showDatensaetze(){
        for(Artikel a : Artikel.getAllArtikel()){
            a.ArtikelToPrint();
        }
        System.out.println("\n");
    }


    public static void datensatzErfassen(){
        int artnr = 0;
        String artbez = null;
        String mge = null;
        double preis = 0;
        int steu = 0;
        System.out.println("Erfassen einer neuen Datensatzes:\n");
        System.out.print("Artikelnummer:");
        try{
            artnr = Integer.parseInt(sc.nextLine());
            System.out.println("\nArtikelbezeichnung:");
            artbez = sc.nextLine();
            System.out.println("\nMengenabgabe in:");
            mge = sc.nextLine();
            System.out.println("\nPreis:");
            preis = Double.parseDouble(sc.nextLine());
            System.out.println("\nSteuer:");
            steu = Integer.parseInt(sc.nextLine());
            Artikel neuerArtikel = new Artikel(artnr, artbez,mge, preis, steu);
            alleArtikel.add(neuerArtikel);
            Artikel.writeArtikel(neuerArtikel);
        }catch(NumberFormatException e){
            System.out.println("Fehler bei der Eingabe.");
        }
    }
}
