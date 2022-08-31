package GestionTramiteDocumentos.Entidades;


public class Requisito {
    private Integer id_requisito;
    private String descipcion;
    private Boolean estado ;
    private TipoTramite tipoTramite;

    public Requisito() {
    }

    public Requisito(Integer id_requisito) {
        this.id_requisito = id_requisito;
    }

    public Requisito(Integer id_requisito, String descipcion, Boolean estado, TipoTramite tipoTramite) {
        this.id_requisito = id_requisito;
        this.descipcion = descipcion;
        this.estado = estado;
        this.tipoTramite = tipoTramite;
    }

    public Integer getId_requisito() {
        return id_requisito;
    }

    public void setId_requisito(Integer id_requisito) {
        this.id_requisito = id_requisito;
    }

    public String getDescipcion() {
        return descipcion;
    }

    public void setDescipcion(String descipcion) {
        this.descipcion = descipcion;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public TipoTramite getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(TipoTramite tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    @Override
    public String toString() {
        return this.descipcion; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
