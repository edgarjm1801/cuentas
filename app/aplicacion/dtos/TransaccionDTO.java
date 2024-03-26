package aplicacion.dtos;

public class TransaccionDTO {

    private String tipo;
    private double monto;
    private String idCuentaOrigen;
    private String idCuentaDestino;

    public TransaccionDTO() {
    }

    public TransaccionDTO(String tipo, double monto, String idCuentaOrigen, String idCuentaDestino) {
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

    public String getIdCuentaOrigen() {
        return idCuentaOrigen;
    }

    public String getIdCuentaDestino() {
        return idCuentaDestino;
    }
}
