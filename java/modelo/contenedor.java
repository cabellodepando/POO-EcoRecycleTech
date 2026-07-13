package modelo;

public class Contenedor {
    private final String tipoMaterial;
    private final double capacidadMax;
    private double nivelActual;

    public Contenedor(String tipoMaterial, double capacidadMax) {
        this.tipoMaterial = tipoMaterial;
        this.capacidadMax = capacidadMax;
        this.nivelActual = 0.0;
    }

    public boolean añadirResiduo(double peso) {
        if (nivelActual + peso <= capacidadMax) {
            nivelActual += peso;
            return true;
        }
        return false; // Contenedor lleno
    }

    public void vaciar() { this.nivelActual = 0.0; }
    public double getPorcentajeLlenado() { return (nivelActual / capacidadMax) * 100; }
    public double getNivelActual() { return nivelActual; }
    public void setNivelActual(double nivel) { this.nivelActual = nivel; }
}
