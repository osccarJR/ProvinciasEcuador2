import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VistaMenuPrincipal extends JFrame {
    private JTextField textFieldNombre;

    public VistaMenuPrincipal() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        JLabel titulo = new JLabel("JUEGO PROVINCIAS EGB", SwingConstants.CENTER); // Título del juego
        titulo.setFont(new Font("Serif", Font.BOLD, 24)); // TamaÑo del tituoo

        textFieldNombre = new JTextField(20);
        textFieldNombre.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isLetter(c) || Character.isWhitespace(c) || (c == KeyEvent.VK_BACK_SPACE) || (c == KeyEvent.VK_DELETE))) {
                    e.consume();  // Esto ignora cualquier caracter que no sea una letra o espacio aaaa
                    JOptionPane.showMessageDialog(null, "Por favor, solo ingrese letras y espacios", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        JButton botonIniciarJuego = new JButton("Iniciar Juego");
        botonIniciarJuego.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nombre = textFieldNombre.getText();
                if (nombre == null || nombre.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, ingrese su nombre", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    setVisible(false); // Cierra la ventana del menu
                    new VistaJuego(nombre); // Abre la ventana del juego
                }
            }
        });

        add(titulo);
        add(new JLabel("Por favor Escribe tu nombre y apellido"));
        add(textFieldNombre);
        add(botonIniciarJuego);

        pack();
        setSize(400, 200); // Hace que la ventana sea más grande
        setLocationRelativeTo(null); // Centra la ventana en la pantalla
        setVisible(true);
    }
}
