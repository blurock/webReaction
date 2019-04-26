/*
 * ReactMechanismGraphNode.java
 *
 * Created on January 18, 2002, 12:23 PM
 */

package react.mechanisms;
import javax.swing.JPanel;

/**
 *
 * @author  reaction
 * @version 
 */
public class ReactMechanismGraphNode extends graph.DrawGraphNode {
    public String nodeName;
    
    /** Creates new ReactMechanismGraphNode */
    public ReactMechanismGraphNode(String name) {
        super(name);
    }
    /** Creates new ReactMechanismGraphNode */
    public ReactMechanismGraphNode() {
    }
    public JPanel objectAsPanel() {
        return new JPanel();
    }
}
