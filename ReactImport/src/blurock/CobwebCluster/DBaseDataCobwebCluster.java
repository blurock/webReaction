/*
 * DBaseDataCobwebCluster.java
 *
 * Created on October 13, 2003, 4:56 PM
 */

package blurock.CobwebCluster;
import blurock.core.*;
import blurock.coreobjects.*;
import utilities.ErrorFrame;
import javax.swing.*;
import graph.*;
import java.util.Vector;
/**
 *
 * @author  reaction
 */
public class DBaseDataCobwebCluster extends DBaseDataObject {
    
    /** Creates a new instance of DBaseDataCobwebCluster */
    public DBaseDataCobwebCluster(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataCobwebCluster cluster = (BaseDataCobwebCluster) Object;
        ObjectAsTreeNode objtop = super.objectAsSubTree(topnode);
        System.out.println("Max Level: " + cluster.maxLevel + "  Min Size" + cluster.minimumElementsInNode);
        addSons(objtop,cluster.topNode);
        return objtop;
    }
    
    public ObjectAsTreeNode addSons(ObjectAsTreeNode topnode, String parent) {
        /**/
        BaseDataCobwebCluster cluster = (BaseDataCobwebCluster) Object;
        DrawGraph g = cluster.Graph;
        try {
            int index = g.getNode(parent);
            DrawGraphNode node = (DrawGraphNode) g.Nodes.elementAt(index);
            BaseDataCobwebClusterNodeStats stats = (BaseDataCobwebClusterNodeStats) cluster.Nodes.findObject(node.getNameTag());
            System.out.println("Node: " + stats.Name + "   index=" + index);
           DataCobwebClusterClass cobwebclass = (DataCobwebClusterClass) OClass;
            DBaseDataObject dobj = stats.getDisplayObject(displayManager,cobwebclass.nodeClass);
            
            if(stats.pointValues.length >= cluster.minimumElementsInNode &&  stats.Level <= cluster.maxLevel) {
            System.out.println("Level: " + stats.Level + "   Size" + stats.pointValues.length);
                 ObjectAsTreeNode topclusternode = dobj.objectAsSubTree(topnode);
                //topclusternode.setToolTipText("Elements: " + stats.pointValues.length);
                topnode.add(topclusternode);

                Vector sons = g.getSons(parent);
                for(int i=0; i< sons.size();i++) {
                    addSons(topclusternode, (String) sons.elementAt(i));
                }
            }
        } catch(ObjectNotFoundException ex) {
            System.out.println(ex);
        }
        return topnode;
    }
    public JPanel objectAsPanel() {
        RWManager io = new RWManager(displayManager.dataClasses);
        DataCobwebClusterClass cobwebclass = (DataCobwebClusterClass) OClass;
        BaseDataCobwebCluster cluster = (BaseDataCobwebCluster) Object;
        
        CobwebClusterMainPanel toppanel = new CobwebClusterMainPanel(cluster, cobwebclass, displayManager, io);

        return toppanel;
    }

}
