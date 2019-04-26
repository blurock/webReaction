/*
 * $Id: StringNumber.java,v 1.1 2008/01/25 13:24:33 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package utilities;

import java.lang.Integer;
import javax.swing.*;
import java.util.*;

public class StringNumber
{
    Integer num;
    Float fnum;
    Double dnum;

    String intSnoblanks;

    public StringNumber(String intS)
    {
	intSnoblanks = betweenBlanks(intS);
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

    public int intValue()
    {
	num = new Integer(intSnoblanks);
	return num.intValue();
    }
    public float floatValue()
    {
	fnum = new Float(intSnoblanks);
	return fnum.floatValue();
    }
    public double doubleValue()
    {
	dnum = new Double(intSnoblanks);
	return dnum.doubleValue();
    }
}
