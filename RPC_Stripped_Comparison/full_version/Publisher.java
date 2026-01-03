import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:7779/ws/hello", new HelloWorldImpl());
        System.out.println("Full Version Service published at http://localhost:7779/ws/hello");
        System.out.println("WSDL available at http://localhost:7779/ws/hello?wsdl");
    }
}
