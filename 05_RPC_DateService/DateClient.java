import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class DateClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:7779/ws/date?wsdl");
        QName qname = new QName("http://date/", "DateService");
        Service service = Service.create(url, qname);
        DateService ds = service.getPort(DateService.class);
        
        System.out.println("Current Date: " + ds.getCurrentDate());
        System.out.println("Current Time: " + ds.getCurrentTime());
        System.out.println("Current DateTime: " + ds.getCurrentDateTime());
    }
}
