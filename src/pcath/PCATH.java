/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pcath;

/**
 *
 * @author maguelES
 */

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import retrieve.StartRetrieve;
import reuse.StartReuse;
import reuse.BoundaryCalculation;
import revise.StartRevise;
import arduino.ReadSerial;

public class PCATH {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here             
        try {  
            /*---------------------------------------------------------------------
             *reserved codes
            String retrieveData = new retrieve.StartRetrieve().getFile();
            *----------------------------------------------------------------------
            */
            
            /*---------------------------------------------------------------------
             *read Serial
             *---------------------------------------------------------------------
            arduino.ReadSerial readSerial = new arduino.ReadSerial();
            readSerial.initialize();
             *---------------------------------------------------------------------
            */           
                    
            //Retrieve Data from Sensors
            retrieve.StartRetrieve retrieveData = new retrieve.StartRetrieve(); 
            int count = retrieveData.getFile();            
            //Integer Declaration
            int j = 0;            
            //Double Declaration
            double t[] = new double[count + 1];
            double h[] = new double[count + 1];            
            while ( j < count)
            {
                h[j] = retrieveData.getHumidity(j);
                t[j] = retrieveData.getTemperature(j);             
                j++;
            }            
            //Reuse casebase class initialization
            reuse.StartReuse reuseData = new reuse.StartReuse(h, t, count);               
            reuseData.getCaseBase();

            //Declare solution and simValue
            String solution[] = new String[count + 1];
            double simValue[] = new double[count + 1];

            //Start assigning solution  and simvalue
            for(j = 0; j< count ; j++)
            {
                solution[j] = reuseData.getSolution(j);
                simValue[j] = reuseData.getSimilarity(j);
            }

            //Revise class
            revise.StartRevise reviseData = new revise.StartRevise(h, t, count, solution, simValue);
            reviseData.Revision();
            
        } catch (IOException ex) {
            Logger.getLogger(PCATH.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
