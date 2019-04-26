/*
 * DecisionTree.java
 *
 * Created on September 16, 2004, 3:17 PM
 */

package blurock.DecisionTree;
import blurock.coreobjects.BaseDataSetOfObjects;
import java.io.IOException;
import graph.DrawGraph;
import blurock.EvaluationTree.BaseDataEvaluationTree;
import blurock.core.RWManagerBase;
import graph.DrawGraph;
import graph.DrawGraphNode;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
import blurock.DirectedTreeObjects.BaseDataDirectedTree;
/**
 *
 * @author  reaction
 */
public class BaseDataDecisionTree extends BaseDataEvaluationTree {
    
    private blurock.coreobjects.BaseDataSetOfObjects Nodes;
    
    
    /** Creates a new instance of DecisionTree */
    public BaseDataDecisionTree() {
        Name = "DecisionTree";
        Type = "DecisionTree";
        Identification = 1;
    }
    
    public void Read(RWManagerBase io) throws IOException {
        super.Read(io);
     }
    
    public void Write(RWManagerBase io) throws IOException {
        super.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man, DataObjectClass cls) {
        return super.getDisplayObject(man,cls);
    }
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
    }
    
    public BaseDataObject Clone() {
        BaseDataObject cls = (BaseDataObject)  new BaseDataDecisionTree();
        cls.CopyClone(this);
       return cls;         
    }
    
    
}
