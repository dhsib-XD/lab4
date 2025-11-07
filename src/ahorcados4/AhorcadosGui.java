package ahorcados4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class AhorcadosGui extends JFrame {

    private JuegoAhorcadoBase juegoActual;
    private AdminPalabrasSecretas admin;

    private JLabel lblPalabraActual;
    private JLabel lblIntentos;
    private JLabel lblLetrasUsadas;
    private JTextArea txtAreaFigura;
    private JTextField txtLetra;
    private JButton btnIntentar;
    private JButton btnNuevoJuegoFijo;
    private JButton btnNuevoJuegoAzar;
    private JLabel lblMensaje;

    public AhorcadosGui() {
        admin = new AdminPalabrasSecretas();
        configurarVentana();
        inicializarComponentes();
        iniciarNuevoJuegoAzar();
    }

    private void configurarVentana() {
        setTitle("Epic Games");
        setSize(600, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(new Color(15, 22, 36));
    }

    private void inicializarComponentes() {
        Color panelOscuro = new Color(32, 42, 60);
        Color acento = new Color(0, 173, 181);
        Color textoPrincipal = new Color(235, 242, 248);
        Color textoSecundario = new Color(188, 200, 212);
        Color bloqueSuave = new Color(45, 58, 82);

        JLabel lblTitulo = new JLabel("AHORCADOS");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 28));
        lblTitulo.setForeground(acento);
        lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitulo.setBounds(20, 15, 540, 35);
        add(lblTitulo);

        JPanel panelSuperior = new JPanel(null);
        panelSuperior.setBounds(20, 60, 540, 240);
        panelSuperior.setOpaque(false);
        add(panelSuperior);

        JPanel panelFigura = new JPanel(null);
        panelFigura.setBounds(0, 0, 260, 240);
        panelFigura.setBackground(panelOscuro);
        panelFigura.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(acento, 2, true),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        panelSuperior.add(panelFigura);

        txtAreaFigura = new JTextArea();
        txtAreaFigura.setFont(new Font("Monospaced", Font.BOLD, 14));
        txtAreaFigura.setEditable(false);
        txtAreaFigura.setForeground(textoPrincipal);
        txtAreaFigura.setBackground(panelOscuro);
        txtAreaFigura.setBounds(10, 10, 240, 220);
        txtAreaFigura.setBorder(BorderFactory.createLineBorder(new Color(55, 66, 88), 1));
        txtAreaFigura.setFocusable(false);
        panelFigura.add(txtAreaFigura);

        JPanel panelInfo = new JPanel(null);
        panelInfo.setBounds(280, 0, 260, 240);
        panelInfo.setBackground(panelOscuro);
        panelInfo.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(acento, 2, true),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
        ));
        panelSuperior.add(panelInfo);

        lblPalabraActual = new JLabel("_ _ _ _ _");
        lblPalabraActual.setFont(new Font("Arial", Font.BOLD, 30));
        lblPalabraActual.setForeground(textoPrincipal);
        lblPalabraActual.setHorizontalAlignment(SwingConstants.CENTER);
        lblPalabraActual.setBounds(10, 10, 240, 50);
        panelInfo.add(lblPalabraActual);

        lblIntentos = new JLabel("Intentos restantes: 6");
        lblIntentos.setFont(new Font("Arial", Font.PLAIN, 16));
        lblIntentos.setForeground(textoSecundario);
        lblIntentos.setBounds(10, 80, 240, 25);
        panelInfo.add(lblIntentos);

        lblLetrasUsadas = new JLabel("Letras usadas: ");
        lblLetrasUsadas.setFont(new Font("Arial", Font.PLAIN, 14));
        lblLetrasUsadas.setForeground(textoSecundario);
        lblLetrasUsadas.setOpaque(true);
        lblLetrasUsadas.setBackground(bloqueSuave);
        lblLetrasUsadas.setVerticalAlignment(SwingConstants.TOP);
        lblLetrasUsadas.setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));
        lblLetrasUsadas.setBounds(10, 120, 240, 90);
        panelInfo.add(lblLetrasUsadas);

        JPanel panelControl = new JPanel(null);
        panelControl.setBounds(20, 320, 540, 90);
        panelControl.setBackground(panelOscuro);
        panelControl.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(acento, 2, true),
                BorderFactory.createEmptyBorder(10, 14, 10, 14)
        ));
        add(panelControl);

        JLabel lblIngresarLetra = new JLabel("Ingrese una letra:");
        lblIngresarLetra.setFont(new Font("Arial", Font.PLAIN, 14));
        lblIngresarLetra.setForeground(textoSecundario);
        lblIngresarLetra.setBounds(10, 10, 150, 25);
        panelControl.add(lblIngresarLetra);

        txtLetra = new JTextField();
        txtLetra.setFont(new Font("Arial", Font.PLAIN, 18));
        txtLetra.setBounds(170, 10, 50, 32);
        txtLetra.setHorizontalAlignment(JTextField.CENTER);
        txtLetra.setBackground(new Color(55, 68, 92));
        txtLetra.setForeground(textoPrincipal);
        txtLetra.setBorder(BorderFactory.createLineBorder(acento, 2, true));
        panelControl.add(txtLetra);

        btnIntentar = new JButton("Intentar");
        btnIntentar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnIntentar.setBounds(240, 10, 120, 32);
        btnIntentar.setBackground(acento);
        btnIntentar.setForeground(Color.BLACK);
        btnIntentar.setFocusPainted(false);
        btnIntentar.setBorder(BorderFactory.createEmptyBorder(5, 12, 5, 12));
        btnIntentar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIntentar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                intentarLetra();
            }
        });
        panelControl.add(btnIntentar);

        lblMensaje = new JLabel("");
        lblMensaje.setFont(new Font("Arial", Font.ITALIC, 14));
        lblMensaje.setForeground(acento);
        lblMensaje.setBounds(10, 50, 500, 25);
        panelControl.add(lblMensaje);

        JPanel panelBotones = new JPanel();
        panelBotones.setBounds(20, 420, 540, 70);
        panelBotones.setOpaque(false);
        panelBotones.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 10));
        add(panelBotones);

        btnNuevoJuegoFijo = new JButton("Nuevo Juego (Palabra Fija)");
        btnNuevoJuegoFijo.setFont(new Font("Arial", Font.PLAIN, 14));
        btnNuevoJuegoFijo.setBackground(acento);
        btnNuevoJuegoFijo.setForeground(Color.BLACK);
        btnNuevoJuegoFijo.setFocusPainted(false);
        btnNuevoJuegoFijo.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        btnNuevoJuegoFijo.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNuevoJuegoFijo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarNuevoJuegoFijo();
            }
        });
        panelBotones.add(btnNuevoJuegoFijo);

        btnNuevoJuegoAzar = new JButton("Nuevo Juego (Palabra Aleatoria)");
        btnNuevoJuegoAzar.setFont(new Font("Arial", Font.PLAIN, 14));
        btnNuevoJuegoAzar.setBackground(acento);
        btnNuevoJuegoAzar.setForeground(Color.BLACK);
        btnNuevoJuegoAzar.setFocusPainted(false);
        btnNuevoJuegoAzar.setBorder(BorderFactory.createEmptyBorder(8, 18, 8, 18));
        btnNuevoJuegoAzar.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnNuevoJuegoAzar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                iniciarNuevoJuegoAzar();
            }
        });
        panelBotones.add(btnNuevoJuegoAzar);

        txtLetra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                intentarLetra();
            }
        });
    }

    private void iniciarNuevoJuegoFijo() {
        String palabra = JOptionPane.showInputDialog(this, "Ingrese la palabra secreta:", "Palabra Fija", JOptionPane.QUESTION_MESSAGE);
        if (palabra != null && !palabra.trim().isEmpty()) {
            juegoActual = new JuegoAhorcadoFijo(palabra);
            actualizarInterfaz();
            lblMensaje.setText("Nuevo juego iniciado con palabra fija");
            txtLetra.setEnabled(true);
            btnIntentar.setEnabled(true);
        }
    }

    private void iniciarNuevoJuegoAzar() {
        juegoActual = new JuegoAhorcadoAzar(admin);
        actualizarInterfaz();
        lblMensaje.setText("Nuevo juego iniciado con palabra aleatoria");
        txtLetra.setEnabled(true);
        btnIntentar.setEnabled(true);
    }

    private void intentarLetra() {
        if (juegoActual.juegoTerminado()) {
            lblMensaje.setText("El juego ha terminado. Inicie un nuevo juego.");
            return;
        }

        String texto = txtLetra.getText().trim();

        if (texto.isEmpty()) {
            lblMensaje.setText("Por favor ingrese una letra");
            txtLetra.setText("");
            return;
        }

        if (texto.length() > 1) {
            lblMensaje.setText("Ingrese solo UNA letra");
            txtLetra.setText("");
            return;
        }

        char letra = texto.charAt(0);

        if (!Character.isLetter(letra)) {
            lblMensaje.setText("Ingrese solo letras");
            txtLetra.setText("");
            return;
        }

        letra = Character.toUpperCase(letra);

        if (juegoActual.getLetrasUsadas().contains(letra)) {
            lblMensaje.setText("Letra repetida. Ya usaste: " + letra);
            txtLetra.setText("");
            return;
        }

        boolean acerto = juegoActual.verificarLetra(letra);

        if (acerto) {
            lblMensaje.setText(" Muy Bien! La letra " + letra + " esta en la palabra");
        } else {
            lblMensaje.setText("La letra " + letra + " NO esta en la palabra");
        }

        txtLetra.setText("");
        actualizarInterfaz();

        if (juegoActual.juegoTerminado()) {
            if (juegoActual.hasGanado()) {
                lblMensaje.setText("FELICIDADES, eres una bestia parda! Ganaste. La palabra era: \n" + juegoActual.getPalabraSecreta());
                JOptionPane.showMessageDialog(this, "¡GANASTE!", "Victoria", JOptionPane.INFORMATION_MESSAGE);
            } else {
                lblMensaje.setText("Perdiste. La palabra era: " + juegoActual.getPalabraSecreta());
                JOptionPane.showMessageDialog(this, "Juego terminado. La palabra era: " + juegoActual.getPalabraSecreta(), "Derrota", JOptionPane.INFORMATION_MESSAGE);
            }
            txtLetra.setEnabled(false);
            btnIntentar.setEnabled(false);
        }
    }

    private void actualizarInterfaz() {
        String palabraMostrar = "";
        for (int i = 0; i < juegoActual.getPalabraActual().length(); i++) {
            palabraMostrar += juegoActual.getPalabraActual().charAt(i) + " ";
        }
        lblPalabraActual.setText(palabraMostrar);

        lblIntentos.setText("Intentos restantes: " + juegoActual.getIntentosRestantes());

        txtAreaFigura.setText(juegoActual.getFiguraActual());
        txtAreaFigura.setCaretPosition(0);

        if (juegoActual.getLetrasUsadas().isEmpty()) {
            lblLetrasUsadas.setText("<html>Letras usadas:&nbsp;</html>");
        } else {
            StringBuilder html = new StringBuilder("<html>Letras usadas:&nbsp;");
            int contador = 0;
            for (Character c : juegoActual.getLetrasUsadas()) {
                html.append(c).append("&nbsp;");
                contador++;
                if (contador % 8 == 0) {
                    html.append("<br>");
                }
            }
            html.append("</html>");
            lblLetrasUsadas.setText(html.toString());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                AhorcadosGui gui = new AhorcadosGui();
                gui.setVisible(true);
            }
        });
    }
}
