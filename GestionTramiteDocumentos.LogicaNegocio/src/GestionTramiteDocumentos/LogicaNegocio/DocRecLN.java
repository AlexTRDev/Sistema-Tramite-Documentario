package GestionTramiteDocumentos.LogicaNegocio;

import Conector.Conexion;
import GestionTramiteDocumentos.AccesoDatos.*;
import GestionTramiteDocumentos.Entidades.*;
import java.util.ArrayList;
import java.util.List;

public class DocRecLN {
    
    public void Insertar(DocRec objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocRecAD docRecAD = new DocRecAD(conexion.getConnection());
            docRecAD.Insertar(objeto);
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
    
    public void EliminarAll(DocumentoRecibido objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocRecAD docRecAD = new DocRecAD(conexion.getConnection());
            docRecAD.EliminarAll(objeto);
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
    
    public void Modificar(DocRec objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocRecAD docRecAD = new DocRecAD(conexion.getConnection());
            docRecAD.Modificar(objeto);
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
    
    public List<DocRec> ConsultarAll(DocumentoRecibido objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocRecAD docRecAD = new DocRecAD(conexion.getConnection());
            return docRecAD.ConsultarAll(objeto);
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
    
    public List<Requisito> ConsultarAllRequisitos(DocumentoRecibido objeto) throws Exception {
        Conexion conexion = null;

        try {
            conexion = new Conexion();
            conexion.Abrir(true);

            DocRecAD docRecAD = new DocRecAD(conexion.getConnection());
            List<DocRec> docRecs = docRecAD.ConsultarAll(objeto);
            
            List<Requisito> requisitos = new ArrayList<>();
            for (int i = 0; i < docRecs.size(); i++) {
                requisitos.add(docRecs.get(i).getRequisito());
            }
            return requisitos;
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

}
