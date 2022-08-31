package GestionTramiteDocumentos.AccesoDatos;

import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.Ruta;
import GestionTramiteDocumentos.Entidades.Tramite;
import java.sql.Connection;

public class TramiteAD extends EntidadAD {
    UnidadTramiteAD unidadTramiteAD;
    RutaAD rutaAD;
    
    public TramiteAD(Connection connection) {
        super(connection);
        unidadTramiteAD = new UnidadTramiteAD(connection);
        rutaAD = new RutaAD(connection);
    }

    public void Insertar(Tramite objeto) throws Exception {
        
        try {
            System.out.println("se queda en acceso");
            String dml = "insert into tramite("+
                    "descripcion, tipo_traslado,tipo_entidad, descripcion_entidad, fecha_emision,id_ruta) values("+
                    "'"+objeto.getDescripcion()+"',"+
                    "'"+objeto.getTipo_traslado()+"',"+
                    "'"+objeto.getTipo_entidad()+"',"+
                    "'"+objeto.getDescripcion_entidad()+"',"+
                    "'"+objeto.getFecha_emision()+"', "+
                    " "+objeto.getRuta().getId_ruta()+");";
            
            System.out.println("se queda en acceso");
            System.out.println(dml);
            
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Modificar(Tramite objeto) throws Exception {
       try {
            String dml = "update tramite set "+
                    "descripcion = '"+objeto.getDescripcion()+"', "+
                    "tipo_traslado = '"+objeto.getTipo_traslado()+"',"+
                    "tipo_entidad = '"+objeto.getTipo_entidad()+"', "+
                    "descripcion_entidad = '"+objeto.getDescripcion_entidad()+"' "+
                    "fecha_emision = '"+objeto.getFecha_emision()+" "+
                    "where id_tramite = "+objeto.getId_tramite()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }

    public void Eliminar(Tramite objeto) throws Exception {
       try { 
            String dml = "delete from tramite "
                    + "where id_tramite ="+objeto.getId_tramite()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 

    public Tramite Consultar1(Tramite tramite) throws Exception {
        try {
            String sql = "select * from tramite where id_tramite = "+tramite.getId_tramite()+";";
            System.out.println(sql);
            Tramite objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new Tramite();
                
                objeto.setId_tramite(rs.getInt("id_tramite"));
                objeto.setDescripcion(rs.getString("descripcion"));
                objeto.setTipo_traslado(rs.getString("tipo_traslado"));
                objeto.setTipo_entidad(rs.getString("tipo_entidad"));
                objeto.setDescripcion_entidad(rs.getString("descripcion_entidad"));
                objeto.setFecha_emision(rs.getDate("fecha_emision"));
                objeto.setRuta(rutaAD.Consultar1(new Ruta(rs.getInt("id_ruta"))));
                
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
    
    public Tramite ConsultarUltimoAgregado(Ruta ruta) throws Exception {
        try {
            String sql = "select * from tramite where id_ruta = "+ruta.getId_ruta()+
                         "order by id_tramite desc limit 1";
            
            Tramite objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new Tramite();
                
                objeto.setId_tramite(rs.getInt("id_tramite"));
                objeto.setDescripcion(rs.getString("descripcion"));
                objeto.setTipo_traslado(rs.getString("tipo_traslado"));
                objeto.setTipo_entidad(rs.getString("tipo_entidad"));
                objeto.setDescripcion_entidad(rs.getString("descripcion_entidad"));
                objeto.setFecha_emision(rs.getDate("fecha_emision"));
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
