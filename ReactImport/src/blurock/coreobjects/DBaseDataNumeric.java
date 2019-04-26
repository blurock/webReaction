/*
 * DBaseDataNumeric.java
 *
 * Created on February 27, 2001, 4:35 PM
 */

package blurock.coreobjects;
import blurock.core.*;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataNumeric extends blurock.coreobjects.DBaseDataObject {

    public PanelNumeric panelNumeric = new PanelNumeric();
    /** Creates new DBaseDataNumeric */
    public DBaseDataNumeric(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        panelNumeric.objectInfo.add(top);
        panelNumeric.numericValue.setText(getValueAsString());
        return panelNumeric;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    public String getValueAsString() {
        return new String("0");
    }
    void setValueAsString(String value) {
    }
}
