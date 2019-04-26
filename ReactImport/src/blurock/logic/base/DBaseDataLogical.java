/*
 * DBaseDataLogical.java
 *
 * Created on March 2, 2001, 8:46 PM
 */

package blurock.logic.base;
import blurock.coreobjects.*;
import blurock.core.*;
import java.io.IOException;
import javax.swing.JPanel;
import blurock.utilities.TopObjectPanel;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataLogical extends blurock.coreobjects.DBaseDataObject {

    /** Creates new DBaseDataLogical */
    public DBaseDataLogical(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataLogical log = (BaseDataLogical) Object;
        PanelLogical logpanel = new PanelLogical(String.valueOf(log.Value));
        TopObjectPanel toppanel = new TopObjectPanel("Logical",top,logpanel);
        return toppanel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode top = super.objectAsSubTree(topnode);
        BaseDataLogical log = (BaseDataLogical) Object;
        ObjectAsTreeNode value = new ObjectAsTreeNode(String.valueOf(log.Value));
        top.add(value);
        return top;
    }
    
}
