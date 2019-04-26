/*
 * BaseDataInstanceDoubleVector.java
 *
 * Created on June 8, 2005, 2:29 PM
 */

package blurock.InstanceObjects;
import java.io.IOException;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.BaseDataKeyWords;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.core.ObjectDisplayManager;
import blurock.core.RWManagerBase;

/**
 *
 * @author  reaction
 */
public class BaseDataInstanceDoubleVector extends BaseDataObject {
    public double[] vector = null;
    /** Creates a new instance of BaseDataInstanceDoubleVector */
    public BaseDataInstanceDoubleVector() {
    }
    public BaseDataInstanceDoubleVector(String name, int id) {
        super(name,id);
    }
    public BaseDataObject Clone() {
        BaseDataInstanceDoubleVector obj = new BaseDataInstanceDoubleVector(Name,Identification);
        obj.Type = Type;
        obj.vector = new double[vector.length];
        for(int i=0;i<vector.length;i++) {
            obj.vector[i] = vector[i];
        }
       return obj;         
    }
    
    public void CopyClone(BaseDataObject obj) {
        Name = obj.Name;
        Identification = obj.Identification;
        Type = obj.Type;
    }

    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return new DBaseDataInstanceDoubleVector(man,this,cls);
    }
    public void Read(RWManagerBase io) throws IOException {
        io.checkToken("Values:");
        String valueS = io.nextToken();
        try {
            Integer I = new Integer(valueS);
            int length = I.intValue();
            vector = new double[length];
            for(int i=0;i<length;i++) {
                valueS = io.nextToken();
                vector[i] = Double.valueOf(valueS);
            }
        } catch(NumberFormatException ex) {
            throw new IOException("Error in reading InstanceDoubleVector " + valueS);
        }
    }
    
    public void Write(RWManagerBase io) throws IOException {
        io.printLine("Values: " + vector.length);
        StringBuffer buf = new StringBuffer();
        for(int i=0;i<vector.length;i++) {
            buf.append(Double.toString(vector[i]));
            buf.append(" ");
        }
        io.printLine(buf.toString());
        
    }
    
}
