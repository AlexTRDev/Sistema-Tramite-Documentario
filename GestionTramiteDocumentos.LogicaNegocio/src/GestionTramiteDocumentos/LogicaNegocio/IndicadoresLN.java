package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.IndicadoresAD;
import GestionTramiteDocumentos.Entidades.DocumentoEmitido;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.Indicador;
import GestionTramiteDocumentos.Entidades.Ruta;
import java.util.List;

public class IndicadoresLN {
    public List<Ruta> ConsultarDocumentosRecibidos(Indicador indicador) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            IndicadoresAD indicadoresAD = new IndicadoresAD(conexion.getConnection());
            return indicadoresAD.consultarDocumentosRecibidos(indicador);
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
    
    public List<DocumentoEmitido> ConsultarDocumentosEnviados(Indicador indicador) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            IndicadoresAD indicadoresAD = new IndicadoresAD(conexion.getConnection());
            return indicadoresAD.consultarDocumentosEnviados(indicador);
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
    
    public List<DocumentoRecibido> ConsultarDocumentosRecibidosFinalizados(Indicador indicador) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            IndicadoresAD indicadoresAD = new IndicadoresAD(conexion.getConnection());
            return indicadoresAD.consultarDocumentosRecibidosFinalizados(indicador);
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

    public double calcularDuracionReal(DocumentoRecibido documentoRecibido) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            IndicadoresAD indicadoresAD = new IndicadoresAD(conexion.getConnection());
            return indicadoresAD.calcularDuracionReal(documentoRecibido);
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
}
