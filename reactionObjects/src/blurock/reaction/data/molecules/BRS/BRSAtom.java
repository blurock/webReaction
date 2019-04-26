
package blurock.reaction.data.molecules.BRS;
import java.util.*;

import info.esblurock.reaction.data.gwt.client.data.molecules.ReactAtom;
import info.esblurock.reaction.data.gwt.client.data.molecules.ReactMolecule;

import java.io.IOException;

@SuppressWarnings("serial")
public class BRSAtom extends ReactAtom
{

    public BRSAtom()
    {
    }
    
    public void parse(byte[] data)
    {
                String atom = new String(data);
                StringTokenizer atomtokens = new StringTokenizer(atom,":");
	
		atomtokens.nextToken();
		String a = atomtokens.nextToken();
		String x = atomtokens.nextToken();
		String y = atomtokens.nextToken();
		String z = atomtokens.nextToken();
		String anum = atomtokens.nextToken();
	
                ID = a.replaceAll("\\D", "");
		X = Float.parseFloat(x.trim());
		Y = Float.parseFloat(y.trim());
		Z = Float.parseFloat(z.trim());
		AtomicNumber = Integer.parseInt(anum.trim());
    }
      

        
    // Deprecated below this line
    // _______________________________________________________________________________________________________________
    
     /**
     * @deprecated
     * @see parse(byte[])
     */   
    public void parse(String atom, ReactMolecule mol) throws IOException 
    {
                StringTokenizer atomtokens = new StringTokenizer(atom,":");
	
		atomtokens.nextToken();
		String a = atomtokens.nextToken();
		String x = atomtokens.nextToken();
		String y = atomtokens.nextToken();
		String z = atomtokens.nextToken();
		String anum = atomtokens.nextToken();
	
                ID = a.replaceAll("\\D", "");
		X = Float.parseFloat(x.trim());
		Y = Float.parseFloat(y.trim());
		Z = Float.parseFloat(z.trim());
		AtomicNumber = Integer.parseInt(anum.trim());
    }
}
