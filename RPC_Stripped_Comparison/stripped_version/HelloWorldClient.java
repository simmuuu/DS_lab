import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloWorldClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:7780/ws/hello?wsdl");
        
        // IMPORTANT: QName values must match what's in the actual WSDL
        // For stripped version without package:
        //   - namespace: JAX-WS auto-generates (check WSDL for exact value)
        //   - service name: "HelloWorldImplService" (ClassName + "Service")
        
        // ALWAYS check WSDL first: http://localhost:7780/ws/hello?wsdl
        // Look for <service name="..."> and targetNamespace="..."
        
        // Example - MUST REPLACE with actual values from WSDL:
        String namespace = "REPLACE_WITH_ACTUAL_NAMESPACE_FROM_WSDL";
        String serviceName = "HelloWorldImplService";
        QName qname = new QName(namespace, serviceName);
        
        Service service = Service.create(url, qname);
        HelloWorldImpl hw = service.getPort(HelloWorldImpl.class);
        System.out.println(hw.sayHello("Distributed Systems"));
    }
}
