/*
 * $Id: RxnPatternSubstructure.java,v 1.1 2008/01/25 13:23:56 blurock Exp $
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
import java.util.StringTokenizer;
import javax.swing.*;
import java.awt.event.*;
import graph.*;
import java.awt.Graphics;
import java.awt.Color;
/**
 * Reaction Pattern Substructure Information Menu <p>
 *
 * @author Edward S. Blurock
 * @version 1.0
 */
 
public class RxnPatternSubstructure extends JPanel implements MouseListener,MouseMotionListener
{
    public MoleculeListButton subButton;
    String relaxS = new String("Adjust");
    JPanel substructure;
    JPanel menu;
    JLabel molName;
    public int boxSizeX;
    public int boxSizeY;
    ReactionPatternDraw RxnPatternDraw;
    TopReactionMenu Top;

    int highLighted[] = new int[200];
    public int Index;

    public boolean Reactant = true;
    RxnPatternSubstructure(TopReactionMenu top, ReactionPatternDraw draw, int index, int xsize, int ysize)
    {
	RxnPatternDraw = draw;
	Top = top;

	boxSizeX = xsize*90/100;
	boxSizeY = ysize*90/100;
	Index = index;
	Dimension psize = new Dimension(boxSizeX,boxSizeY);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
	menu = new JPanel();
	substructure = new JPanel();
	molName = new JLabel("     ");

	subButton = new MoleculeListButton(new String("Substructure"), 
					   top.Common.SubstructureList,
					   top,
					   psize);
        
        add(menu);
	add(subButton.getObjectPanel());
        subButton.getObjectPanel().setPreferredSize(psize);
	add(molName);

        menu.setLayout(new BoxLayout(menu, BoxLayout.X_AXIS));
        substructure.setLayout(new BoxLayout(substructure, BoxLayout.X_AXIS));

	//JButton relax = new JButton(relaxS);
	menu.add(subButton);
	//menu.add(relax);
        /*
        getObjectPanel().addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {  
		JButton source = (JButton) e.getSource();
		if(source.getText() == relaxS) {
		    for(int i=0;i<20;i++)
			subButton.drawMolecule.relax();
		}
            
            }});
         */
	
	//getEmptyMolecule();
    }
    public void mousePressed(MouseEvent e) {        
        if((e.getModifiers() &  InputEvent.BUTTON2_MASK) == InputEvent.BUTTON2_MASK) {
            subButton.drawMolecule.relax();
        } else if((e.getModifiers() &  InputEvent.BUTTON3_MASK) == InputEvent.BUTTON3_MASK) {
                try {
                    int index = getNodeFromCoordinates(e.getX(), e.getY());
                    ReactAtom a = subButton.CurrentMolecule.getAtom(index);
                    a.print();
                    DrawGraph draw = subButton.drawMolecule;
                    Graphics g = draw.getGraphics();
                    g.setColor(Color.black);
                    //NodeFont = g.getFontMetrics();
                    //int w = draw.NodeFont.stringWidth(NameTag) + 10;
                    //int h = draw.NodeFont.getHeight();
                    int x = e.getX();
                    int y = e.getY();
                    String s = "Atomic Number = " + Integer.toString(a.AtomicNumber);
                    g.drawString(s,x,y);
                    
                    } catch(NullPointerException io) {
                }
        } else {    
        if(e.isShiftDown()) {
		int highlighted = subButton.drawMolecule.toggleHighLightedAtom(e);
		setHighLightedAtom(highlighted);
		subButton.drawMolecule.repaint();
	    } else if(e.isControlDown()) {
		int index = getNodeFromCoordinates(e.getX(), e.getY());
		RxnPatternDraw.highLightMatchingAtoms(this,Reactant,subButton.getCurrentMolecule().ID,index);
	    } else
	        subButton.drawMolecule.mousePressed(e);
        }
    }
    public void clearHighLightedAtoms() throws NullPointerException
    {
	for(int i=0;i<subButton.getCurrentMolecule().NumberOfAtoms;i++)
	    {
		if(highLighted[i] == 1)
		    subButton.drawMolecule.resetHighLightedAtom(i);
		highLighted[i] = 0;
	    }
    }
    public void setHighLightedAtom(int highlighted)  throws NullPointerException
    {
	clearHighLightedAtoms();
	if(highlighted >= 0)
	    {
		highLighted[highlighted] = 1;
		subButton.drawMolecule.setHighLightedAtom(highlighted);
	    }
    }
    public int getHighLightedAtom()
    {
	int nindex = -1;
	for(int i=0;i<subButton.getCurrentMolecule().NumberOfAtoms;i++)
	    if(highLighted[i] == 1)
		nindex = i;
	return nindex;
    }

    public int getNodeFromCoordinates(int x,int y)
    {
	int index = subButton.drawMolecule.getNodeFromCoordinates(x,y);
	return index;
    }
    public void mouseDragged(MouseEvent e) {
	if(e.getSource().getClass().isInstance(subButton.drawMolecule)) {
	    if(e.isShiftDown())
		subButton.drawMolecule.mouseDragged(e);
	}
    }
    public void mouseMoved(MouseEvent e) {
    }

    public void redrawMolecule()
    {
	subButton.drawMolecule.repaint();
	molName.setText(subButton.getCurrentMolecule().MoleculeName);
	repaint();
    }
    public void mouseReleased(MouseEvent e) {
    }
    public void mouseEntered(MouseEvent e) {
    }
    public void mouseExited(MouseEvent e) {
    }
    public void mouseClicked(MouseEvent e) {
    }
    public void actionPerformed(ActionEvent e) {
    }
}
