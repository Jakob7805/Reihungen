
import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;

/**
 * Klasse Zeitungsmeldung.
 * Automatische Zeitungsmeldung mit Maximum und Durchschnitt, Zahlen werden aus CSV-Datei gelesen.
 *
 * @author Thomas Schaller, Simon Gebert
 * @version 05.02.2020
 */
public class Zeitungsmeldung extends PApplet
{       
    // Liste mit allen Werten
    int[]    punkte;    
    String[] namen;

    // Schriften
    PFont kleineSchrift;  
    PFont grosseSchrift;

    /**
     * settings() Methode 
     * Fenstergröße size(int width, int height) und smooth(int level) muss hier eingestellt werden.
     */
    @Override
    public void settings()
    {
        size(1000,700);
    }        

    /**
     * Die setup() Methode wird einmal aufgerufen, wenn das Programm startet.
     * Hier werden Einstellungen wie die Hintergrundfarbe vorgenommen
     * und Medien wie Bilder und Schriftarten geladen.
     */
    @Override
    public void setup()
    {
        background(0);
        // Schriften laden
        kleineSchrift = createFont("fonts/NotoSans-Medium.ttf", 12); //12 / 26
        grosseSchrift = createFont("fonts/NotoSansDisplay-Medium.ttf",20); //20 /48

        // CSV-Datei laden und anzeigen
        ladeTabelle("data/punkte.csv");
        int summe = berechneSumme(punkte);
        System.out.println("Summe: "+summe);
        schreibeZeitungsmeldung();

    }

    public void ladeTabelle(String name) { //<>//
        // Tabelle aus CSV-Datei laden //<>//
        Table csv = loadTable(name, "header,csv");

        if (csv != null  && csv.getColumnCount()==2) {

            // Initialisiere Arrays, in die alle Zeilen der Tabelle passen
            punkte = new int[csv.getRowCount()];
            namen = new String[csv.getRowCount()];

            // Fülle die Arrays mit Werten aus der Tabelle
            for (int i = 0; i < punkte.length; i++) {
                // Lies Wert aus der i. Zeile und der Spalte "Punkte" bzw. "Name"
                punkte[i] = csv.getInt(i, "Punkte");
                namen[i] = csv.getString(i, "Name");
            }
        }
    }

    public void schreibeZeitungsmeldung() {

        clear();

        // Überschrift
        fill(225,225,255);
        stroke(155,155,255);
        textFont(grosseSchrift);
        text("BREAKING NEWS",5,32);
        strokeWeight(3);
        line(2,4,400,4);
        line(2,45,400,45);
        strokeWeight(2);
        line(2,7,400,7);
        line(2,42,400,42);
        strokeWeight(1);
        textFont(kleineSchrift); 
        fill(240);

        int anzahlSpieler   = 31;
        int anzahlSpiele    = 72;
        int summe           = berechneSumme(punkte);
        double durchschnitt = 89.53;

        text("Großartiges Ergebnis - Klasse XY nimmt an Binärwettbewerb teil.", 2, 60);
        text("Die Klasse XY hat beim diesjährigen Binärwettbewerb teilgenommen", 2, 82);
        text("und ein großartiges Ergebnis erzielt. Die "+anzahlSpieler +" Schülerinnen und", 2, 94);
        text("Schüler der Klasse erreichten in "+anzahlSpiele+" Spielen eine Gesamtpunktzahl",2,106);
        text("von "+summe+". Das ist ein Durchschnitt von "+durchschnitt +" pro Spiel.",2,118);

    }

    public int berechneSumme(int[] zahlen) {
        int summe;
        

        // Startwerte setzen //<>//
        summe = 0;

        // Alle Arrayelemente untersuchen
        for (int i=0; i< zahlen.length; i++) {
            summe = summe + zahlen[i];
        }
        // Gib die Summe zurück
        return summe;
    }

    /**
     * Mit der main()-Methode wird das Programm gestartet.
     *
     */    
    public static void main(String _args[]){ 
        PApplet.main(new String[] {Zeitungsmeldung.class.getName() });
    }

}
