import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

// CAN BE STRIPPED: targetNamespace attribute (optional)
@WebService(targetNamespace = "http://hello/")
// CAN BE STRIPPED: @SOAPBinding annotation (optional - defaults to DOCUMENT style)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface HelloWorld {
    // CAN BE STRIPPED: @WebMethod annotation (optional - public methods auto-exposed)
    @WebMethod
    String sayHello(String name);
}

/*
 * WHAT CAN BE STRIPPED:
 * 
 * 1. targetNamespace - defaults to package-based namespace
 * 2. @SOAPBinding - defaults to DOCUMENT/LITERAL style
 * 3. @WebMethod - all public methods automatically exposed
 * 4. This entire interface - can define directly in implementation
 * 
 * See /RPC_Stripped_Comparison folder for working examples
 */
