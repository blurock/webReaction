/*
 * $Id: ClassFile.java,v 1.1 2008/01/25 13:22:31 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package blurock.utilities;

import javax.swing.*;
import java.io.*;

public class ClassFile extends PrintWriter  
{
    boolean newObjectClasses = true;
    public ClassFile(String filename, String description) throws FileNotFoundException 
    {
	super(new FileOutputStream(filename));
	println("%%% " + filename + " ======================= " + description + " ======================= ");
	println("ObjectClasses:");
    }
    
    public void endOfObjectClasses()
    {
	newObjectClasses = false;
	println("END");
	println("ClassNamePairs:");
	println("%% -----------------------------------------------------");
    }
    public void addClassTypeAsString(String baseclass, String newname, String description, String spec)
    {
	println("DataType: " + baseclass + " " + description);
	println(newname);
	println(spec);
    }
    public void printAttributeClassPair(String Attribute, String ClassName)
    {
	if(newObjectClasses)
	    endOfObjectClasses();
	println(Attribute + " " + ClassName);
    }
    public void endFile()
    {
	println("END");
	println("%% ----------------------------------------------------");
	println("ObjectClasses:");
	println("END");
	println("%% ----------------------------------------------------");
	println("ClassNamePairs:");
	println("END");
	println("%% ----------------------------------------------------");
	close();
    }
}
