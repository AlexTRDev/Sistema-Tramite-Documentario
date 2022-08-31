package GestionTramiteDocumentos.AccesoDatos;
import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.DocumentoEmitido;
import GestionTramiteDocumentos.Entidades.Indicador;
import GestionTramiteDocumentos.Entidades.Ruta;
import GestionTramiteDocumentos.Entidades.TipoDocumento;
import GestionTramiteDocumentos.Entidades.Tramite;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class IndicadoresAD extends EntidadAD {
    UnidadOrganizativaAD unidadOrganizativaAD;
    UnidadTramiteAD unidadTramiteAD;
    TipoDocumentoAD tipoDocumentoAD;
    DocumentoRecibidoAD documentoRecibidoAD;
    TramiteAD tramiteAD;
    public IndicadoresAD(Connection connection) {
        super(connection);
        unidadOrganizativaAD = new UnidadOrganizativaAD(connection);
        unidadTramiteAD = new UnidadTramiteAD(connection);
        tipoDocumentoAD = new TipoDocumentoAD(connection);
        documentoRecibidoAD = new DocumentoRecibidoAD(connection);
        tramiteAD = new TramiteAD(connection);
    }
    
    public List<Ruta> consultarDocumentosRecibidos(Indicador indicador) throws Exception {
        try {
            String sql = "select * from ruta\n" +
                         "where to_char(fecha_hora_recepcion,'YYYY') = '"+indicador.getAño()+"'\n" +
                         "and to_char(fecha_hora_recepcion,'MM') = '"+indicador.getMes()+"'\n" +
                         "and id_unidad_tramite = "+indicador.getId_unidad_tramite()+";";
            System.out.println(sql);
            List<Ruta> objetos = new ArrayList<>();
            Ruta objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                objeto = new Ruta();
                
                objeto.setId_ruta(rs.getInt("id_ruta"));
                objeto.setTipo_expediente(rs.getString("tipo_expediente"));
                objeto.setFechaHora_recepcion(rs.getDate("fecha_hora_recepcion"));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                System.out.println(rs.getInt("id_documento_recibido"));
                objeto.setDocumentoRecibido(documentoRecibidoAD.Consultar1(new DocumentoRecibido(rs.getInt("id_documento_recibido"))));
                
                objetos.add(objeto);
            }

            return objetos;
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(rs != null) {
                rs.close();
            }

            if(ps != null) {
                ps.close();
            }
        }
    }
    
    public List<DocumentoEmitido> consultarDocumentosEnviados(Indicador indicador) throws Exception {
        try {
            String sql =    "select * from documento_emitido\n" +
                            "where id_unidad_tramite = "+indicador.getId_unidad_tramite()+"\n" +
                            "and to_char(fecha_recepcion,'MM') = '"+indicador.getMes()+"'\n" +
                            "and to_char(fecha_recepcion,'YYYY') = '"+indicador.getAño()+"';";
            System.out.println(sql);
            List<DocumentoEmitido> lista = new ArrayList<>();
            DocumentoEmitido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                objeto = new DocumentoEmitido();
                
                objeto.setId_documentoEmitido(rs.getInt("id_documento_emitido"));
                objeto.setAnio(rs.getInt("año"));
                objeto.setPara(rs.getString("para"));
                objeto.setAsunto(rs.getString("asunto"));
                objeto.setNumero_expediente(rs.getString("numero_expediente"));
                objeto.setNumero(rs.getString("numero"));
                objeto.setFecha_recepcion(rs.getDate("fecha_recepcion"));
                objeto.setFecha_emision(rs.getDate("fecha_emision"));
                objeto.setTramite(tramiteAD.Consultar1(new Tramite(rs.getInt("id_tramite"))));
                objeto.setTipoDocumento(tipoDocumentoAD.Consultar1(new TipoDocumento(rs.getInt("id_tipo_documento"))));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                lista.add(objeto);
            }

            return lista;
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(rs != null) {
                rs.close();
            }

            if(ps != null) {
                ps.close();
            }
        }
    }
    
    public List<DocumentoRecibido> consultarDocumentosRecibidosFinalizados(Indicador indicador) throws Exception {
        try {
            String sql =    "select distinct d.id_documento_recibido, d.año_expediente, d.de, d.asunto, d.observacion, d.id_tipo_documento, d.numero, d.numero_expediente, d.estado\n" +
                            "from ruta r join documento_recibido d on (r.id_documento_recibido = d.id_documento_recibido)\n" +
                            "where r.tipo_expediente = '"+indicador.getTipoTramite()+"'\n" +
                            "and to_char(fecha_hora_recepcion,'MM') = '"+indicador.getMes()+"'\n" +
                            "and d.estado =true\n" +
                            "and d.numero_expediente like '%"+indicador.getUnidadTramite().getUnidadOrganizativa().getAbreviatura()+"%';";
            System.out.println(sql);
            
            List<DocumentoRecibido> objetos = new ArrayList<>();
            DocumentoRecibido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                objeto = new DocumentoRecibido();
                
                objeto = new DocumentoRecibido();
                objeto.setId_documentoRecibido(rs.getInt("id_documento_recibido"));
                objeto.setAño_expediente(rs.getInt("año_expediente"));
                objeto.setDe(rs.getString("de"));
                objeto.setAsunto(rs.getString("asunto"));
                objeto.setObservacion(rs.getString("observacion"));
                objeto.setNumero(rs.getString("numero"));
                objeto.setNumero_expediente(rs.getString("numero_expediente"));
                objeto.setEstado(rs.getBoolean("estado"));
                objeto.setTipoDocumento(tipoDocumentoAD.Consultar1(new TipoDocumento(rs.getInt("id_tipo_documento"))));
                
                objetos.add(objeto);
            }

            return objetos;
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(rs != null) {
                rs.close();
            }

            if(ps != null) {
                ps.close();
            }
        }
    }
    
    public double calcularDuracionReal(DocumentoRecibido documentoRecibido) throws Exception {
        try {
            String sql =    "select extract(Day from max(fecha_hora_recepcion)) - extract(Day from min(fecha_hora_recepcion)) duracion\n" +
                            "from ruta\n" +
                            "where id_documento_recibido = "+documentoRecibido.getId_documentoRecibido()+";";
            System.out.println(sql);

            double promedio=0.0;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                promedio = rs.getDouble(1);
            }

            return promedio;
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(rs != null) {
                rs.close();
            }

            if(ps != null) {
                ps.close();
            }
        }
    }
}
