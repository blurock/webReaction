/*
 * $Id: ReactAtom.java,v 1.1 2008/01/25 13:23:45 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.molecules;
import javax.swing.*;
import java.util.*;
import java.io.IOException;
public class ReactAtom
{
    public float X,Y,Z;
    public int AtomicNumber;
    public int Info = 0;

    public ReactAtom()
    {
    }
    public void parse(String atom, ReactMolecule mol) throws IOException 
    {
	StringTokenizer atomtokens = new StringTokenizer(atom,":");
	
	String preamble = atomtokens.nextToken();
	String a = atomtokens.nextToken();
	String x = atomtokens.nextToken();
	String y = atomtokens.nextToken();
	String z = atomtokens.nextToken();
	String anum = atomtokens.nextToken();
	
	Float xx = new Float(x);
	Float yy = new Float(y);
	Float zz = new Float(z);
	
	int count = 0;
	while(anum.startsWith(" ",count))
	    count++;
	int count1 = count;
	while(!anum.startsWith(" ",count1))
	    count1++;

	Integer aa = new Integer(anum.substring(count,count1));

	X = xx.floatValue();
	Y = yy.floatValue();
	Z = zz.floatValue();
	AtomicNumber = aa.intValue();
    }
    public String asString(ReactPeriodicTable table)
    {
	String name = table.AtomName(AtomicNumber);
	if(Info == 4)
	    name.concat(".");
	return name;
    }
    public void print()
    {
	System.out.println("AtomicNumber:   : " + AtomicNumber);
	System.out.println("X,Y,Z:          : " + X + ", " + Y + ", " + Z); 
	
    }
}
