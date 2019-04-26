/*
 * SClient.java
 *
 * Created on April 26, 2004, 10:50 AM
 */

package blurock.reactions.common;

/**
 *
 * @author  moliate
 */
public class SClient extends SProperties
{   
	static
	{
		if (null == properties.getProperty("client.reaction.home")) 
					properties.setProperty("client.reaction.home", System.getProperty("user.home"));

	}
    
    public static String getClientHome()
    {
	    return SUserProperties.getProperty("user.reaction.home");
    }
	

    public static void setClientHome(String home)
    {
	    setProperty("client.reaction.home", home);
            SUserProperties.setProperty("user.reaction.home", home);
    }
    
    public static void selectHome()
    {
        javax.swing.JFileChooser chooser = new javax.swing.JFileChooser();
        chooser.setFileSelectionMode(javax.swing.JFileChooser.DIRECTORIES_ONLY);
        int returnVal = chooser.showOpenDialog(null);
        if(returnVal == javax.swing.JFileChooser.APPROVE_OPTION) {
           SClient.setClientHome(chooser.getSelectedFile().getPath());
           Log.println("Set new path: " + chooser.getSelectedFile().getPath());
        }

    }

}
