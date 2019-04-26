/*
 * $Id: ReactBond.java,v 1.1 2008/01/25 13:23:45 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.molecules;
import javax.swing.*;
import java.util.*;


public class ReactBond
{
    int I,J,Info;
    String BondType;

    public ReactBond()
    {
    }

    public String betweenBlanks(String str)
    {
	int count = 0;
	while(str.startsWith(" ",count))
	    count++;
	int count1 = count;
	while(!str.startsWith(" ",count1) && str.length() > count1)
	    count1++;
	return str.substring(count,count1);
    }

    public void parse(String str, ReactMolecule mol)
    {
	StringTokenizer bondtokens = new StringTokenizer(str,":");
	String preamble = bondtokens.nextToken();
	String btype    = bondtokens.nextToken();
	BondType = betweenBlanks(btype);
	String i        = bondtokens.nextToken();
	String ii = betweenBlanks(i);
	String j        = bondtokens.nextToken();
	String jj = betweenBlanks(j);
	String t        = bondtokens.nextToken();
	String tt = betweenBlanks(t);
	Integer iii = new Integer(ii);
	Integer jjj = new Integer(jj);
	Integer ttt = new Integer(tt);
	I = iii.intValue();
	J = jjj.intValue();
	Info = ttt.intValue();
    }
    public String asString()
    {
	return BondType + "(" + I + "," + J + ")";
    }
    public void print()
    {
	System.out.println(" : " + BondType + " : " + I + " : " + J + " : " + Info + " : ");
    }
}
