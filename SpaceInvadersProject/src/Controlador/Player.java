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
import java.io.FileInputStream;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Player extends JPanel implements ActionListener, KeyListener
{
    public boolean isAlive = true;
    Timer timer = new Timer(5, this);
    double x=0, y=0, speedX=0, speedY=0;
    double velocidad = 3;
    int vidas = 3;
    
    boolean level2Ready = false;
    
    boolean oleadaCompletada = false;
    int oleada = 0;
    
    int BOARD_WIDTH=800;
    int BOARD_HEIGHT=600;
    
    Enemy alienMaestro = new Enemy(0, 0, 3, 3);
    int vidasAlienMaestro = 3;
    
    
    Enemy enemy[] = new Enemy[18];
    Casitas casitas[] = new Casitas[9];
    
    
    Shot shot = new Shot((int)x, (int)y, 5, 5);
    Shot alienShot = new Shot(800, 600, 5, 5);
    
    public Player(int x, int y){
        
        this.x = x;
        this.y = y;
        timer.start();
        addKeyListener(this);
        
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        
        generarAliens();
        generarEscudos();
    }
    
    void resetearParaLevel2()
    {
        alienMaestro.x = 10;
        alienMaestro.y = 20;
        alienMaestro.speedX = 5;
        
        alienMaestro.isVisible = true;
        generarAliens();
        for (Enemy alien : enemy) {
            alien.speedX = 3;
        }
    }
    
    void AlienShotLogic()
    {
        
        if (alienShot.isVisible && alienShot.x > x && alienShot.y > y && alienShot.x < x+40 && alienShot.y < y+40 ) {
            
            alienShot.isVisible = false;
            alienShot.x = 800;
            alienShot.y = 600;
            
            
            if (vidas > 1) 
            {
                vidas--;
                System.out.println("te quedan" + vidas);
            }
            else{
                
                this.setVisible(false);
                
                isAlive = false;
                //fin del juego
                
                JFrame frame = (JFrame) SwingUtilities.getRoot(this);
                
                //frame.add(menu);
                frame.getContentPane().setBackground(Color.RED);
                frame.setVisible(false);
                Menu menu = new Menu();
                
                menu.setVisible(true);
                menu.clip = null;
                //parar la cancion
            }
                
            
        }
        
        for (Casitas casa : casitas) {
            if (casa.isVisible && alienShot.isVisible && alienShot.x > casa.x && alienShot.y > casa.y && alienShot.x < casa.x+40 && alienShot.y < casa.y+40 ) 
            {
            
                alienShot.isVisible = false;
                alienShot.x = 800;
                alienShot.y = 600;
                
                casa.isVisible = false;
            } 
        }
        
        
    }
    
    void generarDisparoAlien(){
        ArrayList<Integer> disponibles = new ArrayList();
        disponibles.clear();
        //String disponibles = "";
        
        for (int i = 0; i < enemy.length; i++)
        {
            if (enemy[i].isVisible) {
                disponibles.add(i);
            }
        }
        
        //int randomAlien = (int)Math.random()*disponibles.size();
        int randomAlien = (int) ((Math.random() * (enemy.length+1 - 0)) + 0);
        //Math.floor(Math.random()*6);
        
        alienShot.movingDown = true;
        alienShot.movingUp = false;
        alienShot.isVisible = true;
        
        alienShot.x = enemy[ disponibles.get(randomAlien) ].x;
        alienShot.y = enemy[ disponibles.get(randomAlien) ].y;
        
    }
    
    void generarEscudos()
    {
        int casitasX = BOARD_WIDTH/10;
        int casitasY = 420;
        
        for (int i = 0; i < casitas.length; i++) {
            casitas[i] = new Casitas(casitasX, casitasY, 2, 2);
            casitasX += 40;
            
            if ( (i+1) % 3 == 0)
            {
                casitasX += BOARD_WIDTH/6;
                casitasY += 0;
            }
        }
    }
    
    void generarAliens()
    {
        int alienX = 10;
        int alienY = 10;
        
        for (int i = 0; i < enemy.length+oleada*2; i++) {
            enemy[i] = new Enemy(alienX, alienY, 2, 2);
            alienX += 45;
            if (i == 5+oleada*2)
            {
                alienX = 10;
                alienY+= 45;
            }
//            
            if (i == 11+oleada*2)
            {
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
        
        for (int i = 1; i < vidas+1; i++)
        {
            g2d.fill(new Ellipse2D.Double(20*i, 20, 20, 20));
            
        }
        
        moveAliens();
        moveAlienMaestro();
        shotTrigger(shot, enemy);
        verificarOleada();
        AlienShotLogic();
        
        //ESTA LINEA ES PARA RENDERIZAR UNA IMAGEN
//        java.awt.image.BufferedImage fondoSpace = ImageIO.read(new FileInputStream("/RECURSOS/space.jpg"));
//        g2d.drawImage(fondoSpace, 400, 400, this);
        
//ALIEN MAESTRO
        if (alienMaestro.isVisible)
        {
            g2d.fillRect( alienMaestro.x, alienMaestro.y, 40, 30 );
            g2d.fillRect( alienMaestro.x, alienMaestro.y, 20, 40 );
            g2d.fillRect( alienMaestro.x, alienMaestro.y, 10, 60 );
            
        }

        if (shot.isVisible) {
            shot.y -= shot.speedY;
            g2d.fill(new Ellipse2D.Double(shot.x, shot.y, 10, 10));
            if (shot.y < 0) {
                shot.isVisible = false;
            }
        }
        
        if (alienShot.isVisible) {
            alienShot.y += alienShot.speedY;
            g2d.fill(new Ellipse2D.Double(alienShot.x, alienShot.y, 10, 10));
            if (alienShot.y > 600) {
                alienShot.isVisible = false;
            }
        }
        
        for (int i = 0; i < enemy.length; i++) {
            if (enemy[i].isVisible) {
                g2d.fillRect( enemy[i].x, enemy[i].y, 40, 40 );
            }
            //g2d.fillRect(new Ellipse2D.Double(enemy[i].x, enemy[i].y, 40, 40));
        }
        
        for (Casitas casa : casitas)
        {
            if (casa.isVisible) {
                g2d.fillRect( casa.x, casa.y, 40, 40 );
            }
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
        else if(x>700)
        {
            x = 700; //0
        }
        else if(x<-40){
            x = 0; //760
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
        
        generarDisparoAlien();
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
            
            if (true) { //!shot.isVisible
                shot();
            }
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        release();
    }
    
    public void moveAlienMaestro()
    {
            if (alienMaestro.moveLeft == true) {
                alienMaestro.x -= alienMaestro.speedX;
            }
            
            if (alienMaestro.moveRight == true) {
                alienMaestro.x += alienMaestro.speedX; //(int)enemy[i].speedX
            }
        
            if (alienMaestro.x > BOARD_WIDTH-55) 
            {
                alienMaestro.y += 20;
                alienMaestro.moveLeft = true;
                alienMaestro.moveRight = false;
            }
            
            if (alienMaestro.x < 0)
            {
                alienMaestro.y += 20;
                alienMaestro.moveLeft = false;
                alienMaestro.moveRight = true;
            }
    }
    
    public void moveAliens(){
        for (int i = 0; i < enemy.length; i++) 
        {
            if (enemy[i].moveLeft == true) {
                enemy[i].x -= enemy[i].speedX;
            }
            
            if (enemy[i].moveRight == true) {
                enemy[i].x += enemy[i].speedX; //(int)enemy[i].speedX
            }
        }
        
        for (int i = 0; i < enemy.length; i++) {
            if (enemy[i].x > BOARD_WIDTH-55) 
            {
                for (int j = 0; j < enemy.length; j++) 
                {
                    enemy[j].y += 20;
                    enemy[j].moveLeft = true;
                    enemy[j].moveRight = false;
                    
                }
            }
            
            if (enemy[i].x < 0) 
            {
                for (int j = 0; j < enemy.length; j++) 
                {
                    enemy[j].y += 20;
                    enemy[j].moveLeft = false;
                    enemy[j].moveRight = true;
                    
                }
            }
        }
        
        boolean tamosListos = false;
        for (int i = 0; i < enemy.length; i++) 
        {
            if (enemy[i].y > 600-45 && enemy[i].isVisible)
            {
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
            
            if (cont == enemy.length && !oleadaCompletada) {
                oleadaCompletada = true;
                oleada++;
                System.out.println("LISTA");
            }
        }
    }
    
    void shotTriggerAlienMaestro(){
        
    }
    
    void shotTrigger(Shot shot, Enemy[] enemy)
    {
        if (shot.isVisible && alienMaestro.isVisible && shot.x > alienMaestro.x && shot.y > alienMaestro.y && shot.x < alienMaestro.x+40 && shot.y < alienMaestro.y+40) {
            vidasAlienMaestro--;
            if (vidasAlienMaestro < 1) {
                alienMaestro.isVisible = false;
            }
            shot.isVisible = false;
        }
        
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
                shot.isVisible = false;
                shot.x = 0;
                shot.y = 0;
                //CORREGIR CON AREAS _ que sea un rango de area
                
            }
        }
    }
}
