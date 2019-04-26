/*
 * FlowGraph.java
 *
 * Created on October 21, 2002, 8:57 PM
 */

package react.mechanisms.flow;
import java.util.Hashtable;
import java.util.HashSet;
import java.io.IOException;
import java.util.Enumeration;
import java.io.PrintStream;
import graph.*;
import java.awt.event.*;
import javax.swing.JPanel;
import blurock.utilities.BaseScrollFrame;
import java.awt.Color;
/**
 *
 * @author  reaction
 */
public class FlowGraph extends graph.DrawGraph implements MouseListener {
    Hashtable nodeTable = new Hashtable();
    int depth = 0;
    int bondCutOffValue = 0;
    /** Creates a new instance of FlowGraph */
    public FlowGraph() {
        setDefaultBondType(100);
        xMax = 1000;
        yMax = 1000;
        addMouseListener(this);
    }
    public void clearGraph() {
        super.clearGraph();
        nodeTable = new Hashtable();
    }
    public void read(String species, String dir) throws IOException {
        nextSpecies(species,dir,0);
        FlowSpecies flow = (FlowSpecies) nodeTable.get(species);
        addBonds();       
     }
    public void draw(String species) {
        FlowSpecies flow = (FlowSpecies) nodeTable.get(species);
        treeGraphPositions(flow,xTotalMax,yInitial,yIncrement);
        repaint();
    }
     public void setDepth(int d) {
         depth = d;
     }
     public void toFile(String species, PrintStream prt) {
         stringGraph(species);
     }
    private void nextSpecies(String species,String dir, int d) throws IOException {
        if(depth == 0 || d<depth) {
            if(!nodeTable.containsKey(species)) {
                SingleFlowFile f = new SingleFlowFile(species,dir,true);
                SingleFlow[] flowset = f.getFlow();
                if(flowset != null) {
                    FlowSpecies flow = new FlowSpecies(species,flowset);
                    nodeTable.put(species,flow);
                    addNode(flow);
                    d++;
                    for(int i=0;i<flowset.length;i++) {
                        if(flowset[i].relativeFlow > bondCutOffValue)
                         nextSpecies(flowset[i].sonSpecies,dir,d);
                    }
                }
            }
        } else {
            System.out.println("Maximum depth reached");
        }
    }
    private void addBonds() {
        Enumeration elements = nodeTable.elements();
        while(elements.hasMoreElements()) {
            FlowSpecies flow = (FlowSpecies) elements.nextElement();
            String parent = flow.getNameTag();
            SingleFlow[] flowset = flow.getFlow();
            for(int i=0;i<flowset.length;i++) {
                SingleFlow son = flowset[i];
                if(son.relativeFlow > bondCutOffValue) {
                    DrawGraphBond bond = addBond(parent,son.sonSpecies);
                    if(bond != null) {
                        double fd = 255.0*son.relativeFlow/100.0;
                        Color bcolor = new Color(255,(int) fd,102);
                        bond.setBondColor(bcolor);
                    }
                }
            }
        }
    }
    
    public graph.DrawGraphBond addBond(String parent, String son) {
        graph.DrawGraphBond bond = null;
        if(nodeTable.containsKey(son)) {
            bond = super.addBond(parent,son);
        }
        return bond;
    }
    public String stringGraph(String top) {
        FlowSpecies flow = (FlowSpecies) nodeTable.get(top);
        HashSet used = new HashSet();
        return nextStringGraph(flow,0,used);
    }
       
    private String nextStringGraph(FlowSpecies flow, int d, HashSet used) {
        StringBuffer setS = new StringBuffer();
        if(!used.contains(flow.getNameTag())) {
            if(d==0 || d<depth ) {
                used.add(flow.getNameTag());
                StringBuffer prefix = new StringBuffer();
                for(int i=0;i<d;i++)
                     prefix.append("       ");
                SingleFlow[] set = flow.getFlow();
                d++;
                for(int i=0;i<set.length;i++) {
                    if(bondCutOffValue < set[i].relativeFlow) {
                        String singleS = set[i].toString();
                        setS.append(prefix + singleS + "\n");
                        FlowSpecies next = (FlowSpecies) nodeTable.get(set[i].sonSpecies);
                        if(next != null) {
                            String nextS = nextStringGraph(next,d,used);
                            setS.append(nextS);
                        }
                    }
                }
            }
        }
        return setS.toString();
    }
    public void setMinimumFlowValue(int value) {
        if(value < 0) value = 0;
        if(value > 100) value = 100;
        bondCutOffValue = value;
    }
    public void mouseClicked(MouseEvent evnt) {
        System.out.println("mousePressed(MouseEvent evnt)");
        super.mouseClicked(evnt);
        System.out.println(getBestChosenNode());
        int ind = getNode(getBestChosenNode());
        if(ind >= 0) {
            FlowSpecies flow = (FlowSpecies) Nodes.elementAt(ind);
            JPanel pnl = flow.objectAsPanel();
                BaseScrollFrame fr = new BaseScrollFrame(pnl,
                            getBestChosenNode(),
                            "Flows",
                            "Flows from node");
                fr.show();
        }
    }

}
