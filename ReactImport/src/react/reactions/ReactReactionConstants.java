/*
 * $Id: ReactReactionConstants.java,v 1.1 2008/01/25 13:23:57 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.reactions;
import utilities.StringNumber;
import link.*;
import javax.swing.*;
import java.util.*;

public class ReactReactionConstants
{
    public double aFactor;
    public double nFactor;
    public double eFactor;
    public double multiplicity;

    public ReactReactionConstants(double a, double n, double e, double m, boolean f)
    {
	aFactor = a;
	nFactor = n;
	eFactor = e;
	multiplicity = m;
    }

    public ReactReactionConstants(String asLine)
    {
	StringTokenizer cTokens = new StringTokenizer(asLine," ");
	StringNumber aS = new StringNumber(cTokens.nextToken());
	StringNumber nS = new StringNumber(cTokens.nextToken());
	StringNumber eS = new StringNumber(cTokens.nextToken());
	StringNumber mS = new StringNumber(cTokens.nextToken());
	
	aFactor = aS.doubleValue();
	nFactor = nS.doubleValue();
	eFactor = eS.doubleValue();
	multiplicity = mS.doubleValue();
    }
}
