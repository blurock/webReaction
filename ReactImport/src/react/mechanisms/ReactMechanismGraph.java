/*
 * ReactMechanismGraph.java
 *
 * Created on January 18, 2002, 12:16 PM
 */

package react.mechanisms;
import react.molecules.*;
import graph.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.JFrame;
import react.common.*;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Vector;
/**
 *
 * @author  reaction
 * @version 
 */
public class ReactMechanismGraph extends graph.DrawGraph 
                                 implements MouseListener, MouseMotionListener {
    TopReactionMenu Top;
    public ReactionMechanism Mechanism;
    
    public Color rxnClassColor = Color.blue;
    public Color rxnColor      = Color.cyan;
    public Color molColor      = Color.red;
    public Color bondReactantsColor     = Color.black;
    public Color bondProductsColor      = Color.red;
    
    java.util.Hashtable Molecules;
    java.util.Hashtable RxnClasses;
    
    int index;
    public int molCount = 0;
    
    public boolean drawRxnClass = true;
    /** Creates new ReactMechanismGraph */
    public ReactMechanismGraph(TopReactionMenu top, ReactionMechanism mechanism, boolean rxncls) {
        Mechanism = mechanism;
        Top = top;
        Molecules  = new java.util.Hashtable(mechanism.Molecules.size());
        RxnClasses = new java.util.Hashtable(mechanism.rxnClasses.size());
        xMax = 1000;
        yMax = 1000;
        EdgeAdjust = false;
        drawRxnClass = rxncls;
        drawMechanism();
	addMouseMotionListener(this);
        relax();
        ReactMechanismGraphNodeMolecule p = 
                (ReactMechanismGraphNodeMolecule) Molecules.get(mechanism.seedMolecule);
        recomputeNodePositions(p,true);
        repaint();
   }
   void drawMechanismTree(ReactMechanismGraphNodeMolecule mol) {
       
   }
    void drawMechanism() {
        index = 0;
        molCount = 0;
        for(int i=0;i<Mechanism.rxnClasses.size();i++) {
           ReactMechanismRxnClass rxnclass = 
                  (ReactMechanismRxnClass) Mechanism.rxnClasses.elementAt(i);
           drawRxnClass(i,rxnclass);
        }
    }
    void drawRxnClass(int id, ReactMechanismRxnClass rxnclass) {
        ReactMechanismGraphNodeRxnClass rxnclsnode = 
            new ReactMechanismGraphNodeRxnClass(Top,id,rxnclass);
        RxnClasses.put(rxnclass.className,rxnclsnode);
        drawRxnClassNode(rxnclsnode);
        for(int i=0;i<rxnclass.reactions.size();i++) {
            index++;
            ReactMechanismRxn rxn = (ReactMechanismRxn) rxnclass.reactions.elementAt(i);
            drawReaction(index,rxn,rxnclass,rxnclsnode);
        }
     }
     void drawReaction(int index, ReactMechanismRxn rxn, ReactMechanismRxnClass rxncls,
        ReactMechanismGraphNodeRxnClass rxnclsnode) {
         ReactMechanismGraphNodeRxn rxnnode = new ReactMechanismGraphNodeRxn(index,rxn,rxncls);
         rxnclsnode.addRxn(rxnnode);
         drawRxnNode(rxnnode);
       for(int i = 0; i<rxn.numReactants;i++) {
            String molname = rxn.reactantMolecules[i];
            ReactMechanismGraphNodeMolecule molnode = moleculeNode(molname);
            rxnclsnode.addReactant(molnode);
            drawMolRxnBond(molnode,rxnnode,rxnclsnode,true);
        }
        for(int j = 0; j<rxn.numProducts;j++) {
            String molname = rxn.productMolecules[j];
            ReactMechanismGraphNodeMolecule molnode = moleculeNode(molname);
            rxnclsnode.addProduct(molnode);
            drawMolRxnBond(molnode,rxnnode,rxnclsnode,false);
        }
     }
     ReactMechanismGraphNodeMolecule moleculeNode(String name) {
         ReactMechanismGraphNodeMolecule molnode = null;
         if(Molecules.containsKey(name)) {
         try {
             molnode = (ReactMechanismGraphNodeMolecule) Molecules.get(name);
         } catch(NullPointerException io) {
             System.out.println("Opps");
         }
         } else {
         ReactMolecule mol = (ReactMolecule) Mechanism.Molecules.get(name);
         molnode = new ReactMechanismGraphNodeMolecule(mol);
         Molecules.put(name,molnode);
         drawMoleculeNode(molnode);
         }
         return molnode;
     }
     void drawRxnClassNode(ReactMechanismGraphNodeRxnClass rxnclsnode) {
         if(drawRxnClass) {
             rxnclsnode.drawNode(this);
         }
     }
     void drawRxnNode(ReactMechanismGraphNodeRxn rxnnode) {
         if(!drawRxnClass) {
             rxnnode.drawNode(this);
         }
     }
     void drawMoleculeNode(ReactMechanismGraphNodeMolecule molnode) {
         molnode.drawNode(this);
     }
     void drawMolRxnBond(ReactMechanismGraphNodeMolecule molnode,
                ReactMechanismGraphNodeRxn rxnnode,
                ReactMechanismGraphNodeRxnClass rxnclsnode,
                boolean reactant) {
         if(!drawRxnClass) {
             if(reactant) {
                DrawGraphBond bond = addBond(molnode.getNameTag(),rxnnode.getNameTag());
                bond.setBondColor(bondReactantsColor);
                bond.setType(100);
             } else {
                 DrawGraphBond bond = addBond(rxnnode.getNameTag(),molnode.getNameTag());
                 bond.setBondColor(bondProductsColor);
                bond.setType(100);
             }
         } else {
             if(reactant) {
                DrawGraphBond bond = addBond(molnode.getNameTag(), rxnclsnode.getNameTag());
                bond.setBondColor(bondReactantsColor);
                bond.setType(100);
             } else {
                 DrawGraphBond bond = addBond(rxnclsnode.getNameTag(), molnode.getNameTag());
                 bond.setBondColor(bondProductsColor);
                bond.setType(100);
             }
         }
     }
/** No current action
 * @param e The event
 */    
    public void mouseDragged(MouseEvent e) {
        if((e.getModifiers() &  InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK) {
            relax();
        } else  if((e.getModifiers() &  InputEvent.BUTTON1_MASK) == InputEvent.BUTTON1_MASK) {      
	    super.mouseDragged(e);
        }
    }
/** no current action
 * @param e The event
 */    
    public void mouseMoved(MouseEvent e) {
    }
    public void recomputeNodePositions(ReactMechanismGraphNode pick, boolean toRxnClasses) {
        Hashtable used = new Hashtable();
        Hashtable usedrxns = new Hashtable();
        Vector current = new Vector();
        current.add(pick.getNameTag());
        used.put(pick.getNameTag(),pick.getNameTag());
        int maxWidth = 900;
        int ylevel = 100;
        while(current.size() > 0) {
            int dist = maxWidth/(current.size()+1);
            for(int cnt=0;cnt<current.size();cnt++) {
                String name = (String) current.get(cnt);
                int n = getNode(name);
                ReactMechanismGraphNode p = (ReactMechanismGraphNode) Nodes.elementAt(n);
                p.setYCoordinate(ylevel,yOffSet);
                p.setXCoordinate((cnt+1)*dist,xOffSet);
            }
            Vector next = new Vector();
            for(int cnt=0;cnt<current.size();cnt++) {
                String p = (String) current.elementAt(cnt); 
                    Vector sons = getSons(p);
                    for(int soncnt = 0;soncnt<sons.size();soncnt++) {
                        String name = (String) sons.elementAt(soncnt);
                        if(!used.contains(name)) {
                            used.put(name,name);
                            next.add(name);
                        }
                    }
                }
            ylevel = ylevel + 50;
            current = next;
        }
        ylevel = 100;
        int xlevel = maxWidth + ylevel;
        for(int n=0;n<Nodes.size();n++) {
            ReactMechanismGraphNode p = (ReactMechanismGraphNode) Nodes.elementAt(n);
            if(!used.contains(p.getNameTag())) {
                System.out.println("Not used: " + p.nodeName);
                p.setXCoordinate(xlevel,xOffSet);
                p.setYCoordinate(ylevel,yOffSet);
                ylevel += 50;
            }
        }
    }
}
