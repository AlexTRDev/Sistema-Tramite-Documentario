package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.TipoTramiteAD;
import GestionTramiteDocumentos.AccesoDatos.UnidadOrganizativaAD;
import GestionTramiteDocumentos.AccesoDatos.UnidadTramiteAD;
import GestionTramiteDocumentos.Entidades.TipoTramite;
import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.util.List;

public class TipoTramiteLN {
    
    public List<TipoTramite> ConsultarAll() throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TipoTramiteAD tipoTramiteAD = new TipoTramiteAD(conexion.getConnection());
            return tipoTramiteAD.ConsultarAll();
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
    
    public List<TipoTramite> ConsultarAll(UnidadTramite unidadTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TipoTramiteAD tipoTramiteAD = new TipoTramiteAD(conexion.getConnection());
            return tipoTramiteAD.ConsultarAll(unidadTramite);
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
    
    public void Insertar(TipoTramite tipoTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TipoTramiteAD tipoTramiteAD = new TipoTramiteAD(conexion.getConnection());
            tipoTramiteAD.Insertar(tipoTramite);
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
    
    public void Eliminar(TipoTramite tipoTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TipoTramiteAD tipoTramiteAD = new TipoTramiteAD(conexion.getConnection());
            tipoTramiteAD.Eliminar(tipoTramite);
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
    
    public void Modificar(TipoTramite tipoTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TipoTramiteAD tipoTramiteAD = new TipoTramiteAD(conexion.getConnection());
            tipoTramiteAD.Modificar(tipoTramite);
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
