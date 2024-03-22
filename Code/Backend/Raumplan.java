package Code.Backend;

import java.util.ArrayList;

public class Raumplan {
    private ArrayList<ArrayList<String>> schuelernamen = new ArrayList<>();
    private ArrayList<String> raumName = new ArrayList<>();

    public void addRaum(String raumname){
        this.raumName.add(raumname);
    }

    public ArrayList<ArrayList<String>> getSchuelernamen(){
        return this.schuelernamen;
    }

    public void addSchueler(ArrayList<String> schuelernamen){
        this.schuelernamen.add(schuelernamen);
    }

    public void setRaum(String raumname, int position){
        this.raumName.set(position, raumname);
    }

    public String print(){
        String t = "Schueler: ";
        for(ArrayList<String> s: this.schuelernamen){
            for(String name : s){
                t += name + ", ";
            }
            t += ", ";
        }
        t += "; Räume: ";
        for(String r : this.raumName){
            t += r + ", ";
        }
        return t;
    }
}
