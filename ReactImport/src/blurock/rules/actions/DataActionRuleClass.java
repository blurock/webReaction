/*
 * DataActionRuleClass.java
 *
 * Created on February 28, 2001, 9:05 AM
 */

package blurock.rules.actions;
import blurock.opandalgs.parameterized.*;
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
public class DataActionRuleClass extends blurock.opandalgs.parameterized.DataParameterizedFunctionClass {

    public DataParameterizedFunctionClass ConditionClass = null;
    
    public DataParameterSetClass ActionsClass = null;
    
    /** Creates new DataActionRuleClass */
    public DataActionRuleClass() {
    }
    public DataActionRuleClass(int id, String type, String descr) {
        super(id,type,descr);
        SubClass = "ParameterizedFunction";
    }

    public BaseDataObject BaseDataObjectExample() {
        BaseDataObject obj = (BaseDataObject) new BaseDataActionRule();
        obj.Type = Name;
        return obj;
    }
    
    public DataObjectClass Clone() {
        DataActionRuleClass cls = 
                new DataActionRuleClass(Identification,Name,Description);
        cls.derivedClass = derivedClass;
        cls.SubClass = SubClass;
        try {
            cls.ConditionClass = (DataParameterizedFunctionClass) ConditionClass.Clone();
            cls.ActionsClass = (DataParameterSetClass) ActionsClass.Clone();
        } catch(NullPointerException exp) {
            cls.ConditionClass = null;
            cls.ActionsClass = null;
        }
       return cls;
     }
    
    public void Read(RWManager io) throws IOException {
        super.Read(io);
        ConditionClass = (DataParameterizedFunctionClass) io.getClassFromNextElement();
        ActionsClass   = (DataParameterSetClass) io.getClassFromNextElement();
    }
    
    public void Write(RWManager io) throws IOException {
        super.Write(io);
        try {
            io.printLine(ConditionClass.Name + " " +
                        ActionsClass.Name);
        } catch (NullPointerException nexp) {
            throw new IOException("ActionRule Class not fully defined");
        }
     }
    
}
