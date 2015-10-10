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
public class Fraction {
    int a,b,k;
    Fraction(int a, int b, int k)
    {
        this.a = a;
        this.b = b;
        this.k = k;
    }
    
    public String ConvertTo_K_SoC()
    {
        Integer k = this.k;
        double d=this.a/(double)this.b;
        Integer int_tmp, int_d0,int_d1;
        String result = "";

//Если k>9, то в качестве символов обозначающих числа >9 и <k в системе отсчета k
//используем запись данного чила в DEC заключенного в квадратные скобки
//Например,
        
    //Если есть целая часть, то сначала переводим ее в k
        int_d0=(int)d;
        while((int_d1 = int_d0 / k) >= 1)
        {
            int_tmp = int_d0 - k*int_d1;
            result = int_tmp>=10 ? "[" + int_tmp.toString()+"]"+ result :  int_tmp.toString()+ result;                    
            int_d0=int_d1;
        }
        result = int_d0>=10 ? "[" + int_d0.toString()+"]"+ result+"." : int_d0.toString()+ result+".";

    //Переводим дробную часть, если он сущесвует
        if(d-(int)d > 0.000000001)
        {
            List<Double> db_al = new ArrayList<Double>(); //Будем добавлять дробную части от умножения дробной части на k
                                                          //Первым элементом списка будет дробная часть исходной дроби
                                                          //Если при очередном добавлении окажется, что уже такая дробная часть существует,
                                                          //значит дробь периодическая с началом периода в индексе элемента списка с которым текущая дробная часть совпала
            double acc = Math.pow(10, 9);
            double frac_d0,frac_d1;
            int per = -1;

            frac_d0 = d -(int) d;                    
            db_al.add((double)Math.round(frac_d0*acc)/acc);

            for(int i = 1; i<100 && frac_d0>=0.000000001 && per==-1; i++)
            {
                frac_d1=frac_d0*k;
                int_tmp = (int)frac_d1;
                result=int_tmp>=10 ? result + "[" + int_tmp.toString()+"]" : result + int_tmp.toString();
                frac_d0 = frac_d1 - (int) frac_d1;

                if((per = db_al.indexOf((double)Math.round(frac_d0*acc)/acc))==-1)
                {
                    db_al.add((double)Math.round(frac_d0*acc)/acc);
                }
            }

        // Заключение периода в круглые скобки в случае периодической дроби 
            if(per!=-1)
            {
                int j = result.indexOf(".");
                int op,cl,per_tmp=per;
                String res_tmp = result.substring(j+1);

            //Если k>9, то в записи результата могут присутствовать числа заключенные в квадратные скобки
            //При наличии таковых до начала периодической части, нужно сдвинуть индекс начала периода на длину символа в квадратных скобках + 1(длина закрывающей скобки)                
                while((op = res_tmp.indexOf("["))!=-1 && op<=per_tmp) 
                {
                    cl = res_tmp.indexOf("]");
                    per=per+cl-op;
                    per_tmp=per_tmp - op -1;
                    res_tmp=res_tmp.substring(cl+1);
                }

                result = result.substring(0,j+1+per)+"("+result.substring(j+1+per)+")";
            }            
        }
        else result = result+"0";
        
        return result;
    }
}
