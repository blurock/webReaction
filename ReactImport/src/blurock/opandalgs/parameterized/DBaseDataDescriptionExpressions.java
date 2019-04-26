/*
 * DBaseDataDescriptionExpressions.java
 *
 * Created on March 3, 2001, 9:10 PM
 */

package blurock.opandalgs.parameterized;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.ObjectAsTreeNode;
import javax.swing.JPanel;
import blurock.utilities.TopObjectPanel;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataDescriptionExpressions extends blurock.opandalgs.parameterized.DBaseDataParameterizedFunction {

    /** Creates new DBaseDataDescriptionExpressions */
    public DBaseDataDescriptionExpressions(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataDescriptionExpressions exp = (BaseDataDescriptionExpressions) Object;
        DataDescriptionExpressionsClass cls = (DataDescriptionExpressionsClass) OClass;
        DBaseDataObject pexp =
            exp.Expressions.getDisplayObject(displayManager,cls.ExpressionsClass);
        JPanel exppanel = pexp.objectAsPanel();
        PanelDescriptionExpressions toppanel = 
            new PanelDescriptionExpressions(exp.PostOperation,top,exppanel);
        return toppanel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode top = new ObjectAsTreeNode("Description Expressions");
        topnode.add(top);
        ObjectAsTreeNode objtop = super.objectAsSubTree(top);
        BaseDataDescriptionExpressions exp = (BaseDataDescriptionExpressions) Object;
        DataDescriptionExpressionsClass cls = (DataDescriptionExpressionsClass) OClass;
        DBaseDataObject pexp =
            exp.Expressions.getDisplayObject(displayManager,cls.ExpressionsClass);
        ObjectAsTreeNode postop;
        if(exp.PostOperation) 
            postop = new ObjectAsTreeNode("Execute Post Operation");
        else
            postop = new ObjectAsTreeNode("No Post Operation");
        top.add(postop);
        postop.add(objtop);
        pexp.objectAsSubTree(top);
        return top;
    }
    
}
