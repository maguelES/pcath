/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package reuse;

/**
 *
 * @author maguelES
 */
public class SimilarityAssessment {    
    
    private double[] dbHumid = new double[999];
    private double[] dbTempt = new double[999];
    private int i;
    private final double rangeH;
    private final double rangeT;
    private String solution[] = new String[999];
    private String caseSolution;
    private int simil;
    
    public SimilarityAssessment(double t[], double h[], int i, double hR, double tR, String solution[])
    {
        //class variable assignment        
        this.i = i;
        this.rangeH = hR;
        this.rangeT = tR;        
        for(int cnt = 0; cnt < i; cnt++){           
            this.dbHumid[cnt] = h[cnt];
            this.dbTempt[cnt] = t[cnt];
            this.solution[cnt] = solution[cnt];            
        }
    }
    
    public double calculateSimilarity(double t, double h)
    {
        //calculate Local Similarity for Temperature        
        double globalSim = calculateSim(t, h);             
        return globalSim;
    }
    
    private double calculateSim(double t, double h)
    {
        double simT;
        double simH;
        double globalSim;
        double realGlobalSim = 0;        
        
        for(int cnt = 0; cnt < this.i ; cnt++)
        {
            simH = Math.abs(1 - ( Math.abs( this.dbHumid[cnt] - h) / this.rangeH ));            
            simT = Math.abs(1 - ( Math.abs( this.dbTempt[cnt] - t) / this.rangeT ));            
            globalSim = calculateGlobalSim(simH, simT);           
                if(globalSim > realGlobalSim)
                {
                    realGlobalSim = globalSim;
                    this.caseSolution = this.solution[cnt];
                    this.simil = cnt;
                }            
        }
       /* System.out.println("Most Similar to case no. : " + simil );
        System.out.println("The Real Global Similarity is: " + realGlobalSim);
        System.out.println("And The Solution is: " + this.caseSolution);*/
        return realGlobalSim;
    }        
    
    private double calculateGlobalSim(double h, double t)
    {
        double globalSim;
        
        globalSim = (1/1) * ( ( 0.4 * h) + (0.6 * t) );
        
        return globalSim;
    }
    
    public String getSolution(){
        
        String sol = this.caseSolution;
        return sol;
    }
    
    public int getSimilarCaseNum(){
        
        int caseNum = this.simil;
        return caseNum;
    }
}
