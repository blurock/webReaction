/*
 * DataEvaluationTreeClass.java
 *
 * Created on September 16, 2004, 5:40 PM
 */

package blurock.EvaluationTree;
import blurock.DirectedTreeObjects.BaseDataTreeNode;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.DirectedTreeObjects.DataDirectedTreeClass;
/**
 *
 * @author  reaction
 */
public class DataEvaluationTreeClass extends blurock.coreobjects.DataObjectClass {
    DataDirectedTreeClass TreeClass = new DataDirectedTreeClass(15020,"DirectedTree","Directed Tree");
    /** Creates a new instance of DataEvaluationTreeClass */
    public DataEvaluationTreeClass() {
    }
    public DataEvaluationTreeClass(int id, String type, String descr) {
        super(id,type,descr);
    }
    
    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataEvaluationTree();
        obj.Type = this.Type;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataObjectClass cls =   new DataEvaluationTreeClass(Identification,Name,Description);
        cls.CopyClone(this);
       return cls;         
    }
    
    public void CopyClone(DataObjectClass cls) {
        super.CopyClone(cls);
        if(TreeClass != null) 
            TreeClass.CopyClone(cls);
    }
    
    public void Read(RWManagerBase io) throws IOException {
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }
    
}
