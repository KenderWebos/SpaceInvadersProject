package Controlador;

public class Shot extends GameObject
{
    boolean movingUp;
    boolean movingDown;
    
    boolean isVisible;
    //poner si es enemigo o aliado
    
    public Shot(int x, int y, int speedX, int speedY)
    {
        super(x, y, speedX, speedY);
        
        isVisible = false;
        movingUp = true;
        movingDown = false;
    }
}
