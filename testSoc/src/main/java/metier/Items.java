package metier;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "items")
public class Items {
    private List<information> informations;

    @XmlElement(name = "item")
    public List<information> getAccidents() {
        return informations;
    }

    public void setAccidents(List<information> informations) {
        this.informations = informations;
    }
}
