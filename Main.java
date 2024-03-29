import java.util.Scanner;

public class Main{
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        DatenVerwaltung.initFiles();
        DatenVerwaltung.initIndexPaare();
        int auswahl = -1;
        while(auswahl != 0){
            System.out.println(
                    "1) Erfassen eines neuen Datensatzes.\n" +
                            "2) Alle Datensatze aus artikel.dat anzeigen.\n" +
                            "3) ISAM-Indexliste anzeigen und Datensatz anhand der Artikel-Nummer suchen.\n" +
                            "4) ISAM-Indexliste persistieren.\n"+
                            "0) Program beenden.");
            try{
                auswahl = Integer.parseInt(sc.nextLine());
                if(auswahl < 1 || auswahl > 4){
                    throw new NumberFormatException();
                }
            }catch(NumberFormatException e){
                System.out.println("Bitte eine Nummer zwischen 1 und 4 eingeben.");
            }

            switch(auswahl){
                case 1:
                    DatenVerwaltung.datensatzErfassen();
                    break;
                case 2:
                    DatenVerwaltung.showDatensaetze();
                    break;
                case 3:
                    Indexpaar.showIndexPaare();
                    break;
                case 4:
                    Indexpaar.writeMultiple(DatenVerwaltung.alleIndexPaare);
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}
