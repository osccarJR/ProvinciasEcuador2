import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class VistaJuego extends JFrame {
    private Juego juego;
    private JLabel labelPuntos;
    private JLabel labelProvincia;
    private JTextField textFieldCapital;
    private JLabel labelTiempo;
    private Timer timer;
    private int tiempo;
    private Map<String, JButton> mapaBotones;
    private JButton botonVerificar;
    private JLabel labelNombreJugador;
    private Provincia provinciaAnterior;
    private JButton cerrarSesion;

    public VistaJuego(String nombreJugador) {
        juego = new Juego();
        mapaBotones = new HashMap<>();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelCentral = new JPanel();
        panelCentral.setLayout(new BoxLayout(panelCentral, BoxLayout.Y_AXIS));

        labelNombreJugador = new JLabel("Jugador: " + nombreJugador);
        labelPuntos = new JLabel("Puntos: " + juego.getPuntos());
        labelProvincia = new JLabel("Provincia: " + juego.getProvinciaActual().getNombre());
        textFieldCapital = new JTextField(20);
        tiempo = 240; // 4 minutos en segundos
        labelTiempo = new JLabel("Tiempo restante: " + tiempo);

        panelCentral.add(labelNombreJugador);
        panelCentral.add(labelPuntos);
        panelCentral.add(labelProvincia);
        panelCentral.add(textFieldCapital);
        panelCentral.add(labelTiempo);

        botonVerificar = new JButton("Verificar");
        botonVerificar.setBackground(Color.YELLOW); // Cambiar el color a amarillo
        botonVerificar.setEnabled(false);

        for (Provincia provincia : juego.getProvincias()) {
            JButton boton = new JButton(provincia.getNombre());
            mapaBotones.put(provincia.getNombre(), boton);
            boton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if (provincia.isIntentada()) {
                        JOptionPane.showMessageDialog(null, "Ya intentaste con esta provincia", "Información", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        provincia.setIntentada(true);
                        provinciaAnterior = juego.getProvinciaActual();
                        juego.setProvinciaActual(provincia);
                        labelProvincia.setText("Provincia: " + juego.getProvinciaActual().getNombre());
                        botonVerificar.setEnabled(true);
                        botonVerificar.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                boolean correcto = juego.verificarCapital(textFieldCapital.getText());
                                if (correcto) {
                                    boton.setBackground(Color.GREEN);
                                    boton.setForeground(Color.BLACK);
                                    textFieldCapital.setText("");
                                    labelPuntos.setText("Puntos: " + juego.getPuntos());
                                    botonVerificar.setEnabled(false);
                                    botonVerificar.removeActionListener(this);
                                    if (juego.getRespuestasCorrectasSeguidas() == 3) {
                                        JOptionPane.showMessageDialog(null, "¡Impresionante! ¡3 respuestas correctas seguidas!", "¡Bien hecho!", JOptionPane.INFORMATION_MESSAGE);
                                    } else if (juego.getRespuestasCorrectasSeguidas() == 6) {
                                        JOptionPane.showMessageDialog(null, "¡Increíble! ¡6 respuestas correctas seguidas!", "¡Excelente!", JOptionPane.INFORMATION_MESSAGE);
                                    }

                                    if (juego.getPuntos() >= 1000) {
                                        timer.stop();
                                        JOptionPane.showMessageDialog(null, "¡Ganaste el juego!", "Felicidades", JOptionPane.INFORMATION_MESSAGE);
                                        resetJuego();
                                    }
                                } else {
                                    boton.setBackground(Color.RED);
                                    boton.setForeground(Color.WHITE);
                                    labelPuntos.setText("Puntos: " + juego.getPuntos());
                                    botonVerificar.setEnabled(false);
                                    botonVerificar.removeActionListener(this);
                                    if (juego.getPuntos() <= -500) {
                                        timer.stop();
                                        JOptionPane.showMessageDialog(null, "Perdiste el juego", "Lo siento", JOptionPane.ERROR_MESSAGE);
                                        resetJuego();
                                    }
                                    juego.setProvinciaActual(provinciaAnterior);
                                }
                            }
                        });
                    }
                }
            });
            panelCentral.add(boton);
        }

        timer = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tiempo--;
                labelTiempo.setText("Tiempo restante: " + tiempo);
                if (tiempo <= 0) {
                    ((Timer)e.getSource()).stop();
                    JOptionPane.showMessageDialog(null, "Se agotó el tiempo. Perdiste el juego", "Lo siento", JOptionPane.ERROR_MESSAGE);
                    resetJuego();
                }
            }
        });
        timer.start();

        panelCentral.add(botonVerificar);

        ImageIcon iconoImagen = new ImageIcon("C:/Users/oscar/Downloads/ecuador.png"); // Ruta de la imagen de la pc en donde presentemos en progra
        JLabel labelImagen = new JLabel(iconoImagen);

        add(panelCentral, BorderLayout.CENTER);
        add(labelImagen, BorderLayout.EAST);

        cerrarSesion = new JButton("Cerrar Sesión");

        JPanel surPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        surPanel.add(cerrarSesion);
        add(surPanel, BorderLayout.SOUTH);

        initEventos();

        pack();
        setVisible(true);
    }

    private void resetJuego() {
        juego.setProvinciaActual(null);
        juego.resetPuntos();
        labelPuntos.setText("Puntos: " + juego.getPuntos());
        tiempo = 240; // Reinicia el tiempo 4 minutos - 240 segundos
        for (Provincia provincia : juego.getProvincias()) {
            provincia.reset();
            JButton boton = mapaBotones.get(provincia.getNombre());
            boton.setBackground(null);
            boton.setForeground(Color.BLACK);
            boton.setEnabled(true);
        }
        textFieldCapital.setText("");
        botonVerificar.setEnabled(false);
        timer.start();
    }

    private void initEventos() {
        cerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Cerrando sesión...");
                System.exit(0);
            }
        });
    }
}
