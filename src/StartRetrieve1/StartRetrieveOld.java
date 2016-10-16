/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package StartRetrieve1;

/**
 *
 * @author maguelES
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class StartRetrieveOld {
    
    public String getFile() throws IOException{
        
        String fileLocation = "data/coolterm.txt";
        File file = new File(fileLocation);
        
        BufferedReader br = new BufferedReader(new FileReader(file));        
        String line = null;
        String lastLine = "";
        
        while ((line=br.readLine()) != null){                                
            lastLine = line; //read until last line;   
        }               
        /*----------------------------------------------------
         * Testing Purpose
         *----------------------------------------------------
        System.out.println(lastLine);
        String values[] = lastLine.split(",");
        int i = 0;
        while (i <2)
        {
            System.out.println(values[i]);
            i++;
        }
        *
        *-----------------------------------------------------
        */
        br.close();
        return lastLine;
    } 
}
