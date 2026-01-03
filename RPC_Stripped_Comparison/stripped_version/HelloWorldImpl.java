import javax.jws.WebService;

// STRIPPED VERSION: Removed targetNamespace, serviceName, endpointInterface, @SOAPBinding, @WebMethod
// No interface needed - service defined directly in implementation class
@WebService
public class HelloWorldImpl {
    // No @WebMethod needed - all public methods are automatically exposed
    public String sayHello(String name) {
        return "Hello " + name + " from Stripped RPC Service!";
    }
}
