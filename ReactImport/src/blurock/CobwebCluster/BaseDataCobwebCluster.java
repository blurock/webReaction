/*
 * BaseDataCobwebCluster.java
 *
 * Created on October 13, 2003, 4:45 PM
 */

package blurock.CobwebCluster;
import java.util.*;
import blurock.core.*;
import blurock.coreobjects.*;
import java.io.IOException;
import graph.*;

/**
 *
 * @author  reaction
 */
public class BaseDataCobwebCluster extends BaseDataObject {
    
    public BaseDataSetOfObjects Nodes;
    ClassNamePairs PredicateNameClass;
    DrawGraph Graph = new DrawGraph();
    CompareDescriptorDifferences compare = new CompareDescriptorDifferences();
    
    public int minimumElementsInNode = 5;
    public int maxLevel = 5;
    public String topNode = new String("Cobweb");
    
    /** Creates a new instance of BaseDataCobwebCluster */
    public BaseDataCobwebCluster() {
        Name = "CobwebCluster";
        Type = "CobwebCluster";
        Identification = 1;
    }
    public void Read(RWManagerBase io) throws IOException {
        PredicateNameClass = new ClassNamePairs();
        PredicateNameClass.Read(io);
        // Skip over the predicates for now
        for(int i=0;i<PredicateNameClass.Names.size();i++) {
            io.readNextLine();
            io.readNextLine();
        }
        String line = io.readNextLine();
       line = io.readNextLine();

        Nodes = new BaseDataSetOfObjects();
        Nodes.Read(io);
        setDescriptors();
        
        fillGraphNodes(io);
        
        readConnections(io);
    }
    void setDescriptors() {
        Object[] NodeStats = Nodes.setAsArray();
        for(int i=0;i<NodeStats.length;i++) {
            BaseDataCobwebClusterNodeStats n = (BaseDataCobwebClusterNodeStats) NodeStats[i];
            StringTokenizer tok = new StringTokenizer(n.Name,"#");
            n.Level = tok.countTokens();
            n.setDescriptors(PredicateNameClass.Names,compare);
        }
    }
    void fillGraphNodes(RWManagerBase io) {
        Object NodeStats[] = Nodes.setAsArray();
        for(int i=0;i<NodeStats.length;i++) {
            BaseDataCobwebClusterNodeStats n = (BaseDataCobwebClusterNodeStats) NodeStats[i];
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
        Object[] NodeStats = Nodes.setAsArray();
        for(int level=0;level<maxLevel;level++) {
            for(int i=0;i<NodeStats.length;i++) {
                BaseDataCobwebClusterNodeStats n = (BaseDataCobwebClusterNodeStats) NodeStats[i];
                if(n.pointValues.length >= minimumElementsInNode && level == n.Level)
                    n.Write(io);
            }
        }        
    }
 
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataCobwebCluster(man,this,cls);
    }
    
}
