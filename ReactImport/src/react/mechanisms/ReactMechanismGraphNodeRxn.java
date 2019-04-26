/*
 * ReactMechanismGraphNodeRxn.java
 *
 * Created on January 18, 2002, 3:11 PM
 */

package react.mechanisms;
import java.awt.Color;
import javax.swing.JPanel;
/**
 *
 * @author  reaction
 * @version 
 */
public class ReactMechanismGraphNodeRxn extends react.mechanisms.ReactMechanismGraphNode {
    ReactMechanismRxn Reaction;
    int Index;
    /** Creates new ReactMechanismGraphNodeRxn */
    public ReactMechanismGraphNodeRxn(int index, ReactMechanismRxn rxn,ReactMechanismRxnClass rxnclass) {
        super();
        Reaction = rxn;
        Index = index;
        nodeName = rxnclass.className;
    }
    public void drawNode(ReactMechanismGraph mechanism) {
        setNameTag("R" + Integer.toString(Index));
        mechanism.addNode(this);
        setNodeColor(mechanism.rxnColor);
        setNodeType(200);
    }
            public JPanel objectAsPanel() {
                return new JPanel();
            }
}
