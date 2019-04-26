package blurock.reaction.reactions.BRS;

import blurock.reaction.common.SProperties;
import blurock.reaction.data.molecules.BRS.BRSMolecule;
import blurock.reaction.link.ReactLink;
import info.esblurock.reaction.data.gwt.client.data.ReactionLog;
import info.esblurock.reaction.data.gwt.client.data.reactions.ReactSubstructureSet;

@SuppressWarnings("serial")
public class BRSSubStructureSet extends ReactSubstructureSet {
    ReactLink command;
    String molcommand;
    private String REACTION_HOME =  SProperties.getProperty("reaction.home");  //SReaction.getHome();
	
    public void addSubstructure(int ignored, String id)
    {
        if (null == command) {
            ReactionLog.logError(" > ReactSubstructureSet.addSubstructure: command not set");
            }   
        command.setParameters( new String[] {id,REACTION_HOME} );
        if (!command.start()) 
            {
                ReactionLog.logError(command.getError());
                return;
            }
        
        BRSMolecule corr = new BRSMolecule(command,molcommand);
        String output = command.getResult();
        try
        {
            corr.parse(output.getBytes());
        } catch(java.text.ParseException e)
        {
            ReactionLog.logError(" > parseReactionConstants: "+ e.toString());
        }
	add(corr);
    }    


}
