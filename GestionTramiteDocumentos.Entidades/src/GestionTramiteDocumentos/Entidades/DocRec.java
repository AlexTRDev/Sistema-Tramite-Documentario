
package GestionTramiteDocumentos.Entidades;


public class DocRec {
    private DocumentoRecibido documentoRecibido;
    private Requisito requisito;

    public DocRec() {
    }

    public DocRec(DocumentoRecibido documentoRecibido, Requisito requisito) {
        this.documentoRecibido = documentoRecibido;
        this.requisito = requisito;
    }

    public DocumentoRecibido getDocumentoRecibido() {
        return documentoRecibido;
    }

    public void setDocumentoRecibido(DocumentoRecibido documentoRecibido) {
        this.documentoRecibido = documentoRecibido;
    }

    public Requisito getRequisito() {
        return requisito;
    }

    public void setRequisito(Requisito requisito) {
        this.requisito = requisito;
    }
    
    
}
