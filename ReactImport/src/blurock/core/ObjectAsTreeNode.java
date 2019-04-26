/*
 * ObjectAsTreeNode.java
 *
 * Created on February 22, 2001, 6:17 PM
 */

package blurock.core;
import blurock.coreobjects.*;
    import javax.swing.*;
    import javax.swing.tree.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class ObjectAsTreeNode extends javax.swing.tree.DefaultMutableTreeNode {

    public boolean isObject = true;
    public DBaseDataObject nodeObject;
    String nodeName;
    
    /** Creates new ObjectAsTreeNode */
    public ObjectAsTreeNode(DBaseDataObject obj) {
        nodeObject = obj;
        nodeName = nodeObject.getName();
    }
    public ObjectAsTreeNode(String name) {
        super(name);
        nodeName = name;
        nodeObject = null;
        isObject = false;
    }
    public java.lang.String toString() {
        return nodeName;
    }
    
    public JPanel objectAsPanel() {
        JPanel panel;
        if(isObject)
            panel = nodeObject.objectAsPanel();
        else {
            panel = new JPanel();
            JLabel lbl = new JLabel(nodeName);
            panel.add(lbl);
        }
        return panel;
    }
}
    
