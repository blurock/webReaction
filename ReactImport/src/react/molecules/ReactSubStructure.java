/*
 * $Id: ReactSubStructure.java,v 1.1 2008/01/25 13:23:45 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.molecules;
import link.*;
import javax.swing.*;
import java.util.*;

public class ReactSubStructure extends ReactMolecule
{
    public ReactSubStructure() {
	super();
	//molcommand = new String("printsubstructure.sh ");
    }
    public ReactSubStructure(String name, ReactLink link,String command)
    {
	super(name,link,command);
	molcommand = new String(command);
    }
}
