package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.UnidadTramiteAD;
import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.util.List;

public class UnidadTramiteLN {
    
    public List<UnidadTramite> ConsultarAll() throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UnidadTramiteAD unidadTramiteAD = new UnidadTramiteAD(conexion.getConnection());
            return unidadTramiteAD.ConsultarAll();
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
    
    public List<UnidadTramite> ConsultarAll(UnidadOrganizativa unidadOrganizativa) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UnidadTramiteAD unidadTramiteAD = new UnidadTramiteAD(conexion.getConnection());
            return unidadTramiteAD.ConsultarAll(unidadOrganizativa);
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
    
    public UnidadTramite Consultar1Abrev(UnidadTramite unidadTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UnidadTramiteAD unidadTramiteAD = new UnidadTramiteAD(conexion.getConnection());
            return unidadTramiteAD.Consultar1Abrev(unidadTramite);
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
    
    public void Insertar(UnidadTramite unidadTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UnidadTramiteAD unidadTramiteAD = new UnidadTramiteAD(conexion.getConnection());
            unidadTramiteAD.Insertar(unidadTramite);
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
    
    public void Eliminar(UnidadTramite unidadTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UnidadTramiteAD unidadTramiteAD = new UnidadTramiteAD(conexion.getConnection());
            unidadTramiteAD.Eliminar(unidadTramite);
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
    
    public void Modificar(UnidadTramite unidadTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UnidadTramiteAD unidadTramiteAD = new UnidadTramiteAD(conexion.getConnection());
            unidadTramiteAD.Modificar(unidadTramite);
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
