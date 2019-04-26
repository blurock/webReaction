/*
 * FlowSpeciesSon.java
 *
 * Created on October 21, 2002, 6:01 PM
 */

package react.mechanisms.flow;
import javax.swing.JPanel;
/**
 *
 * @author  reaction
 */
public class FlowFromSpecies extends graph.DrawGraphBond {
    SingleFlow flowValues = null;    
    /** Creates a new instance of FlowSpeciesSon */
    public FlowFromSpecies(int type, SingleFlow single,
                            FlowSpecies parent, FlowSpecies son){
        super(type,parent,son);
        setType(100);
        flowValues = single;
    }
    public double getFlowValue() {
        return flowValues.relativeFlow;
    }
    public JPanel objectAsPanel() {
        return flowValues.objectAsPanel();
    }
}
