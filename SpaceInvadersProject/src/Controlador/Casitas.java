package Controlador;

public class Casitas extends GameObject
{
    boolean isVisible;

    public Casitas(int x, int y, float speedX, float speedY) 
    {
        super(x, y, speedX, speedY);
        
        isVisible = true;
    }
}
