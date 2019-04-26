/*
 * $Id: ReactionPatternButtonList.java,v 1.1 2008/01/25 13:23:57 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.reactions;

import react.common.*;
import react.utilities.*;
import utilities.TokenizeOutput;
import link.*;
import javax.swing.*;
import java.util.*;
import java.awt.Dimension;
import java.awt.event.*;

public class ReactionPatternButtonList extends ListButtonPanel implements MouseListener, MouseMotionListener
{
    
    ReactRxnPattern CurrentRxnPat;
    public ReactionPatternDraw drawReaction;
    boolean currentReactionSet = false;

    public ReactionPatternButtonList() {
    }
    
    /**
       * Operation of click of the button.
       * @return true if successful.
       */
    public boolean doOperation(String rxn) {
	drawReaction(rxn);
	return true;
    }
    void drawReaction(String rxn) {
	JPanel objp = getObjectPanel();
	Dimension panelsize = objp.getPreferredSize();
	CurrentRxnPat = new ReactRxnPattern(Top);
	CurrentRxnPat.getReactionInfo(rxn);
        objp.add(new JLabel("Reaction Pattern"));
        JPanel rPanel = new JPanel();
	objp.removeAll();
        objp.add(rPanel);
        System.out.println("Begin: --------------- Draw Reaction Pattern ----------");
	drawReaction = new ReactionPatternDraw(Top,CurrentRxnPat,rPanel);
        System.out.println("End:   --------------- Draw Reaction Pattern ----------");
	currentReactionSet = true;
	//drawReaction.addMouseListener(this);
	//drawReaction.addMouseMotionListener(this);
	repaint();
	updateUI();
    }
    public ReactRxnPattern getCurrentRxnPat() {return CurrentRxnPat;}
    
    /**
       * Set the value of CurrentRxnPat.
       * @param v  Value to assign to CurrentRxnPat.
       */
    public void setCurrentRxnPat(ReactRxnPattern  v) {this.CurrentRxnPat = v;}
    
    public void mouseClicked(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mousePressed(MouseEvent e) {
	//drawMolecule.mousePressed(e);
    }
    public void mouseDragged(MouseEvent e) {
	//drawMolecule.mouseDragged(e);
    }
    public void mouseMoved(MouseEvent e) {
    }
}
