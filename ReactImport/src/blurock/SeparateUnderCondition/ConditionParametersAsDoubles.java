/*
 * ConditionParametersAsDoubles.java
 *
 * Created on September 28, 2004, 6:25 PM
 */

package blurock.SeparateUnderCondition;

/**
 *
 * @author  reaction
 */
public class ConditionParametersAsDoubles extends ConditionParameters {
    public double[] Parameters = null;
    /** Creates a new instance of ConditionParametersAsDoubles */
    public ConditionParametersAsDoubles(double[] parms) {
        Parameters = parms;
    }
    public String toString() {
        StringBuffer buf = new StringBuffer();
        for(int i=0;i<Parameters.length;i++) {
            Double d = new Double(Parameters[i]);
            buf.append(d.toString());
            buf.append(" ");
        }
        return buf.toString();
    }
}
