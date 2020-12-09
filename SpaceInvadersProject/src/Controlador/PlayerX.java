package Controlador;

public class PlayerX extends GameObject
{
    boolean moveRight;
    boolean moveLeft;
    
    public PlayerX(int x, int y, int speedX, int speedY) {
        super(x, y, speedX, speedY);
        
        moveRight = false;
        moveLeft = false;
    }
}
