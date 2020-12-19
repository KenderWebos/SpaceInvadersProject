package Controlador;

public class PlayerX extends GameObject
{
    boolean moveRight;
    boolean moveLeft;
    
    public PlayerX(int x, int y, float speedX, float speedY) {
        super(x, y, speedX, speedY);
        
        moveRight = false;
        moveLeft = false;
    }
}
