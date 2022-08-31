package GestionTramiteDocumentos.Entidades;

public class TramiteUnidad {
    private UnidadTramite unidadTramite;
    private Tramite tramite;

    public TramiteUnidad() {
    }

    public TramiteUnidad(UnidadTramite unidadTramite, Tramite tramite) {
        this.unidadTramite = unidadTramite;
        this.tramite = tramite;
    }

    public UnidadTramite getUnidadTramite() {
        return unidadTramite;
    }

    public void setUnidadTramite(UnidadTramite unidadTramite) {
        this.unidadTramite = unidadTramite;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    @Override
    public String toString() {
        return "unidadTramite=" + unidadTramite + ", tramite=" + tramite + '}';
    }
    
    
}
