package GestionTramiteDocumentos.Entidades;

import java.util.Date;

public class DocumentoEmitido {
    private Integer id_documentoEmitido;
    private Integer anio;
    private String numero;
    private Date fecha_emision;
    private String para;
    private String asunto;
    private String numero_expediente;
    private Date fecha_recepcion;
    private TipoDocumento tipoDocumento;
    private UnidadTramite unidadTramite;
    private Tramite tramite;
    private boolean inicio_tramite;
    
    public DocumentoEmitido() {
    }

    public DocumentoEmitido(Integer id_documentoEmitido) {
        this.id_documentoEmitido = id_documentoEmitido;
    }

    public DocumentoEmitido(Integer id_documentoEmitido, Integer anio, String numero, Date fecha_emision, String para, String asunto, String numero_expediente, Date fecha_recepcion, TipoDocumento tipoDocumento, UnidadTramite unidadTramite, Tramite tramite, boolean inicio_tramite) {
        this.id_documentoEmitido = id_documentoEmitido;
        this.anio = anio;
        this.numero = numero;
        this.fecha_emision = fecha_emision;
        this.para = para;
        this.asunto = asunto;
        this.numero_expediente = numero_expediente;
        this.fecha_recepcion = fecha_recepcion;
        this.tipoDocumento = tipoDocumento;
        this.unidadTramite = unidadTramite;
        this.tramite = tramite;
        this.inicio_tramite = inicio_tramite;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    
    public Integer getId_documentoEmitido() {
        return id_documentoEmitido;
    }

    public void setId_documentoEmitido(Integer id_documentoEmitido) {
        this.id_documentoEmitido = id_documentoEmitido;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer año) {
        this.anio = año;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public String getPara() {
        return para;
    }

    public void setPara(String para) {
        this.para = para;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getNumero_expediente() {
        return numero_expediente;
    }

    public void setNumero_expediente(String numero_expediente) {
        this.numero_expediente = numero_expediente;
    }

    public Date getFecha_recepcion() {
        return fecha_recepcion;
    }

    public void setFecha_recepcion(Date fecha_recepcion) {
        this.fecha_recepcion = fecha_recepcion;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public UnidadTramite getUnidadTramite() {
        return unidadTramite;
    }

    public void setUnidadTramite(UnidadTramite unidadTramite) {
        this.unidadTramite = unidadTramite;
    }

    public boolean isInicio_tramite() {
        return inicio_tramite;
    }

    public void setInicio_tramite(boolean inicio_tramite) {
        this.inicio_tramite = inicio_tramite;
    }

    @Override
    public String toString() {
        return this.numero_expediente;
    }
}
