package GestionTramiteDocumentos.AccesoDatos;

import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.TipoTramite;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class TipoTramiteAD extends EntidadAD {
    UnidadOrganizativaAD unidadOrganizativaAD;
    UnidadTramiteAD unidadTramiteAD;
    public TipoTramiteAD(Connection connection) {
        super(connection);
        unidadOrganizativaAD = new UnidadOrganizativaAD(connection);
        unidadTramiteAD = new UnidadTramiteAD(connection);
    }

    public void Insertar(TipoTramite objeto) throws Exception {
        try {
            String dml = "insert into tipo_tramite("+
                    "nombre,tiempo_estimado,id_unidad_tramite) values("+
                    "'"+objeto.getNombre()+"','"+objeto.getTiempo_estimado()+"','"+objeto.getUnidadTramite().getId_unidad_tramite()+"');";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Modificar(TipoTramite objeto) throws Exception {
       try {
            String dml = "update tipo_tramite set "+
                    "nombre = '"+objeto.getNombre()+"', "+
                    "tiempo_estimado = "+objeto.getTiempo_estimado()+","+
                    "id_unidad_tramite = "+objeto.getUnidadTramite().getId_unidad_tramite()+" "+
                    "where id_tipo_tramite= "+objeto.getId_tipoTramite()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
           System.out.println(e.getLocalizedMessage());
            throw e;
       }
    }

    public void Eliminar(TipoTramite objeto) throws Exception {
       try { 
            String dml = "delete from tipo_tramite "
                    + "where id_tipo_tramite ="+objeto.getId_tipoTramite()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 
    
    public List<TipoTramite> ConsultarAll() throws Exception {
        try {
            String sql = "select * from unidad_tramite;";

            List<TipoTramite> objetos = new ArrayList<TipoTramite>();
            TipoTramite objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                objeto = new TipoTramite();
                
                objeto.setId_tipoTramite(rs.getInt("id_tipo_tramite"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setTiempo_estimado(rs.getInt("tiempo_estimado"));
                objeto.setEstado(rs.getBoolean("estado"));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                
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
    
    public List<TipoTramite> ConsultarAll(UnidadTramite unidadTramite) throws Exception {
        try {
            String sql = "select * from tipo_tramite"
                    + " where id_unidad_tramite= "+unidadTramite.getId_unidad_tramite();

            List<TipoTramite> objetos = new ArrayList<TipoTramite>();
            TipoTramite objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                objeto = new TipoTramite();
                
                objeto.setId_tipoTramite(rs.getInt("id_tipo_tramite"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setTiempo_estimado(rs.getInt("tiempo_estimado"));
                objeto.setEstado(rs.getBoolean("estado"));
                objeto.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                
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
    
    public Integer getId() throws Exception {
        try {
            String sql = "select nextval('tb_tema_tb_tema_id_seq') as tema_id;";

            Integer id = 0;

            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                id = rs.getInt("tema_id");

                if(rs.wasNull()) {
                    id = 0;
                }
            }

            return id;
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
