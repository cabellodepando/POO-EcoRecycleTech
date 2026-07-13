package modelo;

/**
 * Factoría encargada de la instanciación de objetos de tipo Residuo.
 * Implementa el Patrón Factory para desacoplar la creación del uso.
 * 
 * @see Residuo
 */
public class ResiduoFactory {
    /**
     * Genera un residuo aleatorio simulando la entrada en la cinta.
     * Cumple con el principio SOLID de Abierto/Cerrado al permitir nuevos tipos.
     * 
     * @return Una instancia concreta de una subclase de Residuo.
     */
    public static Residuo crearResiduoAleatorio() {
        // ... lógica de creación ...
    }
}
