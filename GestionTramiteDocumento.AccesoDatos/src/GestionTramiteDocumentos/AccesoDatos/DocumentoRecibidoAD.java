package GestionTramiteDocumentos.AccesoDatos;

import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.TipoDocumento;
import GestionTramiteDocumentos.Entidades.TipoTramite;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DocumentoRecibidoAD extends EntidadAD {
    UnidadOrganizativaAD unidadOrganizativaAD;
    UnidadTramiteAD unidadTramiteAD;
    TipoDocumentoAD tipoDocumentoAD;
    
    public DocumentoRecibidoAD(Connection connection) {
        super(connection);
        unidadOrganizativaAD = new UnidadOrganizativaAD(connection);
        unidadTramiteAD = new UnidadTramiteAD(connection);
        tipoDocumentoAD = new TipoDocumentoAD(connection);
    }

    public void Insertar(DocumentoRecibido objeto) throws Exception {
        try {
            String dml = "insert into documento_recibido("+
                    "año_expediente,de,asunto,observacion,id_tipo_documento,numero,numero_expediente,estado) values("+
                    "'"+objeto.getAño_expediente()+"', "+
                    "'"+objeto.getDe()+"',"+
                    "'"+objeto.getAsunto()+"',"+
                    "'"+objeto.getObservacion()+"',"+
                    ""+objeto.getTipoDocumento().getId_tipoDocumento()+","+
                    "'"+objeto.getNumero()+"',"+
                    "'"+objeto.getNumero_expediente()+"',"+
                    ""+objeto.getEstado()+");";
            
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Modificar(DocumentoRecibido objeto) throws Exception {
       try {
            String dml = "update documento_recibido set "+
                    "año_expediente = '"+objeto.getAño_expediente()+"', "+
                    "de = '"+objeto.getDe()+"', "+
                    "asunto = '"+objeto.getAsunto()+"', "+
                    "observacion = '"+objeto.getObservacion()+"', "+
                    "id_tipo_documento = "+objeto.getTipoDocumento().getId_tipoDocumento()+", "+
                    "numero = '"+objeto.getNumero()+"', "+
                    "numero_expediente = '"+objeto.getNumero_expediente()+"', "+
                    "estado = '"+objeto.getEstado()+"' "+
                    "where id_tipo_documento = "+objeto.getId_documentoRecibido()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }

    public void Eliminar(DocumentoRecibido objeto) throws Exception {
       try { 
            String dml = "delete from documento_recibido "
                    + "where id_documento_recibido = "+objeto.getId_documentoRecibido()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 
    
    public List<DocumentoRecibido> ConsultarAll() throws Exception {
        try {
            String sql = "select * from documento_recibido;";

            List<DocumentoRecibido> objetos = new ArrayList<DocumentoRecibido>();
            DocumentoRecibido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                objeto = new DocumentoRecibido();
                
                objeto.setId_documentoRecibido(rs.getInt("id_documento_recibido"));
                objeto.setAño_expediente(rs.getInt("id_año_expediente"));
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
    
    public DocumentoRecibido Consultar1(DocumentoRecibido documentoRecibido) throws Exception {
        try {
            String sql = "select * from documento_recibido"+
                         " where id_documento_recibido = "+documentoRecibido.getId_documentoRecibido()+";";
            System.out.println(sql);
            DocumentoRecibido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
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
    
    public DocumentoRecibido Consultar1NumExp(DocumentoRecibido documentoRecibido) throws Exception {
        try {
            String sql = "select * from documento_recibido"
                    + " where numero_expediente = '"+documentoRecibido.getNumero_expediente()+"';";
            System.out.println(sql);
            DocumentoRecibido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
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
    
    public TipoTramite Consultar1(TipoTramite tipoTramite) throws Exception {
        try {
            String sql = "select * from tipo_tramite"
                    + " where id_tipo_tramite= "+tipoTramite.getId_tipoTramite();

            List<TipoTramite> objetos = new ArrayList<TipoTramite>();
            TipoTramite objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new TipoTramite();
                
                objeto.setId_tipoTramite(rs.getInt("id_tipo_tramite"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setTiempo_estimado(rs.getInt("tiempo_estimado"));
                objeto.setEstado(rs.getBoolean("estado"));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
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
    
    public DocumentoRecibido consultarUltimo() throws Exception{
        try {
            String sql = "select * from documento_recibido order by id_documento_recibido desc limit 1;";

            DocumentoRecibido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
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
    
    public DocumentoRecibido consultarUltimoUT(UnidadTramite unidadTramite) throws Exception{
        try {
            String sql =    "select dr.id_documento_recibido, dr.año_expediente, dr.de, dr.asunto, dr.observacion, dr.id_tipo_documento, dr.numero, dr.numero_expediente, dr.estado\n" +
                            "from documento_recibido dr join ruta r on(dr.id_documento_recibido = r.id_documento_recibido)\n" +
                            "where r.id_unidad_tramite ="+unidadTramite.getId_unidad_tramite()+" \n" +
                            "and dr.numero_expediente like '%"+unidadTramite.getAbreviatura()+"%'"+" \n" +
                            "order by id_documento_recibido desc limit 1;";
            System.out.println(sql);
            DocumentoRecibido objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new DocumentoRecibido();
                
                objeto.setId_documentoRecibido(rs.getInt("id_documento_recibido"));
                objeto.setAño_expediente(rs.getInt("año_expediente"));
                objeto.setDe(rs.getString("de"));
                objeto.setAsunto(rs.getString("asunto"));
                objeto.setObservacion(rs.getString("observacion"));
                objeto.setNumero(rs.getString("numero"));
                objeto.setNumero_expediente(rs.getString("numero_expediente"));
                objeto.setEstado(rs.getBoolean("estado"));
                objeto.setTipoDocumento(null);
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
                fecha= rs.getTimestamp(1);
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
    
    public String generarNumeroDocumento(UnidadTramite unidadTramite,TipoDocumento tipoDocumento) throws Exception {
        try {
            String sql = "select max(numero) numero from documento_emitido where"+
                    " id_unidad_tramite = "+unidadTramite.getId_unidad_tramite()+
                    " and id_tipo_documento = "+tipoDocumento.getId_tipoDocumento()+";";
            System.out.println(sql);
            String nt="";
            Integer ni=0;

            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                nt = rs.getString("numero");

            }
            if(nt!=null){
                int numero = Integer.parseInt(nt.substring(0,3))+1;
                return formatearNumeroDocumento(numero);
            }else{
                int numero = 1;
                return formatearNumeroDocumento(numero);
            }
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
    
    public String formatearNumeroDocumento(int num){
        String nf;
        if(num<10){
            nf="00"+Integer.toString(num);
        }else if(num<100){
            nf="0"+Integer.toString(num);
        }else{
            nf=Integer.toString(num);
        }
        return nf;
    }
}
