
import processing.core.PApplet;
import processing.core.PFont;
import processing.data.Table;

/**
 * Klasse Maximumsuche.
 * Balkendiagramm für int-Array, Zahlen werden aus CSV-Datei gelesen, Maximum der Werte wird bestimmt.
 * Hinweis zur Benutzung:
 * Klicke in das Zeichenfenster
 * Animierte Suche mit Taste "a"
 *
 * @author T.Schaller, S.Gebert
 * @version 05.02.2020
 */
public class Maximumsuche extends PApplet
{       

    // Liste mit allen Werten //<>//
    int[]    zahlen;    
    String[] namen;

    // Hilfsvariablen für die Suche
    // -------------------------------------------------------------------
    // ToDo: Hilfsvariablen erzeugen für aktuell größtes und aktuell 
    //       untersuchtes Element
    // -------------------------------------------------------------------
    // aktuell groesstes Element
    // aktuell untersuchtes Element
    public int verzoegerung=1000;  // Geschwindigkeit der Ausführung

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
        zeichneBalken();
    }

    /**
     * Die draw() Methode wird nach der setup() Methode aufgerufen
     * und führt den Code innerhalb ihres Blocks fortlaufend aus,
     * bis das Programm gestoppt oder noLoop() aufgerufen wird.
     */
    @Override
    public void draw()
    {
        zeichneBalken();
    }

    /**
     * Die keyPressed() Methode wird jedes mal aufgerufen, wenn eine Taste
     * der Tastatur gedrückt wurde. 
     * Die gedrückte Taste ist in der Variable 'key' gespeichert.
     */
    @Override
    public void keyPressed() {
        // Animierte Suche mit Taste "a"
        if (key=='a') {
            thread("maximumsuche");
        }
    }

    /**
     * Läd Daten aus einer Datei in das Programm
     *
     * @param name Name der zu ladenden Datei. Diese muss im Ordner "data" liegen.
     */
    public void ladeTabelle(String name) {
        // Tabelle aus CSV-Datei laden
        Table csv = loadTable(name, "header,csv");

        if (csv != null  && csv.getColumnCount()==2) {

            // Initialisiere Arrays, in die alle Zeilen der Tabelle passen
            zahlen = new int[csv.getRowCount()];
            namen = new String[csv.getRowCount()];

            // Fülle die Arrays mit Werten aus der Tabelle
            for (int i = 0; i < zahlen.length; i++) {
                // Lies Wert aus der i. Zeile und der Spalte "Punkte" bzw. "Name"
                zahlen[i] = csv.getInt(i, "Punkte");
                namen[i] = csv.getString(i, "Name");
            }
        }
    }

    /**
     * Zeichnet ein Balkendiagramm auf Grundlage der geladenen Daten.
     *
     */
    public void zeichneBalken() {

        clear();

        // Überschrift
        fill(255,255,255);
        textFont(grosseSchrift);
        text("Punkte", 2, 20);
        textFont(kleineSchrift);  

        // Alle Einträge darstellen
        if (zahlen != null) {
            for (int i = 0; i< zahlen.length; i++) {

                fill(20,25,165);
                // aktuelle Elemente farblich hervorheben
                // ----------------------------------------------------------------------
                // ToDo: Falls i dem aktuell untersuchtem oder der aktuellen Maximal-
                //       position entspricht, muss eine andere Farbe gewählt werden
                // ----------------------------------------------------------------------

                // Balkendiagramm zeichnen
                if (zahlen[i]>=0) rect(120, 25+i*15, zahlen[i]+1, 13);

                // Beschriftung
                fill(255,255,255);
                text(namen[i], 2, 35+i*15);
                text(""+zahlen[i], 70, 35+i*15);
                
                
                
            }
        }
    }

    /**
     * Bestimmt das Maximum der geladenen Daten.
     *
     * @return index des Maximums
     */
    public int maximumsuche(){
        return maximumsuche_rekursiv(0, 1, zahlen);
        
    }
    
    public int maximumsuche_rekursiv(int i_max,  int i, int[] zahlen) {
        if(i == zahlen.length) return i_max; //falls die Methode ferig ist
        if(zahlen[i] > zahlen[i]) i_max = i;     //i_max wir bestimmt
        return maximumsuche_rekursiv(i_max, i, zahlen); //nächste überprüfung
    }

    /**
     * Mit der main()-Methode wird das Programm gestartet.
     *
     */    
    public static void main(String _args[]){ 
                PApplet.main(Maximumsuche.class, _args);
    }

}
