package modelo;

public class ResiduoFactory {
    /**
     * Genera un residuo aleatorio para simular la entrada en la cinta.
     */
    public static Residuo crearResiduoAleatorio() {
        String id = "RES-" + System.currentTimeMillis();
        double peso = (Math.random() * 5) + 0.5; // Entre 0.5 y 5.5 kg
        int tipo = (int) (Math.random() * 4);

        return switch (tipo) {
            case 0 -> new ResiduoPlastico(id, peso);
            case 1 -> new ResiduoVidrio(id, peso);
            case 2 -> new ResiduoPapel(id, peso);
            default -> new ResiduoMetal(id, peso); // Nuevo tipo soportado
        };
    }
}
