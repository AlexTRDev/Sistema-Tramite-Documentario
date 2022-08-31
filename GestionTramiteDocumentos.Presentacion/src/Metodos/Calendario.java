
package Metodos;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Calendario {
    public static String getFechaTxt(Date date){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        return formato.format(date);
    }
    public static String getHoraTxt(Date date){
        SimpleDateFormat formato = new SimpleDateFormat("HH:mm:ss");
        return formato.format(date);
    }
    
    public static String getFechaHoraTxt(Date date){
        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return formato.format(date);
    }
    
    public static Date getFechaDate(String fecha){
        String f=fecha.substring(6,10)+"/"+fecha.substring(3,5)+"/"+fecha.substring(0,2)+" "+fecha.substring(10,fecha.length());
        Date date = new Date(f);
        return date;
    }

    
}
