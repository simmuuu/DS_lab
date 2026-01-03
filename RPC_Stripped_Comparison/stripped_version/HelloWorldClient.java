import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloWorldClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:7780/ws/hello?wsdl");
        
        // Note: QName must match what's in WSDL
        // For stripped version: namespace is auto-generated, service name is "HelloWorldImplService"
        // Check the WSDL at http://localhost:7780/ws/hello?wsdl to get exact values
        
        // This will work if you check WSDL first and update accordingly
        // Typically: namespace="http://stripped_version/" and service="HelloWorldImplService"
        QName qname = new QName("http://stripped_version/", "HelloWorldImplService");
        
        Service service = Service.create(url, qname);
        HelloWorldImpl hw = service.getPort(HelloWorldImpl.class);
        System.out.println(hw.sayHello("Distributed Systems"));
    }
}
