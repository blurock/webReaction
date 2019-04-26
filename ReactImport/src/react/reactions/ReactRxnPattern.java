/*
 * $Id: ReactRxnPattern.java,v 1.1 2008/01/25 13:23:56 blurock Exp $
 *
 * Copyright (c) 2000, Edward S. Blurock.
 * All Rights Reserved.
 */
package react.reactions;
import utilities.StringNumber;
import react.common.*;
import react.molecules.*;
import link.*;
import javax.swing.*;
import java.util.*;

public class ReactRxnPattern
{
    String Name;
    public ReactSubStructureSet Reactants;
    public ReactSubStructureSet Products;
    public ReactReactionConstants Forward;
    public ReactReactionConstants Reverse;
    public ReactCorrespondenceSet CorrSet;

    TopReactionMenu Top;

    public ReactRxnPattern(TopReactionMenu top)
    {
	Top = top;
	Reactants = new ReactSubStructureSet(Top.Defaults.maxMoleculesInReactions,Top.tLink,Top);
	Products  = new ReactSubStructureSet(Top.Defaults.maxMoleculesInReactions,Top.tLink,Top);
    }
    public void getReactionInfo(String rxnname)
    {
	String command = new String(Top.Scripts.printReactionPattern.getValue() + " " + rxnname);
	System.out.println("Command: '" + command + "'");
	String rxnstring = Top.tLink.singleCommand(command);
	Top.tLink.stop();
	parseReaction(rxnstring);
    }

  void parseReaction(String rxnpatinp)
  {
      System.out.println("parseReaction: reaction pattern");
      try {
	  StringTokenizer rxnpatblocks = new StringTokenizer(rxnpatinp,"~");
	  //String preamble0      = rxnpatblocks.nextToken();
	  String preamble1      = rxnpatblocks.nextToken();
	  String topinfo       = rxnpatblocks.nextToken();
          try {
                parseTopInfo(topinfo);
          } catch(NullPointerException io) {
                System.out.println("Exception caught in reading top info");
          }
	  System.out.println("Parsing Rxn Pattern: Blocks");
	  while(rxnpatblocks.hasMoreTokens())
	    {
		String property = rxnpatblocks.nextToken();
		System.out.println("Property '" + property +"'");
		if(property.startsWith("Reaction Constants",1))
		    {
			System.out.println("Reaction Constants");
			parseReactionConstants(property);
		    }
		else if(property.startsWith("Total Set of Atom Correspondences",1))
		    {
			parseCorrespondences(property);
		    }
	    }
	  
	  
      } catch(java.util.NoSuchElementException ios) {
	  System.out.println("Get Reaction Pattern failed:");
	  System.out.println(rxnpatinp);
      }
  }
    void parseTopInfo(String topinfo) throws NullPointerException
    {
	System.out.println("parseTopInfo");
	StringTokenizer info = new StringTokenizer(topinfo,"\n");
	String top = info.nextToken();
	Name = info.nextToken();
	System.out.println("Name of Reaction Pattern: " + Name);
	String idline = info.nextToken();
	String reactants = info.nextToken();
	String products = info.nextToken();

	int ids = idline.indexOf(new String(":Rxn "))+5;
	int ide = idline.indexOf(new String(" ("),ids);
	StringNumber idS = new StringNumber(idline.substring(ids,ide));
	int id = idS.intValue();
	System.out.println("Reaction ID: " + id);
	
	StringTokenizer reactantstokens = new StringTokenizer(reactants,":");
	StringTokenizer productstokens = new StringTokenizer(products,":");
	
	reactantstokens.nextToken();
	reactantstokens.nextToken();
	String rs = reactantstokens.nextToken();
	System.out.println("Reactants: " + rs);
	productstokens.nextToken();
	productstokens.nextToken();
	String ps = productstokens.nextToken();
	System.out.println("Products: " + ps);
	
	StringTokenizer rsTokens = new StringTokenizer(rs," ");
	StringTokenizer psTokens = new StringTokenizer(ps," ");
	int rID = 0;
	while(rsTokens.hasMoreTokens())
	    {
		String mid = rsTokens.nextToken();
		System.out.println("Reactant Molecule: " + rID + " '" + mid + "'");
		Reactants.addSubstructure(rID,mid);
		rID = rID + 1;
	    }
	int pID = 0;
	while(psTokens.hasMoreTokens())
	    {
		String mid = psTokens.nextToken();
		System.out.println("Product Molecule: " + pID + "'" + mid + "'");
		Products.addSubstructure(pID,mid);
		pID = pID + 1;
	    }
    }
    void parseReactionConstants(String constants)
	{
	    StringTokenizer constTokens = new StringTokenizer(constants,"\n");
            if(constTokens.countTokens() >= 3) {
	        String firstS = constTokens.nextToken();
	        String titleS = constTokens.nextToken();
	        String typeS = constTokens.nextToken();

	        if(typeS.startsWith("Forward",13))
	            {
                        Forward = new ReactReactionConstants(typeS.substring(21));
	            }
	        if(typeS.startsWith("Reverse",13))
	            {
                        Reverse = new ReactReactionConstants(typeS.substring(21));
	            }
            } else {
                if(constants.substring(27).startsWith("Forward"))
                    Forward = new ReactReactionConstants(0.0,0.0,0.0,1.0,true);
                else
                    Reverse = new ReactReactionConstants(0.0,0.0,0.0,1.0,false);
            }
	}
    void parseCorrespondences(String corrs)
	{
	    StringTokenizer constTokens = new StringTokenizer(corrs,"\n");

	    String firstS = constTokens.nextToken();
	    String titleS = constTokens.nextToken();
	    String numS = constTokens.nextToken();
	    
	    StringNumber matchedS = new StringNumber(numS.substring(7,9));
	    int nummatched = matchedS.intValue();
	    CorrSet = new ReactCorrespondenceSet(nummatched);
	    for(int i=0; i< nummatched;i++)
		{
		    String matchpairS = constTokens.nextToken();
		    ReactAtomCorrespondence acorr = new ReactAtomCorrespondence(matchpairS);
		    CorrSet.addCorrespondence(i,acorr);
		}						     
	}
    public int findReactantIndex(int molID)
    {
	return Reactants.findMoleculeIndex(molID);
    }
    public int findProductIndex(int molID)
    {
	return Products.findMoleculeIndex(molID);
    }
  }
