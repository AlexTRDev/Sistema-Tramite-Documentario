package GestionTramiteDocumentos.Entidades;

import java.util.Date;


public class Tramite {
    private Integer id_tramite;
    private String descripcion;
    private String tipo_traslado;
    private String tipo_entidad;
    private String descripcion_entidad;
    private Date fecha_emision;
    private Ruta ruta;

    public Tramite() {
    }

    public Tramite(Integer id_tramite) {
        this.id_tramite = id_tramite;
    }

    public Tramite(Integer id_tramite, String descripcion, String tipo_traslado, String tipo_entidad, String descripcion_entidad, Date fecha_emision, Ruta ruta) {
        this.id_tramite = id_tramite;
        this.descripcion = descripcion;
        this.tipo_traslado = tipo_traslado;
        this.tipo_entidad = tipo_entidad;
        this.descripcion_entidad = descripcion_entidad;
        this.fecha_emision = fecha_emision;
        this.ruta = ruta;
    }

    public Integer getId_tramite() {
        return id_tramite;
    }

    public void setId_tramite(Integer id_tramite) {
        this.id_tramite = id_tramite;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo_traslado() {
        return tipo_traslado;
    }

    public void setTipo_traslado(String tipo_traslado) {
        this.tipo_traslado = tipo_traslado;
    }

    public String getTipo_entidad() {
        return tipo_entidad;
    }

    public void setTipo_entidad(String tipo_entidad) {
        this.tipo_entidad = tipo_entidad;
    }

    public String getDescripcion_entidad() {
        return descripcion_entidad;
    }

    public void setDescripcion_entidad(String descripcion_entidad) {
        this.descripcion_entidad = descripcion_entidad;
    }

    public Date getFecha_emision() {
        return fecha_emision;
    }

    public void setFecha_emision(Date fecha_emision) {
        this.fecha_emision = fecha_emision;
    }

    public Ruta getRuta() {
        return ruta;
    }

    public void setRuta(Ruta ruta) {
        this.ruta = ruta;
    }

    @Override
    public String toString() {
        return this.descripcion; //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
