import javax.jws.WebService;
import java.util.Date;
import java.text.SimpleDateFormat;

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
