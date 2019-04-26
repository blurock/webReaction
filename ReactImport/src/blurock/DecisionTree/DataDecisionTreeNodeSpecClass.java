/*
 * DataDecisionTreeNodeSpecClass.java
 *
 * Created on September 17, 2004, 8:36 PM
 */

package blurock.DecisionTree;
import blurock.DirectedTreeObjects.BaseDataTreeNode;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.RWManagerBase;
import java.io.IOException;
//import blurock.coreobjects.DataKeyWordsClass;
import blurock.logic.description.DataConjunctionClass;

/**
 *
 * @author  reaction
 */
public class DataDecisionTreeNodeSpecClass extends blurock.DirectedTreeObjects.DataTreeNodeClass {
    DataConjunctionClass ConjunctionClass = new DataConjunctionClass(10006,"Conjunction","Conjunction");
    //DataKeyWordsClass    PartitionClass = null;

    /** Creates a new instance of DataDecisionTreeNodeSpecClass */
    public DataDecisionTreeNodeSpecClass() {
        super(12030,"DecisionTreeNodeSpec","Decision Tree Node Spec");
        SubClass = "TreeNode";
    }
    
    public DataDecisionTreeNodeSpecClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "TreeNode";
    }
    
    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataDecisionTreeNodeSpec();
        obj.Type = this.Type;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataObjectClass cls = new DataDecisionTreeNodeSpecClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;         
    }
    
    public void CopyClone(DataObjectClass cls) {
        DataDecisionTreeNodeSpecClass obj = new DataDecisionTreeNodeSpecClass();
        if(ConjunctionClass != null) obj.ConjunctionClass = ConjunctionClass;
        //if(Partition != null)   cls.Partition = Partition;
        obj.CopyClone((DataObjectClass) this);
    }
    
    public void Read(RWManagerBase io) throws IOException {
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }
    
}
