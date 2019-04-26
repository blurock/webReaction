/*
 * BaseDataEvaluationTree.java
 *
 * Created on September 16, 2004, 5:39 PM
 */

package blurock.EvaluationTree;
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
import javax.swing.JPanel;

/**
 *
 * @author  reaction
 */
public class BaseDataEvaluationTree extends blurock.coreobjects.BaseDataObject {
    public BaseDataDirectedTree Tree = null;
    
    /** Creates a new instance of BaseDataEvaluationTree */
    public BaseDataEvaluationTree() {
        Tree = new BaseDataDirectedTree();
    }
    
    public void Read(RWManagerBase io) throws IOException {
        Tree.Read(io);
    }
    
    public void Write(RWManagerBase io) throws IOException {
        Tree.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man, DataObjectClass cls) {
        return new DBaseDataEvaluationTree(man,this,cls);
    }
    
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
        BaseDataEvaluationTree evaltree = (BaseDataEvaluationTree) obj;
        Tree = (BaseDataDirectedTree) evaltree.Tree.Clone();
    }
    
    public BaseDataObject Clone() {
        BaseDataObject cls = (BaseDataObject)  new BaseDataEvaluationTree();
        cls.CopyClone(this);
       return cls;         
    }
    
}
