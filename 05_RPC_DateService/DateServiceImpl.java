import javax.jws.WebService;
import java.util.Date;
import java.text.SimpleDateFormat;

// CAN BE STRIPPED: serviceName (optional - defaults to "DateServiceImplService")
// CAN BE STRIPPED: endpointInterface (optional - can work without interface)
// CAN BE STRIPPED: targetNamespace (optional - auto-generated from package)
@WebService(
    serviceName = "DateService",
    endpointInterface = "DateService",
    targetNamespace = "http://date/"
)
public class DateServiceImpl implements DateService {
    public String getCurrentDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
    
    public String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        return sdf.format(new Date());
    }
    
    public String getCurrentDateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(new Date());
    }
}

/*
 * MINIMAL VERSION (everything stripped):
 * 
 * @WebService
 * public class DateServiceImpl {
 *     // No interface needed, no extra attributes
 *     public String getCurrentDate() { 
 *         SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
 *         return sdf.format(new Date()); 
 *     }
 *     public String getCurrentTime() { ... }
 *     public String getCurrentDateTime() { ... }
 * }
 * 
 * Note: Client must adjust QName to use auto-generated service name
 */
