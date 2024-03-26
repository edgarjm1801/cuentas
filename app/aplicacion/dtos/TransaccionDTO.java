package aplicacion.dtos;

public class TransaccionDTO {

    private String tipo;
    private double monto;
    private Long idCuentaOrigen;
    private Long idCuentaDestino;

    public TransaccionDTO() {
    }

    public TransaccionDTO(String tipo, double monto, Long idCuentaOrigen, Long idCuentaDestino) {
        this.tipo = tipo;
        this.monto = monto;
        this.idCuentaOrigen = idCuentaOrigen;
        this.idCuentaDestino = idCuentaDestino;
    }

    public String getTipo() {
        return tipo;
    }

    public double getMonto() {
        return monto;
    }

    public Long getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    public Long getIdCuentaDestino() {
        return idCuentaDestino;
    }
}
