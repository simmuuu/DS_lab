import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

// CAN BE STRIPPED: targetNamespace (optional - auto-generated from package if omitted)
@WebService(targetNamespace = "http://date/")
// CAN BE STRIPPED: @SOAPBinding annotation (optional - defaults to DOCUMENT style)
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DateService {
    // CAN BE STRIPPED: @WebMethod annotations (optional - all public methods exposed by default)
    @WebMethod
    String getCurrentDate();
    
    @WebMethod
    String getCurrentTime();
    
    @WebMethod
    String getCurrentDateTime();
}

/*
 * WHAT CAN BE STRIPPED FROM RPC PROGRAMS:
 * 
 * 1. targetNamespace attribute - Auto-generated from package name
 * 2. @SOAPBinding annotation - Defaults to DOCUMENT/LITERAL style  
 * 3. @WebMethod annotations - All public methods automatically exposed
 * 4. This entire interface - Can define methods directly in implementation class
 * 
 * MINIMAL VERSION:
 * @WebService
 * public class DateServiceImpl {
 *     public String getCurrentDate() { ... }
 *     public String getCurrentTime() { ... }
 *     public String getCurrentDateTime() { ... }
 * }
 */
