/*
 * $Id: ReactionPatternDraw.java,v 1.1 2008/01/25 13:23:56 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock
 * All Rights Reserved.
 */
package react.reactions;

import link.*;
import react.molecules.*;
import react.reactions.*;
import react.common.*;
import java.awt.Dimension;
import java.util.*;
import javax.swing.*;
import java.awt.event.*;

public class ReactionPatternDraw implements MouseListener {
    int maxPatterns;
    public Vector Reactants;
    public Vector Products;
    
    int totalBoxSizeX;
    int patternBoxSizeX;
    int totalBoxSizeY;
    int patternBoxSizeY;

    TopReactionMenu Top;
    ReactRxnPattern CurrentReaction;

    public ReactionPatternDraw(TopReactionMenu top, ReactRxnPattern rxnpat, JPanel panel) {
	Top = top;
	CurrentReaction = rxnpat;

	maxPatterns = Top.Defaults.maxMoleculesInReactions;
	Reactants = new Vector(maxPatterns);
	Products = new Vector(maxPatterns);

	//totalBoxSizeX = Top.Defaults.MenuWindowSizeX;
	//totalBoxSizeY = Top.Defaults.MenuWindowSizeY;
        totalBoxSizeX = 700;
        totalBoxSizeY = 700;
	patternBoxSizeX = (totalBoxSizeX)/maxPatterns;
	patternBoxSizeY = (totalBoxSizeY)/maxPatterns;
	setupPatterns(Top,rxnpat,panel);
    }
    void setupPatterns(TopReactionMenu top, ReactRxnPattern rxnpat,JPanel panel)
    {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	JPanel reactants = new JPanel();
	JPanel products = new JPanel();
        reactants.setLayout(new BoxLayout(reactants, BoxLayout.X_AXIS));
        products.setLayout(new BoxLayout(products, BoxLayout.X_AXIS));
	panel.add(reactants);
	panel.add(products);
	for(int ind=0; ind<maxPatterns;ind++)
	    {
                JPanel rp = new JPanel();
                JPanel pp = new JPanel();
                RxnPatternSubstructure r = new RxnPatternSubstructure(top,this,ind,
                                        patternBoxSizeX,patternBoxSizeY);
                if(rxnpat.Reactants.filled[ind]) {
                    Object obj = rxnpat.Reactants.Molecules.elementAt(ind);
                    r.subButton.CurrentMolecule = (ReactSubStructure) obj;
                    r.subButton.drawMolecule("Reactant");
                    r.Reactant = true;
		    r.subButton.drawMolecule.addMouseListener(r);
                    rp.add(r);
  		    reactants.add(rp);
                }
                RxnPatternSubstructure p = new RxnPatternSubstructure(top,this,ind,
                                        patternBoxSizeX,patternBoxSizeY);
                if(rxnpat.Products.filled[ind]) {
                    Object obj = rxnpat.Products.Molecules.elementAt(ind);
                    p.subButton.CurrentMolecule = (ReactSubStructure) obj;
                    p.subButton.drawMolecule("Product");
		    p.Reactant = false;
		    p.subButton.drawMolecule.addMouseListener(p);
                    pp.add(p);
		    products.add(pp);
                 }
		Reactants.add(ind,r);
		Products.add(ind,p);
	       panel.updateUI();
              
  	    }
       for(int i=0; i< CurrentReaction.Reactants.Molecules.size();i++) {
            ReactMolecule mol = (ReactMolecule) CurrentReaction.Reactants.Molecules.elementAt(i);
            System.out.println(mol.ID);
            mol.ID = i;
       }
       for(int i=0; i< CurrentReaction.Products.Molecules.size();i++) {
            ReactMolecule mol = (ReactMolecule) CurrentReaction.Products.Molecules.elementAt(i);
            System.out.println(mol.ID);
            mol.ID = i;
        }

    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void mousePressed(MouseEvent e) {
    }
    public void highLightMatchingAtoms(RxnPatternSubstructure sub,boolean isReactant,int id, int index)
    {
        System.out.println("highLightMatchingAtoms " + id + " " + index);
	ReactAtomCorrespondence corr = getCorrespondence(isReactant,id,index);
	resetAllHighLighted();
	sub.setHighLightedAtom(index);
        try {
	if(isReactant)
	    {
		RxnPatternSubstructure subsP = getProductSubStructureMenu(corr.Molecule2);
		subsP.setHighLightedAtom(corr.Atom2);
		subsP.redrawMolecule();
	    }
	else
	    {
		RxnPatternSubstructure subsR = getReactantSubStructureMenu(corr.Molecule1);
		subsR.setHighLightedAtom(corr.Atom1);
		subsR.redrawMolecule();
	    }
	sub.redrawMolecule();
        } catch(NullPointerException io) {
        }
    }
    public void resetAllHighLighted()
    {
	for(int iR = 0;iR < Reactants.size();iR++)
	    {
		RxnPatternSubstructure r = (RxnPatternSubstructure) Reactants.elementAt(iR);
                try {
                r.clearHighLightedAtoms();
		r.redrawMolecule();
                } catch(NullPointerException io) {}
	    }
	for(int iP = 0;iP < Products.size();iP++)
	    {
		RxnPatternSubstructure p = (RxnPatternSubstructure) Products.elementAt(iP);
		try {
                    p.clearHighLightedAtoms();
		    p.redrawMolecule();
	            } catch(NullPointerException io) {}
        }
    }
    public RxnPatternSubstructure getReactantSubStructureMenu(int molID) throws NullPointerException
    {
	int index = CurrentReaction.findReactantIndex(molID);
        if(index < 0) throw new NullPointerException("Reactant: " + molID + " not found");
	return (RxnPatternSubstructure) Reactants.elementAt(index);
    }
    public RxnPatternSubstructure getProductSubStructureMenu(int molID) throws NullPointerException
    {
	int index = CurrentReaction.findProductIndex(molID);
        if(index < 0) throw new NullPointerException("Product: " + molID + " not found");
	return (RxnPatternSubstructure) Products.elementAt(index);
    }
    public ReactAtomCorrespondence getCorrespondence(boolean reactant, int mol, int atm)
    {
	if(reactant)
	    return CurrentReaction.CorrSet.getReactantCorrespondence(mol,atm);
	else
	    return CurrentReaction.CorrSet.getProductCorrespondence(mol,atm);	    
    }
    
}
