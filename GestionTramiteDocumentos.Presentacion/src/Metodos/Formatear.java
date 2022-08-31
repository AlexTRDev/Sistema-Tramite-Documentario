import com.toedter.calendar.JDateChooser;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author Alex
 */
public class Formatear {
    
    public static String getFecha(Date date,String Formato){
        SimpleDateFormat formato = new SimpleDateFormat(Formato);
        
        return formato.format(date);
    }
    
    public static String getFecha(JDateChooser jDateChooser,String Formato){
        SimpleDateFormat formato = new SimpleDateFormat(Formato);
        
        return formato.format(jDateChooser.getDate());
    }
    
    public static String getFecha(Timer timer,String Formato){
        SimpleDateFormat formato = new SimpleDateFormat(Formato);
        
        return formato.format(timer);
    }
    
    public static String getFecha(Timestamp timestamp,String Formato){
        SimpleDateFormat formato = new SimpleDateFormat(Formato);
        
        return formato.format(timestamp.getTimestamp());
    }
    
}
