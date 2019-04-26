/*
 * DBaseDataProductionSystem.java
 *
 * Created on February 28, 2001, 12:05 PM
 */

package blurock.rules.prodsys;
import blurock.rules.actions.*;
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
public class DBaseDataProductionSystem extends blurock.opandalgs.parameterized.DBaseDataParameterizedFunction {

    /** Creates new DBaseDataProductionSystem */
    public DBaseDataProductionSystem(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataProductionSystem psys = (BaseDataProductionSystem) Object;
        DataProductionSystemClass psyscls = (DataProductionSystemClass) OClass;
        DBaseDataSetOfRules drules = 
            new DBaseDataSetOfRules(displayManager,psys.Rules,psyscls.RulesClass);
        JPanel prules = drules.objectAsPanel();
        return new PanelProductionSystem("Production Rules",top,prules);
     }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        BaseDataProductionSystem psys = (BaseDataProductionSystem) Object;
        DataProductionSystemClass psyscls = (DataProductionSystemClass) OClass;
        DBaseDataSetOfRules drules = 
            new DBaseDataSetOfRules(displayManager,psys.Rules,psyscls.RulesClass);
        ObjectAsTreeNode prules = drules.objectAsSubTree(objtop);
        return objtop;
     }    
}
