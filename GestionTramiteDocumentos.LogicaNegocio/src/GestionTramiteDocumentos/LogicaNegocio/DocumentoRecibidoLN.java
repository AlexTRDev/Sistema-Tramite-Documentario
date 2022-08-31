package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.DocumentoRecibidoAD;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.util.Date;

public class DocumentoRecibidoLN {
    
    public void Insertar(DocumentoRecibido documentoRecibido) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoRecibidoAD documentoRecibidoAD = new DocumentoRecibidoAD(conexion.getConnection());
            documentoRecibidoAD.Insertar(documentoRecibido);
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
    
    public void Eliminar(DocumentoRecibido documentoRecibido) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoRecibidoAD documentoRecibidoAD = new DocumentoRecibidoAD(conexion.getConnection());
            documentoRecibidoAD.Insertar(documentoRecibido);
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
    
    public void Modificar(DocumentoRecibido documentoRecibido) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoRecibidoAD documentoRecibidoAD = new DocumentoRecibidoAD(conexion.getConnection());
            documentoRecibidoAD.Modificar(documentoRecibido);
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
    
    public DocumentoRecibido Consultar11(DocumentoRecibido documentoRecibido) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoRecibidoAD documentoRecibidoAD = new DocumentoRecibidoAD(conexion.getConnection());
            return documentoRecibidoAD.Consultar1(documentoRecibido);
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
    
    public DocumentoRecibido Consultar1NumExp(DocumentoRecibido documentoRecibido) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoRecibidoAD documentoRecibidoAD = new DocumentoRecibidoAD(conexion.getConnection());
            return documentoRecibidoAD.Consultar1NumExp(documentoRecibido);
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
    
    public DocumentoRecibido ConsultarUltimo() throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoRecibidoAD documentoRecibidoAD = new DocumentoRecibidoAD(conexion.getConnection());
            return documentoRecibidoAD.consultarUltimo();
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
    
    public DocumentoRecibido ConsultarUltimoUT(UnidadTramite unidadTramite) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoRecibidoAD documentoRecibidoAD = new DocumentoRecibidoAD(conexion.getConnection());
            return documentoRecibidoAD.consultarUltimoUT(unidadTramite);
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
    
    public Date ConsultarFechaBD() throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoRecibidoAD documentoRecibidoAD = new DocumentoRecibidoAD(conexion.getConnection());
            return documentoRecibidoAD.consultarFechaBD();
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
