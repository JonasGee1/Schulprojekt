package Code.Backend;

public class Raum {
    private String bezeichnung;
    private int kapazitaet;

    public void setBezeichnung(String bezeichnung){
        this.bezeichnung = bezeichnung;
    }

    public void setKapazitaet(int kapazitaet){
        this.kapazitaet = kapazitaet;
    }

    public String getBezeichnung(){
        return this.bezeichnung;
    }

    public int getKapazitaet(){
        return this.kapazitaet;
    }
}
