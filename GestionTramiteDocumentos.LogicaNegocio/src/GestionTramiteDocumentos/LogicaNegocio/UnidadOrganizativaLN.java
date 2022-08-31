package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.UnidadOrganizativaAD;
import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import java.util.List;

public class UnidadOrganizativaLN {
    
    public List<UnidadOrganizativa> ConsultarAll() throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UnidadOrganizativaAD unidadOrganizativaAD = new UnidadOrganizativaAD(conexion.getConnection());
            return unidadOrganizativaAD.ConsultarAll();
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
    
    public void Insertar(UnidadOrganizativa unidadOrganizativa) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UnidadOrganizativaAD unidadOrganizativaAD = new UnidadOrganizativaAD(conexion.getConnection());
            unidadOrganizativaAD.Insertar(unidadOrganizativa);
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
    
    public void Eliminar(UnidadOrganizativa unidadOrganizativa) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UnidadOrganizativaAD unidadOrganizativaAD = new UnidadOrganizativaAD(conexion.getConnection());
            unidadOrganizativaAD.Eliminar(unidadOrganizativa);
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
    
    public void Modificar(UnidadOrganizativa unidadOrganizativa) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UnidadOrganizativaAD unidadOrganizativaAD = new UnidadOrganizativaAD(conexion.getConnection());
            unidadOrganizativaAD.Modificar(unidadOrganizativa);
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
