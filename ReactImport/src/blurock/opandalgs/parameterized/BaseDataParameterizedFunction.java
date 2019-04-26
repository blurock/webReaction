/*
 * BaseDataParameterizedFunction.java
 *
 * Created on February 27, 2001, 9:32 PM
 */

package blurock.opandalgs.parameterized;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.*;
import blurock.core.*;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class BaseDataParameterizedFunction extends blurock.opandalgs.ops.BaseDataOperation {

    public BaseDataParameterSet ParameterSet;
    
    public BaseDataOperation Operation;
    
    public boolean ParametersWithOperation = true;
    
    /** Creates new BaseDataParameterizedFunction */
    public BaseDataParameterizedFunction() {
    }

    public void Read(RWManager io) throws IOException {
        try {
            DataParameterizedFunctionClass cls = (DataParameterizedFunctionClass) 
                           io.dataClasses.findClass(Type);
            ParameterSet = (BaseDataParameterSet) cls.ParameterSetClass.BaseDataObjectExample();
            Operation    = (BaseDataOperation) cls.OperationClass.BaseDataObjectExample();
            ParameterSet.Name = "ParameterSet";
            Operation.Name = cls.Name;
            ParameterSet.Read(io);
            io.checkToken("Operation:");
            Operation.Read(io);
        } catch (NullPointerException nexp) {
            throw new IOException("ParameterizedFunction Class not fully defined: '" + Type + "'");
        } catch (ObjectNotFoundException f) {
            throw new IOException("Parameterized Function not registered: " + f);
        }   
    }
    
    public void Write(RWManager io) throws IOException {
        try {
            ParameterSet.Write(io);
            Operation.Write(io);
        } catch(NullPointerException nexp) {
            throw new IOException("Parameterized Function: '" +
                                  Type + "' not fully defined");
        }
    }
    
    public DBaseDataObject getDisplayObject(ObjectDisplayManager man,DataObjectClass cls) {
        return (DBaseDataObject) new DBaseDataParameterizedFunction(man,this,cls);
    }
    
}
