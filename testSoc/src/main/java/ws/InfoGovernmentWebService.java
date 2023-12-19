package ws;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import metier.information;

import java.io.File;
import java.util.List;
import java.util.Scanner;

@WebService
public class InfoGovernmentWebService {
    private List<information> GovernmentData;

    public InfoGovernmentWebService() {
    	
    }
    //charger des données d'accidents à partir d'un fichier XML à l'aide de la technologie JAXB (Java Architecture for XML Binding)
    private void loadaccidentsGovernmentDataFromXML() {
        try {
            File file = new File("C:\\Users\\wefistore\\Downloads\\test_Soap\\testSoc\\src\\main\\resources\\gouvernorat2023.xml");
            JAXBContext jaxbContext = JAXBContext.newInstance(metier.Items.class);
            // Crée un objet Unmarshaller à partir du contexte JAXB. 
             //L'Unmarshaller est utilisé pour convertir le contenu XML en objets Java.
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();

            // désérialiser le contenu du fichier XML en un objet de type Items
            metier.Items items = (metier.Items) jaxbUnmarshaller.unmarshal(file);

            // Récupère les données  à partir de l'objet Items
            GovernmentData = items.getAccidents();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }
    @WebMethod
    public int GovernmentGetTotalDetails() {
        int totalAccidents = 0;
        int totalTues = 0;
        int totalBlesses = 0;

        for (metier.information acc :GovernmentData) {
            totalAccidents += acc.getAccidents();
            totalTues += acc.getTues();
            totalBlesses += acc.getBlesses();
        }
        return totalAccidents + totalTues + totalBlesses;
    }
    @WebMethod
    public String GetGouvermentWithMaxBlesses() {
        String gouvernementMaxBlesses = "";
        int maxBlesses = 0;

        for (metier.information acc :GovernmentData) {
            int blesses = 0;
            if (acc.getBlesses() > maxBlesses) {
                maxBlesses = blesses;
                gouvernementMaxBlesses = acc.getLabelle();
            }
        }

        return gouvernementMaxBlesses;
    }
    @WebMethod
    public void DeleteAccidentByGovernment(String governmentLabelle) {
        GovernmentData.removeIf(acc -> acc.getLabelle().equals(governmentLabelle));
    }

    @WebMethod
    public void AddAccidentGovernment(String governmentLabelle, information newAccident) {
        GovernmentData.add(newAccident);
    }
    @WebMethod
    public void AddAccidentByUserInput() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Entrez le labelle du gouvernement : ");
        String labelle = scanner.nextLine();

        System.out.println("Entrez le nombre d'accidents : ");
        int accidents = scanner.nextInt();

        System.out.println("Entrez le nombre de tués : ");
        int tues = scanner.nextInt();

        System.out.println("Entrez le nombre de blessés : ");
        int blesses = scanner.nextInt();

        information newAccident = new information(labelle, accidents, tues, blesses);
        GovernmentData.add(newAccident);

        System.out.println("Accident ajouté avec succès.");
    }
}
