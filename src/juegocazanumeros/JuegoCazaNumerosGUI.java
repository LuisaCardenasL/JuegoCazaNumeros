package juegocazanumeros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Lu
 */
public class JuegoCazaNumerosGUI extends JFrame{
    private JButton bPulsador, bIniciar, bNumeros[], bVidas[];
    private JLabel lPuntos,lImagen;
    private JTextField tfPuntos;
    private JPanel pSuperior, pCentral, pInferior;
    
    public JuegoCazaNumerosGUI()
    {
        initComponents();
        //contruccion del frame
        setTitle("Juego Caza Numeros");
        setSize(600,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    private void initComponents()
    {
        bNumeros = new JButton[10];
        bVidas = new JButton[2];
        
        pSuperior = new JPanel(new GridLayout(2,5,3,3));
        pCentral = new JPanel();
        pInferior = new JPanel();
        
        for (int i =0; i < bNumeros.length; i++)
        {
            bNumeros[i] = new JButton("   ");
            pSuperior.add(bNumeros[i]);
        }
        
        bPulsador = new JButton();
        bPulsador.setPreferredSize(new Dimension(100,100));
        lImagen = new JLabel(new ImageIcon("src/imagenes/pezca.png"));
        pCentral.add(bPulsador);
        pCentral.add(lImagen);
        pCentral.setBackground(Color.WHITE);
        
        for (int j = 0; j < bVidas.length; j++) {
            bVidas[j] = new JButton(""+(j+1));
            bVidas[j].setBackground(Color.WHITE);
            pInferior.add(bVidas[j]);
        }
        
        bIniciar = new JButton(" Iniciar ");
        tfPuntos = new JTextField(14);
        lPuntos = new JLabel("Puntos: ");
        pInferior.add(lPuntos);
        pInferior.add(tfPuntos);
        pInferior.add(bIniciar);
        
        //adicionar los escuchas
        ManejaPulsador eventoP = new ManejaPulsador();
        bPulsador.addActionListener(eventoP);
        bIniciar.addActionListener(eventoP);
        
        
        //Configuracion del frame
        add(pSuperior, BorderLayout.NORTH);
        add(pCentral, BorderLayout.CENTER);
        add(pInferior, BorderLayout.SOUTH);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        JuegoCazaNumerosGUI obj = new JuegoCazaNumerosGUI();
    }
    
    class ManejaPulsador implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
        }
        
    }
    
}
