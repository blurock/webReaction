/*
 * BaseDataDecitionTreeNodeStats.java
 *
 * Created on September 17, 2004, 11:00 PM
 */

package blurock.DecisionTree;
import blurock.DirectedTreeObjects.DBaseDataTreeNode;
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
public class BaseDataDecisionTreeNodeStats extends blurock.DecisionTree.BaseDataDecisionTreeNodeSpec {
    double NumberOfObjects;
    double Gain;
    double GoalStats[];
    int goalPartitionCount;
    int predicatePartitionCount;
    double PartitionMatrix[][];
    
    /** Creates a new instance of BaseDataDecitionTreeNodeStats */
    public BaseDataDecisionTreeNodeStats() {
        Type = "DecisionTreeNodeStats";
        Name = "DecisionTreeNodeStats";
        Identification = 12030;
    }
    
    public BaseDataObject Clone() {
       BaseDataObject obj = (BaseDataObject)  new BaseDataDecisionTreeNodeStats();
       obj.CopyClone(this);
       return obj;         
     }
    
    public void CopyClone(BaseDataObject obj) {
        super.CopyClone(obj);
    }
    
    public void Read(RWManagerBase io) throws IOException {
        super.Read(io);
        NumberOfObjects = io.readDouble();
        Gain = io.readDouble();
        predicatePartitionCount = io.readInteger();
        goalPartitionCount = io.readInteger();
        PartitionMatrix = new double[predicatePartitionCount][goalPartitionCount];
        for(int i=0;i<predicatePartitionCount;i++) {
            for(int j=0;j < goalPartitionCount;j++) {
                PartitionMatrix[i][j] = io.readDouble();
            }
        }
        GoalStats = new double[goalPartitionCount];
        for(int i=0;i<goalPartitionCount;i++) {
            GoalStats[i] = io.readDouble();
        }
        io.checkToken("END");
        
    }
    
    public void Write(RWManagerBase io) throws IOException {
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man, DataObjectClass cls) {
        return new DBaseDataDecisionTreeNodeStats(man,this,cls);
    }
    
}
