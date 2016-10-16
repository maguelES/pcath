/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package revise;

/**
 *
 * @author maguelES
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import retain.StartRetain;

public class StartRevise {

    private double humidity[] = new double[99999];
    private double temperature[] = new double[99999];
    private int count = 0;
    private String solution[] = new String[99999];
    private double simValue[] = new double[99999];
    private int numRows;

    public StartRevise(double h[], double t[], int count, String sol[], double sim[])
    {
    	int i;
    	this.count = count;

    	for(i=0;i<count;i++)
    	{
    		this.humidity[i] = h[i];
    		this.temperature[i] = t[i];
    		this.solution[i] = sol[i];
    		this.simValue[i] = sim[i];
    	}
    }

    public void Revision(){    	

    	String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "";

        try{

        	//
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            Connection con = DriverManager.getConnection(url, user, password);
            
            //
            Statement stt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            stt.execute("USE pcath");
            
            //
            ResultSet res = stt.executeQuery("SELECT * FROM casebase");

            if(res.last()){
                this.numRows = res.getRow();                
                res.beforeFirst();
            }

            //double declaration
            double[] dbTemp = new double[this.numRows + 1];
            double[] dbHumid = new double[this.numRows + 1];
            String[] dbSolution = new String[this.numRows];
            int i = 0;
            
            while(res.next())
            {
                dbTemp[i] = res.getDouble("temperature");
                dbHumid[i] = res.getDouble("humidity");
                dbSolution[i] = res.getString("solution");               
                i++;
            }
            //Close connections 
            res.close();
            stt.close();
            con.close();
            //Get Number of Rows
            int rows = this.numRows;           
            
            //Check For RevisionThreshold
            CheckThreshold checkTresh = new CheckThreshold(dbTemp, dbHumid, rows); 
            double threshold = checkTresh.getThreshold();          

            //Initiliaze StartRetain
            retain.StartRetain saveData = new retain.StartRetain(); 
            double similarVal = 0;

            for(int loopValue = 0;loopValue < this.count;loopValue++){                
                if(this.simValue[loopValue] >= threshold){
                    similarVal = 0;
                    for(int secLoopValue = 0;secLoopValue<rows;secLoopValue++){                        
                        if(humidity[loopValue] != dbHumid[secLoopValue] && temperature[loopValue] != dbTemp[secLoopValue]){
                            similarVal = 1;
                        }
                    }
                }
                if(similarVal == 1 || similarVal >= 1){
                    saveData.retainData(humidity[loopValue], temperature[loopValue], solution[loopValue]);
                }
            }                       
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
}
