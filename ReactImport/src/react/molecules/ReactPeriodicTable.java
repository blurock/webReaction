/*
 * $Id: ReactPeriodicTable.java,v 1.1 2008/01/25 13:23:44 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.molecules;
import javax.swing.*;
import java.util.*;

public class ReactPeriodicTable
{
    String Row1 = new String(" H He ");
    String Row2 = new String("Li Be  B  C  N  O  F Ne ");
    String Row3 = new String("Na Mg Al Si  P  S Cl Ar ");
    String Row4 = new String(" K Ca Sc Ti  V Cr Mn Fe Co Ni Cu Zn Ga Ge As Se Br Kr ");
    String Row5 = new String("Rb Sr  Y Zr Nb Mo Tc Ru Rh Pd Ag Cd In Sn Sb Te  I Xe ");

    ReactPeriodicTable()
    {
    }

    public String AtomName(int atomicnumber)
    {
	String name = new String("XX");
	int b = 0;
	if(atomicnumber <= 2) {
	    b = atomicnumber * 3 - 3;
	    name = new String(Row1.substring(b,b+2));
	} else if(atomicnumber <= 10) {
	    b = (atomicnumber - 2) * 3 - 3;
	    name = new String(Row2.substring(b,b+2));
	} else if(atomicnumber <= 18) {
	    b = (atomicnumber - 10) * 3 - 3;
	    name = new String(Row3.substring(b,b+2));
	} else if(atomicnumber <= 36) {
	    b = (atomicnumber - 18) * 3 - 3;
	    name = new String(Row4.substring(b,b+2));
	} else if(atomicnumber <= 54) {
	    b = (atomicnumber - 36) * 3 - 3;
	    name = new String(Row5.substring(b,b+2));
	}
	if(name.startsWith(" "))
	    return name.substring(1);
	else
	    return name;
    }
}
