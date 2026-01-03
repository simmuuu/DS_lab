import javax.xml.ws.Endpoint;

public class Publisher {
    public static void main(String[] args) {
        Endpoint.publish("http://localhost:7780/ws/hello", new HelloWorldImpl());
        System.out.println("Stripped Version Service published at http://localhost:7780/ws/hello");
        System.out.println("WSDL available at http://localhost:7780/ws/hello?wsdl");
        System.out.println("\nNotice: Service name will be 'HelloWorldImplService' (auto-generated)");
        System.out.println("Namespace will be auto-generated from package");
    }
}
