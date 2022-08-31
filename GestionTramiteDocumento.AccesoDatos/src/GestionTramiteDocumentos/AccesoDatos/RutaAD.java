package GestionTramiteDocumentos.AccesoDatos;

import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.Ruta;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class RutaAD extends EntidadAD {
    UnidadTramiteAD unidadTramiteAD;
    DocumentoRecibidoAD documentoRecibidoAD;
    
    public RutaAD(Connection connection) {
        super(connection);
        unidadTramiteAD = new UnidadTramiteAD(connection);
        documentoRecibidoAD = new DocumentoRecibidoAD(connection);
    }

    public void Insertar(Ruta objeto) throws Exception {
        try {  
            //para insertar una fecha en postgres necesita ir entre comillas. Pero que pasa si el valor es null las comillas que lo encierran no lo dejarina interpretar como null en el codigo sql ("null")
            String dml;
            if(objeto.getFecha_respuesta()!=null){
                dml = "insert into ruta("+
                "tipo_expediente,fecha_hora_recepcion,fecha_respuesta,id_unidad_tramite,id_documento_recibido) values("+
                "'"+objeto.getTipo_expediente()+"',"+
                "'"+objeto.getFechaHora_recepcion()+"',"+
                "'"+objeto.getFecha_respuesta()+"',"+
                " "+objeto.getUnidadTramite().getId_unidad_tramite()+","+
                " "+objeto.getDocumentoRecibido().getId_documentoRecibido()+");";
            }else{
                dml = "insert into ruta("+
                "tipo_expediente,fecha_hora_recepcion,fecha_respuesta,id_unidad_tramite,id_documento_recibido) values("+
                "'"+objeto.getTipo_expediente()+"',"+
                "'"+objeto.getFechaHora_recepcion()+"',"+
                " "+objeto.getFecha_respuesta()+" ,"+ //<---
                " "+objeto.getUnidadTramite().getId_unidad_tramite()+","+
                " "+objeto.getDocumentoRecibido().getId_documentoRecibido()+");";
            
            }
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Modificar(Ruta objeto) throws Exception {
       try {
            String dml = "update ruta set "+
                    "tipo_expediente = '"+objeto.getTipo_expediente()+"', "+
                    "fecha_hora_recepcion = '"+objeto.getFechaHora_recepcion()+"',"+
                    "fecha_respuesta = '"+objeto.getFechaHora_recepcion()+"',"+
                    "id_unidad_tramite = "+objeto.getUnidadTramite().getId_unidad_tramite()+", "+
                    "id_documento_recibido = "+objeto.getDocumentoRecibido().getId_documentoRecibido()+" "+
                    "where id_ruta = "+objeto.getId_ruta()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }

    public void Eliminar(Ruta objeto) throws Exception {
       try { 
            String dml = "delete from ruta "
                    + "where id_ruta ="+objeto.getId_ruta()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 
    
    public List<Ruta> ConsultarAll(DocumentoRecibido documentoRecibido) throws Exception {
        try {
            String sql = "select * from ruta where id_documento_recibido = "+documentoRecibido.getId_documentoRecibido()+";";
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
                objeto.setDocumentoRecibido(documentoRecibidoAD.Consultar1(new DocumentoRecibido(rs.getInt("id_documento_recibido"))));
                
                objetos.add(objeto);
            }

            return objetos;
        
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

    public List<Ruta> ConsultarXFiltro(String sql) throws Exception {
        try {

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
                objeto.setFecha_respuesta(rs.getDate("fecha_respuesta"));
                objeto.setDocumentoRecibido(documentoRecibidoAD.Consultar1(new DocumentoRecibido(rs.getInt("id_documento_recibido"))));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                
                objetos.add(objeto);
            }

            return objetos;
        
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
    
    public Ruta Consultar1(Ruta ruta) throws Exception {
        try {
            String sql = "select * from ruta where id_ruta = "+ruta.getId_ruta()+";";
            System.out.println(sql);
            Ruta objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                objeto = new Ruta();
                
                objeto.setId_ruta(rs.getInt("id_ruta"));
                objeto.setTipo_expediente(rs.getString("tipo_expediente"));
                objeto.setFechaHora_recepcion(rs.getDate("fecha_hora_recepcion"));
                objeto.setFecha_respuesta(rs.getDate("fecha_respuesta"));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                objeto.setDocumentoRecibido(documentoRecibidoAD.Consultar1(new DocumentoRecibido(rs.getInt("id_documento_recibido"))));
            }
            return objeto;
        
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
    
    public Ruta Consultar1DU(Ruta ruta) throws Exception {
        try {
            String sql = "select * from ruta where id_unidad_tramite = "+ruta.getUnidadTramite().getId_unidad_tramite()+
                    " and id_documento_recibido = "+ruta.getDocumentoRecibido().getId_documentoRecibido()+";";
            System.out.println(sql);
            Ruta objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new Ruta();
                objeto.setId_ruta(rs.getInt("id_ruta"));
                objeto.setTipo_expediente(rs.getString("tipo_expediente"));
                objeto.setFechaHora_recepcion(rs.getDate("fecha_hora_recepcion"));
                objeto.setFecha_respuesta(rs.getDate("fecha_respuesta"));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                objeto.setDocumentoRecibido(documentoRecibidoAD.Consultar1(ruta.getDocumentoRecibido()));
            }
            return objeto;
        
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
    
    public Ruta ConsultarUltima(Ruta ruta) throws Exception {
        try {
            String sql =    "select *\n" +
                            "from ruta\n" +
                            "where id_documento_recibido = "+ruta.getDocumentoRecibido().getId_documentoRecibido()+"\n" +
                            "order by id_ruta desc limit 1;";
            
            System.out.println(sql);
            Ruta objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                objeto = new Ruta();
                
                objeto.setId_ruta(rs.getInt("id_ruta"));
                objeto.setTipo_expediente(rs.getString("tipo_expediente"));
                objeto.setFechaHora_recepcion(rs.getDate("fecha_hora_recepcion"));
                objeto.setFecha_respuesta(rs.getDate("fecha_respuesta"));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                objeto.setDocumentoRecibido(documentoRecibidoAD.Consultar1(new DocumentoRecibido(rs.getInt("id_documento_recibido"))));
            }
            return objeto;
        
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
    
}
