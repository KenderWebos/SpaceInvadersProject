package certamen_1;

import java.math.BigInteger;
import java.util.Scanner;

public class Certamen_1 {

    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        int cant = sc.nextInt();

        for (int i = 0; i < cant; i++)
        {
            int count = 0;
            long numero = sc.nextLong();
            
            if (numero%2 == 0 && numero!=2) {
                
            }
            
            for (int j = 1; j < numero+1; j++)
            {
                if (numero%j == 0)
                {
                    count++;
                }
            }

            if (count == 3){
                System.out.println("YES");
            }
            else{
                System.out.println("NO");
            }
        }
    }

}