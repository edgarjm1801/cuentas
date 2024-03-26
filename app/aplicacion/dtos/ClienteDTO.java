package aplicacion.dtos;

import java.util.Date;
public class ClienteDTO {

    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private Date fechaNacimiento;

    public ClienteDTO() {
    }

    public ClienteDTO(String tipoIdentificacion, String numeroIdentificacion, String nombres, String apellidos, String correoElectronico, Date fechaNacimiento) {
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getTipoIdentificacion() {
        return tipoIdentificacion;
    }

    public String getNumeroIdentificacion() {
        return numeroIdentificacion;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }
}
