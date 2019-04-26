/*
 * DataDecisionTreeNodeStatsClass.java
 *
 * Created on September 17, 2004, 11:03 PM
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
public class DataDecisionTreeNodeStatsClass extends blurock.DecisionTree.DataDecisionTreeNodeSpecClass {
    
    /** Creates a new instance of DataDecisionTreeNodeStatsClass */
    public DataDecisionTreeNodeStatsClass() {
        super(12040,"DecisionTreeNodeStats","Decision Tree Node Statistics");
        SubClass = "DecisionTreeNodeStats";
    }
    public DataDecisionTreeNodeStatsClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "DecisionTreeNodeStats";
    }
    
    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataDecisionTreeNodeStats();
        obj.Type = this.Type;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataObjectClass cls = new DataDecisionTreeNodeStatsClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;         
    }
    
    public void CopyClone(DataObjectClass cls) {
        DataDecisionTreeNodeStatsClass obj = new DataDecisionTreeNodeStatsClass();
        obj.CopyClone((DataObjectClass) this);
    }
    
    public void Read(RWManagerBase io) throws IOException {
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }
    
}
