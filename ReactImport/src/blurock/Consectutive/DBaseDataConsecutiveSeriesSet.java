/*
 * DBaseDataConsecutiveSeriesSet.java
 *
 * Created on November 5, 2003, 6:27 PM
 */

package blurock.Consectutive;
import blurock.core.*;
import utilities.ErrorFrame;
import javax.swing.*;
import blurock.coreobjects.*;
import java.awt.*;
import graph.*;
import java.util.Vector;
/**
 *
 * @author  reaction
 */
public class DBaseDataConsecutiveSeriesSet extends blurock.coreobjects.DBaseDataSetOfObjects {
    
    
    /** Creates a new instance of DBaseDataConsecutiveSeriesSet */
    public DBaseDataConsecutiveSeriesSet(ObjectDisplayManager man,BaseDataObject obj,DataObjectClass cls) {
        super(man,obj,cls);
    }
    
    public JPanel objectAsPanel() {
        System.out.println("objectAsPanel");
      //JPanel panel = super.objectAsPanel();
        BaseDataConsecutiveSeriesSet set = (BaseDataConsecutiveSeriesSet) Object;
      ClusterRegionsCanvas regions = new ClusterRegionsCanvas(set);
      JPanel pnl = new JPanel();
      //regions.addMouseListener(this);
      //pnl.addMouseListener(this);
      pnl.add(regions);
      pnl.setMinimumSize(new java.awt.Dimension(200,200));
      regions.repaint();
      return pnl;
    }
    
    public ObjectAsTreeNode objectAsSubTree(ObjectAsTreeNode topnode) {
        BaseDataConsecutiveSeriesSet set = (BaseDataConsecutiveSeriesSet) Object;
        //ObjectAsTreeNode node = super.objectAsSubTree(topnode);
        ObjectAsTreeNode node = addSons(topnode,set.topNode);
        return node;
    }
    public ObjectAsTreeNode addSons(ObjectAsTreeNode topnode, String parent) {
        BaseDataConsecutiveSeriesSet set = (BaseDataConsecutiveSeriesSet) Object;
        DrawGraph g = set.Graph;
        try {
            int index = g.getNode(parent);
            System.out.println("TopNode: " + set.topNode + "   index=" + index);
            DrawGraphNode node = (DrawGraphNode) g.Nodes.elementAt(index);
            BaseDataConsecutiveSeries series = (BaseDataConsecutiveSeries) set.findObject(node.getNameTag());
            DataConsecutiveSeriesSetClass setclass = (DataConsecutiveSeriesSetClass) OClass;
            DBaseDataObject dobj = series.getDisplayObject(displayManager,setclass.nodeClass);
            ObjectAsTreeNode topclusternode = dobj.objectAsSubTree(topnode);
            topnode.add(topclusternode);
            Vector sons = g.getSons(parent);
            for(int i=0; i< sons.size();i++) {
                addSons(topclusternode, (String) sons.elementAt(i));
            }
        } catch(ObjectNotFoundException ex) {
            System.out.println(ex);
        }
        return topnode;
    }

  public void display() {
      BaseDataConsecutiveSeriesSet set = (BaseDataConsecutiveSeriesSet) Object;
      JFrame fr = new JFrame("Regions");
      ClusterRegionsCanvas regions = new ClusterRegionsCanvas(set);
      regions.repaint();
      JPanel pnl = new JPanel();
      pnl.add(regions);
      fr.getContentPane().removeAll();
      fr.getContentPane().add(pnl);
      fr.setVisible(true);
      fr.pack();
      fr.show();
      java.awt.Graphics g = fr.getGraphics();
      regions.paint(g);
  }
 
}
