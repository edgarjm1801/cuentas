package dominio.modelos;

import java.util.Arrays;

public enum TipoProductoFinanciero {
    CUENTA_CORRIENTE,
    CUENTA_AHORRO,
    DESCONOCIDO;

    public static TipoProductoFinanciero fromString(String tipoString) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(tipoString))
                .findFirst()
                .orElse(DESCONOCIDO);
    }
}
