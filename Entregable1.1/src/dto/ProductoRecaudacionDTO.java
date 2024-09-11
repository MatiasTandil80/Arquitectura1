package dto;

public class ProductoRecaudacionDTO {

    private int idProducto;
    private String nombre;
    private float valor;
    private int cantidadTotal;
    private float recaudacion;

    public ProductoRecaudacionDTO(int idProducto, String nombre, float valor, int cantidadTotal, float recaudacion) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.valor = valor;
        this.cantidadTotal = cantidadTotal;
        this.recaudacion = recaudacion;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }

    public int getCantidadTotal() {
        return cantidadTotal;
    }

    public void setCantidadTotal(int cantidadTotal) {
        this.cantidadTotal = cantidadTotal;
    }

    public float getRecaudacion() {
        return recaudacion;
    }

    public void setRecaudacion(float recaudacion) {
        this.recaudacion = recaudacion;
    }

    @Override
    public String toString() {
        return "ProductoRecaudacionDTO{" +
                "idProducto=" + idProducto +
                ", nombre='" + nombre + '\'' +
                ", valor=" + valor +
                ", cantidadTotal=" + cantidadTotal +
                ", recaudacion=" + recaudacion +
                '}';
    }
}
