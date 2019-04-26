/*
 * SUserProperties.java
 *
 * Created on March 7, 2005, 9:14 AM
 */

package blurock.reactions.common;
import java.io.File;

/**
 *
 * @author  reaction
 */
public class SUserProperties extends SProperties {
    
    /** Creates a new instance of SUserProperties */
    public SUserProperties() {
    }
	static
	{
	if (null == properties.getProperty("user.reaction.home")) 
    				properties.setProperty("user.reaction.home",    "/webReaction");
	if (null == properties.getProperty("user.name")) 
    				properties.setProperty("user.name", 		"reaction");
	if (null == properties.getProperty("user.password")) 
    				properties.setProperty("user.password", 	"laguna");
	if (null == properties.getProperty("user.signin")) 
    				properties.setProperty("user.signin", 		"reaction");
        } 
        public static void save() {
            try
            {
                java.io.FileOutputStream fos = new java.io.FileOutputStream("terminus.properties");
                properties.store(fos, "Terminus properties");
            }
            catch(java.io.IOException e)
            {
                Interact.report("Failed to save settings.", e);
            }
        
        }
        
        public static void loadUserProperties()
        {
                File userdata= new File(System.getProperty("user.home"),".webReaction");
                System.out.println("User Derfined Properties" + userdata.toString());
            try {
                java.io.FileInputStream fis = new java.io.FileInputStream(userdata.toString());
               properties.load(fis);
                System.out.println("User Name: " + properties.getProperty("server.username"));
                System.out.println("User Home: " + properties.getProperty("user.reaction.home"));
                SClient.setClientHome(properties.getProperty("user.reaction.home"));
                SServer.setPassword(properties.getProperty("server.property"));
                SServer.setUsername(properties.getProperty("server.username"));
             }
            catch(Exception e)
            {
                Log.println("User property file not definted: " + userdata);
	        // -- try to load on classpath (if running as server /WEB-INF/lib would be root)
	        // loadOnClassPath();
            }
        }
    public static String getUsername()
    {
	    return getProperty("server.username");
	}

    public static void setUsername(String user)
    {
	    setProperty("server.username", user);
	}

	public static String getPassword()
    {
	    return getProperty("server.password");
	}

    public static void setPassword(String pass)
    {
	    setProperty("server.password", pass);
	}
}
