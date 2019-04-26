/*
 * DBaseDataContinuous.java
 *
 * Created on March 2, 2001, 8:49 PM
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
public class DBaseDataContinuous extends blurock.logic.base.DBaseDataLogical {

    /** Creates new DBaseDataContinuous */
    public DBaseDataContinuous(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataLogical log = (BaseDataLogical) Object;
        PanelLogical logpanel = new PanelLogical(String.valueOf(log.Value));
        TopObjectPanel toppanel = new TopObjectPanel("Continuous",top,logpanel);
        return toppanel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
}
