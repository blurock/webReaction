/*
 * DBaseDataExpressionTree.java
 *
 * Created on February 28, 2001, 4:11 PM
 */

package blurock.opandalgs.expressions;
import blurock.coreobjects.*;
import blurock.core.*;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataExpressionTree extends blurock.opandalgs.ops.DBaseDataOperation {

    /** Creates new DBaseDataExpressionTree */
    public DBaseDataExpressionTree(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataExpressionTree tree = (BaseDataExpressionTree) Object;
        PanelExpressionTree pexp = new PanelExpressionTree(Object.Name,top,tree.expressionAsString);
        return pexp;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        return super.objectAsSubTree(topnode);
    }
    
}
