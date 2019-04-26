/*
 * DBaseDataPopulation.java
 *
 * Created on March 1, 2001, 10:18 PM
 */

package blurock.pop.base;
import blurock.core.ObjectDisplayManager;
import blurock.coreobjects.BaseDataObject;
import blurock.coreobjects.DBaseDataObject;
import blurock.coreobjects.DataObjectClass;
import blurock.core.ObjectAsTreeNode;
import blurock.utilities.TopObjectPanel;
import javax.swing.JPanel;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataPopulation extends blurock.coreobjects.DBaseDataSetOfObjects {

    /** Creates new DBaseDataPopulation */
    public DBaseDataPopulation(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataPopulation pop = (BaseDataPopulation) Object;
        DataPopulationClass cls = (DataPopulationClass) OClass;
        DBaseDataObject dgenobj = 
            pop.GeneticObject.getDisplayObject(displayManager,cls.GeneticClass);
        JPanel pgenobj = dgenobj.objectAsPanel();
        PanelPopulation poppanel = new PanelPopulation(pop.PopulationSize,pgenobj);
        TopObjectPanel toppanel = new TopObjectPanel("Population",top,poppanel);
        return toppanel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataPopulation pop = (BaseDataPopulation) Object;
        DataPopulationClass cls = (DataPopulationClass) OClass;
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        ObjectAsTreeNode popsize = 
            new ObjectAsTreeNode("Population Size: " + String.valueOf(pop.PopulationSize));
        objtop.add(popsize);
        DBaseDataObject dgenobj = 
            pop.GeneticObject.getDisplayObject(displayManager,cls.GeneticClass);
        dgenobj.objectAsSubTree(objtop);
        return objtop;
    }
    
}
