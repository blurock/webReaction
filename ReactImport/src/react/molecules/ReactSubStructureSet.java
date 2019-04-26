/*
 * $Id: ReactSubStructureSet.java,v 1.1 2008/01/25 13:23:44 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.molecules;
import link.*;
import javax.swing.*;
import java.util.*;
import react.common.TopReactionMenu;

public class ReactSubStructureSet extends ReactMoleculeSet
{
    public ReactSubStructureSet(int num, ReactLink link, TopReactionMenu top)
    {
	tLink = link;
	maxSubstructures = num;
	retrieveCommand = top.Scripts.printSubstructure.getValue();
	Initialize();
    }
    public ReactMolecule getMoleculeInfo(String mol)
    {
	ReactSubStructure molecule = new ReactSubStructure(mol,tLink,retrieveCommand);
	molecule.getMoleculeInfo();
	return molecule;
    }
}
