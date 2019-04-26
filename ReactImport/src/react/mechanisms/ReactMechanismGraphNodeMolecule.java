/*
 * ReactMechanismGraphNodeMolecule.java
 *
 * Created on January 18, 2002, 3:11 PM
 */

package react.mechanisms;
import react.molecules.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.JPanel;
/**
 *
 * @author  reaction
 * @version 
 */
public class ReactMechanismGraphNodeMolecule extends react.mechanisms.ReactMechanismGraphNode 
            implements MouseListener, MouseMotionListener {
    public ReactMolecule Molecule;
    ReactMoleculeGraph drawMolecule;
    /** Creates new ReactMechanismGraphNodeMolecule */
    public ReactMechanismGraphNodeMolecule(ReactMolecule mol) {
        Molecule = mol;
        nodeName = mol.MoleculeName;
    }
    public void drawNode(ReactMechanismGraph mechanism) {
        setNameTag("A" + Integer.toString(mechanism.molCount++));
        mechanism.addNode(this);
        setNodeColor(mechanism.molColor);
        setNodeType(200);
    }
        public JPanel objectAsPanel() {
            JPanel panel = new JPanel();
            drawMolecule= new ReactMoleculeGraph(Molecule,400,400);
	    drawMolecule.addMouseListener(this);
	    drawMolecule.addMouseMotionListener(this);
            drawMolecule.relax();
            drawMolecule.relax();
            drawMolecule.relax();
            panel.add(drawMolecule);
            return panel;
        }
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
	drawMolecule.mousePressed(e);
    }
    public void mouseReleased(MouseEvent e) {}
    public void mouseDragged(MouseEvent e) {
	drawMolecule.mouseDragged(e);
    }
    public void mouseMoved(MouseEvent e) {
    }
}
