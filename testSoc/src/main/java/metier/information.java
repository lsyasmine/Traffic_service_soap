package metier;


import jakarta.xml.bind.annotation.XmlElement;
import java.io.Serializable;

public class information implements Serializable {
    private String labelle;
    private int accidents;
    private int tues;
    private int blesses;

    public information() {
    }

    public information(String labelle, int accidents, int tues, int blesses) {
        this.labelle = labelle;
        this.accidents = accidents;
        this.tues = tues;
        this.blesses = blesses;
    }

    public information(int accidents, int tues, int blesses) {
        this.accidents = accidents;
        this.blesses = blesses;
        this.tues = tues;
    }

    @XmlElement
    public String getLabelle() {
        return labelle;
    }

    public int getAccidents() {
        return accidents;
    }

    public int getBlesses() {
        return blesses;
    }

    public int getTues() {
        return tues;
    }

    public void setLabelle(String labelle) {
        this.labelle = labelle;
    }

    public void setAccidents(int accidents) {
        this.accidents = accidents;
    }

    public void setBlesses(int blesses) {
        this.blesses = blesses;
    }

    public void setTues(int tues) {
        this.tues = tues;
    }
}
