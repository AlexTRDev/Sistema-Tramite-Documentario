package Reporte;

public class Parametro {
    private Integer Id;
    private String Nombre;
    private Object valor;

    public Parametro() {
    }

    public Parametro(Integer Id) {
        this.Id = Id;
    }

    public Parametro(Integer Id, String Nombre, Object valor) {
        this.Id = Id;
        this.Nombre = Nombre;
        this.valor = valor;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public Object getValor() {
        return valor;
    }

    public void setValor(Object valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return this.Id.toString();
    }
}