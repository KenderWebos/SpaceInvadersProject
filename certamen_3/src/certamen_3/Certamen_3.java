
package certamen_3;

import java.util.Scanner;

public class Certamen_3 {

    public static void main(String[] args) 
    {
        /*
        Gire la perilla en sentido horario 2 vueltas completas.
        Det´engase en el 1er n´umero de la combinaci´on.
        Gire la perilla en sentido anti-horario 1 vuelta completa.
        Contin´ue girando en sentido anti-horario hasta que el 2do n´umero sea alcanzado.
        Gire la perilla en sentido horario de nuevo hasta que el 3ro n´umero sea alcanzado.
        Tire del candado y se abrir´a.
        */
        
        Scanner sc = new Scanner(System.in); // son 9 grados cada cambio de numero
        int numeroInicial = sc.nextInt();
        int numero1 = sc.nextInt();
        int numero2 = sc.nextInt();
        int numero3 = sc.nextInt();
        
        int totalSentidoHorario = 0;
        int totalSentidoAntiHorario = 0;
        
        totalSentidoHorario += 360 + 360; // dos giros
        totalSentidoHorario += cantGrados(numeroInicial, numero1, true);
                
        totalSentidoAntiHorario += 360; // un giro
        totalSentidoHorario += cantGrados(numero1, numero2, false);
        
        totalSentidoHorario += 0; // ningun giro
        totalSentidoHorario += cantGrados(numero2, numero3, true);
        
        System.out.println(totalSentidoHorario + totalSentidoAntiHorario);
        System.out.println(totalSentidoHorario);
        System.out.println(totalSentidoAntiHorario);
        
        
    }
    
    public static int cantGrados(int num1, int num2, boolean esHorario)
    {
        int grados = 0;
        
        if (esHorario) {
            if (num1 > num2) {
                grados = cantGrados(num1, 40, true) + cantGrados(0, num2, true);
            }
            else if (num1 < num2) {
                grados = ((num2 - num1 -1)*9);
            }
            else{
                grados = 0;
            }
        }
        else{
            if (num1 > num2) {
                grados = cantGrados(30, 40, true) + cantGrados(0, num1, true);
            }
            else if (num1 < num2) {
                grados = ((num2 - num1 -1)*9);
            }
            else{
                grados = 0;
            }
            grados = grados-360;
               
        }
        
        return grados;
    }
    
}
