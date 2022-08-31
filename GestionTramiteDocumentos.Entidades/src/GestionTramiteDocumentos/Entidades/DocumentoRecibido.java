package GestionTramiteDocumentos.Entidades;

public class DocumentoRecibido {
    private Integer id_documentoRecibido;
    private Integer año_expediente;
    private String numero_expediente;
    private String numero;
    private String de;
    private String asunto;
    private String observacion;
    private Boolean estado;
    private TipoDocumento tipoDocumento;
    
    public DocumentoRecibido() {
    }

    public DocumentoRecibido(Integer id_documentoRecibido) {
        this.id_documentoRecibido = id_documentoRecibido;
    }

    public DocumentoRecibido(Integer id_documentoRecibido, Integer año_expediente, String numero_expediente, String numero, String de, String asunto, String observacion, Boolean estado, TipoDocumento tipoDocumento) {
        this.id_documentoRecibido = id_documentoRecibido;
        this.año_expediente = año_expediente;
        this.numero_expediente = numero_expediente;
        this.numero = numero;
        this.de = de;
        this.asunto = asunto;
        this.observacion = observacion;
        this.estado = estado;
        this.tipoDocumento = tipoDocumento;
    }

    public Integer getId_documentoRecibido() {
        return id_documentoRecibido;
    }

    public void setId_documentoRecibido(Integer id_documentoRecibido) {
        this.id_documentoRecibido = id_documentoRecibido;
    }

    public Integer getAño_expediente() {
        return año_expediente;
    }

    public void setAño_expediente(Integer año_expediente) {
        this.año_expediente = año_expediente;
    }

    public String getNumero_expediente() {
        return numero_expediente;
    }

    public void setNumero_expediente(String numero_expediente) {
        this.numero_expediente = numero_expediente;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getDe() {
        return de;
    }

    public void setDe(String de) {
        this.de = de;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @Override
    public String toString() {
        return this.numero;//To change body of generated methods, choose Tools | Templates.
    }

    
}
