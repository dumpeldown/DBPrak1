import java.io.*;
import java.util.ArrayList;

public class Indexpaar implements Serializable{
    public Indexpaar(int artnr, long offset){
        this.artnr = artnr;
        this.offset = offset;
    }
    int artnr;
    long offset;

    public static void writeIndexPaar(Indexpaar zuschreiben) {
        FileOutputStream fout = null;
        ObjectOutputStream oos = null;
        final String artikel_idx = "C:\\Users\\Jurek\\eclipse-workspace\\DBPrak1\\src\\ARTIKEL.IDX";
        try{
            fout = new FileOutputStream(artikel_idx, true);
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
    public static void writeMultiple(ArrayList<Indexpaar> alleIndexPaare){
        for(Indexpaar ip: alleIndexPaare){
            Indexpaar.writeIndexPaar(ip);
        }
    }

}
