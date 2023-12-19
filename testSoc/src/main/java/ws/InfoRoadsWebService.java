package ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;

import java.io.File;
import java.util.List;

@WebService
public class InfoRoadsWebService {
    private List<metier.information> AccidentRoadsData;

    public InfoRoadsWebService() {
        loadAccidentRoadsDataFromXML();
    }
    private void loadAccidentRoadsDataFromXML() {
        try {
            File file = new File("C:\\Users\\wefistore\\Downloads\\test_Soap\\testSoc\\src\\main\\resources\\route2023.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(metier.Items.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Unmarshal the XML file into the Items object
            metier.Items items = (metier.Items) jaxbUnmarshaller.unmarshal(file);

            // Set the accidents data
            AccidentRoadsData = items.getAccidents();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @WebMethod
    public metier.information getAccidentDetailByRoadType(@WebParam(name = "RoadType") String RoadType) {
        for (metier.information acc : AccidentRoadsData) {
            if (acc.getLabelle().equalsIgnoreCase(RoadType)) {
                return acc;
            }
        }
        return new metier.information();
    }
}
