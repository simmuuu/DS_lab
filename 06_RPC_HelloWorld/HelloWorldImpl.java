import javax.jws.WebService;

// CAN BE STRIPPED: serviceName (defaults to "HelloWorldImplService")
// CAN BE STRIPPED: endpointInterface (optional when no interface used)
// CAN BE STRIPPED: targetNamespace (auto-generated from package)
@WebService(
    serviceName = "HelloWorld",
    endpointInterface = "HelloWorld",
    targetNamespace = "http://hello/"
)
public class HelloWorldImpl implements HelloWorld {
    public String sayHello(String name) {
        return "Hello " + name + " from RPC Service!";
    }
}

/*
 * MINIMAL VERSION (stripped):
 * 
 * @WebService
 * public class HelloWorldImpl {
 *     public String sayHello(String name) {
 *         return "Hello " + name + " from RPC Service!";
 *     }
 * }
 * 
 * See /RPC_Stripped_Comparison folder for complete working examples
 */
