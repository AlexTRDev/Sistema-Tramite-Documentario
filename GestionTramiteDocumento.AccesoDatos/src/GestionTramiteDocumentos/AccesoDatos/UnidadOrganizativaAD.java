package GestionTramiteDocumentos.AccesoDatos;

import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class UnidadOrganizativaAD extends EntidadAD {

    public UnidadOrganizativaAD(Connection connection) {
        super(connection);
    }

    public void Insertar(UnidadOrganizativa objeto) throws Exception {
        try {
            String dml = "insert into unidad_organizativa("+
                    "nombre,abreviatura) values("+
                    "'"+objeto.getNombre()+"','"+objeto.getAbreviatura()+"');";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Modificar(UnidadOrganizativa objeto) throws Exception {
       try {
            String dml = "update unidad_organizativa set "+
                    "nombre = '"+objeto.getNombre()+"', "+
                    "abreviatura = '"+objeto.getAbreviatura()+"' "+
                    "where id_unidad_organizativa = "+objeto.getId_unidadOrganizativa()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }

    public void Eliminar(UnidadOrganizativa objeto) throws Exception {
       try { 
            String dml = "delete from unidad_organizativa "
                    + "where id_unidad_organizativa="+objeto.getId_unidadOrganizativa()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 
    
    public List<UnidadOrganizativa> ConsultarAll() throws Exception {
        try {
            String sql = "select * from unidad_organizativa;";

            List<UnidadOrganizativa> objetos = new ArrayList<UnidadOrganizativa>();
            UnidadOrganizativa objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                objeto = new UnidadOrganizativa();
                
                objeto.setId_unidadOrganizativa(rs.getInt("id_unidad_organizativa"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setAbreviatura(rs.getString("abreviatura"));
                objeto.setEstado(rs.getBoolean("estado")); 
                
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
    
    public UnidadOrganizativa Consultar1(UnidadOrganizativa unidadOrganizativa) throws Exception {
        try {
            String sql = "select * from unidad_organizativa"+
                   " where id_unidad_organizativa = "+unidadOrganizativa.getId_unidadOrganizativa();
            System.out.println(sql);
            UnidadOrganizativa objeto = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                objeto = new UnidadOrganizativa();
                
                objeto.setId_unidadOrganizativa(rs.getInt("id_unidad_organizativa"));
                objeto.setNombre(rs.getString("nombre"));
                objeto.setAbreviatura(rs.getString("abreviatura"));
                objeto.setEstado(rs.getBoolean("estado")); 
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
