package Controlador;

import Vista.Menu;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import javax.swing.*;

public class Player extends JPanel implements ActionListener, KeyListener
{
    public boolean isAlive = true;
    Timer timer = new Timer(5, this);
    double x=0, y=0, speedX=0, speedY=0;
    double velocidad = 3;
    
    boolean oleadaCompletada = false;
    int oleada = 0;
    
    int BOARD_WIDTH=800;
    int BOARD_HEIGHT=600;
    
    Enemy enemy[] = new Enemy[50];
    
    Shot shot = new Shot((int)x, (int)y, 5, 5);
    
    public Player(int x, int y){
        
        this.x = x;
        this.y = y;
        timer.start();
        addKeyListener(this);
        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        generarAliens();
    }
    
    void generarAliens()
    {
        int alienX = 10;
        int alienY = 10;
        
        int total = 10+oleada*2;
        int fixedLenght=10;
        
        for (int i = 0; i < 10+oleada*2; i++) {
            enemy[i] = new Enemy(alienX, alienY, 2, 2);
            alienX += 45;
            if (i == 4+oleada*2) {
                alienX = 10;
                alienY+= 45;
            }
            
        }
    }
    
    public void paintComponent(Graphics grafico)
    {
        super.paintComponent(grafico);
        
        Graphics2D g2d = (Graphics2D)grafico;
        g2d.fill(new Ellipse2D.Double(x, y, 40, 40));
        
        moveAliens();
        shotTrigger(shot, enemy);
        verificarOleada();
        
        if (shot.isVisible) {
            shot.y -= shot.speedY;
            g2d.fill(new Ellipse2D.Double(shot.x, shot.y, 10, 10));
            if (shot.y < 0) {
                shot.isVisible = false;
            }
        }
        
        
        if (oleadaCompletada) {
            generarAliens();
        }
        
        for (int i = 0; i < 10; i++) {
            if (enemy[i].isVisible) {
                g2d.fillRect( enemy[i].x, enemy[i].y, 40, 40 );
            }
            //g2d.fillRect(new Ellipse2D.Double(enemy[i].x, enemy[i].y, 40, 40));
        }
        
        
        
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
        
        if ( x <= 800 && x >= -40 ) 
        {
            x += speedX;
            y += speedY;
        }
        else if(x>800)
        {
            x = 0;
        }
        else if(x<-40){
            x = 760;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void left(){
        speedY = 0;
        speedX = -velocidad;
    }
    
    public void right(){
        speedY = 0;
        speedX = velocidad;
    }
    
    public void shot(){
        System.out.println("disparanding...");
        shot.x = (int)x+15;
        shot.y = (int)y+15;
        shot.isVisible = true;
        
        //Toolkit.getDefaultToolkit().beep();
        
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
        if (code == KeyEvent.VK_SPACE) {
            
            if (!shot.isVisible) {
                shot();
            }
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        release();
    }
    
    public void moveAliens(){
        for (int i = 0; i < 10+oleada*2; i++) 
        {
            if (enemy[i].moveLeft == true) {
                enemy[i].x -= enemy[i].speedX;
            }
            
            if (enemy[i].moveRight == true) {
                enemy[i].x += enemy[i].speedX; //(int)enemy[i].speedX
            }
        }
        
        for (int i = 0; i < 10+oleada*2; i++) {
            if (enemy[i].x > BOARD_WIDTH-55) 
            {
                for (int j = 0; j < 10+oleada*2; j++) 
                {
                    enemy[j].y += 20;
                    enemy[j].moveLeft = true;
                    enemy[j].moveRight = false;
                    
                }
            }
            
            if (enemy[i].x < 0) 
            {
                for (int j = 0; j < 10+oleada*2; j++) 
                {
                    enemy[j].y += 20;
                    enemy[j].moveLeft = false;
                    enemy[j].moveRight = true;
                    
                }
            }
        }
        
        boolean tamosListos = false;
        for (int i = 0; i < 10+oleada*2; i++) 
        {
            if (enemy[i].y > 600-45) {
                tamosListos = true;
                this.setVisible(false);
                
                isAlive = false;
                //fin del juego
                
                JFrame frame = (JFrame) SwingUtilities.getRoot(this);
                
                //frame.add(menu);
                frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(false);
                Menu menu = new Menu();
                menu.setVisible(true);
                
                break;
            }
            if (tamosListos) {
                break;
            }
            
        }
    }
    
    void verificarOleada()
    {
        int cont = 0;
        for (Enemy alien : enemy) 
        {
            if (!alien.isVisible)
            {
                cont++;
            }
            
            if (cont == 10+oleada*2 && !oleadaCompletada) {
                oleadaCompletada = true;
                oleada++;
                System.out.println("LISTA");
            }
        }
    }
    
    void shotTrigger(Shot shot, Enemy[] enemy)
    {
        for (Enemy alien : enemy)
        {
            if (!alien.isVisible) {
                continue;
            }
            
            if (shot.isVisible && shot.x > alien.x && shot.y > alien.y && shot.x < alien.x+40 && shot.y < alien.y+45)
            //if(shot.x == alien.x && shot.y == alien.y)
            {
                //destruir Alien
                alien.isVisible = false;
                shot.isVisible = true;
                shot.x = 0;
                shot.y = 0;
                //CORREGIR CON AREAS _ que sea un rango de area
                
            }
        }
    }
}
