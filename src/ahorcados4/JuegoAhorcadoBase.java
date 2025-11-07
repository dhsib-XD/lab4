package ahorcados4;

import java.util.ArrayList;
import javax.swing.JOptionPane;

public abstract class JuegoAhorcadoBase implements JuegoAhorcado {

    protected String palabraSecreta;
    protected String palabraActual;
    protected int intentos;
    protected int limiteIntentos;
    protected ArrayList<Character> letrasUsadas;
    protected ArrayList<String> figuraAhorcado;

    public JuegoAhorcadoBase() {
        this.intentos = 0;
        this.limiteIntentos = 6;
        this.letrasUsadas = new ArrayList<>();
        this.figuraAhorcado = new ArrayList<>();
        inicializarFigura();
    }

    private void inicializarFigura() {
        figuraAhorcado.add("   ___________\n   |/        |\n   |         \n   |         \n   |         \n   |         \n   |         \n___|___      ");
        figuraAhorcado.add("   ___________\n   |/        |\n   |        (_)\n   |         \n   |         \n   |         \n   |         \n___|___      ");
        figuraAhorcado.add("   ___________\n   |/        |\n   |        (_)\n   |         |\n   |         |\n   |         \n   |         \n___|___      ");
        figuraAhorcado.add("   ___________\n   |/        |\n   |        (_)\n   |        \\|\n   |         |\n   |         \n   |         \n___|___      ");
        figuraAhorcado.add("   ___________\n   |/        |\n   |        (_)\n   |        \\|/\n   |         |\n   |         \n   |         \n___|___      ");
        figuraAhorcado.add("   ___________\n   |/        |\n   |        (_)\n   |        \\|/\n   |         |\n   |        / \n   |         \n___|___      ");
        figuraAhorcado.add("   ___________\n   |/        |\n   |        (_)\n   |        \\|/\n   |         |\n   |        / \\\n   |         \n___|___      ");
    }

    public abstract void actualizarPalabraActual(char letra);

    public abstract boolean verificarLetra(char letra);

    public abstract boolean hasGanado();

    public void jugar() {
        letrasUsadas.clear();
        intentos = 0;
        inicializarPalabraSecreta();
        boolean continuar = true;
        while (!juegoTerminado() && continuar) {
            String entrada = JOptionPane.showInputDialog(null,
                    construirEstado(),
                    "Juego del Ahorcado",
                    JOptionPane.PLAIN_MESSAGE);
            if (entrada == null) {
                continuar = false;
                break;
            }
            Character letraValida = validarEntrada(entrada);
            if (letraValida == null) {
                continue;
            }
            boolean acerto = verificarLetra(letraValida);
            String mensaje = acerto
                    ? "¡Bien! La letra " + letraValida + " está en la palabra"
                    : "La letra " + letraValida + " NO está en la palabra";
            JOptionPane.showMessageDialog(null, mensaje, "Intento", JOptionPane.INFORMATION_MESSAGE);
        }
        if (juegoTerminado()) {
            if (hasGanado()) {
                JOptionPane.showMessageDialog(null,
                        "¡FELICIDADES! Ganaste. La palabra era: " + palabraSecreta,
                        "Victoria",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null,
                        "Perdiste. La palabra era: " + palabraSecreta,
                        "Derrota",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,
                    "Juego cancelado.",
                    "Fin",
                    JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private Character validarEntrada(String texto) {
        if (texto == null) {
            return null;
        }
        String valor = texto.trim();
        if (valor.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Por favor ingrese una letra", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        if (valor.length() > 1) {
            JOptionPane.showMessageDialog(null, "Ingrese solo UNA letra", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        char letra = valor.charAt(0);
        if (!Character.isLetter(letra)) {
            JOptionPane.showMessageDialog(null, "Ingrese solo letras", "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        letra = Character.toUpperCase(letra);
        if (letrasUsadas.contains(letra)) {
            JOptionPane.showMessageDialog(null, "Letra repetida. Ya usaste: " + letra, "Aviso", JOptionPane.WARNING_MESSAGE);
            return null;
        }
        return letra;
    }

    private String construirEstado() {
        StringBuilder sb = new StringBuilder();
        sb.append("Palabra: ").append(formatearPalabraActual()).append("\n");
        sb.append("Intentos restantes: ").append(getIntentosRestantes()).append("\n");
        sb.append("Letras usadas: ").append(formatearLetrasUsadas()).append("\n\n");
        sb.append("Figura:\n").append(getFiguraActual()).append("\n\n");
        sb.append("Ingrese una letra:");
        return sb.toString();
    }

    private String formatearPalabraActual() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < palabraActual.length(); i++) {
            sb.append(palabraActual.charAt(i)).append(' ');
        }
        return sb.toString().trim();
    }

    private String formatearLetrasUsadas() {
        if (letrasUsadas.isEmpty()) {
            return "(ninguna)";
        }
        StringBuilder sb = new StringBuilder();
        for (Character c : letrasUsadas) {
            sb.append(c).append(' ');
        }
        return sb.toString().trim();
    }

    public String getPalabraSecreta() {
        return palabraSecreta;
    }

    public String getPalabraActual() {
        return palabraActual;
    }

    public int getIntentos() {
        return intentos;
    }

    public int getIntentosRestantes() {
        return limiteIntentos - intentos;
    }

    public ArrayList<Character> getLetrasUsadas() {
        return letrasUsadas;
    }

    public String getFiguraActual() {
        if (intentos >= figuraAhorcado.size()) {
            return figuraAhorcado.get(figuraAhorcado.size() - 1);
        }
        return figuraAhorcado.get(intentos);
    }

    public boolean juegoTerminado() {
        return hasGanado() || intentos >= limiteIntentos;
    }
}

