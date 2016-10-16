/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package retrieve;

/**
 *
 * @author maguelES
 * 
 */

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.util.Scanner;

public class StartRetrieve{
    
    private double[] temperature = new double[90000];
    private double[] humidity = new double[90000];
    private int count;
    
    public StartRetrieve() throws IOException{
             
    }
    public int getFile() throws IOException{
        
        //integer declaration
        int count = 0;
        
        //String Declaration
        String fileLocation = "data/coolterm.txt";
        String tempTempt = "";
        String tempHumid = "";
        
        //File Location
        File file = new File(fileLocation);
        
        //Get Num of Rows!
        LineNumberReader lnr = new LineNumberReader(new FileReader(file));
        lnr.skip(Long.MAX_VALUE);
        
        //Close LineNumberReader object
        int lineCount = lnr.getLineNumber();
        lnr.close();
        
        //Double Declaration        
        double[] humidity = new double[lineCount + 1];
        double[] temperature = new double[lineCount + 1];
        double t = 0.0;
        double h = 0.0;
        
        Scanner read = new Scanner(new File(fileLocation));             
        String line = "";        
        
        //while file has next
        while(read.hasNextLine())
        {
            //checks for empty line
            if(read.nextLine().equals(""))
            {
                //ignore
                count++;
            }
            else
            {
                //Read each feature as String
                String[] ar = read.next().split(",");
                tempHumid = ar[0];
                tempTempt = ar[1];

                /*------------------------------------------------------
                 *Testing Purposes 01
                 *------------------------------------------------------
                System.out.println("Humidity : " + tempHumid);
                System.out.println("Temperature : " + tempTempt);

                *--------------------------------------------------------
                */

                //Parse into double
                t = Double.parseDouble(tempTempt);
                h = Double.parseDouble(tempHumid);

                humidity[count] = h;
                temperature[count] = t;            

                 /*------------------------------------------------------
                 *Testing Purposes 02
                 *------------------------------------------------------
                System.out.println("Humidity : " + humidity[count]);
                System.out.println("Temperature : " + temperature[count]);

                *--------------------------------------------------------
                */
                assignReturnValues(t, h, count);
                count++;
            }           
        }        
        read.close(); 
        return count;
    }    
    
    private void assignReturnValues(double t, double h, int count)
    {
        this.temperature[count] = t;
        this.humidity[count] = h;
        this.count = count;       
    }
    
    public double getTemperature(int count)
    {
        return this.temperature[count];
    }
    
    public double getHumidity(int count)
    {
        return this.humidity[count];
    } 
    
}
