/*
 * Condition.java
 *
 * Created on June 29, 2004, 12:32 AM
 */

package ignition;

/**
 *
 * @author  reaction
 */
public class Condition {
    
    private int ParameterIndex;
    
    private double BoundaryValue;
    
    private boolean GT;
    
    private boolean EQ;
    
    /** Creates a new instance of Condition */
    public Condition(int idx, double value, boolean gt, boolean eq) {
        ParameterIndex = idx;
        GT = gt;
        EQ = eq;
        BoundaryValue = value;
    }
    
    public boolean Test(double[] parameters) {
        boolean value = true;
        System.out.println("Condition: " + ParameterIndex + ": " + parameters[ParameterIndex] + " > " + BoundaryValue);
        if(GT) {
            boolean calc = (parameters[ParameterIndex] > BoundaryValue);
            System.out.println(calc);
            value = value && calc;
        } else {
            value = value && (parameters[ParameterIndex] < BoundaryValue);
        }
        if(EQ) {
            value = value && (parameters[ParameterIndex] == BoundaryValue);
        }
        System.out.println(value);
        return value;
    }
}
