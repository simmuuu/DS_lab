import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

public class HelloWorldClient {
    public static void main(String[] args) throws Exception {
        URL url = new URL("http://localhost:7779/ws/hello?wsdl");
        QName qname = new QName("http://hello/", "HelloWorldImplService");
        Service service = Service.create(url, qname);
        HelloWorld hw = service.getPort(HelloWorld.class);
        System.out.println(hw.sayHello("Distributed Systems"));
    }
}
