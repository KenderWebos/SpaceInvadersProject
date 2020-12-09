package Controlador;

public class Enemy extends GameObject
{
    boolean moveRight;
    boolean moveLeft;
    boolean isVisible;
    
    public Enemy(int x, int y, int speedX, int speedY) {
        super(x, y, speedX, speedY);
        
        isVisible = true;
        moveRight = false;
        moveLeft = false;
    }
    
}
