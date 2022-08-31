package Configuracion;

import java.util.prefs.Preferences;
import java.util.ResourceBundle;

public class Registro {
    private static ResourceBundle configuracionEstatica = ResourceBundle.getBundle("Configuracion.Variables");
    private static Preferences configuracionDinamica = Preferences.userRoot().node("/SIB");

    public static String getValor(String clave) {
        String valor;

        if(!configuracionDinamica.get(clave,"").isEmpty()) {
            valor = configuracionDinamica.get(clave,"");
        }
        else {
            valor = configuracionEstatica.getString(clave + "_defecto");
        }

        return valor;
    }

    public static void setValor(String clave, String valor) {
        configuracionDinamica.put(clave,valor);
    }
}