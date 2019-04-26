/*
 * DBaseDataCobwebClusterDataDescription.java
 *
 * Created on October 14, 2003, 12:05 PM
 */

package blurock.CobwebCluster;
import blurock.core.*;
import blurock.coreobjects.*;
import utilities.ErrorFrame;
import javax.swing.*;

/**
 *
 * @author  reaction
 */
public class DBaseDataCobwebClusterDescriptorPoint extends DBaseDataObject {
    
     
    /** Creates a new instance of DBaseDataCobwebClusterDataDescription */
    public DBaseDataCobwebClusterDescriptorPoint(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        return objtop;
    }
    public JPanel objectAsPanel() {
       /* JPanel toppanel = new JPanel();
        JPanel base = super.objectAsPanel();
        toppanel.add(base);*/
        BaseDataCobwebClusterDescriptorPoint pnt = (BaseDataCobwebClusterDescriptorPoint) Object;
        DescriptionPointPanel pnl = new DescriptionPointPanel(pnt);
        /*toppanel.add(pnl);*/
        return pnl;
    }    
}
