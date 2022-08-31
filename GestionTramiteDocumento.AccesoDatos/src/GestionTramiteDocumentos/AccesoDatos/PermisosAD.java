package GestionTramiteDocumentos.AccesoDatos;

import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.Permiso;
import GestionTramiteDocumentos.Entidades.UnidadTramite;
import GestionTramiteDocumentos.Entidades.Usuario;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class PermisosAD extends EntidadAD {
    UnidadOrganizativaAD unidadOrganizativaAD;
    UnidadTramiteAD unidadTramiteAD;
    UsuarioAD oUsuarioAD;
    
    public PermisosAD(Connection connection) {
        super(connection);
        unidadOrganizativaAD = new UnidadOrganizativaAD(connection);
        unidadTramiteAD = new UnidadTramiteAD(connection);
        oUsuarioAD = new UsuarioAD(connection);
    }

    public void Insertar(Permiso objeto) throws Exception {
        try {
            String dml = "insert into permiso("+
                    "fecha_inicio,fecha_termino,id_usuario,id_unidad_tramite,id_unidad_organizativa) values("+
                    "'"+objeto.getFecha_inicio()+
                    "','"+objeto.getFecha_termino()+
                    "',"+objeto.getUsuario().getId_usuario()+
                    ","+objeto.getUnidadTramite().getId_unidad_tramite()+
                    ","+objeto.getUnidadOrganizativa().getId_unidadOrganizativa()+");";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Modificar(Permiso objeto) throws Exception {
       try {
            String dml = "update permiso set "+
                    "fecha_inicio = '"+objeto.getFecha_inicio()+"', "+
                    "fecha_termino = '"+objeto.getFecha_termino()+"', "+
                    "id_usuario = '"+objeto.getUsuario().getId_usuario()+"', "+
                    "id_unidad_tramite = "+objeto.getUnidadTramite().getId_unidad_tramite()+" "+
                    "id_unidad_organizativa = "+objeto.getUnidadOrganizativa().getId_unidadOrganizativa()+" "+
                    "where id_permiso= "+objeto.getId_permiso()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }

    public void Eliminar(Permiso objeto) throws Exception {
       try { 
            String dml = "delete from permiso "
                    + "where id_requisito = "+objeto.getId_permiso()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 
    
    public void EliminarPorID(Usuario objeto) throws Exception {
       try { 
            String dml = "delete from permiso "
                    + "where id_usuario = "+objeto.getId_usuario()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }
    
    public List<Permiso> ConsultarAll(Usuario usuario) throws Exception {
        try {
            String sql = "select * from permiso ";
            sql += " where id_usuario = "+usuario.getId_usuario();
            sql += " order by id_unidad_tramite";
            

            List<Permiso> permisos = new ArrayList<Permiso>();
            Permiso permiso = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                permiso = new Permiso();
                permiso.setId_permiso(rs.getInt("id_permiso"));
                permiso.setFecha_inicio(rs.getDate("fecha_inicio").toString());
                permiso.setFecha_termino(rs.getDate("fecha_termino").toString());
                permiso.setUsuario(oUsuarioAD.ConsultarPorID(new Usuario(rs.getInt("id_usuario"))));
                permiso.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                permiso.setUnidadOrganizativa(unidadOrganizativaAD.Consultar1(new UnidadOrganizativa(rs.getInt("id_unidad_organizativa"))));
                
                permisos.add(permiso);
            }

            return permisos;
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

    public List<Permiso> ConsultarAll(UnidadOrganizativa unidadOrganizativa) throws Exception {
        try {
            String sql = "select * from permiso"
                    + " where id_unidad_organizativa= "+unidadOrganizativa.getId_unidadOrganizativa();

            List<Permiso> permisos = new ArrayList<Permiso>();
            Permiso permiso = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                permiso = new Permiso();
                permiso.setId_permiso(rs.getInt("id_id_permiso"));
                permiso.setFecha_inicio(rs.getDate("fecha_inicio").toString());
                permiso.setFecha_termino(rs.getDate("fecha_termino").toString());
                permiso.setUsuario(oUsuarioAD.ConsultarPorID(new Usuario(rs.getInt("id_usuario"))));
                permiso.setUnidadTramite(unidadTramiteAD.Consultar1(new UnidadTramite(rs.getInt("id_unidad_tramite"))));
                permiso.setUnidadOrganizativa(unidadOrganizativaAD.Consultar1(new UnidadOrganizativa(rs.getInt("id_unidad_organizativa"))));
                
                permisos.add(permiso);
            }

            return permisos;
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