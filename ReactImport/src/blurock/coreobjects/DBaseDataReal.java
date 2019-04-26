/*
 * DBaseDataReal.java
 *
 * Created on February 27, 2001, 5:01 PM
 */

package blurock.coreobjects;
import blurock.core.*;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataReal extends blurock.coreobjects.DBaseDataNumeric {

    
    /** Creates new DBaseDataReal */
    public DBaseDataReal(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public String getValueAsString() {
        BaseDataReal r = (BaseDataReal) Object;
        return String.valueOf(r.realValue);
    }
    
    public JPanel objectAsPanel() {
        PanelNumeric num = (PanelNumeric) super.objectAsPanel();
        return num;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode node = super.objectAsSubTree(topnode);
        BaseDataReal r = (BaseDataReal) this.Object;
        String valueS = String.valueOf(r.realValue);
        ObjectAsTreeNode value = new ObjectAsTreeNode(valueS);
        return node;
    }
    
    void setValueAsString(String value) {
        Double d = new Double(value);
        BaseDataReal r = (BaseDataReal) Object;
        r.realValue = d.doubleValue();
    }
    
}
