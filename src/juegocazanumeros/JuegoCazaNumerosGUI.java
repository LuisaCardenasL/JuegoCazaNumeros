package juegocazanumeros;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
    private ArrayList<Integer> numJuego;
    
    public JuegoCazaNumerosGUI()
    {
        initComponents();
        numJuego = new ArrayList<>();
        //contruccion del frame
        setTitle("Juego Caza Numeros");
        setSize(600,400);
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
    
    public void limpiarGUI()
    {
        for (int i =0; i < bNumeros.length; i++)
        {
            bNumeros[i].setText("   ");
            if(i == 0 || i == 1)
            {
                bVidas[i].setBackground(Color.WHITE);
                bVidas[i].setEnabled(true);
            }
        }
        
        bPulsador.setText("");
        tfPuntos.setText("");
    }
    
    class ManejaPulsador implements ActionListener
    {
        int cVidas, puntos, topeNumeros;
        boolean pulsoBien;
        

        @Override
        public void actionPerformed(ActionEvent ae) {
            //
            if(ae.getSource() == bIniciar)
            {
                //iniciar las variables del juego
                cVidas = 2;
                puntos = 0;
                topeNumeros = 50;
                
                //llenar los botones de numeros aleatorios
                for (int i =0; i < bNumeros.length; i++){
                    int alea = (int)(Math.random()*topeNumeros);
                    bNumeros[i].setText(""+alea);
                    numJuego.add(alea);
                }
                
                //colocar color a los botones de las vidas
                for (int i =0; i < bVidas.length; i++){
                    bVidas[i].setBackground(Color.GREEN);
                }
                
                //deshabilitar boton iniciar
                bIniciar.setEnabled(false);
                
                //iniciar el juego del pc
                jugar(2);
                
            }//fin boton iniciar
            else {
                pulsoBien = false;
                String orpimido = ae.getActionCommand();
                for (int i =0; i < bNumeros.length; i++)
                {
                    if(orpimido.equals(bNumeros[i].getText()))
                    {
                        pulsoBien = true;
                        break;
                    }
                }
                if(pulsoBien)
                {
                    puntos++;
                    tfPuntos.setText(""+puntos);
                }
            }
        }//fin actionPerformed
        
        public boolean validarJugada(int numeroJugado)
        {
            for (int i = 0; i < numJuego.size(); i++)
            {
                if(numJuego.get(i) == numeroJugado)
                {
                    try {
                        bNumeros[i].setBackground(Color.yellow);
                        Thread.sleep(1000);
                        bNumeros[i].setBackground(null);
                        return true;
                    } catch (InterruptedException ex) {
                    }
                }
            }//fin for
            return false;
        }
        
        public void jugar(int segundos)
        {
            //Timer -java.util
            Timer timer = new Timer();
            //tarea
            TimerTask tarea = new TimerTask() {
                int numeroJugado;
                boolean marcoMal = false;
                
                @Override
                public void run() {
                    if(pulsoBien == false){
                        //validarJugada (numeroAleatorio -> boolean
                        marcoMal = validarJugada(numeroJugado);
                        if(marcoMal == true)
                        {
                            cVidas--;
                            bVidas[cVidas].setBackground(Color.WHITE);
                            bVidas[cVidas].setEnabled(false);
                        }
                    }
                    pulsoBien = false;
                    //si hay vidad:
                    if (cVidas > 0)
                    {
                        //se genera un numero aleatorio
                        numeroJugado = (int)(Math.random()*topeNumeros);
                        //mostrar el aleatorio en el boton
                        bPulsador.setText(""+numeroJugado);
                        //colocarle un borde al boton
                        bPulsador.setBorder(BorderFactory.createLineBorder(Color.yellow,2));
                        try {
                            //esperar un segundo
                            Thread.sleep(1000);
                            //quitarle el borde al boton
                             bPulsador.setBorder(null);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(JuegoCazaNumerosGUI.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else{
                        //si no hay vidas:
                        //mostrar mensaje perdiste
                        JOptionPane.showMessageDialog(null, "Perdiste! :(");
                        //reiniciar las variables del programa
                        numJuego.clear();
                        //reiniciar GUI
                        limpiarGUI();
                        timer.cancel();//finaliza el timer
                        bIniciar.setEnabled(true);

                    }
                }
            };//cierra timer
            timer.schedule(tarea,10,segundos*1000);
        }
    }//cierra clase interna
}//cierra juego caza numeros
