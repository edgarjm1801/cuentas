package aplicacion.transformadores;

import aplicacion.dtos.TransaccionDTO;
import dominio.modelos.ProductoFinanciero;
import dominio.modelos.TipoTransaccion;
import dominio.modelos.Transaccion;

import java.util.Optional;

public class TransaccionTransformer {

        public static Transaccion fromDTO(TransaccionDTO transaccionDTO, Optional<ProductoFinanciero> cuentaOrigen, Optional<ProductoFinanciero> cuentaDestino) {
            return new Transaccion(
                    TipoTransaccion.fromString(transaccionDTO.getTipo()),
                    transaccionDTO.getMonto(),
                    cuentaOrigen,
                    cuentaDestino
            );
        }
}
