/*
 * SetOfConditionChoices.java
 *
 * Created on September 29, 2004, 3:50 PM
 */

package blurock.SeparateUnderCondition;
import java.util.ArrayList;

/**
 *
 * @author  reaction
 */
public class SetOfConditionChoices {
    ArrayList Choices = new ArrayList();
    /** Creates a new instance of SetOfConditionChoices */
    public SetOfConditionChoices() {
    }
    public void addChoice(ConditionChoice choice) {
        Choices.add(choice);
    }
    public void addChoice(int i,ConditionChoice choice) {
        Choices.add(i,choice);
    }
    public ConditionChoice getChoice(int i) {
        return (ConditionChoice) Choices.get(i);
    }
    public int size() {
        return Choices.size();
    }
}
