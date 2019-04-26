/*
 * $Id: ReactCorrespondenceSet.java,v 1.1 2008/01/25 13:23:56 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.reactions;
import react.molecules.*;
import link.*;
import javax.swing.*;
import java.util.*;

public class ReactCorrespondenceSet extends Vector
{

    public ReactCorrespondenceSet(int num)
    {
	super(num);
    }

    public void addCorrespondence(int index, ReactAtomCorrespondence corr)
    {
	add(index,corr);
    }
    ReactAtomCorrespondence getCorrespondence(int index)
    {
	return (ReactAtomCorrespondence) elementAt(index);
    }
    public ReactAtomCorrespondence getReactantCorrespondence(int mol, int atm)
    {
	ReactAtomCorrespondence corr = new ReactAtomCorrespondence();
	for(int i=0;i<size();i++)
	    {
		ReactAtomCorrespondence element = getCorrespondence(i);
		if(element.Molecule1 == mol && element.Atom1 == atm)
		    corr = element;
	    }
	return corr;
    }
    public ReactAtomCorrespondence getProductCorrespondence(int mol, int atm)
    {
	ReactAtomCorrespondence corr = new ReactAtomCorrespondence();
	for(int i=0;i<size();i++)
	    {
		ReactAtomCorrespondence element = getCorrespondence(i);
		if(element.Molecule2 == mol && element.Atom2 == atm)
		    corr = element;
	    }
	return corr;
    }
}
