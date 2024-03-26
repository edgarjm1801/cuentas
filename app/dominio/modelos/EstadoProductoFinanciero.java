package dominio.modelos;

public enum EstadoProductoFinanciero {
    ACTIVO,
    INACTIVO,
    CANCELADA,
    DESCONOCIDO;

    public static EstadoProductoFinanciero fromString(String estado) {
        try {
            return EstadoProductoFinanciero.valueOf(estado);
        } catch (IllegalArgumentException e) {
            return DESCONOCIDO;
        }
    }
}
