/*
 * $Id: AttributeFile.java,v 1.1 2008/01/25 13:22:31 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package blurock.utilities;

import javax.swing.*;
import java.io.*;

public class AttributeFile extends PrintWriter  
{
    public AttributeFile(String filename, String description) throws FileNotFoundException 
    {
	super(new FileOutputStream(filename));
	println(filename + " ======================= " + description + " ======================= ");
	println("Attributes:");
    }
    
    public void printKeyWords(String name, String[] names)
    {
	println("%%% --------------------------------------------------");
	println("%%%   " + name + "  KeyWords");
	println("%%% --------------------------------------------------");
	for(int i=0;i<names.length;i++)
	    {
		println(names[i]);
	    }
	println("END");
    }

    public void printInteger(String name, int number)
    {
	println("%%% --------------------------------------------------");
	println("%%%   " + name + "  Integer");
	println("%%% --------------------------------------------------");
	println(number);
    }
    public void printReal(String name, double number)
    {
	println("%%% --------------------------------------------------");
	println("%%%   " + name + "  Real");
	println("%%% --------------------------------------------------");
	println(number);
    }
    public void printString(String name, String str)
    {
	println("%%% --------------------------------------------------");
	println("%%%   " + name + "  String");
	println("%%% --------------------------------------------------");
	println(str);
    }
    public void printObjectAsString(String name, String obj, String objType)
    {
	println("%%% --------------------------------------------------");
	println("%%%   " + name + " " + objType);
	println("%%% --------------------------------------------------");
	println(obj);
    }
    public void endFile()
    {
	println("END");
	close();
    }
}
