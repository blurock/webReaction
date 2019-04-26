/*
 * $Id: MoleculeListButton.java,v 1.1 2008/01/25 13:23:44 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.molecules;

import react.common.*;
import react.utilities.*;
import utilities.TokenizeOutput;
import link.*;
import javax.swing.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.event.*;

/** The button which controls the printing of a molecule.
 * The <I>work</I> occurs in the framelist class.
 *
 */
public class MoleculeListButton extends FrameListButton implements MouseListener, MouseMotionListener
{

/** <I>drawMolecule</I>: The basic class which draws the
 * <I>CurrentMolecule</I> in the panel (with the procedure
 * <I>drawMolecule</I>
 */    
    public ReactMoleculeGraph drawMolecule;
/** The chosen molecule that is drawn
 */    
    public ReactMolecule CurrentMolecule;
/** true if a molecule has been chosen
 */    
    boolean currentMoleculeSet;

/** The standard constructor of the MoleculeButtonList
 * @param title The name on the button
 * @param source The source of the selection list when the
 * button is pressed
 * @param top The <I>Top</I> structure having all the <I>global</I>
 * information
 * @param psize The preferred dimension of the
 * molecule panel
 */    
    public MoleculeListButton(String title, ReactList source, TopReactionMenu top, Dimension psize) {
	super(title,source,top,psize);
    }
    
    public MoleculeListButton() {
    }
    
/** The overloaded procedure that is called when
 * the button is pressed.  Here, the <I>drawMolecule</I>
 * procedure is called to draw the selected molecule
 * in the panel
 * @param mol The name of the selected molecule
 * @return true if successful
 */    
    public boolean doOperation(String mol) {
	CurrentMolecule = new ReactMolecule(mol,Top.tLink,Top.Scripts.printMolecule.getValue());
	drawMolecule(mol);
	return true;
    }
    public void drawMolecule(String mol) {
	JPanel objp = getObjectPanel();
	Dimension panelsize = objp.getPreferredSize();
	CurrentMolecule.getMoleculeInfo();
	drawMolecule = new ReactMoleculeGraph(CurrentMolecule,panelsize.width,panelsize.height);
	objp.removeAll();
	currentMoleculeSet = true;
	objp.add(drawMolecule);
	drawMolecule.addMouseListener(this);
	drawMolecule.addMouseMotionListener(this);
	repaint();
	updateUI();
    }
/** Retrieves the current selected molecule
 * @return The molecule
 */    
    public ReactMoleculeGraph getDrawMolecule() {return drawMolecule;}
    
    /**
       * Set the value of drawMolecule.
       * @param v  Value to assign to drawMolecule.
       */
    public void setDrawMolecule(ReactMoleculeGraph  v) {this.drawMolecule = v;}
    
    /**
       * Get the value of CurrentMolecule.
       * @return Value of CurrentMolecule.
       */
    public ReactMolecule getCurrentMolecule() {return CurrentMolecule;}
    
    /**
       * Set the value of CurrentMolecule.
       * @param v  Value to assign to CurrentMolecule.
       */
    public void setCurrentMolecule(ReactMolecule  v) {this.CurrentMolecule = v;}
    
/** no current action
 * @param e The event
 */    
    public void mouseClicked(MouseEvent e) {}
/** NO current action
 * @param e The event
 */    
    public void mouseEntered(MouseEvent e) {}
/** No current action
 * @param e The event
 */    
    public void mouseExited(MouseEvent e) {}
/** No current action
 * @param e The event
 */    
    public void mouseReleased(MouseEvent e) {}
/** This calls the <I>drawMolecule</I> procedure to
 * process the <I>mousePressed</I> action
 * @param e The molecule panel
 */    
    public void mousePressed(MouseEvent e) {
	drawMolecule.mousePressed(e);
    }
/** No current action
 * @param e The event
 */    
    public void mouseDragged(MouseEvent e) {
	drawMolecule.mouseDragged(e);
    }
/** no current action
 * @param e The event
 */    
    public void mouseMoved(MouseEvent e) {
    }
    
}
