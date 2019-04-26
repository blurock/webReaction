/*
 * $Id: ReactMoleculeSet.java,v 1.1 2008/01/25 13:23:45 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.molecules;
import link.*;
import javax.swing.*;
import java.util.*;
import react.common.TopReactionMenu;

public class ReactMoleculeSet extends Hashtable
{
    String retrieveCommand;
    public int maxSubstructures;
    int currentMax = 0;
    public boolean filled[];
    String MoleculeNames[];
    public Vector Molecules;
    protected ReactLink tLink;

    public ReactMoleculeSet()
    {
    }
    public ReactMoleculeSet(int num, ReactLink link,TopReactionMenu top)
    {
	tLink = link;
	retrieveCommand = top.Scripts.printMolecule.getValue();
	maxSubstructures = num;
	Initialize();
    }
    public void Initialize()
    {
	Molecules = new Vector(maxSubstructures);
	MoleculeNames = new String[maxSubstructures];
	filled = new boolean[maxSubstructures];
	for(int i=0;i<maxSubstructures;i++)
	    filled[i] = false;
    }
    public Vector isomerList(ReactMolecule mol) {
        Vector isomers = null;
        if(containsKey(mol.HashCode))
            isomers = (Vector) get(mol.HashCode);
        return isomers;
    }
    public void establishIsomers() {
        for(int i=0;i<Molecules.size();i++) {
            
        }
    }    
    ReactMolecule getMoleculeInfo(String mol)
    {
	ReactMolecule molecule = new ReactMolecule(mol,tLink,retrieveCommand);
	molecule.getMoleculeInfo();
	return molecule;
    }
    public void addSubstructure(int index, String molname)
    {
	System.out.println("addSubstructure: begin " + index);
	if(index < maxSubstructures && index >= 0)
	    {
		MoleculeNames[index] = molname;
		ReactMolecule molecule = getMoleculeInfo(molname);
		System.out.println("addSubstructure  at " + index + "  " + molname);
		Molecules.add(index,molecule);
		filled[index] = true;
	    }
    }
    public int findMoleculeIndex(int molID)
    {
	int index = -1;
	int count = 0;
	while(index == -1 && count < Molecules.size())
	    {
		ReactMolecule mol = (ReactMolecule) Molecules.elementAt(count);
		if(mol.ID  == molID)
			index = count;
		count++;
	    }
	return index;
    }
}
