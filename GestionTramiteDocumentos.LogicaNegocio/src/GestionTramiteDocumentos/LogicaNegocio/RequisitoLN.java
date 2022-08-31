package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.RequisitoAD;
import GestionTramiteDocumentos.Entidades.Requisito;
import GestionTramiteDocumentos.Entidades.TipoTramite;
import java.util.List;

public class RequisitoLN {
    
    public List<Requisito> ConsultarAll(TipoTramite tipoTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RequisitoAD requisitoAD = new RequisitoAD(conexion.getConnection());
            return requisitoAD.ConsultarAll(tipoTramite);
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
  
    public void Insertar(Requisito tipoTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RequisitoAD requisitoAD = new RequisitoAD(conexion.getConnection());
            requisitoAD.Insertar(tipoTramite);
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
    
    public void Eliminar(Requisito tipoTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RequisitoAD requisitoAD = new RequisitoAD(conexion.getConnection());
            requisitoAD.Eliminar(tipoTramite);
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
    
    public void Modificar(Requisito tipoTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RequisitoAD requisitoAD = new RequisitoAD(conexion.getConnection());
            requisitoAD.Modificar(tipoTramite);
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
