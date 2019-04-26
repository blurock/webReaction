/*
 * DBaseDataConsecutiveSeries.java
 *
 * Created on November 5, 2003, 5:41 PM
 */

package blurock.Consectutive;
import blurock.core.*;
import blurock.coreobjects.*;
import utilities.ErrorFrame;
import javax.swing.*;

/**
 *
 * @author  reaction
 */
public class DBaseDataConsecutiveSeries extends blurock.coreobjects.DBaseDataSetOfObjects {
    
    /** Creates a new instance of DBaseDataConsecutiveSeries */
    public DBaseDataConsecutiveSeries(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    
    public JPanel objectAsPanel() {
        JPanel panel = new JPanel();
        panel.setName(Object.Name);
        JLabel label = new JLabel(Object.Name);
        panel.add(label);
        return panel;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataConsecutiveSeries series = (BaseDataConsecutiveSeries) Object;
        ObjectAsTreeNode objtop = new ObjectAsTreeNode(Object.Name);
        topnode.add(objtop);
        for(int i=0;i<series.SubRegions.length;i++) {
            if(series.SubRegions[i] != null) {
                ObjectAsTreeNode objsub = new ObjectAsTreeNode(series.SubRegions[i].nodeInfoName);
                objtop.add(objsub);
            }
        }
        objtop.nodeObject = this;
        return objtop;
    }
    
}
