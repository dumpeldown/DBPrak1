import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class DatenVerwaltung{
    private static final String artikel_dat = "C:\\Users\\Jurek\\eclipse-workspace\\DBPrak1\\src\\ARTIKEL.DAT";
    private static final String artikel_idx = "C:\\Users\\Jurek\\eclipse-workspace\\DBPrak1\\src\\ARTIKEL.IDX";
     static RandomAccessFile raf_dat;
     static RandomAccessFile raf_idx;
     static ArrayList<Indexpaar> alleIndexPaare = new ArrayList<>();
     static ArrayList<Artikel> alleArtikel = new ArrayList<>();

    static void initFiles(){
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
    }
    static void initIndexPaare(){
        alleIndexPaare = Indexpaar.getIndexPaare();
    }

    public static void showDatensaetze(){
        for(Artikel a : Artikel.getAllArtikel()){
            a.ArtikelToString();
        }
    }
}
