/*
 * $Id: RadioButtonsAttributePanel.java,v 1.1 2008/01/25 13:24:33 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package utilities;

import link.*;
import java.awt.Dimension;
import java.util.StringTokenizer;
import javax.swing.*;
import java.io.*;
import java.awt.event.*;

public class RadioButtonsAttributePanel extends JPanel implements ActionListener
{  
  int visibleObject = -1;
  JPanel[] panelSet;
  String[] nameSet;

    JPanel radioButtonPanel;
    JPanel componentPanel;
    ButtonGroup radioButtonGroup = new ButtonGroup();

    public RadioButtonsAttributePanel(JPanel[] panels, String[] names, Dimension panelsize, Dimension listsize)
    {
	radioButtonPanel = new JPanel();
	radioButtonPanel.setPreferredSize(listsize);

	componentPanel = new JPanel();
	componentPanel.setPreferredSize(panelsize);
	JScrollPane scrollPanel = new JScrollPane(radioButtonPanel);
	scrollPanel.setPreferredSize(listsize);

	add(scrollPanel);
	add(componentPanel);

	panelSet = panels;
	nameSet = names;
	for(int i=0; i<panels.length;i++)
	    {
		addButton(nameSet[i],panelSet[i]);
	    }
    }

    public RadioButtonsAttributePanel() {
	radioButtonPanel = new JPanel();
	componentPanel = new JPanel();
	JScrollPane scrollPanel = new JScrollPane(radioButtonPanel);
	add(scrollPanel);
	add(componentPanel);
    }
    
    public void addButton(String name, JPanel panel)
    {
	
	JRadioButton b = new JRadioButton(name);
            b.setPreferredSize(new Dimension(50,300));
	  b.setActionCommand(name);
	  radioButtonGroup.add(b);
	  b.addActionListener(this);
	  radioButtonPanel.add(b);
	  panel.setVisible(false);
	  componentPanel.add(panel);
    }
    public int getComponent(String attr)
  {
      int num = nameSet.length;
      int count = 0;
      int index = -1;
      while(index == -1 && count < num)
	  {
	      if(nameSet[count] == attr)
		  index = count;
	      count++;
      }
    return index;
  }
    public void resetVisible(int v)
    {
	if(visibleObject >= 0)
	    panelSet[visibleObject].setVisible(false);
	panelSet[v].setVisible(true);
	visibleObject = v;
	repaint();
	updateUI();
    }
    public void actionPerformed(ActionEvent e) {
      String attr = e.getActionCommand();
      int v = getComponent(attr);
      resetVisible(v);
    }

    public void setupPanel(java.lang.String[] names,JPanel[] panels) {
	for(int i=0; i<panels.length;i++)
	    {
		addButton(nameSet[i],panelSet[i]);
	    }
    }
    
}
