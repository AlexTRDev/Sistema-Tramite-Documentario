package GestionTramiteDocumentos.Entidades;

public class Usuario {
    private Integer id_usuario;
    private String apellido_paterno;
    private String apellido_materno;
    private String nombre;
    private String cuenta;
    private String contraseña;
    private Boolean estado;
    private String tipo;
    private String email;
    private String rutaImagen;
    
    public Usuario() {
    }

    public Usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public Usuario(String apellido_paterno, String apellido_materno, String nombre, String cuenta, String contraseña, Boolean estado, String tipo, String email) {
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.contraseña = contraseña;
        this.estado = estado;
        this.tipo = tipo;
        this.email = email;
    }

    public Usuario(String apellido_paterno, String apellido_materno, String nombre, String cuenta, String contraseña, Boolean estado, String tipo, String email, String rutaImagen) {
        this.apellido_paterno = apellido_paterno;
        this.apellido_materno = apellido_materno;
        this.nombre = nombre;
        this.cuenta = cuenta;
        this.contraseña = contraseña;
        this.estado = estado;
        this.tipo = tipo;
        this.email = email;
        this.rutaImagen = rutaImagen;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public void setRutaImagen(String rutaImagen) {
        this.rutaImagen = rutaImagen;
    }

    public Integer getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido_paterno ;
    }

}
