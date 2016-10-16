/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package revise;

/**
 *
 * @author SayaSukaAyam
 */

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class CheckThreshold {
    
    private double tHold;
    private double dbHumid[] = new double[999];
    private double dbTemp[] = new double[999];
    private int lineCount;    

    public CheckThreshold(double dbTemp[], double dbHumid[], int count){
        int i = 0;
        for(i=0;i<count;i++){
            this.dbHumid[i] = dbHumid[i];
            this.dbTemp[i] = dbTemp[i];
        }
        this.lineCount = count;
    	this.tHold = 0.0;
    	calculateThreshold();
    }

    public CheckThreshold(int count){
        this.lineCount = count;
        this.tHold = 0.0;
        calculateThreshold();
    }

    private void calculateThreshold(){    	
        double tLevel = this.lineCount;
        int numLines = this.lineCount;

        if(numLines < 25){
            tLevel = 10.00;
        }
        else if(numLines > 50){
            tLevel = 25.00;
        }
        else if(numLines > 75){
            tLevel = 40.00;
        }
        else if(numLines > 100){
            tLevel = 50.00;
        }
        else if(numLines > 200){
            tLevel = 60.00;
        }
        else if(numLines > 250){
            tLevel = 70.00;
        }
        else if(numLines > 300){
            tLevel = 80.00;
        }
        else{
            tLevel = 90.00;
        }


        /*
        switch(numLines){
            case 25: tLevel = 10.00;
                     break;
            case 50: tLevel = 25.00;
                     break;
            case 75: tLevel = 40.00;
                     break;
            case 100: tLevel = 50.00;
                     break;
            case 200: tLevel = 60.00;
                     break;
            case 250: tLevel = 70.00;
                     break;
            case 300: tLevel = 80.00;
                      break;
            case 325: tLevel = 90.00;
                      break;
            default: break;        
        }
        */
                
        setThreshold(tLevel);
    }    

    private void setThreshold(double tLevel){

        this.tHold = tLevel;
    }

    private void setThresholdFile(double tLevel){
        String path = "data/simthreshold/threshold.txt";

        try{

            boolean append_to_file = false;

            //
            Double threshold = tLevel;
            File file = new File(path);
            if (!file.exists()) {
                    file.createNewFile();
            }
            FileWriter write = new FileWriter(path , append_to_file);
            PrintWriter print_line = new PrintWriter(write);
            }
        catch(IOException e){
            e.printStackTrace();
        }        
    }

    public double getThreshold(){
    	
        double tValue = this.tHold;

        return tValue;
    }


}
