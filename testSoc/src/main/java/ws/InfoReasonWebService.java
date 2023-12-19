package ws;

import jakarta.jws.WebParam;
import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@WebService
public class InfoReasonWebService {
    private List<metier.information> accidentsCauseData;

    public InfoReasonWebService() {
        loadaccidentsCauseDataFromXML();
    }

    private void loadaccidentsCauseDataFromXML() {
        try {
            File file = new File("C:\\Users\\wefistore\\Downloads\\test_Soap\\testSoc\\src\\main\\resources\\cause2023.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(metier.Items.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Unmarshal the XML file into the Items object
            metier.Items items = (metier.Items) jaxbUnmarshaller.unmarshal(file);

            // Set the accidents data
            accidentsCauseData = items.getAccidents();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    @WebMethod
    public int CauseGetTotalDetails() {
        int totalAccidents = 0;
        int totalTues = 0;
        int totalBlesses = 0;

        for (metier.information acc : accidentsCauseData) {
            totalAccidents += acc.getAccidents();
            totalTues += acc.getTues();
            totalBlesses += acc.getBlesses();
        }

        return totalAccidents + totalTues + totalBlesses;
    }

    @WebMethod
    public metier.information getAccidentDetailByCause(@WebParam(name = "cause") String cause) {
        for (metier.information acc : accidentsCauseData) {
            if (acc.getLabelle().equalsIgnoreCase(cause)) {
                return acc;
            }
        }
        return new metier.information();
    }
}
