import com.google.inject.AbstractModule;
import dominio.repositorios.ClienteRepository;
import dominio.repositorios.ProductoFinancieroRepository;
import dominio.repositorios.TransaccionRepository;
import infraestructura.adaptadores.cliente.ClienteRepositoryImpl;
import infraestructura.adaptadores.productoFinaciero.ProductoFinancieroRepositoryImpl;
import infraestructura.adaptadores.transaccion.TransaccionRepositoryImpl;


public class AppModule extends AbstractModule {

    @Override
    protected void configure() {
        // Configuración de la aplicación
        //bind(Jdbi.class).toProvider(JdbiProvider.class);
        bind(ClienteRepository.class).to(ClienteRepositoryImpl.class);
        bind(ProductoFinancieroRepository.class).to(ProductoFinancieroRepositoryImpl.class);
        bind(TransaccionRepository.class).to(TransaccionRepositoryImpl.class);
    }
}
