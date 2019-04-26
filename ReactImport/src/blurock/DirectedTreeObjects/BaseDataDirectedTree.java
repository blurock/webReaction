/*
 * BaseDataDirectedTree.java
 *
 * Created on September 16, 2004, 3:30 PM
 */

package blurock.DirectedTreeObjects;
import blurock.core.RWManagerBase;
import graph.DrawGraph;
import graph.DrawGraphNode;
import blurock.core.RWManagerBase;
import java.io.IOException;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;
/**
 *
 * @author  reaction
 */
public class BaseDataDirectedTree extends blurock.coreobjects.BaseDataSetOfObjects {
    public DrawGraph Graph = new DrawGraph();
    public String rootNode;
   
    /** Creates a new instance of BaseDataDirectedTree */
    public BaseDataDirectedTree() {
        Name = "DirectedTree";
        Type = "DirectedTree";
        Identification = 1;
    }
    
    public void Read(RWManagerBase io) throws IOException {
        io.checkToken("DirectedTree:");
        rootNode = io.readElement();
        System.out.println("DecisionTree Read super");
        super.Read(io);
        System.out.println("DecisionTree Read fill");
        fillGraphNodes(io);
        System.out.println("DecisionTree Read connections");
        readConnections(io);        
        System.out.println("DecisionTree Read end");
    }
    void fillGraphNodes(RWManagerBase io) {
        Object NodeStats[] = super.setAsArray();
        System.out.println("DecisionTree Read fill  " + NodeStats.length);
        for(int i=0;i<NodeStats.length;i++) {
            BaseDataTreeNode n = (BaseDataTreeNode) NodeStats[i];
            DrawGraphNode dgnode = new DrawGraphNode(n.Name);
            Graph.addNode(dgnode);
        }
    }
    void readConnections(RWManagerBase io) throws IOException {
        io.checkToken("Connections:"); 
        String name1 = io.readElement();
        while(!name1.equals("END")) {
            String bond = io.readElement();
            Graph.addBond(name1,bond);
            name1 = io.readElement();
        }
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man, DataObjectClass cls) {
        return new DBaseDataDirectedTree(man,this,cls);
    }
    public int numberOfNodes() {
        return size();
    }
    public Object[] setOfNodes() {
        return setAsArray();
    }
}
