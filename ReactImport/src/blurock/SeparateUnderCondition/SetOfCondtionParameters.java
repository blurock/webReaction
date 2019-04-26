/*
 * SetOfCondtionParameters.java
 *
 * Created on September 29, 2004, 3:36 PM
 */

package blurock.SeparateUnderCondition;
import java.util.Vector;

/**
 *
 * @author  reaction
 */
public class SetOfCondtionParameters {
    Vector Parameters = new Vector();
    /** Creates a new instance of SetOfCondtionParameters */
    public SetOfCondtionParameters() {
    }
    public void addParameterSet(ConditionParameters params) {
        Parameters.add(params);
    }
    public void addParameterSet(int i,ConditionParameters params) {
        Parameters.add(i,params);
    }
    public ConditionParameters getIthParameterSet(int i) {
        Object[] objs = Parameters.toArray();
        return (ConditionParameters) objs[i];
    }
    public int size() {
        return Parameters.size();
    }
}
