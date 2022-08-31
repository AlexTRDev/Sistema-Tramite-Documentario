package Configuracion;

public class Configuracion {
    private static String servidorBaseDatos = Registro.getValor("servidor_basedatos");
    private static String puertoBaseDatos = Registro.getValor("puerto_basedatos");
    private static String nombreBaseDatos = Registro.getValor("nombre_basedatos");
    private static String usuarioBaseDatos = Registro.getValor("usuario_basedatos");
    private static String contrasenaBaseDatos = Registro.getValor("contrasena_basedatos");


    public static String getServidorBaseDatos() {
        return servidorBaseDatos;
    }

    public static void setServidorBaseDatos(String servidorBaseDatos) {
        Registro.setValor("servidor_basedatos",servidorBaseDatos);

        Configuracion.servidorBaseDatos = servidorBaseDatos;
    }

    public static String getPuertoBaseDatos() {
        return puertoBaseDatos;
    }

    public static void setPuertoBaseDatos(String puertoBaseDatos) {
        Registro.setValor("puerto_basedatos",puertoBaseDatos);

        Configuracion.puertoBaseDatos = puertoBaseDatos;
    }

    public static String getNombreBaseDatos() {
        return nombreBaseDatos;
    }

    public static void setNombreBaseDatos(String nombreBaseDatos) {
        Registro.setValor("nombre_basedatos",nombreBaseDatos);

        Configuracion.nombreBaseDatos = nombreBaseDatos;
    }

    public static String getUsuarioBaseDatos() {
        return usuarioBaseDatos;
    }

    public static void setUsuarioBaseDatos(String usuarioBaseDatos) {
        Registro.setValor("usuario_basedatos",usuarioBaseDatos);

        Configuracion.usuarioBaseDatos = usuarioBaseDatos;
    }

    public static String getContrasenaBaseDatos() {
        return contrasenaBaseDatos;
    }

    public static void setContrasenaBaseDatos(String contrasenaBaseDatos) {
        Registro.setValor("contrasena_basedatos",contrasenaBaseDatos);

        Configuracion.contrasenaBaseDatos = contrasenaBaseDatos;
    }
}