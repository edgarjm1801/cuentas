package dominio.servicios;

import java.time.LocalDate;
import java.time.Period;

public class ValidarClienteService {

    public static final int EDAD_MINIMA = 18;

    public boolean validaionEdad(LocalDate fechaNacimiento) {
        LocalDate fechaActual = LocalDate.now();
        return Period.between(fechaNacimiento, fechaActual).getYears() >= EDAD_MINIMA;
    }
}
