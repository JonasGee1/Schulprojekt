package Code.Backend;

import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {
        Schueler schueler;


        String filePath = "test";
        ArrayList<String> data = JSONReader.readJSONFile(filePath);
        // Ausgabe der Daten
        for (String value : data) {
            value.split(",");
            System.out.println(value);
        }

        



    }

}
*/
