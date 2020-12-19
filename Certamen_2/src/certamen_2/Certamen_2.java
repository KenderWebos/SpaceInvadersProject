package certamen_2;

import java.util.ArrayList;
import java.util.Scanner;

public class Certamen_2 {

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        boolean terminamos = false;
        
        while(!terminamos)
        {
            int tamanoMazo = sc.nextInt();
            
            if (tamanoMazo > 1)
            {
                ArrayList<Integer> mazo = new ArrayList<Integer>();
                ArrayList<Integer> descartadas = new ArrayList<Integer>();
                
                for (int i = 0; i < tamanoMazo; i++) {
                    mazo.add(i+1);
                }

                while(mazo.size() >= 2)
                {
                    descartadas.add( mazo.remove(0) );
                    mazo.add(mazo.get(0));
                    mazo.remove(0);
                }

                System.out.print("Discarded cards: ");
                for (int i = 0; i < descartadas.size()-1; i++) {
                    System.out.print(descartadas.get(i) + ", ");
                }
                System.out.print(descartadas.get( descartadas.size()-1 ));
                System.out.println("");
                
                System.out.println("Remaining card: " + mazo.remove(0) );
                
            }
            else if(tamanoMazo == 1){
                System.out.println("Discarded cards:");
                System.out.println("Remaining card: 1");
            }
            else{
                terminamos = true;
            }
            
        }
        
    }
    
}
