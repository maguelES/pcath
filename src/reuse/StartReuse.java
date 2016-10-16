/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reuse;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author maguelES
 */

public class StartReuse {    
    
    private int i;
    private double humidity[] = new double[99999];
    private double temperature[] = new double[99999];
    private int numRows;
    private String solution[] = new String[99999];
    private double similarityValue[] = new double[99999];
    private int caseNum[] = new int[99999];
    
    public StartReuse(double h[], double t[], int i) 
    {
        this.i = i;
        for(int cnt = 0; cnt < i; cnt++)
        {
            humidity[cnt] = h[cnt];
            temperature[cnt] = t[cnt];
        }
    }   
    
    public void getCaseBase()
    {
        //String declaration
        
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "";
        String solution = "";
        
        //Double declaration
        double temperature = 0.0;
        double humidity = 0.0;
        double similarity = 0.0;
        
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
            
            //double vals
            
            //Get Upper and Lower Boundaries
            BoundaryCalculation bc = new BoundaryCalculation(dbHumid, dbTemp, numRows);
            
            //boundary values
            double uBH = bc.getUpperBH();
            double uBT = bc.getUpperBT();
            double lBH = bc.getLowerBH();
            double lBT = bc.getLowerBT();         
            
            //range values
            double humidityRange = uBH - lBH;
            double temptRange = uBT - lBT;           
            
            /*----------------------------------------------------------
              Testing Purpose
             -----------------------------------------------------------
            System.out.println(uBH);
            System.out.println(uBT);
            System.out.println(lBH);
            System.out.println(lBT);
            ------------------------------------------------------------
            */            
            
            //Compare current case with case from case bases
            SimilarityAssessment sA = new SimilarityAssessment(dbTemp, dbHumid, this.numRows, humidityRange, temptRange, dbSolution);
            
            for(int j=0; j<this.i; j++)
            {
            /*----------------------------------------------------------
              Testing Purpose
             -----------------------------------------------------------
                System.out.println("---------------------------------------------");
                System.out.println(j + 1);
                System.out.println("---------------------------------------------");
            ------------------------------------------------------------
            */                 
                
                humidity = this.humidity[j];               
                temperature = this.temperature[j];
                similarity = sA.calculateSimilarity(temperature, humidity);               
                
                //assign class similarity
                this.similarityValue[j] = similarity;                
                
                //get the solution
                this.solution[j] = sA.getSolution();
                
                //assign similar case number
                this.caseNum[j] = sA.getSimilarCaseNum();
            }
                  
            
            res.close();
            stt.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
    }    

    public String getSolution(int counter){

        return this.solution[counter];

    }

    public double getSimilarity(int counter)
    {
        return this.similarityValue[counter];
    }
    
}
