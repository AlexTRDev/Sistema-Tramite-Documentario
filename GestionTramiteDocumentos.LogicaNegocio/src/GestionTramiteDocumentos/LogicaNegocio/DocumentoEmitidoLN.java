package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.DocumentoEmitidoAD;
import GestionTramiteDocumentos.AccesoDatos.DocumentoRecibidoAD;
import GestionTramiteDocumentos.Entidades.DocumentoEmitido;
import GestionTramiteDocumentos.Entidades.TipoDocumento;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.util.Date;
import java.util.List;

public class DocumentoEmitidoLN {
    
    public void Insertar(DocumentoEmitido objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoEmitidoAD documentoEmitidoAD = new DocumentoEmitidoAD(conexion.getConnection());
            documentoEmitidoAD.Insertar(objeto);
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
    
    public void Eliminar(DocumentoEmitido objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoEmitidoAD documentoEmitidoAD = new DocumentoEmitidoAD(conexion.getConnection());
            documentoEmitidoAD.Eliminar(objeto);
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
    
    public void Modificar(DocumentoEmitido objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoEmitidoAD documentoEmitidoAD = new DocumentoEmitidoAD(conexion.getConnection());
            documentoEmitidoAD.Modificar(objeto);
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
    
    public DocumentoEmitido Consultar1(DocumentoEmitido objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoEmitidoAD documentoEmitidoAD = new DocumentoEmitidoAD(conexion.getConnection());
            return documentoEmitidoAD.Consultar1(objeto);
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
    
    public DocumentoEmitido Consultar1UniNumDoc(DocumentoEmitido objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoEmitidoAD documentoEmitidoAD = new DocumentoEmitidoAD(conexion.getConnection());
            return documentoEmitidoAD.Consultar1UniNumDoc(objeto);
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

    public List<DocumentoEmitido> ConsultarXFiltros(String sql) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoEmitidoAD documentoEmitidoAD = new DocumentoEmitidoAD(conexion.getConnection());
            return documentoEmitidoAD.ConsultarXFiltros(sql);
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

    public DocumentoEmitido Consultar1PorExpediente(DocumentoEmitido objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoEmitidoAD documentoEmitidoAD = new DocumentoEmitidoAD(conexion.getConnection());
            return documentoEmitidoAD.Consultar1PorExpediente(objeto);
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
    
    public List<DocumentoEmitido> Consultar1DocRecepcionado(DocumentoEmitido documentoEmitido) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoEmitidoAD documentoEmitidoAD = new DocumentoEmitidoAD(conexion.getConnection());
            return documentoEmitidoAD.Consultar1DocRecepcionado(documentoEmitido);
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
      
    
    public DocumentoEmitido Consultar1DocumentoCurso(DocumentoEmitido documentoEmitido) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);
            DocumentoEmitidoAD documentoEmitidoAD = new DocumentoEmitidoAD(conexion.getConnection());
            return documentoEmitidoAD.Consultar1DocumentoCurso(documentoEmitido);
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
    public DocumentoEmitido ConsultarUltimo() throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoEmitidoAD documentoEmitidoAD = new DocumentoEmitidoAD(conexion.getConnection());
            return documentoEmitidoAD.consultarUltimo();
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
    
    public DocumentoEmitido ConsultarUltimo(DocumentoEmitido documentoEmitido) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoEmitidoAD documentoEmitidoAD = new DocumentoEmitidoAD(conexion.getConnection());
            return documentoEmitidoAD.consultarUltimo(documentoEmitido);
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
    
    public String generarNumeroDocumento(UnidadTramite unidadTramite, TipoDocumento tipoDocumento) throws Exception{
         Conexion conexion = null;
        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocumentoRecibidoAD documentoRecibidoAD = new DocumentoRecibidoAD(conexion.getConnection());
            return documentoRecibidoAD.generarNumeroDocumento(unidadTramite, tipoDocumento);
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
