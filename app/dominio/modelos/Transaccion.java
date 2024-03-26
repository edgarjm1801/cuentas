package dominio.modelos;

import java.util.Date;
import java.util.UUID;

public class Transaccion {

    private UUID id;
    private String tipo;
    private double monto;
    private Date fecha;
    private ProductoFinanciero cuentaOrigen;
    private ProductoFinanciero cuentaDestino;

    public Transaccion(UUID id, String tipo, double monto, Date fecha, ProductoFinanciero cuentaOrigen, ProductoFinanciero cuentaDestino) {
        this.id = id;
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = fecha;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public UUID getId() {
        return id;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public ProductoFinanciero getCuentaOrigen() {
        return cuentaOrigen;
    }

    public ProductoFinanciero getCuentaDestino() {
        return cuentaDestino;
    }
}
