package GestionTramiteDocumentos.AccesoDatos;

import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UnidadTramiteAD extends EntidadAD {
    UnidadOrganizativaAD unidadOrganizativaAD;
    
    public UnidadTramiteAD(Connection connection) {
        super(connection);
        unidadOrganizativaAD = new UnidadOrganizativaAD(connection);
    }

    public void Insertar(UnidadTramite objeto) throws Exception {
        try {
            String dml = "insert into unidad_tramite("+
                    "nombre,abreviatura,responsable,id_unidad_organizativa) values("+
                    "'"+objeto.getNombre()+"','"+objeto.getAbreviatura()+"','"+objeto.getResponsable()+"'"+
                    ","+objeto.getUnidadOrganizativa().getId_unidadOrganizativa()+");";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Modificar(UnidadTramite objeto) throws Exception {
       try {
            String dml = "update unidad_tramite set "+
                    "nombre = '"+objeto.getNombre()+"', "+
                    "abreviatura = '"+objeto.getAbreviatura()+"',"+
                    "responsable = '"+objeto.getResponsable()+"' "+
                    "where id_unidad_tramite = "+objeto.getId_unidad_tramite()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }

    public void Eliminar(UnidadTramite objeto) throws Exception {
       try { 
            String dml = "delete from unidad_tramite "
                    + "where id_unidad_tramite="+objeto.getId_unidad_tramite()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 
    
    public List<UnidadTramite> ConsultarAll() throws Exception {
        try {
            String sql = "select * from unidad_tramite order by id_unidad_organizativa;";
            System.out.println(sql);

            List<UnidadTramite> objetos = new ArrayList<UnidadTramite>();
            UnidadTramite objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                objeto = new UnidadTramite();
                
                objeto.setId_unidad_tramite(rs.getInt("id_unidad_tramite"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setAbreviatura(rs.getString("abreviatura"));
                objeto.setResponsable(rs.getString("responsable"));
                objeto.setEstado(rs.getBoolean("estado")); 
                objeto.setUnidadOrganizativa(unidadOrganizativaAD.Consultar1(new UnidadOrganizativa(rs.getInt("id_unidad_organizativa"))));
                
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
    
    public List<UnidadTramite> ConsultarAll(UnidadOrganizativa unidadOrganizativa) throws Exception {
        try {
            String sql = "select * from unidad_tramite"
                    + " where id_unidad_organizativa = "+unidadOrganizativa.getId_unidadOrganizativa();
            System.out.println(sql);
            List<UnidadTramite> objetos = new ArrayList<UnidadTramite>();
            UnidadTramite objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                objeto = new UnidadTramite();
                
                objeto.setId_unidad_tramite(rs.getInt("id_unidad_tramite"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setAbreviatura(rs.getString("abreviatura"));
                objeto.setResponsable(rs.getString("responsable"));
                objeto.setEstado(rs.getBoolean("estado")); 
                objeto.setUnidadOrganizativa(unidadOrganizativaAD.Consultar1(new UnidadOrganizativa(rs.getInt("id_unidad_organizativa"))));
                
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
    
    public UnidadTramite Consultar1(UnidadTramite unidadTramite) throws Exception {
        try {
            String sql = "select * from unidad_tramite"
                    + " where id_unidad_tramite = "+unidadTramite.getId_unidad_tramite();

            UnidadTramite objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new UnidadTramite();
                
                objeto.setId_unidad_tramite(rs.getInt("id_unidad_tramite"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setAbreviatura(rs.getString("abreviatura"));
                objeto.setResponsable(rs.getString("responsable"));
                objeto.setEstado(rs.getBoolean("estado")); 
                objeto.setUnidadOrganizativa(unidadOrganizativaAD.Consultar1(new UnidadOrganizativa(rs.getInt("id_unidad_organizativa"))));
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
    
    public UnidadTramite Consultar1Abrev(UnidadTramite unidadTramite) throws Exception {
        try {
            String sql = "select * from unidad_tramite"
                    + " where abreviatura = '"+unidadTramite.getAbreviatura()+"';";
            System.out.println(sql);
            UnidadTramite objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new UnidadTramite();
                
                objeto.setId_unidad_tramite(rs.getInt("id_unidad_tramite"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setAbreviatura(rs.getString("abreviatura"));
                objeto.setResponsable(rs.getString("responsable"));
                objeto.setEstado(rs.getBoolean("estado")); 
                objeto.setUnidadOrganizativa(new UnidadOrganizativa(rs.getInt("id_unidad_organizativa")));
                objeto.setUnidadOrganizativa(unidadOrganizativaAD.Consultar1(objeto.getUnidadOrganizativa()));
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
