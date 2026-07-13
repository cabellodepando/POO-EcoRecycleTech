
Clase: PlantaReciclajeVista.java
package vista;

import javax.swing.*;
import java.awt.*;

/**
 * Representa la interfaz gráfica de la planta EcoRecycle Tech SA.
 * Según el patrón MVC, esta clase no contiene lógica de negocio [3, 5].
 */
public class PlantaReciclajeVista extends JFrame {

    // Componentes para mostrar el estado de los contenedores [3]
    private JProgressBar barraPlastico, barraVidrio, barraPapel, barraMetal;
    private JLabel lblStatusCinta;
    
    // Botones de acción [3, 6]
    private JButton btnSimularEntrada;
    private JButton btnVaciarContenedor;

    public PlantaReciclajeVista() {
        super("EcoRecycle Tech SA - Sistema de Clasificación");
        inicializarComponentes();
        configurarVentana();
    }

    private void inicializarComponentes() {
        // Layout principal dividido en regiones [7, 8]
        setLayout(new BorderLayout(10, 10));

        // Panel Superior: Estado de la Cinta Transportadora
        JPanel panelCinta = new JPanel();
        lblStatusCinta = new JLabel("Cinta vacía. Esperando residuos...");
        panelCinta.add(new JLabel(new ImageIcon("cinta_icon.png"))); // Opcional
        panelCinta.add(lblStatusCinta);
        add(panelCinta, BorderLayout.NORTH);

        // Panel Central: Estado de los Contenedores (Niveles de llenado) [1]
        JPanel panelContenedores = new JPanel(new GridLayout(4, 2, 5, 5));
        panelContenedores.setBorder(BorderFactory.createTitledBorder("Niveles de Llenado"));

        barraPlastico = crearBarra();
        barraVidrio = crearBarra();
        barraPapel = crearBarra();
        barraMetal = crearBarra();

        panelContenedores.add(new JLabel("Plástico:"));
        panelContenedores.add(barraPlastico);
        panelContenedores.add(new JLabel("Vidrio:"));
        panelContenedores.add(barraVidrio);
        panelContenedores.add(new JLabel("Papel:"));
        panelContenedores.add(barraPapel);
        panelContenedores.add(new JLabel("Metal:"));
        panelContenedores.add(barraMetal);

        add(panelContenedores, BorderLayout.CENTER);

        // Panel Inferior: Acciones del Usuario [3]
        JPanel panelAcciones = new JPanel(new FlowLayout());
        btnSimularEntrada = new JButton("Simular Entrada de Residuo");
        btnVaciarContenedor = new JButton("Vaciar Contenedores");

        panelAcciones.add(btnSimularEntrada);
        panelAcciones.add(btnVaciarContenedor);
        add(panelAcciones, BorderLayout.SOUTH);
    }

    private JProgressBar crearBarra() {
        JProgressBar pb = new JProgressBar(0, 100);
        pb.setStringPainted(true);
        return pb;
    }

    private void configurarVentana() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Centrar en pantalla
    }

    // --- MÉTODOS DE ACTUALIZACIÓN (Usados por el Controlador) [9] ---

    public void actualizarLlenado(String tipo, int porcentaje) {
        switch (tipo.toLowerCase()) {
            case "plástico" -> barraPlastico.setValue(porcentaje);
            case "vidrio" -> barraVidrio.setValue(porcentaje);
            case "papel" -> barraPapel.setValue(porcentaje);
            case "metal" -> barraMetal.setValue(porcentaje);
        }
        
        // Alerta si el contenedor está lleno [3]
        if (porcentaje >= 100) {
            JOptionPane.showMessageDialog(this, "¡ATENCIÓN! Contenedor de " + tipo + " lleno.", 
                                          "Alerta de Planta", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void setStatusCinta(String mensaje) {
        lblStatusCinta.setText(mensaje);
    }

    // --- GETTERS PARA EL CONTROLADOR [9, 10] ---

    public JButton getBtnSimularEntrada() { return btnSimularEntrada; }
    public JButton getBtnVaciarContenedor() { return btnVaciarContenedor; }
}
