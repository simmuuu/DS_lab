import javax.jws.WebService;

// CAN BE STRIPPED: serviceName (if stripped, defaults to class name + "Service")
// CAN BE STRIPPED: endpointInterface (optional when no separate interface used)
// CAN BE STRIPPED: targetNamespace (auto-generated from package if omitted)
// Note: This full version uses explicit serviceName="HelloWorld" and interface
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
 * MINIMAL VERSION (all optional elements stripped):
 * 
 * @WebService
 * public class HelloWorldImpl {
 *     public String sayHello(String name) {
 *         return "Hello " + name + " from RPC Service!";
 *     }
 * }
 * 
 * In minimal version:
 * - No interface needed
 * - serviceName auto-generated as "HelloWorldImplService"
 * - namespace auto-generated from package
 * 
 * See /RPC_Stripped_Comparison folder for complete working examples
 */
