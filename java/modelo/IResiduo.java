package modelo;

/**
 * Define el contrato básico para cualquier material procesado en la planta.
 * Esta interfaz permite el tratamiento polimórfico de los residuos.
 * 
 * @author Estudiante de Ingeniería
 * @version 1.0
 */
public interface IResiduo {
    /** @return Identificador único del residuo. */
    String getID();
    /** @return Peso del material en kilogramos. */
    double getPeso();
    /** @return Nombre del tipo de material (Plástico, Vidrio, etc.). */
    String getTipo();
}
