package GestionTramiteDocumentos.Entidades;


import java.util.Date;

public class Ruta {
    private Integer id_ruta;
    private Date fechaHora_recepcion;
    private Date fecha_respuesta;
    private String tipo_tramite;
    private DocumentoRecibido documentoRecibido;
    private UnidadTramite unidadOrganizativa;
    
    
    public Ruta() {
    }

    public Ruta(Integer id_ruta) {
        this.id_ruta = id_ruta;
    }

    public Ruta(Integer id_ruta, Date fechaHora_recepcion, Date fecha_respuesta, String tipo_expediente, DocumentoRecibido documentoRecibido, UnidadTramite unidadOrganizativa) {
        this.id_ruta = id_ruta;
        this.fechaHora_recepcion = fechaHora_recepcion;
        this.fecha_respuesta = fecha_respuesta;
        this.tipo_tramite = tipo_expediente;
        this.documentoRecibido = documentoRecibido;
        this.unidadOrganizativa = unidadOrganizativa;
    }



    public Date getFecha_respuesta() {
        return fecha_respuesta;
    }

    public void setFecha_respuesta(Date fecha_respuesta) {
        this.fecha_respuesta = fecha_respuesta;
    }

    
    public Integer getId_ruta() {
        return id_ruta;
    }

    public void setId_ruta(Integer id_ruta) {
        this.id_ruta = id_ruta;
    }

    public Date getFechaHora_recepcion() {
        return fechaHora_recepcion;
    }

    public void setFechaHora_recepcion(Date fechaHora_recepcion) {
        this.fechaHora_recepcion = fechaHora_recepcion;
    }

    public String getTipo_expediente() {
        return tipo_tramite;
    }

    public void setTipo_expediente(String tipo_tramite) {
        this.tipo_tramite = tipo_tramite;
    }

    public DocumentoRecibido getDocumentoRecibido() {
        return documentoRecibido;
    }

    public void setDocumentoRecibido(DocumentoRecibido documentoRecibido) {
        this.documentoRecibido = documentoRecibido;
    }

    public UnidadTramite getUnidadTramite() {
        return unidadOrganizativa;
    }

    public void setUnidadTramite(UnidadTramite unidadTramite) {
        this.unidadOrganizativa = unidadTramite;
    }

    @Override
    public String toString() {
        return "Ruta"; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
