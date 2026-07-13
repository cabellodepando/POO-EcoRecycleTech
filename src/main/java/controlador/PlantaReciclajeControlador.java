
package controlador;
import modelo.Residuo;
import vista.PlantaReciclajeVista;

/**
 * Clase mediadora entre la Vista (Swing) y el Modelo (Lógica de negocio).
 * Gestiona los eventos de usuario y la persistencia de datos en archivos planos.
 * 
 * @author Estudiante de Ingeniería
 * @see vista.PlantaReciclajeVista
 */
    public class PlantaReciclajeControlador {

    // 1. Añade aquí tus variables (ajusta los tipos según tu proyecto si es necesario)
    private PlantaReciclajeVista vista;
    private java.util.Map<String, Double> nivelesContenedores = new java.util.HashMap<>();
    private static final String LOG_FILE = "recycle.log";
    private static final String STATE_FILE = "estado.json";
    

    // 2. Tu constructor debería ir aquí (para inicializar la vista y cargar los datos)
    public PlantaReciclajeControlador(PlantaReciclajeVista vista) {
        this.vista = vista;
        inicializarContenedores();
        configurarEventos();
        cargarEstadoPlanta();
    }

    // El resto de tus métodos continúan igual...

    private void inicializarContenedores() {
        nivelesContenedores.put("Plástico", 0.0);
        nivelesContenedores.put("Vidrio", 0.0);
        nivelesContenedores.put("Papel", 0.0);
        nivelesContenedores.put("Metal", 0.0); // Extensión SOLID [8]
    }

    private void configurarEventos() {
        // Evento: Simular Entrada de Residuo [9]
        vista.getBtnSimularEntrada().addActionListener(e -> procesarNuevoResiduo());

        // Evento: Vaciar Contenedores [3]
        vista.getBtnVaciarContenedor().addActionListener(e -> {
            nivelesContenedores.replaceAll((k, v) -> 0.0);
            actualizarVistaCompleta();
            guardarEstadoPlanta();
        });
    }

    private void procesarNuevoResiduo() {
        // Uso del Patrón Factory para desacoplamiento [9, 10]
        Residuo nuevo = ResiduoFactory.crearResiduoAleatorio();
        String tipo = nuevo.getTipo();
        double peso = nuevo.getPeso();

        // Lógica de negocio: Acumular peso en el contenedor correspondiente
        if (nivelesContenedores.containsKey(tipo)) {
            double nuevoNivel = nivelesContenedores.get(tipo) + peso;
            nivelesContenedores.put(tipo, nuevoNivel);

            // 1. Escribir en Histórico (recycle.log) [4]
            registrarEnLog(nuevo);

            // 2. Actualizar la Vista (Porcentaje basado en capacidad de 50kg)
            int porcentaje = (int) Math.min((nuevoNivel / 50.0) * 100, 100);
            vista.actualizarLlenado(tipo, porcentaje);
            vista.setStatusCinta("Procesado: " + nuevo.getID() + " (" + tipo + ")");
            
            // 3. Guardar estado actual [4]
            guardarEstadoPlanta();
        }
    }

    // --- GESTIÓN DE PERSISTENCIA Y LOGS (I/O) [11, 12] ---

    private void registrarEnLog(Residuo r) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            pw.printf("[%tF %tT] ID: %s | Tipo: %s | Peso: %.2f kg%n", 
                      System.currentTimeMillis(), System.currentTimeMillis(),
                      r.getID(), r.getTipo(), r.getPeso());
        } catch (IOException e) {
            System.err.println("Error al escribir log: " + e.getMessage());
        }
    }

    private void guardarEstadoPlanta() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(STATE_FILE))) {
            // Formato JSON manual simplificado para el requerimiento [4]
            pw.println("{");
            nivelesContenedores.forEach((tipo, nivel) -> 
                pw.printf("  \"%s\": %.2f,%n", tipo, nivel));
            pw.println("}");
        } catch (IOException e) {
            System.err.println("Error al persistir estado: " + e.getMessage());
        }
    }

    private void cargarEstadoPlanta() {
        File archivo = new File(STATE_FILE);
        if (!archivo.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                // Lógica de parsing simple para recuperar niveles [11]
                for (String tipo : nivelesContenedores.keySet()) {
                    if (linea.contains("\"" + tipo + "\"")) {
                        double valor = Double.parseDouble(linea.split(":")[13].replace(",", "").trim());
                        nivelesContenedores.put(tipo, valor);
                    }
                }
            }
            actualizarVistaCompleta();
        } catch (Exception e) {
            System.err.println("Error al cargar estado previo: " + e.getMessage());
        }
    }

    private void actualizarVistaCompleta() {
        nivelesContenedores.forEach((tipo, nivel) -> {
            int porcentaje = (int) Math.min((nivel / 50.0) * 100, 100);
            vista.actualizarLlenado(tipo, porcentaje);
        });
    }
}