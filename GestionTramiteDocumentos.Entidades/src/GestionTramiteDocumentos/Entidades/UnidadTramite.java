package GestionTramiteDocumentos.Entidades;

public class UnidadTramite {
    private Integer id_unidad_tramite;
    private String nombre;
    private String abreviatura;
    private String responsable;
    private Boolean estado;
    private UnidadOrganizativa unidadOrganizativa;
    
    private String fechaInicio = null;
    private String fechaFinal = null;
    
    private Ruta ruta;

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinal() {
        return fechaFinal;
    }

    public void setFechaFinal(String fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

   
    public UnidadTramite() {
    }

    public UnidadTramite(Integer id_unidad_tramite) {
        this.id_unidad_tramite = id_unidad_tramite;
    }

    public UnidadTramite(Integer id_unidad_tramite, String nombre, String abreviatura, String responsable, Boolean estado, UnidadOrganizativa unidadOrganizativa, Ruta ruta) {
        this.id_unidad_tramite = id_unidad_tramite;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.responsable = responsable;
        this.estado = estado;
        this.unidadOrganizativa = unidadOrganizativa;
        this.ruta = ruta;
    }

    public Integer getId_unidad_tramite() {
        return id_unidad_tramite;
    }

    public void setId_unidad_tramite(Integer id_unidad_tramite) {
        this.id_unidad_tramite = id_unidad_tramite;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAbreviatura() {
        return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }

    public String getResponsable() {
        return responsable;
    }

    public void setResponsable(String responsable) {
        this.responsable = responsable;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public UnidadOrganizativa getUnidadOrganizativa() {
        return unidadOrganizativa;
    }

    public void setUnidadOrganizativa(UnidadOrganizativa unidadOrganizativa) {
        this.unidadOrganizativa = unidadOrganizativa;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        
        if(this.unidadOrganizativa!=null){
            return this.unidadOrganizativa.getAbreviatura() + " - " + this.nombre;
        }else{
            return this.nombre;
        }
    }
    
//    public String toString() {
//        return this.unidadOrganizativa.getAbreviatura() + " - " + this.nombre; //To change body of generated methods, choose Tools | Templates.
//    }
//    
}
