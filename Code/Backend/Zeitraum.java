package Code.Backend;

import java.util.Date;

public class Zeitraum {
    private Date anfangsZeit;
    private Date endZeit;
    private String bezeichnung;

    public void setAnfangsZeit(Date anfangsZeit){
        this.anfangsZeit = anfangsZeit;
    }

    public void setEndZeit(Date endZeit){
        this.endZeit = endZeit;
    }

    public Date getAnfangsZeit(){
        return this.anfangsZeit;
    }

    public Date getEndZeit(){
        return this.endZeit;
    }

    public String getString(){
        return this.anfangsZeit.getHours()+":"+this.anfangsZeit.getMinutes() + " - " + this.endZeit.getHours()+":"+this.endZeit.getMinutes();
    }
}
