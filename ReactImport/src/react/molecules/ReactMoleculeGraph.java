/*
 * $Id: ReactMoleculeGraph.java,v 1.1 2008/01/25 13:23:45 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.molecules;

import java.awt.Color;
import java.awt.event.MouseEvent;
import javax.swing.*;
import java.util.*;
import graph.*;

public class ReactMoleculeGraph extends DrawGraph
{
    boolean nodeHighLighted = false;
    DrawGraphNode highLightedNode;
    int TypeTable[];
    int TypeTableSize = 0;
    Color TypeColorTable[];
    Color ColorSet[] = {Color.cyan,Color.magenta,Color.red,Color.pink};
    int ColorCount = 0;
    ReactPeriodicTable pTable = new ReactPeriodicTable();
    ReactMolecule Molecule;
    public ReactMoleculeGraph(ReactMolecule molecule, int xSize, int ySize)
    {
	xMax = xSize;
	yMax = ySize;
        Molecule = molecule;
        TypeTable = new int[molecule.NumberOfAtoms];
        TypeColorTable = new Color[molecule.NumberOfAtoms];
	for(int i=0; i < molecule.NumberOfAtoms ; i++)
	    {
		ReactAtom a = (ReactAtom) molecule.Atoms.elementAt(i);
                addTypeToTable(a);
		String atomname = getAtomName(molecule,i);
		DrawGraphNode node = new DrawGraphNode(atomname);
		addNode(node);
		node.setXCoordinate((int) a.X,xOffSet);
		node.setYCoordinate((int) a.Y,yOffSet);
		node.setNodeType(200);
                node.setNodeColor(findNodeColor(a));
		node.fixed = false;
	    }
        normalNodePositions();
	for(int j=0; j < molecule.NumberOfBonds ; j++)
	    {
		ReactBond b = (ReactBond) molecule.Bonds.elementAt(j);
		String atomname1 = getAtomName(molecule,b.I);
		String atomname2 = getAtomName(molecule,b.J);
		
		DrawGraphBond bond = addBond(atomname1,atomname2);
		bond.setType(bond.GRAPH_BOND_TYPE_DIRECT);
                if(b.BondType.startsWith("Single")) {
                    bond.setBondColor(Color.black);
                    bond.BondType = 1;
                } else if(b.BondType.startsWith("Double")) {
                    bond.setBondColor(Color.red);
                    bond.BondType = 2;
                } else if(b.BondType.startsWith("Triple")) {
                    bond.setBondColor(Color.blue);
                    bond.BondType = 3;
                } else
                    bond.setBondColor(Color.orange);
	    }
	
    }
    void addTypeToTable(ReactAtom a) {
        int value = determineAtomValue(a);
        try {
            int position = findValueInTable(value);
        } catch(NullPointerException io) {
            TypeTable[TypeTableSize] = value;
            TypeColorTable[TypeTableSize] = assignColor(value);
            TypeTableSize++;
        }
    }
    int determineAtomValue(ReactAtom a) {
        return a.AtomicNumber;
    }
    int findValueInTable(int value) throws NullPointerException {
        int count = 0;
        while(count < TypeTableSize) {
            if(TypeTable[count] == value) return count;
            count++;
        }
        throw new NullPointerException("Type not found");
    }
    Color assignColor(int value) {
        Color color = Color.white;
        if(value == 6)
            color = Color.black;
        else if(value == 1)
            color = Color.yellow;
        else if(value == 320)
            color = Color.orange;
        else if(value == 8)
            color = Color.blue;
        else {
            color = ColorSet[ColorCount];
            ColorCount++;
        }
        return color;
    }
    public Color findNodeColor(ReactAtom a) {
        Color color = Color.white;
        int value = determineAtomValue(a);
        try {
            int position = findValueInTable(value);
            color = TypeColorTable[position];
        } catch(NullPointerException io) {}
        return color;
    }
    public String getAtomName(ReactMolecule molecule, int i)
    {
	return Integer.toString(i);
    }
    public int toggleHighLightedAtom(MouseEvent e)
    {
	int x = e.getX();
	int y = e.getY();
	int nindex = getNodeFromCoordinates(x,y);
	return toggleHighLightedAtom(nindex);
    }
    public void setHighLightedAtom(int nindex)
    {
	System.out.println("setHighLightedAtom: " + nindex);
	DrawGraphNode pick = (DrawGraphNode) Nodes.elementAt(nindex);
	Color def = getDefaultNodeColor();
	nodeHighLighted = true;
	pick.setNodeColor(Color.green);
	highLightedNode = pick;
    }
    public void resetHighLightedAtom(int nindex)
    {
	System.out.println("resetHighLightedAtom: " + nindex);
	DrawGraphNode pick = (DrawGraphNode) Nodes.elementAt(nindex);
	ReactAtom a = (ReactAtom) Molecule.Atoms.elementAt(nindex);
	Color def = findNodeColor(a);
	nodeHighLighted = false;
	pick.setNodeColor(def);
    }
    public int toggleHighLightedAtom(int nindex)
    {
	System.out.println("toggleHighLightedAtom: " + nindex);
	
	DrawGraphNode pick = (DrawGraphNode) Nodes.elementAt(nindex);
	ReactAtom a = (ReactAtom) Molecule.Atoms.elementAt(nindex);
	Color def = findNodeColor(a);
	if(nodeHighLighted)
	    {
		if(highLightedNode == pick)
		    {
			nodeHighLighted = false;
			highLightedNode.setNodeColor(def);
			System.out.println("Turned off");
			nindex = -1;
		    }
		else
		    {
			highLightedNode.setNodeColor(def);
			System.out.println("Old Shut off");
		    }
	    }
	if(highLightedNode != pick)
	    {
		System.out.println("New Turned on");
		pick.setNodeColor(Color.red);
		nodeHighLighted = true;
		highLightedNode = pick;
	    }
	return nindex;
    }
}
