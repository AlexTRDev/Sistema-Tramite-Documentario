package GestionTramiteDocumentos.AccesoDatos;

import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import GestionTramiteDocumentos.Entidades.Usuario;
import java.sql.Connection;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UsuarioAD extends EntidadAD {
    
    Usuario oUsuario;
    
    public UsuarioAD(Connection connection) {
        super(connection);
    }

    public void Insertar(Usuario objeto) throws Exception {
        try {
            String dml = "insert into usuario(nombre,apellido_paterno,apellido_materno,cuenta,contraseña,estado,tipo,email,ruta_imagen) values(";
                dml += "'" + objeto.getNombre().toUpperCase() + "',";
                dml += "'" + objeto.getApellido_paterno().toUpperCase() + "',";
                dml += "'" + objeto.getApellido_materno().toUpperCase() + "',";
                dml += "'" + objeto.getCuenta().toUpperCase() + "',";
                dml += "'" + objeto.getContraseña() + "',";
                dml += "'" + objeto.getEstado().toString().charAt(0)+ "',";
                dml += "'" + objeto.getTipo().toUpperCase() + "',";
                dml += "'" + objeto.getEmail() + "', ";
                dml += "'" + objeto.getRutaImagen() + "')";
                dml += ";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
            
        }
        catch(Exception e) {
            System.out.println(e.getLocalizedMessage());
            throw e;
        }
    }
    
    public void Modificar(Usuario objeto) throws Exception {
       try {
            String dml = "update usuario set ";
                dml += " nombre = '" + objeto.getNombre().toUpperCase() + "', ";
                dml += " apellido_paterno = '" + objeto.getApellido_paterno().toUpperCase() + "', " ;
                dml += " apellido_materno = '" + objeto.getApellido_materno().toUpperCase() + "', " ;
                dml += " tipo= '" + objeto.getTipo().toUpperCase() + "', " ;
                dml += " ruta_imagen= '" + objeto.getRutaImagen() + "' " ;
                dml += " where id_usuario = " + objeto.getId_usuario() + ";";

                System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
           System.out.println(e.getLocalizedMessage());
            throw e;
       }
    }

    public void Eliminar(Usuario objeto) throws Exception {
       try { 
            String dml = "delete from usuario where id_usuario = ";
                dml += objeto.getId_usuario();
                dml += ";";
                    
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 
    
    public Usuario ConsultarPorID(Usuario objeto) throws Exception {
        try {
            String sql = "SELECT* From usuario WHERE id_usuario = "+objeto.getId_usuario();
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                oUsuario = new Usuario();
                oUsuario.setId_usuario(rs.getInt("id_usuario"));
                oUsuario.setNombre(rs.getString("nombre"));
                oUsuario.setApellido_paterno(rs.getString("apellido_paterno"));
                oUsuario.setApellido_materno(rs.getString("apellido_materno"));
                oUsuario.setCuenta(rs.getString("cuenta"));
                oUsuario.setContraseña(rs.getString("contraseña"));
                oUsuario.setEstado(rs.getBoolean("estado"));
                oUsuario.setTipo(rs.getString("tipo"));
                oUsuario.setEmail(rs.getString("email"));
                oUsuario.setRutaImagen(rs.getString("ruta_imagen"));
            }

            return oUsuario;
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

    public Usuario ConsultarPorCuenta(Usuario objeto) throws Exception {
        try {
            String sql = "SELECT* From usuario WHERE cuenta = '" + objeto.getCuenta().toUpperCase() + "';";
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            if(rs.next()) {
                oUsuario = new Usuario();
                oUsuario.setId_usuario(rs.getInt("id_usuario"));
                oUsuario.setNombre(rs.getString("nombre"));
                oUsuario.setApellido_paterno(rs.getString("apellido_paterno"));
                oUsuario.setApellido_materno(rs.getString("apellido_materno"));
                oUsuario.setCuenta(rs.getString("cuenta"));
                oUsuario.setContraseña(rs.getString("contraseña"));
                oUsuario.setEstado(rs.getBoolean("estado"));
                oUsuario.setTipo(rs.getString("tipo"));
                oUsuario.setEmail(rs.getString("email"));
                oUsuario.setRutaImagen(rs.getString("ruta_imagen"));
            }

            return oUsuario;
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
        
    public List<Usuario> ConsultarAll() throws Exception {
        try {
            String sql = "select* From usuario";

            List<Usuario> lista = new ArrayList<Usuario>();
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while (rs.next()) {
                oUsuario = new Usuario();
                oUsuario.setId_usuario(rs.getInt("id_usuario"));
                oUsuario.setNombre(rs.getString("nombre"));
                oUsuario.setApellido_paterno(rs.getString("apellido_paterno"));
                oUsuario.setApellido_materno(rs.getString("apellido_materno"));
                oUsuario.setCuenta(rs.getString("cuenta"));
                oUsuario.setContraseña(rs.getString("contraseña"));
                oUsuario.setEstado(rs.getBoolean("estado"));
                oUsuario.setTipo(rs.getString("tipo"));
                oUsuario.setEmail(rs.getString("email"));
                oUsuario.setRutaImagen(rs.getString("ruta_imagen"));
                lista.add(oUsuario);
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
    
    public List<Usuario> ConsultarAll(UnidadOrganizativa unidadOrganizativa) throws Exception {
        try {
            String sql = "";
            sql += " SELECT DISTINCT id_usuario, apellido_paterno, apellido_materno, nombre, cuenta, \"contraseña\", estado, tipo, id_unidad_organizativa, email,ruta_imagen";
            sql += " FROM permiso NATURAL JOIN usuario ";
            sql += " WHERE id_unidad_organizativa = " + unidadOrganizativa.getId_unidadOrganizativa();
            
            List<Usuario> lista = new ArrayList<Usuario>();
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while (rs.next()) {
                oUsuario = new Usuario();
                oUsuario.setId_usuario(rs.getInt("id_usuario"));
                oUsuario.setNombre(rs.getString("nombre"));
                oUsuario.setApellido_paterno(rs.getString("apellido_paterno"));
                oUsuario.setApellido_materno(rs.getString("apellido_materno"));
                oUsuario.setCuenta(rs.getString("cuenta"));
                oUsuario.setContraseña(rs.getString("contraseña"));
                oUsuario.setEstado(rs.getBoolean("estado"));
                oUsuario.setTipo(rs.getString("tipo"));
                oUsuario.setEmail(rs.getString("email"));
                oUsuario.setRutaImagen(rs.getString("ruta_imagen"));
                lista.add(oUsuario);
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
    
    public Usuario Verificar(String Cuenta, String Contraseña) throws Exception {
        try {
            String sql = "";
            sql += "SELECT * FROM usuario WHERE ";
            sql += "cuenta = '" + Cuenta.toUpperCase() +"'";
//            sql += "AND contraseña = '" + Contraseña + "'";
            
            EjecutarSentenciaSQL(sql);
            rs.beforeFirst();
            
            if (rs.next()) {
                oUsuario = new Usuario();
                oUsuario.setId_usuario(rs.getInt("id_usuario"));
                oUsuario.setNombre(rs.getString("nombre"));
                oUsuario.setApellido_paterno(rs.getString("apellido_paterno"));
                oUsuario.setApellido_materno(rs.getString("apellido_materno"));
                oUsuario.setCuenta(rs.getString("cuenta"));
                oUsuario.setContraseña(rs.getString("email"));
                oUsuario.setEstado(rs.getBoolean("estado"));
                oUsuario.setTipo(rs.getString("tipo"));
                oUsuario.setEmail(rs.getString("email"));
                oUsuario.setRutaImagen(rs.getString("ruta_imagen"));
            }
            
            return oUsuario;
            
        } catch (Exception e) {
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

    public Timestamp VerFechaBD() throws Exception {
        try {
            String sql = "SELECT CURRENT_TIMESTAMP;";
            
            EjecutarSentenciaSQL(sql);
            rs.beforeFirst();

            Timestamp Fecha = null;
            
            if (rs.next()) {
                Fecha = rs.getTimestamp("NOW");
            }
            
            return Fecha;
            
        } catch (Exception e) {
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
