package Controlador;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Player extends JPanel implements ActionListener, KeyListener
{
    Timer timer = new Timer(5, this);
    double x=0, y=0, speedX=0, speedY=0;
    
    public Player(int x, int y){
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
        g2d.fill(new Ellipse2D.Double(x, y, 40, 40));
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        
        x += speedX;
        y += speedY;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void left(){
        speedY = 0;
        speedX = -1.5;
    }
    
    public void right(){
        speedY = 0;
        speedX = 1.5;
    }
    
    public void up(){
        speedY = -1.5;
        speedX = 0;
    }
    
    public void down(){
        speedY = 1.5;
        speedX = 0;
    }
    
    public void release(){
        speedY = 0;
        speedX = 0;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        
        if (code == KeyEvent.VK_LEFT) {
            left();
        }
        if (code == KeyEvent.VK_RIGHT) {
            right();
        }
        if (code == KeyEvent.VK_UP) {
            up();
        }
        if (code == KeyEvent.VK_DOWN) {
            down();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        release();
    }
    
}
