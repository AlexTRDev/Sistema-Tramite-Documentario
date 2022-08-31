package GestionTramiteDocumentos.Entidades;

public class TipoTramite {
    private Integer id_tipoTramite;
    private String nombre;
    private Integer tiempo_estimado;
    private Boolean estado;
    private UnidadTramite unidadTramite;

    public TipoTramite() {
    }

    public TipoTramite(Integer id_tipoTramite) {
        this.id_tipoTramite = id_tipoTramite;
    }

    public TipoTramite(Integer id_tipoTramite, String nombre, Boolean estado){
        this.id_tipoTramite = id_tipoTramite;
        this.nombre = nombre;
        this.estado = estado;
    }
    
    public TipoTramite(Integer id_tipoTramite, String nombre, Integer tiempo_estimado, Boolean estado, UnidadTramite unidadTramite) {
        this.id_tipoTramite = id_tipoTramite;
        this.nombre = nombre;
        this.tiempo_estimado = tiempo_estimado;
        this.estado = estado;
        this.unidadTramite = unidadTramite;
    }
    

    public Integer getId_tipoTramite() {
        return id_tipoTramite;
    }

    public void setId_tipoTramite(Integer id_tipoTramite) {
        this.id_tipoTramite = id_tipoTramite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTiempo_estimado() {
        return tiempo_estimado;
    }

    public void setTiempo_estimado(Integer tiempo_estimado) {
        this.tiempo_estimado = tiempo_estimado;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public UnidadTramite getUnidadTramite() {
        return unidadTramite;
    }

    public void setUnidadTramite(UnidadTramite unidadTramite) {
        this.unidadTramite = unidadTramite;
    }

    @Override
    public String toString() {
        return this.nombre; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
