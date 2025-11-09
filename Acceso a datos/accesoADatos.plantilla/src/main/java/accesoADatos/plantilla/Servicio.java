package accesoADatos.plantilla;



import java.util.List;
import java.util.stream.Collectors;
import Modelo;
import Repositorio;

/**
 * Servicio genérico
 * Aplica reglas de negocio y proporciona métodos útiles para filtrar o calcular datos
 */
public class Servicio {

    private Repositorio repositorio;

    public Servicio() {
        this.repositorio = new Repositorio(); // Conecta con la capa de repositorio
    }

    // EJEMPLO: agregar un objeto
    public void agregarEntidad(Modelo obj) throws Exception {
        
        repositorio.agregar(obj);         // luego llamamos al CRUD del repositorio
    }

    // EJEMPLO: eliminar un objeto
    public void eliminarEntidad(Modelo obj) {
        repositorio.eliminar(obj);        // llama al método del repositorio
    }

    // EJEMPLO: actualizar un objeto
    public void actualizarEntidad(Modelo objViejo, Modelo objNuevo) throws Exception {
       
        repositorio.actualizar(objViejo, objNuevo); // llama al repositorio
    }
    
    // Valida un atributo obligatorio
    public void validarAtributo1(Modelo obj) throws Exception {
    	//El atributo que sea tiene que estar en la clase Modelo
        if (obj.getAtributo1() == null || obj.getAtributo1().isEmpty()) {
            throw new Exception("Atributo1 obligatorio");
        }
    }

    // Aplica todas las reglas de negocio sobre un objeto
    public void aplicarReglasNegocio(Entidad obj) throws Exception {
        validarAtributo1(obj);
        // Aquí puedes añadir más reglas según tu proyecto
        // Ej: validar rango de atributo2, longitud de listaAtributos, formato de atributo3
    }

    // Busca objetos según el valor de atributo1
    public List<Modelo> buscarPorAtributo1(String valor) {
        return repositorio.cargar().stream()
                .filter(e -> e.getAtributo1().equalsIgnoreCase(valor))
                .collect(Collectors.toList());
    }

    // Filtra objetos según un rango de atributo2
    public List<Entidad> filtrarPorRangoAtributo2(int min, int max) {
        return repositorio.cargar().stream()
                .filter(e -> e.getAtributo2() >= min && e.getAtributo2() <= max)
                .collect(Collectors.toList());
    }

    // Calcula la media de atributo2
    public double calcularMediaAtributo2() {
        return repositorio.cargar().stream()
                .mapToDouble(Entidad::getAtributo2)
                .average()
                .orElse(0.0);
    }

   
}

