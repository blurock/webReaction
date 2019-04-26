/*
 * AssignConditions.java
 *
 * Created on September 29, 2004, 3:58 PM
 */

package blurock.SeparateUnderCondition;

/**
 *
 * @author  reaction
 */
public class AssignConditions {
    DetermineCondition Condition;
    /** Creates a new instance of AssignConditions */
    public AssignConditions(DetermineCondition cond) {
        Condition = cond;
    }
    public SetOfConditionChoices assign(SetOfCondtionParameters parms) {
        SetOfConditionChoices set = new SetOfConditionChoices();
        for(int i=0;i<parms.size();i++) {
            ConditionParameters parm = parms.getIthParameterSet(i);
            //System.out.println(parm.toString());
            ConditionChoice ch = Condition.determineCondition(parm);
            //System.out.println(ch.toString());
            set.addChoice(i,ch);
        }
        return set;
    }
}
