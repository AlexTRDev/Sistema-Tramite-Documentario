package Conector;

import java.sql.Connection;
import java.sql.DriverManager;

import Configuracion.Configuracion;

public class Conexion {
    private Connection connection;

    public void Abrir(Boolean autoConfirmacion) throws Exception {
        try {
//                String servidor = Configuracion.getServidorBaseDatos();
//                String puerto = Configuracion.getPuertoBaseDatos();
//                String nombre = Configuracion.getNombreBaseDatos();
//                String usuario = Configuracion.getUsuarioBaseDatos();
//                String contrasena = Configuracion.getContrasenaBaseDatos();
                
                String servidor ="localhost";
                String puerto="5432";
                String nombre="PROYECTO";
                String usuario ="postgres";
                String contrasena="postgres";
//                String Driver = "jdbc:mysql://"  --> driver de MYSQL

                String cadenaconnection = "jdbc:postgresql://" + servidor + ":" + puerto + "/" + nombre;

                connection = DriverManager.getConnection(cadenaconnection,usuario,contrasena);

                connection.setAutoCommit(autoConfirmacion);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public Connection getConnection() throws Exception {
        return this.connection;
    }

    public void setTRANSACTION_READ_UNCOMMITTED() throws Exception {
        try {
            connection.setTransactionIsolation(connection.TRANSACTION_READ_UNCOMMITTED);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public void setTRANSACTION_READ_COMMITTED() throws Exception {
        try {
            connection.setTransactionIsolation(connection.TRANSACTION_READ_COMMITTED);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public void setTRANSACTION_REPEATABLE_READ() throws Exception {
        try {
            connection.setTransactionIsolation(connection.TRANSACTION_REPEATABLE_READ);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public void setTRANSACTION_SERIALIZABLE() throws Exception {
        try {
            connection.setTransactionIsolation(connection.TRANSACTION_SERIALIZABLE);
        }
        catch (Exception e) {
            throw e;
        }
    }

    public boolean EstaCerrada() throws Exception {
        try {
            Boolean cerrada;

           if(connection != null) {
                cerrada = connection.isClosed();
           }
           else {
               cerrada = true;
           }

            return cerrada;
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Cerrar() throws Exception {
        try {
            if(!EstaCerrada()) {
                connection.close();
            }
        }
        catch(Exception e) {
            throw e;
        }
    }
}