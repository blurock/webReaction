/*
 * RxnMechMolecule.java
 *
 * Created on January 14, 2002, 3:02 PM
 */

package react.mechanisms;
import react.common.TopReactionMenu;
import javax.swing.*;
import react.molecules.*;
import java.awt.event.*;
/**
 *
 * @author  reaction
 * @version 
 */
public class RxnMechMolecule extends react.mechanisms.RxnMechNode implements MouseListener, MouseMotionListener{
    ReactMolecule Molecule;
    ReactMoleculeGraph drawMolecule;
    /** Creates new RxnMechMolecule */
    public RxnMechMolecule(TopReactionMenu top, ReactMolecule mol, String title) {
        super(top,mol.MoleculeName,title);
        Molecule = mol;
    }

    public void showNode() {
        JPanel panel = new JPanel();
        drawMolecule= new ReactMoleculeGraph(Molecule,400,400);
	drawMolecule.addMouseListener(this);
	drawMolecule.addMouseMotionListener(this);
        drawMolecule.relax();
        drawMolecule.relax();
        drawMolecule.relax();
        panel.add(drawMolecule);
        setUpFrame(panel);        
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
