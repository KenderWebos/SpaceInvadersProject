package spaceinvadersproject;

import Controlador.Player;
import Vista.Menu;
import javax.swing.JFrame;

public class SpaceInvadersProject 
{

    public static void main(String[] args) 
    {
        JFrame ventana = new JFrame();
//        Menu menu = new Menu();
//        menu.setVisible(true);

        Player player = new Player(380, 500);
        ventana.add(player);
        
        ventana.setSize(800,600);
        
        ventana.setVisible(true);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
    }
    
}
