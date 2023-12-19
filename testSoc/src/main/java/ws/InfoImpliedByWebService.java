package ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import metier.information;

import java.io.File;
import java.util.List;

@WebService
public class InfoImpliedByWebService {
    private List<metier.information> accidentsImpliedByData;

    public InfoImpliedByWebService() {
        loadaccidentsImpliedByDataFromXML();
    }
    private void loadaccidentsImpliedByDataFromXML() {
        try {
            File file = new File("C:\\Users\\wefistore\\Downloads\\test_Soap\\testSoc\\src\\main\\resources\\implique2023.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(metier.Items.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Unmarshal the XML file into the Items object
            metier.Items items = (metier.Items) jaxbUnmarshaller.unmarshal(file);

            // Set the accidents data
            accidentsImpliedByData = items.getAccidents();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @WebMethod
    public int ImpliedByGetTotalDetails() {
        int totalAccidents = 0;
        int totalTues = 0;
        int totalBlesses = 0;

        for (metier.information acc : accidentsImpliedByData) {
            totalAccidents += acc.getAccidents();
            totalTues += acc.getTues();
            totalBlesses += acc.getBlesses();
        }
        return totalAccidents + totalTues + totalBlesses;
    }
    @WebMethod
    public metier.information getAccidentDetailByImpliedBy(@WebParam(name = "ImpliedBy") String ImpliedBy) {
        for (metier.information acc : accidentsImpliedByData) {
            if (acc.getLabelle().equalsIgnoreCase(ImpliedBy)) {
                return acc;
            }
        }
        return new metier.information();
    }
    @WebMethod
    public void AddAccidentImpliedBy(String ImpliedByLabelle, information newAccident) {
        accidentsImpliedByData.add(newAccident);
    }
    
}
