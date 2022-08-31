package GestionTramiteDocumentos.Entidades;

public class TipoDocumento {
    private Integer id_tipoDocumento;
    private String nombre;
    private Boolean estado;

    public TipoDocumento() {
    }

    public TipoDocumento(Integer id_tipoDocumento) {
        this.id_tipoDocumento = id_tipoDocumento;
    }

    public TipoDocumento(Integer id_tipoDocumento, String nombre, Boolean estado) {
        this.id_tipoDocumento = id_tipoDocumento;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Integer getId_tipoDocumento() {
        return id_tipoDocumento;
    }

    public void setId_tipoDocumento(Integer id_tipoDocumento) {
        this.id_tipoDocumento = id_tipoDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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
