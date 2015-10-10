/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hhTask2;

/**
 *
 * @author Artak
 */
import java.io.*;
import java.util.*;
public class FractionKSoC {
    public static void main(String args[]) throws IOException
    {
        System.out.println("Введите полный путь к файлу. Для выхода из программы введите q");
        Scanner sin = new Scanner(System.in);
        String file = sin.nextLine();
        if(file.compareTo("q")==0) return;
        try (FileReader fr = new FileReader(file))//"C:\\MyJava\\hhTask2\\fractions.txt"))
        {
            BufferedReader in = new BufferedReader(fr);
            String str;
            String[] stra = new String[3];
            ArrayList<Fraction> f = new ArrayList<Fraction>();
           
            while((str = in.readLine()) != null) 
            {
                stra=str.split(" ");
                f.add(new Fraction(Integer.parseInt(stra[0]),Integer.parseInt(stra[1]),Integer.parseInt(stra[2])));
            }
            for(Fraction fi: f)
            {
//                System.out.println(fi.a+" "+fi.b+" "+fi.k);
                System.out.println(fi.ConvertTo_K_SoC());
//                System.out.println();
            }
        }
        catch(IOException e) {
            System.out.println("I/O Error: " + e);
            main(args);
        }    
    }    
}
