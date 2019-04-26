/*
 * ConditionChoiceAsInteger.java
 *
 * Created on September 28, 2004, 6:21 PM
 */

package blurock.SeparateUnderCondition;

/**
 *
 * @author  reaction
 */
public class ConditionChoiceAsInteger extends blurock.SeparateUnderCondition.ConditionChoice {
    Integer Choice = null;
    /** Creates a new instance of ConditionChoiceAsInteger */
    public ConditionChoiceAsInteger(int choice) {
        Choice = new Integer(choice);
    }
    public int getChoice() {
        return Choice.intValue();
    }
    public String toString() {
        return Choice.toString();
    }
}
