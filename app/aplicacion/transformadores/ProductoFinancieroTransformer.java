package aplicacion.transformadores;

import aplicacion.dtos.ProductoFinancieroDTO;
import dominio.modelos.ProductoFinanciero;
import dominio.modelos.TipoProductoFinanciero;

public class ProductoFinancieroTransformer {

    public static ProductoFinanciero fromDTO(ProductoFinancieroDTO productoFinancieroDTO) {
        return new ProductoFinanciero(
            TipoProductoFinanciero.fromString(productoFinancieroDTO.getTipo()),
            productoFinancieroDTO.getSaldo(),
            productoFinancieroDTO.isExentaGMF()
        );
    }
}
