
package info.esblurock.reaction.data.gwt.client.data.molecules;

import java.io.Serializable;

import info.esblurock.reaction.data.gwt.client.data.IParsableElement;
import info.esblurock.reaction.data.gwt.client.data.ReactionLog;

public abstract class ReactAtom implements IParsableElement, Serializable
{
	private static final long serialVersionUID = 1L;
	public float X,Y,Z;
    public int AtomicNumber;
    public int Info = 0;
    public String ID;
    
    
    public ReactAtom() {
	}

	public void print()
    {
		System.out.println("AtomicNumber:   : " + AtomicNumber);
		System.out.println("X,Y,Z:          : " + X + ", " + Y + ", " + Z);         
		ReactionLog.logInfo("AtomicNumber:   : " + AtomicNumber);
		ReactionLog.logInfo("X,Y,Z:          : " + X + ", " + Y + ", " + Z); 	
    }
    
    public String toString()
    {
		String name = ReactPeriodicTable.AtomName(AtomicNumber);
		if(Info == 4)
	    	name.concat(".");
        
		return "\""+name+"\"";
    }
    
    public void setData(IParsableElement el)
    {
        if (! (el instanceof ReactAtom) )
        {   
            ReactionLog.logError(" > Tried to parse an element of wrong type: " + el.getClass().getName() + " where " + this.getClass().getName()+ " was expected.");
            return;
        }
        
        ReactAtom e = (ReactAtom)el;
        X = e.X;
        Y = e.Y;
        Z = e.Z;
        AtomicNumber = e.AtomicNumber;
        Info = e.Info;
    }
    
    
    /**
     * @deprecated
     * @see toString()
     */
    public String asString(ReactPeriodicTable table)
    {
	String name = table.AtomName(AtomicNumber);
	if(Info == 4)
	    name.concat(".");
        
	return name;
    }

}
