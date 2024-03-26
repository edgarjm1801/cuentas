package dominio.modelos;

public enum TipoTransaccion {
    CONSIGNACION, RETIRO, TRANSFERENCIA, DESCONOCIDO;

    public static TipoTransaccion fromString(String tipo) {
        try {
            return TipoTransaccion.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            return DESCONOCIDO;
        }
    }
}
