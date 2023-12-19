package server;

import jakarta.xml.ws.Endpoint;
import ws.*;

public class ServerJWS {
    public static void main(String[] args) {
        String url1 = "http://0.0.0.0:8086/AccidentsReason";
        String url2 = "http://0.0.0.0:8086/HourlyAccidents";
        String url3 = "http://0.0.0.0:8086/AccidentGovernment";
        String url4 = "http://0.0.0.0:8086/AccidentImpliedBy";
        String url5 = "http://0.0.0.0:8086/MonthlyAccidents";
        String url6 = "http://0.0.0.0:8086/AccidentsByRoad";
        Endpoint.publish(url1, new InfoReasonWebService());
        Endpoint.publish(url2, new HourlyInfoWebService());
        Endpoint.publish(url3, new InfoGovernmentWebService());
        Endpoint.publish(url4, new InfoImpliedByWebService());
        Endpoint.publish(url5, new MonthlyInfoWebService());
        Endpoint.publish(url6, new InfoRoadsWebService());
        System.out.println("AccidentsReason Web Service est deployé sur " + url1);
        System.out.println("HourlyAccident Web Service est deployé sur " + url2);
        System.out.println("AccidentGovernment Web Service est deployé sur " + url3);
        System.out.println("AccidentImpliedBy Web Service est deployé sur " + url4);
        System.out.println("MonthlyAccident Web Service est deployé sur " + url5);
        System.out.println("AccidentRoads Web Service est deployé sur " + url6);


    }
}
