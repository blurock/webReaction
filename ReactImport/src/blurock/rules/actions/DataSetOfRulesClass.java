/*
 * DataSetOfRulesClass.java
 *
 * Created on February 28, 2001, 11:42 AM
 */

package blurock.rules.actions;
import blurock.opandalgs.parameterized.*;
import blurock.coreobjects.*;
import blurock.core.*;
import javax.swing.*;
import java.io.IOException;

/**
 *
 * @author  reaction
 * @version 
 */
public class DataSetOfRulesClass extends blurock.opandalgs.parameterized.DataParameterSetClass {

    /** Creates new DataSetOfRulesClass */
    public DataSetOfRulesClass() {
    }
    public DataSetOfRulesClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "ParameterSet";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataSetOfRules();
        obj.Type = Name;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataSetOfRulesClass cls = new DataSetOfRulesClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
       return cls;
    }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
    }
    
}
