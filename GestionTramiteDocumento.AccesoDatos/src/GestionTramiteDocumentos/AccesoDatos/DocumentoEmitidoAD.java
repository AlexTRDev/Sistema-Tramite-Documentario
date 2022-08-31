package GestionTramiteDocumentos.AccesoDatos;

import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.DocumentoEmitido;
import GestionTramiteDocumentos.Entidades.TipoDocumento;
import GestionTramiteDocumentos.Entidades.Tramite;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentoEmitidoAD extends EntidadAD {
    UnidadOrganizativaAD unidadOrganizativaAD;
    UnidadTramiteAD unidadTramiteAD;
    TipoDocumentoAD tipoDocumentoAD;
    TramiteAD tramiteAD;
    
    public DocumentoEmitidoAD(Connection connection) {
        super(connection);
        unidadOrganizativaAD = new UnidadOrganizativaAD(connection);
        unidadTramiteAD = new UnidadTramiteAD(connection);
        tipoDocumentoAD = new TipoDocumentoAD(connection);
        tramiteAD = new TramiteAD(connection);
    }

    public void Insertar(DocumentoEmitido objeto) throws Exception {
        try {
            String dml = "insert into documento_emitido("+
                    "año,numero,fecha_recepcion,fecha_emision,para,asunto,numero_expediente,"+
                    "id_tipo_documento,id_unidad_tramite,id_tramite,inicio_tramite) values("+
                    "'"+objeto.getAnio()+"', "+
                    "'"+objeto.getNumero()+"',"+
                    "'"+objeto.getFecha_recepcion()+"',"+
                    "'"+objeto.getFecha_emision()+"',"+
                    "'"+objeto.getPara()+"',"+
                    "'"+objeto.getAsunto()+"',"+
                    "'"+objeto.getNumero_expediente()+"',"+
                    ""+objeto.getTipoDocumento().getId_tipoDocumento()+","+
                    ""+objeto.getUnidadTramite().getId_unidad_tramite()+", "+
                    ""+objeto.getTramite().getId_tramite()+", "+
                    ""+objeto.isInicio_tramite()+");";
            
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Modificar(DocumentoEmitido objeto) throws Exception {
       try {
            String dml = "update documento_emitido set "+
                    "id_documento_emitido = '"+objeto.getId_documentoEmitido()+"', "+
                    "año = '"+objeto.getAnio()+"', "+
                    "numero = '"+objeto.getNumero()+"', "+
                    "fecha_emision = '"+objeto.getFecha_emision()+"', "+
                    "fecha_recepcion = "+objeto.getFecha_recepcion()+", "+
                    "para = '"+objeto.getPara()+"', "+
                    "asunto = '"+objeto.getAsunto()+"', "+
                    "numero_expediente = '"+objeto.getNumero_expediente()+"', "+
                    "id_tipo_documento = '"+objeto.getTipoDocumento().getId_tipoDocumento()+"', "+
                    "id_unidad_tramite = '"+objeto.getUnidadTramite().getId_unidad_tramite()+"', "+
                    "inicio_tramite = '"+objeto.isInicio_tramite()+"' "+
                    "where id_documento_emitido = "+objeto.getId_documentoEmitido()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }

    public void Eliminar(DocumentoEmitido objeto) throws Exception {
       try { 
            String dml = "delete from documento_emitido "
                    + "where id_documento_emitido = "+objeto.getId_documentoEmitido()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 
    
    public List<DocumentoEmitido> ConsultarXFiltros(String sql) throws Exception {
        try {
            System.out.println(sql);
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            List<DocumentoEmitido> lista = new ArrayList<DocumentoEmitido>();
            
            while(rs.next()) {
                DocumentoEmitido objeto = new DocumentoEmitido();
                
                objeto.setId_documentoEmitido(rs.getInt("id_documento_emitido"));
                objeto.setAnio(rs.getInt("año"));
                objeto.setPara(rs.getString("para"));
                objeto.setAsunto(rs.getString("asunto"));
                objeto.setNumero_expediente(rs.getString("numero_expediente"));
                objeto.setNumero(rs.getString("numero"));
                objeto.setFecha_recepcion(rs.getDate("fecha_recepcion"));
                objeto.setFecha_emision(rs.getDate("fecha_emision"));
                objeto.setTipoDocumento(tipoDocumentoAD.Consultar1(new TipoDocumento(rs.getInt("id_tipo_documento"))));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                objeto.setInicio_tramite(rs.getBoolean("inicio_tramite"));
                objeto.setTramite(tramiteAD.Consultar1(new Tramite(rs.getInt("id_tramite"))));
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
    
    public DocumentoEmitido Consultar1(DocumentoEmitido documentoRecibido) throws Exception {
        try {
            String sql = "select * from documento_emitido"
                    + " where id_documento_emitido = "+documentoRecibido.getId_documentoEmitido();
            System.out.println(sql);
            DocumentoEmitido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new DocumentoEmitido();
                
                objeto.setId_documentoEmitido(rs.getInt("id_documento_emitido"));
                objeto.setAnio(rs.getInt("id_año"));
                objeto.setPara(rs.getString("para"));
                objeto.setAsunto(rs.getString("asunto"));
                objeto.setNumero_expediente(rs.getString("observacion"));
                objeto.setFecha_recepcion(rs.getDate("fecha_recepcion"));
                objeto.setFecha_emision(rs.getDate("fecha_emision"));
                objeto.setTipoDocumento(tipoDocumentoAD.Consultar1(new TipoDocumento(rs.getInt("id_tipo_documento"))));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                objeto.setInicio_tramite(rs.getBoolean("inicio_tramite"));
                objeto.setTramite(tramiteAD.Consultar1(new Tramite(rs.getInt("id_tramite"))));
            }

            return objeto;
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
    
    public DocumentoEmitido Consultar1PorExpediente(DocumentoEmitido documentoRecibido) throws Exception {
        try {
            String sql = "select * from documento_emitido"
                    + " where numero_expediente = '"+documentoRecibido.getNumero_expediente()+"';";
            System.out.println(sql);
            System.out.println(sql);
            DocumentoEmitido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new DocumentoEmitido();
                
                objeto.setId_documentoEmitido(rs.getInt("id_documento_emitido"));
                objeto.setAnio(rs.getInt("año"));
                objeto.setPara(rs.getString("para"));
                objeto.setAsunto(rs.getString("asunto"));
                objeto.setNumero_expediente(rs.getString("numero_expediente"));
                objeto.setFecha_recepcion(rs.getDate("fecha_recepcion"));
                objeto.setFecha_emision(rs.getDate("fecha_emision"));
                objeto.setTipoDocumento(tipoDocumentoAD.Consultar1(new TipoDocumento(rs.getInt("id_tipo_documento"))));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                objeto.setInicio_tramite(rs.getBoolean("inicio_tramite"));
                objeto.setTramite(tramiteAD.Consultar1(new Tramite(rs.getInt("id_tramite"))));
            }

            return objeto;
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
	
    public DocumentoEmitido Consultar1UniNumDoc(DocumentoEmitido documentoRecibido) throws Exception {
        try {
            String sql = "select * from documento_emitido"
                    + " where id_unidad_tramite = "+documentoRecibido.getUnidadTramite().getId_unidad_tramite()
                    + " and numero = '"+documentoRecibido.getNumero()+"'"
                    + " and id_tipo_documento = "+documentoRecibido.getTipoDocumento().getId_tipoDocumento()+";";
            System.out.println(sql);
            DocumentoEmitido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new DocumentoEmitido();
                
                objeto.setId_documentoEmitido(rs.getInt("id_documento_emitido"));
                objeto.setAnio(rs.getInt("año"));
                objeto.setPara(rs.getString("para"));
                objeto.setAsunto(rs.getString("asunto"));
                objeto.setNumero_expediente(rs.getString("numero_expediente"));
                objeto.setNumero(rs.getString("numero"));
                objeto.setFecha_recepcion(rs.getDate("fecha_recepcion"));
                objeto.setFecha_emision(rs.getDate("fecha_emision"));
                objeto.setTipoDocumento(tipoDocumentoAD.Consultar1(new TipoDocumento(rs.getInt("id_tipo_documento"))));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                objeto.setTramite(tramiteAD.Consultar1(new Tramite(rs.getInt("id_tramite"))));
                objeto.setInicio_tramite(rs.getBoolean("inicio_tramite"));
            }

            return objeto;
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

    public List<DocumentoEmitido> Consultar1DocRecepcionado(DocumentoEmitido documentoEmitido) throws Exception {
        try {
            String sql =    "select de.id_documento_emitido, de.año, de.fecha_emision, de.para, de.asunto, de.fecha_recepcion, de.id_tipo_documento, de.id_unidad_tramite, de.numero, de.numero_expediente, de.id_tramite, de.inicio_tramite\n" +
                            "from ruta r \n" +
                            "join documento_recibido dr on (r.id_documento_recibido = dr.id_documento_recibido)\n" +
                            "join tramite t on (r.id_ruta = t.id_ruta)\n" +
                            "join documento_emitido de on (t.id_tramite = de.id_tramite)\n" +
                            "where de.numero_expediente = '"+documentoEmitido.getNumero_expediente()+"';";
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
                objeto.setInicio_tramite(rs.getBoolean("inicio_tramite"));
                
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
    
    public DocumentoEmitido Consultar1DocumentoCurso(DocumentoEmitido documentoEmitido) throws Exception {
        try {
            String sql =    "select de.id_documento_emitido, de.año, de.fecha_emision, de.para, de.asunto, de.fecha_recepcion, de.id_tipo_documento, de.id_unidad_tramite, de.numero, de.numero_expediente, de.id_tramite, de.inicio_tramite\n" +
                            "from ruta r \n" +
                            "join documento_recibido dr on (r.id_documento_recibido = dr.id_documento_recibido)\n" +
                            "join tramite t on (r.id_ruta = t.id_ruta)\n" +
                            "join documento_emitido de on (t.id_tramite = de.id_tramite)\n" +
                            "where de.numero_expediente = '"+documentoEmitido.getNumero_expediente()+"'\n"+
                            "order by r.id_ruta desc limit 1;";
            System.out.println(sql);
            DocumentoEmitido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
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
                objeto.setInicio_tramite(rs.getBoolean("inicio_tramite"));
            }
            return objeto;
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
    
    public Date consultarFechaBD()throws Exception {
        try{
            String sql = "SELECT CURRENT_TIMESTAMP;";
            Date fecha = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                fecha= rs.getDate(1);
            }

            return fecha;
        }catch(Exception e) {
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
    
    public DocumentoEmitido consultarUltimo() throws Exception{
        try {
            String sql = "select * from documento_emitido order by id_documento_emitido desc limit 1;";

            System.out.println(sql);
            DocumentoEmitido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();
            if(rs.next()) {
                objeto = new DocumentoEmitido();
                objeto.setId_documentoEmitido(rs.getInt("id_documento_emitido"));
                objeto.setAnio(rs.getInt("año"));
                objeto.setPara(rs.getString("para"));
                objeto.setAsunto(rs.getString("asunto"));
                objeto.setNumero_expediente(rs.getString("numero_expediente"));
                objeto.setFecha_recepcion(rs.getDate("fecha_recepcion"));
                objeto.setFecha_emision(rs.getDate("fecha_emision"));
                objeto.setTipoDocumento(tipoDocumentoAD.Consultar1(new TipoDocumento(rs.getInt("id_tipo_documento"))));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                objeto.setTramite(tramiteAD.Consultar1(new Tramite(rs.getInt("id_tramite"))));
                objeto.setInicio_tramite(rs.getBoolean("inicio_tramite"));
            }
            return objeto;
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
    
    public DocumentoEmitido consultarUltimo(DocumentoEmitido documentoEmitido) throws Exception{
        try {
            String sql =    "select * \n" +
                            "from documento_emitido\n" +
                            "where numero_expediente = '"+documentoEmitido.getNumero_expediente()+"' \n" +
                            "order by id_documento_emitido desc limit 1;";

            System.out.println("HOLA"+sql);
            DocumentoEmitido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();
            if(rs.next()) {
                objeto = new DocumentoEmitido();
                objeto.setId_documentoEmitido(rs.getInt("id_documento_emitido"));
                objeto.setAnio(rs.getInt("año"));
                objeto.setPara(rs.getString("para"));
                objeto.setAsunto(rs.getString("asunto"));
                objeto.setNumero_expediente(rs.getString("numero_expediente"));
                objeto.setFecha_recepcion(rs.getDate("fecha_recepcion"));
                objeto.setFecha_emision(rs.getDate("fecha_emision"));
                objeto.setTipoDocumento(tipoDocumentoAD.Consultar1(new TipoDocumento(rs.getInt("id_tipo_documento"))));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                objeto.setTramite(tramiteAD.Consultar1(new Tramite(rs.getInt("id_tramite"))));
            }
            return objeto;
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
