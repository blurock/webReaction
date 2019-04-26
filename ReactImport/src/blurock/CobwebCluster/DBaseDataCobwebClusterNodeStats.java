/*
 * DBaseDataCobwebClusterNodeStats.java
 *
 * Created on October 13, 2003, 6:50 PM
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
public class DBaseDataCobwebClusterNodeStats extends DBaseDataObject{
    
    /** Creates a new instance of DBaseCobwebClusterNodeStats */
    public DBaseDataCobwebClusterNodeStats(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        return objtop;
    }
    public JPanel objectAsPanel() {
        JPanel toppanel = new JPanel();
        JPanel base = super.objectAsPanel();
        BaseDataCobwebClusterNodeStats stats = (BaseDataCobwebClusterNodeStats) Object;
        DataCobwebClusterNodeStatsClass statsclass = (DataCobwebClusterNodeStatsClass) OClass;
        DataCobwebClusterDescriptorPointClass pntclass = statsclass.descriptorPoinClass;
        
        java.awt.GridBagConstraints gridBagConstraints;
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridwidth = java.awt.GridBagConstraints.REMAINDER;
        gridBagConstraints.fill = java.awt.GridBagConstraints.HORIZONTAL;
        
        toppanel.setLayout(new java.awt.GridBagLayout());
        /*toppanel.add(base,gridBagConstraints);*/
        JLabel l = new JLabel(stats.Name);
        toppanel.add(l,gridBagConstraints);
        for(int i=0;i<stats.descriptorPoints.length;i++) {
            DBaseDataObject pntpnl = stats.descriptorPoints[i].getDisplayObject(displayManager,pntclass);
            toppanel.add(pntpnl.objectAsPanel(),gridBagConstraints);
        }
        return toppanel;
    }
    
}
