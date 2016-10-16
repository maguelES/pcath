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
public class BoundaryCalculation {
    
    private double upperBoundaryH = 0.0;
    private double upperBoundaryT = 0.0;
    private double lowerBoundaryH = 0.0;
    private double lowerBoundaryT = 0.0;
    
    public BoundaryCalculation(double[] h, double[] t, int i)
    {
        setBoundaries(h, t, i);
    }
    
    private void setBoundaries(double[] h, double[] t, int i){
        
        //Integer declaration
        int count = 0;
        
        //double declaration
        double upperBH = h[0]; //upper Humidity
        double upperBT = t[0]; //upper Temperature
        
        double lowerBH = h[0]; //lower Humidity
        double lowerBT = t[0]; //lower Temperature
        
        double beforeBH = 0.0; //pointer for Humidity
        double beforeBT = 0.0; //pointer for Temperature        
        
        //For Temperature
        while(count < i)
        {           
            beforeBT = t[count]; //temp
            
            if( beforeBT < lowerBT )
            {                
                lowerBT = beforeBT;              
            }            
            if( beforeBT > upperBT)
            {
                upperBT = beforeBT;
            }          
            
            count++;
        }     
        
        
        //Reset Counter
        count = resetCount(count);
        
        //Assign Temperature Values
        this.upperBoundaryT = upperBT;
        this.lowerBoundaryT = lowerBT;        
        
        //For Humidity
        while(count < i)
        {           
            beforeBH = h[count]; //temp
            
            if( beforeBH < lowerBH )
            {                
                lowerBH = beforeBH;              
            }            
            if( beforeBH > upperBH)
            {
                upperBH = beforeBH;
            }          
            
            count++;
        }
        
        //Reset Counter
        count = resetCount(count);
        
        //Assign Humidity Values
        this.upperBoundaryH = upperBH;
        this.lowerBoundaryH = lowerBH;       
        
        /*-----------------------------------------------------------
         Testing Purpose 01
        -------------------------------------------------------------
        System.out.println(upperBT);
        System.out.println(lowerBT);
        System.out.println(count); 
        -------------------------------------------------------------
        */
        
    }
    
    public double getUpperBT(){
        
        return this.upperBoundaryT;
    }
    
    public double getUpperBH(){
        
        return this.upperBoundaryH;
    }
    
    public double getLowerBH(){
        
        return this.lowerBoundaryH;
    }
    
    public double getLowerBT(){
        
        return this.lowerBoundaryT;
    }
    
    private int resetCount(int count){
        
        count = 0;
        return count;
    }
}
