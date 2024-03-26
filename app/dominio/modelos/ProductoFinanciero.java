package dominio.modelos;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class ProductoFinanciero {

    private static final String PREFIJO_CUENTA_CORRIENTE = "33";
    private static final String PREFIJO_CUENTA_AHORRO = "53";
    private UUID id;
    private TipoProductoFinanciero tipo;
    private String numeroCuenta;
    private EstadoProductoFinanciero estado;
    private double saldo;
    private boolean exentaGMF;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public ProductoFinanciero(TipoProductoFinanciero tipo, double saldo,
                              boolean exentaGMF) {
        this.id = UUID.randomUUID();
        this.tipo = tipo;
        this.numeroCuenta = generarNumeroCuenta();
        this.estado = EstadoProductoFinanciero.ACTIVO;
        this.saldo = saldo;
        this.exentaGMF = exentaGMF;
        this.fechaCreacion = new Date();
        this.fechaModificacion =  new Date();
    }

    public ProductoFinanciero(UUID id, TipoProductoFinanciero tipo, String numeroCuenta, EstadoProductoFinanciero estado,
                              double saldo, boolean exentaGMF, Date fechaCreacion, Date fechaModificacion) {
        this.id = id;
        this.tipo = tipo;
        this.numeroCuenta = numeroCuenta;
        this.estado = estado;
        this.saldo = saldo;
        this.exentaGMF = exentaGMF;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
    }

    private String generarNumeroCuenta() {
        String prefijo;
        if (this.tipo == TipoProductoFinanciero.CUENTA_CORRIENTE) {
            prefijo = PREFIJO_CUENTA_CORRIENTE;
        } else if (this.tipo == TipoProductoFinanciero.CUENTA_AHORRO) {
            prefijo = PREFIJO_CUENTA_AHORRO;
        } else {
            throw new IllegalArgumentException("Tipo de cuenta no válido");
        }

        Random random = new Random();
        int numeroAleatorio = random.nextInt(100000000);
        String numeroCuenta = prefijo + String.format("%08d", numeroAleatorio);

        return numeroCuenta;
    }

    public UUID getId() {
        return id;
    }

    public TipoProductoFinanciero getTipo() {
        return tipo;
    }

    public String getNumeroCuenta() {
        return numeroCuenta;
    }

    public EstadoProductoFinanciero getEstado() {
        return estado;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isExentaGMF() {
        return exentaGMF;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

}
