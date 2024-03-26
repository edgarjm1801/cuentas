package dominio.modelos;

import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;
import java.util.List;

public class Cliente {

    private UUID id;
    private String tipoIdentificacion;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private Date fechaNacimiento;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private List<ProductoFinanciero> productosFinancieros;

    public Cliente(String tipoIdentificacion, String numeroIdentificacion, String nombres, String apellidos,
                   String correoElectronico, Date fechaNacimiento) {
        this.id = UUID.randomUUID();
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.fechaNacimiento = fechaNacimiento;
        Date fechaCreacion = new Date();
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaCreacion;
        this.productosFinancieros = new ArrayList<>();
    }

    public Cliente(UUID id, String tipoIdentificacion, String numeroIdentificacion, String nombres, String apellidos,
                   String correoElectronico, Date fechaNacimiento, Date fechaCreacion) {
        this.id = id;
        this.tipoIdentificacion = tipoIdentificacion;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.fechaNacimiento = fechaNacimiento;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = new Date();
        this.productosFinancieros = new ArrayList<>();
    }

    public UUID getId() {
        return id;
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

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public Cliente(List<ProductoFinanciero> productosFinancieros) {
        this.productosFinancieros = productosFinancieros;
    }
}
