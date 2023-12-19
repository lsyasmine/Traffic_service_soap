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

public class MonthlyInfoWebService {
    private List<metier.information> MonthlyaccidentsData;

    public MonthlyInfoWebService() {
        loadmonthlyaccidentsDataFromXML();
    }
    private void loadmonthlyaccidentsDataFromXML() {
        try {
            File file = new File("C:\\Users\\wefistore\\Downloads\\test_Soap\\testSoc\\src\\main\\resources\\mois2023.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(metier.Items.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // Unmarshal the XML file into the Items object
            metier.Items items = (metier.Items) jaxbUnmarshaller.unmarshal(file);

            // Set the accidents data
            MonthlyaccidentsData = items.getAccidents();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @WebMethod
    public metier.information getAccidentDetailByMonth(@WebParam(name = "mois") String month) {
        for (metier.information acc : MonthlyaccidentsData) {
            if (acc.getLabelle().equalsIgnoreCase(month)) {
                return acc;
            }
        }
        return new metier.information();
    }
    @WebMethod
    public String GetMonthWithMaxAccidents() {
        String MonthWithMaxAccidents = "";
        int maxAccidents = 0;

        for (metier.information acc :MonthlyaccidentsData) {
            int blesses = 0;
            if (acc.getAccidents() > maxAccidents) {
                maxAccidents = blesses;
                MonthWithMaxAccidents = acc.getLabelle();
            }
        }

        return MonthWithMaxAccidents;
    }
}
