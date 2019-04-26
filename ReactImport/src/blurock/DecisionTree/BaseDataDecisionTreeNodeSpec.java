/*
 * BaseDataDecisionTreeNodeSpec.java
 *
 * Created on September 17, 2004, 8:36 PM
 */

package blurock.DecisionTree;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.coreobjects.BaseDataKeyWords;
import blurock.logic.description.BaseDataConjunction;
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
public class BaseDataDecisionTreeNodeSpec extends blurock.DirectedTreeObjects.BaseDataTreeNode {
    BaseDataConjunction Conjunction = new BaseDataConjunction();
    BaseDataKeyWords Partition = new BaseDataKeyWords();
    /** Creates a new instance of BaseDataDecisionTreeNodeSpec */
    public BaseDataDecisionTreeNodeSpec() {
        Type = "DecisionTreeNodeSpec";
        Name = "DecisionTreeNodeSpec";
        Identification = 12040;
    }
    
    public void Read(RWManagerBase io) throws IOException {
        System.out.println("BaseDataDecisionTreeNodeSpec Read");
        super.Read(io);
        io.checkToken("TreeNodeSpec:"); 
        System.out.println("BaseDataDecisionTreeNodeSpec Read Conjuction");
         Conjunction.Read(io);
        System.out.println("BaseDataDecisionTreeNodeSpec Read Partition");
         Partition.Read(io);
        System.out.println("BaseDataDecisionTreeNodeSpec Read Done");
        Conjunction.Name = "Conjunction for: " + Name;
     }
    
    public void Write(RWManagerBase io) throws IOException {
        super.Write(io);
        Conjunction.Write(io);
        Partition.Write(io);
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man, DataObjectClass cls) {
        return new DBaseDataDecisionTreeNodeSpec(man,this,cls);
    }
    
    public BaseDataObject Clone() {
        BaseDataObject cls = (BaseDataObject)  new BaseDataDecisionTreeNodeSpec();
        cls.CopyClone(this);
       return cls;                 
    }
    
    public void CopyClone(BaseDataObject obj) {
        BaseDataDecisionTreeNodeSpec nodespec = (BaseDataDecisionTreeNodeSpec) obj;
        if(Conjunction != null) Conjunction = nodespec.Conjunction;
        if(Partition != null)   Partition   = nodespec.Partition;
        super.CopyClone(obj);
    }
    
}
