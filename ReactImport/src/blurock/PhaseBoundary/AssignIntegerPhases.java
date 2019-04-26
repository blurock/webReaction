/*
 * PointsSatisfyingCondition.java
 *
 * Created on September 29, 2004, 2:59 PM
 */

package blurock.PhaseBoundary;
import blurock.SeparateUnderCondition.DetermineCondition;
import blurock.SeparateUnderCondition.SetOfConditionParametersAsDoubles;
import blurock.SeparateUnderCondition.SetOfConditionChoices;
import blurock.SeparateUnderCondition.ConditionChoiceAsInteger;
import ignition.XMatrix;
import ignition.SetOfRanges;
import java.util.ArrayList;
/**
 *
 * @author  reaction
 */
public class AssignIntegerPhases extends blurock.SeparateUnderCondition.AssignConditions {
    int refindex = 0;
    /** Creates a new instance of PointsSatisfyingCondition */
    public AssignIntegerPhases(DetermineCondition cond) {
        super(cond);
    }
    public ArrayList assignRanges(XMatrix x) {
        ArrayList setofrangesets = new ArrayList();
        int[] assignments = assign(x);
        int max = findMaximumPhase(assignments);
        for(int phase=0;phase <= max;phase++) {
            boolean[] inphase = new boolean[assignments.length];
            for(int i=0;i<assignments.length;i++) {
                if(assignments[i] == phase) 
                    inphase[i] = true;
                else
                    inphase[i] = false;
            }
            double reference[] = x.BuildReference(refindex);
            SetOfRanges ranges = new SetOfRanges();
            ranges.PointRange(inphase,reference);
            setofrangesets.add(ranges);
        }
        return setofrangesets;
    }
    public int[] assign(XMatrix x) {
        SetOfConditionParametersAsDoubles setofconds = new SetOfConditionParametersAsDoubles(x);
        SetOfConditionChoices assigns = assign(setofconds);
        int[] ans = new int[assigns.size()];
        for(int i=0;i<assigns.size();i++) {
            ConditionChoiceAsInteger ichoice = (ConditionChoiceAsInteger) assigns.getChoice(i);
            ans[i] = ichoice.getChoice();
        }
        return ans;
    }
    public int findMaximumPhase(int[] assignments) {
        int max = assignments[0];
        for(int i=0;i<assignments.length;i++) {
            if(assignments[i] > max)
                max = assignments[i];
        }
        return max;
    }
}
