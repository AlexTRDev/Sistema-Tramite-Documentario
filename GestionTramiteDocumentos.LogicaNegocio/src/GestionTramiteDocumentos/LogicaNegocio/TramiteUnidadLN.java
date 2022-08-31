package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.TramiteUnidadAD;
import GestionTramiteDocumentos.Entidades.Tramite;
import GestionTramiteDocumentos.Entidades.TramiteUnidad;

public class TramiteUnidadLN {
    
    public TramiteUnidad ConsultarAll(Tramite objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TramiteUnidadAD tramiteUnidadAD = new TramiteUnidadAD(conexion.getConnection());
            return  tramiteUnidadAD.Consultar1(objeto);
        }
        catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
    public void Insertar(TramiteUnidad objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TramiteUnidadAD tramiteUnidadAD = new TramiteUnidadAD(conexion.getConnection());
            tramiteUnidadAD.Insertar(objeto);
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
    public void Eliminar(TramiteUnidad objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TramiteUnidadAD tramiteUnidadAD = new TramiteUnidadAD(conexion.getConnection());
            tramiteUnidadAD.Eliminar(objeto);
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
    public void ModificarUnidad(TramiteUnidad objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TramiteUnidadAD tramiteUnidadAD = new TramiteUnidadAD(conexion.getConnection());
            tramiteUnidadAD.ModificarUnidad(objeto);
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
}
