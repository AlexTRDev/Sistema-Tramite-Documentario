package GestionTramiteDocumentos.Entidades;

public class UnidadOrganizativa {
    private Integer id_unidadOrganizativa;
    private String nombre;
    private String abreviatura;
    private Boolean estado;

    public UnidadOrganizativa() {
    }

    public UnidadOrganizativa(Integer id_unidadOrganizativa) {
        this.id_unidadOrganizativa = id_unidadOrganizativa;
    }

    public UnidadOrganizativa(Integer id_unidadOrganizativa, String nombre, String abreviatura, Boolean estado) {
        this.id_unidadOrganizativa = id_unidadOrganizativa;
        this.nombre = nombre;
        this.abreviatura = abreviatura;
        this.estado = estado;
    }

    public Integer getId_unidadOrganizativa() {
        return id_unidadOrganizativa;
    }

    public void setId_unidadOrganizativa(Integer id_unidadOrganizativa) {
        this.id_unidadOrganizativa = id_unidadOrganizativa;
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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return this.nombre; //To change body of generated methods, choose Tools | Templates.
    }
    
    
}   

