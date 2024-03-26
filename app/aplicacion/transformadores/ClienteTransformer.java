package aplicacion.transformadores;

import aplicacion.dtos.ClienteDTO;
import dominio.modelos.Cliente;

import java.util.UUID;

public class ClienteTransformer {

    public static Cliente fromDTO(ClienteDTO clienteDTO) {
        return new Cliente(
                clienteDTO.getTipoIdentificacion(),
                clienteDTO.getNumeroIdentificacion(),
                clienteDTO.getNombres(),
                clienteDTO.getApellidos(),
                clienteDTO.getCorreoElectronico(),
                clienteDTO.getFechaNacimiento()
        );
    }
    public static Cliente fromDTOUpdate(ClienteDTO clienteDTO, Cliente clienteExistente) {
        return new Cliente(
                clienteExistente.getId(),
                clienteDTO.getTipoIdentificacion(),
                clienteDTO.getNumeroIdentificacion(),
                clienteDTO.getNombres(),
                clienteDTO.getApellidos(),
                clienteDTO.getCorreoElectronico(),
                clienteDTO.getFechaNacimiento(),
                clienteExistente.getFechaCreacion()
        );
    }
}
