/*
 * DBaseDataExactlyEqualPredicate.java
 *
 * Created on July 12, 2005, 4:18 PM
 */

package blurock.logic.predicates;
import blurock.core.ObjectNotFoundException;
import blurock.core.ObjectAsTreeNode;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import blurock.opandalgs.ops.DataOperationClass;
import blurock.opandalgs.ops.BaseDataOperation;
import blurock.opandalgs.ops.DBaseDataOperation;

/**
 *
 * @author  reaction
 */
public class DBaseDataExactlyEqualPredicate extends DBaseDataOperation {
    
    /** Creates a new instance of DBaseDataExactlyEqualPredicate */
    public DBaseDataExactlyEqualPredicate(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataExactlyEqualPredicate pred = (BaseDataExactlyEqualPredicate) this.Object;
        ObjectAsTreeNode name = new ObjectAsTreeNode(this);
        topnode.add(name);
        ObjectAsTreeNode parameter = new ObjectAsTreeNode(pred.parameterName);
        name.add(parameter);
        try {
            DataObjectClass cls = this.displayManager.dataClasses.findClass(pred.objectToCompare.Type);
            DataExactlyEqualPredicateClass objcls = (DataExactlyEqualPredicateClass) cls;
            DBaseDataObject objnode = pred.objectToCompare.getDisplayObject(this.displayManager,objcls);
            ObjectAsTreeNode node = new ObjectAsTreeNode(objnode);
        } catch(ObjectNotFoundException ex) {
            System.out.println("Classes not initialized properly");
        }  
        return name;
    }
    public JPanel objectAsPanel() {
        BaseDataExactlyEqualPredicate pred = (BaseDataExactlyEqualPredicate) this.Object;
        ExactlyEqualPredicatePanel panel = null;
        try {
            DataObjectClass cls = this.displayManager.dataClasses.findClass(pred.objectToCompare.Type);
            DataExactlyEqualPredicateClass objcls = (DataExactlyEqualPredicateClass) cls;
            panel = new ExactlyEqualPredicatePanel(this.displayManager,pred,objcls);
        } catch(ObjectNotFoundException ex) {
            System.out.println("Classes not initialized properly");
        }  
        return panel;
    }
    public String getType() {
        return OClass.Name;
    }
   
     
}
