package Code.Backend;

import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class RaumUndZeitplan {
    private ArrayList<Raum> raeume = new ArrayList<>();
    private ArrayList<Zeitraum> bloecke = new ArrayList<>();

    public void addRaum(Raum raum){
        this.raeume.add(raum);
    }

    public void addZeitraum(){
        Zeitraum z = new Zeitraum();
        Date startDate = new Date();
        if(this.bloecke.isEmpty()){
            startDate.setHours(8);
            startDate.setMinutes(45);

        } else {
            Zeitraum letzterZeitraum = this.bloecke.get(this.bloecke.size()-1);
            startDate.setHours(letzterZeitraum.getEndZeit().getHours());
            startDate.setMinutes(letzterZeitraum.getEndZeit().getMinutes());
            startDate.setTime(startDate.getTime() + TimeUnit.MINUTES.toMillis(20));
        }

        Date endDate = new Date();
        endDate.setHours(startDate.getHours());
        endDate.setMinutes(startDate.getMinutes());

        endDate.setTime(endDate.getTime() + TimeUnit.MINUTES.toMillis(45));
        z.setAnfangsZeit(startDate);
        z.setEndZeit(endDate);

        this.bloecke.add(z);
    }

    public void printRaumUndZeitplan(){
        for(Zeitraum block : this.bloecke){
            System.out.println(block.getString());
        }
    }


}
