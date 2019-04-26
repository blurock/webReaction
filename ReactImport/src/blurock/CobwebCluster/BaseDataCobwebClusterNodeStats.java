/*
 * BaseDataCobwebClusterNodeStats.java
 *
 * Created on October 13, 2003, 6:40 PM
 */

package blurock.CobwebCluster;
import java.util.*;
import blurock.core.*;
import blurock.coreobjects.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 */
public class BaseDataCobwebClusterNodeStats extends BaseDataObject {
    Vector Descriptors;
    BaseDataCobwebClusterDescriptorPoint descriptorPoints[];
    CompareDescriptorDifferences compare;
    int Level = 0;

    ClassNamePairs PointNames;
    double pointValues[];
    double description[];
    double goalProbabilities[];
    double probChanges[];
    
    /** Creates a new instance of BaseDataCobwebClusterNodeStats */
    public BaseDataCobwebClusterNodeStats() {
        Name = "CobwebClusterNodeStats";
        Type = "CobwebClusterNodeStats";
        Identification = 1;
    }
     public void Read(RWManagerBase io) throws IOException {
        PointNames = new ClassNamePairs();
        PointNames.Read(io);
        System.out.println(PointNames.Names);
       int n = PointNames.Names.size();
        pointValues = new double[n];
        readVectorOfDoubles(pointValues,io);
        n = io.readInteger();
        description = new double[n];
        readVectorOfDoubles(description,io);
        n = io.readInteger();
        goalProbabilities = new double[n];
        readVectorOfDoubles(goalProbabilities,io);
        n = io.readInteger();
        probChanges = new double[n];
        readVectorOfDoubles(probChanges,io);
         }
 
    public void Write(RWManagerBase io) throws IOException {
        io.printLine("----------------------------------------------------------------------------------------------------");
        io.printLine(Name + "     (" + pointValues.length+ " elements)");
        for(int i=0;i<descriptorPoints.length;i++) {
            descriptorPoints[i].Write(io);
        }
    }
    void setDescriptors(Vector names,CompareDescriptorDifferences comp) {
        compare = comp;
        Descriptors = names;
        descriptorPoints = new BaseDataCobwebClusterDescriptorPoint[names.size()];
        for(int i=0;i<names.size();i++) {
            String name = (String) names.elementAt(i);
            descriptorPoints[i] = new BaseDataCobwebClusterDescriptorPoint(name,description[i],probChanges[i]);
        }
        sortDescriptors();
    }
    public void sortDescriptors() {
        Arrays.sort(descriptorPoints,compare);
    }
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataCobwebClusterNodeStats(man,this,cls);
    }
   void readVectorOfDoubles(double vec[],RWManagerBase io ) throws IOException {
        for(int i=0;i<vec.length;i++) {
           vec[i] = io.readDouble();
        }
   }
}
