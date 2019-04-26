/*
 * DataDecisionTreeClass.java
 *
 * Created on September 17, 2004, 6:02 PM
 */

package blurock.DecisionTree;
import blurock.DirectedTreeObjects.BaseDataTreeNode;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.RWManagerBase;
import java.io.IOException;

/**
 *
 * @author  reaction
 */
public class DataDecisionTreeClass extends blurock.EvaluationTree.DataEvaluationTreeClass {
    
    /** Creates a new instance of DataDecisionTreeClass */
    public DataDecisionTreeClass() {
        super(12010,"DecisionTree","Decision Tree");
        SubClass = "EvaluationTree";
    }
    public DataDecisionTreeClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "EvaluationTree";
    }
    
    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataDecisionTree();
        obj.Type = this.Type;
        obj.CopyClone((DataObjectClass) this);
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataObjectClass cls = new DataDecisionTreeClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;         
    }
    
    public void CopyClone(DataObjectClass cls) {
         super.CopyClone(cls);
    }
    
    public void Read(RWManagerBase io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManagerBase io) throws IOException {
        super.Write(io);
    }
    
    
}
