/*
 * SetOfConditions.java
 *
 * Created on June 29, 2004, 12:49 AM
 */

package ignition;

/**
 *
 * @author  reaction
 */
public class SetOfConditions extends java.util.Vector {
    
    /** Creates a new instance of SetOfConditions */
    public SetOfConditions() {
    }
    public void addCondition(Condition cond) {
        this.add(cond);
    }
    public boolean Test(double[] parameters) {
        boolean ans = true;
        for(int i=0; i<this.size() && ans; i++) {
            Condition cond = (Condition) this.elementAt(i);
            boolean calc = cond.Test(parameters);
           ans  = ans && calc;
        }
        return ans;
    }
}
