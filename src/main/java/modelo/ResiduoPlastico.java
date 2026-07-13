package modelo;

public class ResiduoPlastico extends Residuo {
    public ResiduoPlastico(String id, double peso) { super(id, peso); }
    @Override public String getTipo() { return "Plástico"; }
    @Override public boolean esReciclable() { return true; }
}

class ResiduoVidrio extends Residuo {
    public ResiduoVidrio(String id, double peso) { super(id, peso); }
    @Override public String getTipo() { return "Vidrio"; }
    @Override public boolean esReciclable() { return peso < 10.0; }
}

class ResiduoPapel extends Residuo {
    public ResiduoPapel(String id, double peso) { super(id, peso); }
    @Override public String getTipo() { return "Papel"; }
    @Override public boolean esReciclable() { return true; }
}

// Residuos Metal: Requisito de extensibilidad (Excelente)
class ResiduoMetal extends Residuo {
    public ResiduoMetal(String id, double peso) { super(id, peso); }
    @Override public String getTipo() { return "Metal"; }
    @Override public boolean esReciclable() { return true; }
}
