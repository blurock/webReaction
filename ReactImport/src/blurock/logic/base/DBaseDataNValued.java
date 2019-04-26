/*
 * DBaseDataNValued.java
 *
 * Created on March 2, 2001, 8:51 PM
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
public class DBaseDataNValued extends blurock.logic.base.DBaseDataLogical {

    /** Creates new DBaseDataNValued */
    public DBaseDataNValued(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataNValued log = (BaseDataNValued) Object;
        PanelLogical logpanel = new PanelLogical(log.stringValue);
        TopObjectPanel toppanel = new TopObjectPanel("NValued",top,logpanel);
        return toppanel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode top = super.objectAsSubTree(topnode);
        BaseDataNValued log = (BaseDataNValued) Object;
        ObjectAsTreeNode value = new ObjectAsTreeNode(log.stringValue);
        top.add(value);
        return top;
    }
    
}
