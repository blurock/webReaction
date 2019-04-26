/*
 * MenuList.java
 *
 * Created on February 12, 2001, 10:38 PM
 */

package blurock.utilities;
import react.common.*;
import link.ReactLink;
import utilities.TokenizeOutput;
import react.utilities.ReactList;

/**
 *
 * @author  reaction
 * @version 
 */
public class MenuList extends ReactList {

    /** Creates new MenuList */
    protected String textChosen = new String("TEXTCHOICE");
    protected String endTextChosen = new String("ENDTEXTCHOICE");
    protected boolean menuList;
    public boolean singleCommand = true;
    /*
     */
    public MenuList(String title, String command, 
                    TopReactionMenu top, boolean update) {
	Top = top;
	commandString = command;
        menuList = false;
	if(update) 
            updateList();
        else {
            numNames = 0;
        }
    }
    
    public void updateList() {
	numNames = 0;
        menuList = false;
	TokenizeOutput tokens = new TokenizeOutput(Top.reactLink, singleCommand);
	tokens.startCommand(commandString);
        maxNames = tokens.countTokens();
        boolean notdone = true;
	nameList = new String[maxNames];
	idList = new String[maxNames];
	String next = tokens.getNextToken();
	while(notdone && numNames < maxNames) {
            if(next.startsWith(endTextChosen))
                    notdone = false;
            if(next.startsWith(textChosen)){
                menuList = true;
                next = tokens.getNextToken();
                next = tokens.getNextToken();
	        next = tokens.getNextToken();
            }
            if(notdone && menuList)
	           isolateAndAddElement(next);
	    next = tokens.getNextToken();
	}
        if(singleCommand)
	    tokens.endCommand();
    }
    protected void isolateAndAddElement(String strLine) {
	String id = Integer.toString(numNames);
        addElement(id,strLine);
    }
}
