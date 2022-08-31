package GestionTramiteDocumentos.AccesoDatos;

import Conector.EntidadAD;
import GestionTramiteDocumentos.Entidades.DocRec;
import GestionTramiteDocumentos.Entidades.DocumentoRecibido;
import GestionTramiteDocumentos.Entidades.Requisito;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class DocRecAD extends EntidadAD {
    
    RequisitoAD requisitoAD;
    public DocRecAD(Connection connection) {
        super(connection);
        requisitoAD = new RequisitoAD(connection);
    }

    public void Insertar(DocRec docRec) throws Exception {
        try {
            String dml = "insert into doc_rec("+
                    "id_documento_recibido,id_requisito,estado) values("+
                    ""+docRec.getDocumentoRecibido().getId_documentoRecibido()+
                    ","+docRec.getRequisito().getId_requisito()+
                    ","+docRec.getRequisito().getEstado()+");";

            System.out.println(dml);
            EjecutarSentenciaDML(dml);
        }
        catch(Exception e) {
            throw e;
        }
    }

    public void Modificar(DocRec objeto) throws Exception {
       try {
            String dml = "update doc_rec "+
                    "id_documento_recibido = "+objeto.getDocumentoRecibido().getId_documentoRecibido()+","+
                    "id_requisito = "+objeto.getRequisito().getId_requisito()+","+
                    "id_estado= "+objeto.getRequisito().getEstado()+";";
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    }

    public void EliminarAll(DocumentoRecibido objeto) throws Exception {
       try { 
            String dml = "delete from doc_rec "
                    +"where id_documento_recibido = "+objeto.getId_documentoRecibido();
            System.out.println(dml);
            EjecutarSentenciaDML(dml);
       }
       catch(Exception e) {
            throw e;
       }
    } 
    
    public List<DocRec> ConsultarAll(DocumentoRecibido objeto) throws Exception {
        try {
            String sql = "select * from doc_rec"
                    + " where id_documento_recibido = "+objeto.getId_documentoRecibido();
            System.out.println(sql);
            List<DocRec> docRecs = new ArrayList<>();
            DocRec docRec = null;
            
            EjecutarSentenciaSQL(sql);

            rs.beforeFirst();

            while(rs.next()) {
                docRec = new DocRec();
                
                docRec.setDocumentoRecibido(objeto);
                docRec.setRequisito(requisitoAD.Consultar1(new Requisito(rs.getInt("id_requisito"),null,rs.getBoolean("estado"),null)));
                
                docRecs.add(docRec);
            }

            return docRecs;
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
