package Controlador;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JPanel;
import javax.swing.Timer;

public class GameBoard extends JPanel implements ActionListener, KeyListener
{
    Timer timer = new Timer(5, this);
    double x=0, y=0, speedX=0, speedY=0;
    
    int BOARD_WIDTH=800;
    int BOARD_HEIGHT=600;
    
    PlayerX playerX;
    Enemy enemy;
    
    public GameBoard(int x, int y)
    {
        playerX = new PlayerX(380, 500, 0, 0);
        enemy = new Enemy(380, 100, 0, 0);
        
        this.x = x;
        this.y = y;
        timer.start();
        addKeyListener(this);
        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
    }
    
    public void paintComponent(Graphics grafico){
        super.paintComponent(grafico);
        
        Graphics2D g2d = (Graphics2D)grafico;
        g2d.fill(new Ellipse2D.Double(playerX.x, playerX.y, 40, 40));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        
        playerX.x += playerX.speedX;
        playerX.y += playerX.speedY;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void left(){
        playerX.speedY = 0;
        playerX.speedX = -1.5f;
    }
    
    public void right(){
        playerX.speedY = 0;
        playerX.speedX = 1.5f;
    }
    
    public void shot(){
        System.out.println("Disparanding...");
    }
    
    
    public void release(){
        playerX.speedY = 0;
        playerX.speedX = 0;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_LEFT && playerX.x > 0) {
            left();
        }
        if (code == KeyEvent.VK_RIGHT && playerX.x < 800) {
            right();
        }
        if (code == KeyEvent.VK_SPACE) {
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        release();
    }
}
