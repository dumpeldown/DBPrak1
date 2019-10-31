import java.io.*;
import java.util.ArrayList;

public class Indexpaar implements Serializable{
    public Indexpaar(int artnr, long offset){
        this.artnr = artnr;
        this.offset = offset;
    }
    private int artnr;
    private long offset;

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

    static ArrayList<Indexpaar> getIndexPaare(){
        ArrayList<Indexpaar> indexPaare = new ArrayList<>();
        String zeile = null;
        String[] gespaltet = new String[5];
        Indexpaar neuesIndexpaar = null;
        int artnr = 0;
        long fp;
        try{
            DatenVerwaltung.raf_dat.seek(0);
        }catch(IOException e){
            e.printStackTrace();
        }
        try{
        fp = DatenVerwaltung.raf_dat.getFilePointer();
        //solange machen, bis readLine() == null.
        while((zeile = DatenVerwaltung.raf_dat.readLine()) != null){

                gespaltet = zeile.split(";");
                artnr = Integer.parseInt(gespaltet[0]);
                neuesIndexpaar = new Indexpaar(artnr, fp);
                indexPaare.add(neuesIndexpaar);
                fp = DatenVerwaltung.raf_dat.getFilePointer();
        }
        }catch(IOException e){
            e.printStackTrace();
        }
        return indexPaare;
    }


    public static void writeMultiple(ArrayList<Indexpaar> alleIndexPaare){
        for(Indexpaar ip: alleIndexPaare){
            Indexpaar.writeIndexPaar(ip);
        }
    }

    public static void showIndexPaare(ArrayList<Indexpaar> l){
        System.out.println("Alle Indexpaare:\n");
        for(Indexpaar idp : l){
            System.out.println("   "+idp.artnr+":"+idp.offset+"\n");
        }
    }
}
