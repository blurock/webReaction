/*
 * FlowSpecies.java
 *
 * Created on October 21, 2002, 5:55 PM
 */

package react.mechanisms.flow;
import javax.swing.JPanel;

/**
 *
 * @author  reaction
 */
public class FlowSpecies extends graph.DrawGraphNode {
    SingleFlow[] flows = null;
    /** Creates a new instance of FlowSpecies */
    public FlowSpecies(String name, SingleFlow[] f) {
        super(name);
        flows = f;
    }
    public SingleFlow[] getFlow() {
        return flows;
    }
    public JPanel objectAsPanel() {
        JPanel barPanel = new JPanel();
        barPanel.setLayout(new java.awt.GridBagLayout());
        java.awt.GridBagConstraints gridBagConstraints;
        for(int i=0;i<flows.length;i++) {
            if(flows[i].relativeFlow > 1.0) {
                JPanel bar = flows[i].objectAsPanel();
                gridBagConstraints = new java.awt.GridBagConstraints();
                gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
                gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
                barPanel.add(bar, gridBagConstraints);
            }
        }
       return barPanel;
     }
     public String toString() {
        StringBuffer buf = new StringBuffer();
        buf.append("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        buf.append("%%%Flow of " + getNameTag() + "\n");
        for(int i=0;i<flows.length;i++) {
            buf.append(flows[i].toString());
            buf.append("\n");
        }
        return buf.toString();
     }

}
