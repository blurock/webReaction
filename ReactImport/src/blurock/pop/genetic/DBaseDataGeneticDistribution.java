/*
 * DBaseDataGeneticDistribution.java
 *
 * Created on March 1, 2001, 11:22 PM
 */

package blurock.pop.genetic;
import blurock.core.*;
import blurock.coreobjects.*;
import blurock.opandalgs.ops.BaseDataOperation;
import java.io.IOException;
import javax.swing.*;
import blurock.core.RWManager;
import blurock.utilities.TopObjectPanel;

/**
 *
 * @author  reaction
 * @version 
 */
public class DBaseDataGeneticDistribution extends blurock.pop.base.DBaseDataGeneticObject {

    /** Creates new DBaseDataGeneticDistribution */
    public DBaseDataGeneticDistribution(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }

    public JPanel objectAsPanel() {
        JPanel top = super.objectAsPanel();
        BaseDataGeneticDistribution gdist = (BaseDataGeneticDistribution) Object;
        PanelGeneticDistribution panel = 
                   new PanelGeneticDistribution(gdist.DistributionName,gdist.ParameterName);
        TopObjectPanel ptop = new TopObjectPanel(gdist.Name,top,panel);
        return ptop;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        BaseDataGeneticDistribution gdist = (BaseDataGeneticDistribution) Object;
        ObjectAsTreeNode distname = new ObjectAsTreeNode("Distribution: " + 
                    gdist.DistributionName);
        ObjectAsTreeNode paramname = new ObjectAsTreeNode("Parameter: " +
                    gdist.ParameterName);
        objtop.add(distname);
        objtop.add(paramname);
        return objtop;
    }
}
