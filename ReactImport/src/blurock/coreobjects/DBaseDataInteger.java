/*
 * DBaseDataInteger.java
 *
 * Created on February 27, 2001, 4:47 PM
 */

package blurock.coreobjects;
import blurock.core.*;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataInteger extends blurock.coreobjects.DBaseDataNumeric {

    /** Creates new DBaseDataInteger */
    public DBaseDataInteger(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        PanelNumeric num = (PanelNumeric) super.objectAsPanel();
        return num;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    public String getValueAsString() {
        BaseDataInteger i = (BaseDataInteger) Object;
        return String.valueOf(i.integerValue);
    }
    void setValueAsString(String value) {
        Integer i = new Integer(value);
        BaseDataInteger iv = (BaseDataInteger) Object;
        iv.integerValue = i.intValue();
    }
    
}
