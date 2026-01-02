import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(targetNamespace = "http://date/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface DateService {
    @WebMethod
    String getCurrentDate();
    
    @WebMethod
    String getCurrentTime();
    
    @WebMethod
    String getCurrentDateTime();
}
