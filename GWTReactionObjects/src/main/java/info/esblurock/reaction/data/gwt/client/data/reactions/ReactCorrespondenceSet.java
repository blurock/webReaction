/*
 * $Id: ReactCorrespondenceSet.java,v 1.1.1.1 2008/11/13 13:33:14 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package info.esblurock.reaction.data.gwt.client.data.reactions;

import java.io.Serializable;
import java.util.*;

public class ReactCorrespondenceSet extends ArrayList<ReactAtomCorrespondence> implements Cloneable, Serializable
{
	private static final long serialVersionUID = 1L;

	public ReactCorrespondenceSet() {
    }

    public void addCorrespondence(ReactAtomCorrespondence corr) {
	add(corr);
    }
        
    public ReactAtomCorrespondence getReactantCorrespondence(int mol_id, int atm) {

	for(ReactAtomCorrespondence element: this) {
		if(element.Molecule1_id == mol_id && element.Atom1 == atm)
		    return element;
	    }
        return null;
    }
    
    public ReactAtomCorrespondence getProductCorrespondence(int mol_id, int atm)
    {
    	for(ReactAtomCorrespondence element: this) {
		if(element.Molecule2_id == mol_id && element.Atom2 == atm)
		    return element;
	    }
        return null;
    }

    public String toString() {
    	StringBuilder build = new StringBuilder();
    	for(ReactAtomCorrespondence corr : this) {
    		build.append(corr.toString());
    	}
    	return build.toString();
    }
}
