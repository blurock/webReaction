/*
 * $Id: ReactAtomCorrespondence.java,v 1.1 2008/01/25 13:23:56 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.reactions;
import utilities.StringNumber;
import react.molecules.*;
import link.*;
import javax.swing.*;
import java.util.*;

public class ReactAtomCorrespondence
{
    public int Molecule1;
    public int Atom1;
    public int Molecule2;
    public int Atom2;

    public ReactAtomCorrespondence()
    {
    }
    public ReactAtomCorrespondence(String matchpairS)
    {
        System.out.println(matchpairS);
	StringNumber mol1 = new StringNumber(matchpairS.substring(14,29));
	StringNumber atm1 = new StringNumber(matchpairS.substring(30,34));
	StringNumber mol2 = new StringNumber(matchpairS.substring(38,54));
	StringNumber atm2 = new StringNumber(matchpairS.substring(55,59));

	Molecule1 = mol1.intValue();
	Atom1 = atm1.intValue();
	Molecule2 = mol2.intValue();
	Atom2 = atm2.intValue();
    }
}
