/*
 * ReactMechanismGraphNodeRxnClass.java
 *
 * Created on January 18, 2002, 3:12 PM
 */

package react.mechanisms;
import java.awt.Color;
import java.util.Vector;
import java.util.Hashtable;
import react.common.*;
import javax.swing.JPanel;
import java.awt.event.*;
import react.reactions.*;
/**
 *
 * @author  reaction
 * @version 
 */
public class ReactMechanismGraphNodeRxnClass extends react.mechanisms.ReactMechanismGraphNode {
    TopReactionMenu Top;
    Vector Reactions;
    Hashtable Products;
    Hashtable Reactants;
    int Index;
    public ReactMechanismRxnClass RxnClass;
    /** Creates new ReactMechanismGraphNodeRxnClass */
    public ReactMechanismGraphNodeRxnClass(TopReactionMenu top, int id, ReactMechanismRxnClass rxnclass) {
        super();
        Index = id;
        Reactions = new Vector();
        Products = new Hashtable();
        Reactants = new Hashtable();
        RxnClass = rxnclass;
        nodeName = rxnclass.className;
        Top = top;
    }
    public void addRxn(ReactMechanismGraphNodeRxn rxn) {
        Reactions.add(rxn);
    }
    public void addProduct(ReactMechanismGraphNodeMolecule mol) {
        Products.put(mol.Molecule.MoleculeName,mol);
    }
    public void addReactant(ReactMechanismGraphNodeMolecule mol) {
        Reactants.put(mol.Molecule.MoleculeName,mol);
    }
    public void drawNode(ReactMechanismGraph mechanism) {
        setNameTag("R" + Integer.toString(Index));
        setNodeColor(mechanism.rxnClassColor);
        mechanism.addNode(this);
        setNodeType(200);
    }
        public JPanel objectAsPanel() {
            JPanel panel = new JPanel();
            ReactRxnPattern rxnpat = new ReactRxnPattern(Top);
            rxnpat.getReactionInfo(nodeName);
            ReactionPatternDraw drawRxnPat = new ReactionPatternDraw(Top,rxnpat,panel);
            return panel;
        }
}
