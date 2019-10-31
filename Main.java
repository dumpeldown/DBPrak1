import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main{
    //TODO eventuell _idx bei jedem Programmneustart löschen, weil sonst wird immer nur angehängt...
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args){

        //Passiert bei jedem programmstart
        DatenVerwaltung.initFiles();
        DatenVerwaltung.initIndexPaare();
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
                case 2: DatenVerwaltung.showDatensaetze();
                        break;
                case 3: Indexpaar.showIndexPaare(DatenVerwaltung.alleIndexPaare);
                        break;
                case 4: Indexpaar.writeMultiple(DatenVerwaltung.alleIndexPaare);
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



    public static void seekArtNrIDX(int artnr){
        //TODO wie sucht man in der .IDX?
        //TODO Serialisiert aus .idx auslesen, dann in jedem object die artnr suchen.

    }
}
