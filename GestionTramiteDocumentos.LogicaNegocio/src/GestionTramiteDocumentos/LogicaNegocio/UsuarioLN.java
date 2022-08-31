package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.UsuarioAD;
import GestionTramiteDocumentos.Entidades.UnidadOrganizativa;
import GestionTramiteDocumentos.Entidades.Usuario;
import java.sql.Timestamp;
import java.util.List;

public class UsuarioLN {
    
    public Usuario ConsultarUsuarioID(Usuario usuario) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UsuarioAD usuarioAD = new UsuarioAD(conexion.getConnection());
            return usuarioAD.ConsultarPorID(usuario);
        }
        catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
    public Usuario ConsultarUsuarioCuenta(Usuario usuario) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UsuarioAD usuarioAD = new UsuarioAD(conexion.getConnection());
            return usuarioAD.ConsultarPorCuenta(usuario);
        }
        catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
    public List<Usuario> ConsultarAll() throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UsuarioAD usuarioAD = new UsuarioAD(conexion.getConnection());
            return usuarioAD.ConsultarAll();
        }
        catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
    public List<Usuario> ConsultarAll(UnidadOrganizativa unidadOrganizativa) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UsuarioAD usuarioAD = new UsuarioAD(conexion.getConnection());
            return usuarioAD.ConsultarAll(unidadOrganizativa);
        }
        catch(Exception e) {
            System.out.println("Error: "+e.getMessage());
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
    public void Insertar(Usuario usuario) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UsuarioAD usuarioAD = new UsuarioAD(conexion.getConnection());
            usuarioAD.Insertar(usuario);
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
    public void Eliminar(Usuario usuario) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UsuarioAD usuarioAD = new UsuarioAD(conexion.getConnection());
            usuarioAD.Eliminar(usuario);
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
    public void Modificar(Usuario usuario) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UsuarioAD usuarioAD = new UsuarioAD(conexion.getConnection());
            usuarioAD.Modificar(usuario);
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
    public Usuario VerificarIngreso(String Cuenta,String Contraseña) throws Exception{
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UsuarioAD usuarioAD = new UsuarioAD(conexion.getConnection());
            
            return usuarioAD.Verificar(Cuenta,Contraseña);
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
   
    public Timestamp getFechaBD() throws Exception{
        Conexion conexion = null;
        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            UsuarioAD usuarioAD = new UsuarioAD(conexion.getConnection());
            
            return usuarioAD.VerFechaBD();
        }
        catch(Exception e) {
            throw e;
        }
        finally {
            if(conexion != null) {
                if(conexion.getConnection() != null) {
                    if(!conexion.EstaCerrada()) {
                        conexion.Cerrar();
                    }
                }
            }
        }
    }
    
}
