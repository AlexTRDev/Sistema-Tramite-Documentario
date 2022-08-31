package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.TipoDocumentoAD;
import GestionTramiteDocumentos.Entidades.TipoDocumento;
import java.util.List;

public class TipoDocumentoLN {
    
    public List<TipoDocumento> ConsultarAll() throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TipoDocumentoAD tipoDocumentoAD = new TipoDocumentoAD(conexion.getConnection());
            return tipoDocumentoAD.ConsultarAll();
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
    
    public TipoDocumento Consultar1(TipoDocumento objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TipoDocumentoAD tipoDocumentoAD = new TipoDocumentoAD(conexion.getConnection());
            return tipoDocumentoAD.Consultar1(objeto);
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
    
    public void Insertar(TipoDocumento tipoDocumento) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TipoDocumentoAD tipoDocumentoAD = new TipoDocumentoAD(conexion.getConnection());
            tipoDocumentoAD.Insertar(tipoDocumento);
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
    
    public void Eliminar(TipoDocumento tipoDocumento) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TipoDocumentoAD tipoDocumentoAD = new TipoDocumentoAD(conexion.getConnection());
            tipoDocumentoAD.Eliminar(tipoDocumento);
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
    
    public void Modificar(TipoDocumento tipoDocumento) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            TipoDocumentoAD tipoDocumentoAD = new TipoDocumentoAD(conexion.getConnection());
            tipoDocumentoAD.Modificar(tipoDocumento);
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
