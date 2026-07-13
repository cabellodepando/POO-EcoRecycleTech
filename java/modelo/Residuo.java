package modelo;

public abstract class Residuo implements IResiduo {
    protected String id;
    protected double peso;

    public Residuo(String id, double peso) {
        this.id = id;
        this.peso = peso;
    }

    @Override public String getID() { return id; }
    @Override public double getPeso() { return peso; }
    
    // Método polimórfico obligatorio
    public abstract boolean esReciclable(); 
}
