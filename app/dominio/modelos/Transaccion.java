package dominio.modelos;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public class Transaccion {

    private UUID id;
    private TipoTransaccion tipo;
    private double monto;
    private Date fecha;
    private Optional<ProductoFinanciero> cuentaOrigen;
    private Optional<ProductoFinanciero> cuentaDestino;

    public Transaccion(TipoTransaccion tipo, double monto, Optional<ProductoFinanciero> cuentaOrigen, Optional<ProductoFinanciero> cuentaDestino) {
        this.id = UUID.randomUUID();
        this.tipo = tipo;
        this.monto = monto;
        this.fecha = new Date();
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public Transaccion(UUID id, TipoTransaccion tipo, double monto, Date fecha, Optional<ProductoFinanciero> cuentaOrigen, Optional<ProductoFinanciero> cuentaDestino) {
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

    public TipoTransaccion getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public Date getFecha() {
        return fecha;
    }

    public Optional<ProductoFinanciero> getCuentaOrigen() {
        return cuentaOrigen;
    }

    public Optional<ProductoFinanciero> getCuentaDestino() {
        return cuentaDestino;
    }
}
