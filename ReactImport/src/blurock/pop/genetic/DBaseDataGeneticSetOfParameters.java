/*
 * DBaseDataGeneticSetOfParameters.java
 *
 * Created on March 1, 2001, 11:26 PM
 */

package blurock.pop.genetic;
import blurock.pop.base.*;
import blurock.utilities.TopObjectPanel;
import javax.swing.*;
import blurock.core.*;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.coreobjects.DBaseDataObject;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataGeneticSetOfParameters extends blurock.pop.base.DBaseDataGeneticObject {

    /** Creates new DBaseDataGeneticSetOfParameters */
    public DBaseDataGeneticSetOfParameters(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataGeneticSetOfParameters set = 
                (BaseDataGeneticSetOfParameters) Object;
        DataGeneticSetOfParametersClass cls = 
                (DataGeneticSetOfParametersClass) OClass;
        DBaseDataObject dset = 
                set.GeneticObjects.getDisplayObject(displayManager,cls.GeneticObjectsClass);
        JPanel objtop = dset.objectAsPanel();
        TopObjectPanel toppanel = new TopObjectPanel("Genetic Parameters",
                top,objtop);
         return toppanel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataGeneticSetOfParameters set = 
                (BaseDataGeneticSetOfParameters) Object;
        DataGeneticSetOfParametersClass cls = 
                (DataGeneticSetOfParametersClass) OClass;
        DBaseDataObject dset = 
                set.GeneticObjects.getDisplayObject(displayManager,cls.GeneticObjectsClass);
        ObjectAsTreeNode objtop = dset.objectAsSubTree(topnode);
        return objtop;
    }
    
}
