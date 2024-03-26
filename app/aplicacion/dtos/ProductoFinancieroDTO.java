package aplicacion.dtos;

import java.util.UUID;

public class ProductoFinancieroDTO {

    private UUID idCliente;
    private String tipo;
    private double saldo;
    private boolean exentaGMF;

    public ProductoFinancieroDTO() {
    }

    public ProductoFinancieroDTO(UUID idCliente, String tipo, double saldo, boolean exentaGMF) {
        this.idCliente = idCliente;
        this.tipo = tipo;
        this.saldo = saldo;
        this.exentaGMF = exentaGMF;
    }

    public UUID getIdCliente() {
        return idCliente;
    }
    public String getTipo() {
        return tipo;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isExentaGMF() {
        return exentaGMF;
    }
}
