package GestionTramiteDocumentos.Entidades;

public class Indicador {
    private Integer id_unidad_tramite;
    private String año;
    private String mes;
    private String tipoTramite;
    private UnidadTramite unidadTramite;
    public Indicador() {
    }

    public Indicador(Integer id_unidad_tramite, String año, String mes, String tipoTramite, UnidadTramite unidadTramite) {
        this.id_unidad_tramite = id_unidad_tramite;
        this.año = año;
        this.mes = mes;
        this.tipoTramite = tipoTramite;
        this.unidadTramite=unidadTramite;
    }

    public UnidadTramite getUnidadTramite() {
        return unidadTramite;
    }

    public void setUnidadTramite(UnidadTramite unidadTramite) {
        this.unidadTramite = unidadTramite;
    }

    
    public Integer getId_unidad_tramite() {
        return id_unidad_tramite;
    }

    public void setId_unidad_tramite(Integer id_unidad_tramite) {
        this.id_unidad_tramite = id_unidad_tramite;
    }

    public String getAño() {
        return año;
    }

    public void setAño(String año) {
        this.año = año;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getTipoTramite() {
        return tipoTramite;
    }

    public void setTipoTramite(String tipoTramite) {
        this.tipoTramite = tipoTramite;
    }

    
    
}
