package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.RutaAD;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.Ruta;
import java.util.List;

public class RutaLN {
    
    public List<Ruta> ConsultarXFiltros(String sql) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RutaAD rutaAD = new RutaAD(conexion.getConnection());
            return rutaAD.ConsultarXFiltro(sql);
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
    
    public List<Ruta> ConsultarAll(DocumentoRecibido documentoRecibido) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RutaAD rutaAD = new RutaAD(conexion.getConnection());
            return rutaAD.ConsultarAll(documentoRecibido);
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
    
    public Ruta Consulta1(Ruta ruta) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RutaAD rutaAD = new RutaAD(conexion.getConnection());
            return rutaAD.Consultar1(ruta);
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
    
    public Ruta Consulta1DU(Ruta ruta) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RutaAD rutaAD = new RutaAD(conexion.getConnection());
            return rutaAD.Consultar1DU(ruta);
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
    
    public Ruta ConsultarUltima(Ruta ruta) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RutaAD rutaAD = new RutaAD(conexion.getConnection());
            return rutaAD.ConsultarUltima(ruta);
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
    
    public void Insertar(Ruta ruta) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RutaAD rutaAD = new RutaAD(conexion.getConnection());
            rutaAD.Insertar(ruta);
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
    
    public void Eliminar(Ruta ruta) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RutaAD rutaAD = new RutaAD(conexion.getConnection());
            rutaAD.Eliminar(ruta);
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
    
    public void Modificar(Ruta ruta) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            RutaAD rutaAD = new RutaAD(conexion.getConnection());
            rutaAD.Modificar(ruta);
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
