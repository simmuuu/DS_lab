import javax.xml.ws.Endpoint;

public class DateServer {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:7779/ws/date", new DateServiceImpl());
        System.out.println("Date Service published...");
    }
}
