/*
 * $Id: FrameListButton.java,v 1.1 2008/01/25 13:23:56 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.reactions;

import utilities.TokenizeOutput;
import react.common.*;
import react.utilities.*;
import link.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.awt.Dimension;

public class FrameListButton extends JPanel
{
    public TopReactionMenu Top;
    String buttonTitle;
    StandardListPanel listPanel;
    ReactList Source;
    String selectedElement;
    
    JPanel objectPanel;
    
    public FrameListButton(String title, ReactList source, TopReactionMenu top, Dimension psize) {
	super();
	objectPanel = new JPanel(); 

	setupList(title,source,top,psize);
	}
        
        public FrameListButton() {
            super();
            objectPanel = new JPanel(); 
        }
        
        public void setupList(java.lang.String title,ReactList source,TopReactionMenu top,Dimension psize) {
	Top = top;
	Source = source;
	buttonTitle = title;
	objectPanel.setPreferredSize(psize);
	listPanel = new StandardListPanel(buttonTitle,Source.getNameList());
	JButton button = new JButton(buttonTitle);
        button.setBorder(new javax.swing.border.BevelBorder(0));
	button.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
		    System.out.println("FrameListButton: mouse clicked");
		    setSelected();}
		public void mouseEntered(MouseEvent e) {}
		public void mouseExited(MouseEvent e) {}
		public void mousePressed(MouseEvent e) {}
		public void mouseReleased(MouseEvent e) {}});
	add(button);
        listPanel.updateUI();
        updateUI();
    }
    void setSelected() {
	System.out.println("set Selected");
	StandardListDialog dialog = new StandardListDialog(Source.getNameList());
	System.out.println("created dialog");
	dialog.setSize(Top.Defaults.dialogSizeX.getValue(),
                Top.Defaults.dialogSizeY.getValue());
	dialog.show();
	System.out.println("came back: " + dialog.getReturnStatus());
	if(dialog.getReturnStatus() == dialog.RET_OK) {
	    System.out.println("OK was Pressed");
	    String[] selected = dialog.listPanel.selectedItemsToString(true);
	    selectedElement = selected[0];
	}
	System.out.println("prepare to do operation");
	doOperation(selectedElement);
	System.out.println("did operation");
    }
    /**
       * Operation of click of the button.
       * @return true if successful.
       */
    public boolean doOperation(String mol) {
	return true;
    }
    /**
       * Get the value of objectPanel.
       * @return Value of objectPanel.
       */
    public JPanel getObjectPanel() {return objectPanel;}
    
    /**
       * Set the value of objectPanel.
       * @param v  Value to assign to objectPanel.
       */
    public void setObjectPanel(JPanel  v) {this.objectPanel = v;}
    
    /**
       * Get the value of buttonTitle.
       * @return Value of buttonTitle.
       */
    public String getButtonTitle() {return buttonTitle;}
    
    /**
       * Set the value of buttonTitle.
       * @param v  Value to assign to buttonTitle.
       */
    public void setButtonTitle(String  v) {this.buttonTitle = v;}
    
    /**
       * Get the value of commandString.
       * @return Value of commandString.
       */
    /**
       * Get the value of selectedElement.
       * @return Value of selectedElement.
       */
    public String getSelectedElement() {return selectedElement;}
    
    /**
       * Set the value of selectedElement.
       * @param v  Value to assign to selectedElement.
       */
    public void setSelectedElement(String  v) {this.selectedElement = v;}
    
}
