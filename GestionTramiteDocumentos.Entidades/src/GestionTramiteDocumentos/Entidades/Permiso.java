package GestionTramiteDocumentos.Entidades;

public class Permiso {
    private Integer id_permiso;
    private String fecha_inicio;
    private String fecha_termino;
    private Usuario usuario;
    private UnidadOrganizativa unidadOrganizativa;
    private UnidadTramite unidadTramite;

    public Permiso() {
    }

    public Permiso(Integer id_requisito) {
        this.id_permiso = id_requisito;
    }

    public Permiso(String fecha_inicio, String fecha_termino, Usuario usario, UnidadOrganizativa unidadOrganizativa, UnidadTramite unidadTramite) {
        this.fecha_inicio = fecha_inicio;
        this.fecha_termino = fecha_termino;
        this.usuario = usario;
        this.unidadOrganizativa = unidadOrganizativa;
        this.unidadTramite = unidadTramite;
    }
    

    public Integer getId_permiso() {
        return id_permiso;
    }

    public void setId_permiso(Integer id_requisito) {
        this.id_permiso = id_requisito;
    }

    public String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public String getFecha_termino() {
        return fecha_termino;
    }

    public void setFecha_termino(String fecha_termino) {
        this.fecha_termino = fecha_termino;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public UnidadOrganizativa getUnidadOrganizativa() {
        return unidadOrganizativa;
    }

    public void setUnidadOrganizativa(UnidadOrganizativa unidadOrganizativa) {
        this.unidadOrganizativa = unidadOrganizativa;
    }

    public UnidadTramite getUnidadTramite() {
        return unidadTramite;
    }

    public void setUnidadTramite(UnidadTramite unidadTramite) {
        this.unidadTramite = unidadTramite;
    }
    
    public String toString(){
        return unidadTramite.toString();
    }
    
}
