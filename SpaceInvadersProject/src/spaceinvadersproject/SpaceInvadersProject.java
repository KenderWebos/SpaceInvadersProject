package spaceinvadersproject;

import Controlador.Player;
import javax.swing.JFrame;

public class SpaceInvadersProject 
{

    public static void main(String[] args) 
    {
        JFrame ventana = new JFrame();
        
        Player player = new Player();
        ventana.add(player);
        
        ventana.setSize(800,600);
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}
