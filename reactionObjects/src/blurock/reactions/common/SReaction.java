
/*
 * SServer.java
 *
 * Created on March 1, 2004, 2:41 PM
 */

package blurock.reactions.common;
/**
 *
 * @author  moliate
 */
public class SReaction extends SProperties 
{
	static
	{
	if (null == properties.getProperty("services.home")) 
    				properties.setProperty("services.home", 		"/home/reaction/cvs/java/webservices");
	if (null == properties.getProperty("reaction.home")) 
    				properties.setProperty("reaction.home", 		"/opt/ReactUsers");
    	if (null == properties.getProperty("reaction.scripts.home")) 
    				properties.setProperty("reaction.scripts.home", 	"/programs/scripts");
    	if (null == properties.getProperty("reaction.inputs.home")) 
    				properties.setProperty("reaction.inputs.home", 	"/programs/inputs");
    	if (null == properties.getProperty("reaction.mechs.home")) 
    				properties.setProperty("reaction.mechs.home", 		"/data/mechanisms");
    	if (null == properties.getProperty("reaction.mechs.suffix")) 
    				properties.setProperty("reaction.mechs.suffix", 	".mech");
    	if (null == properties.getProperty("reaction.lsr.home")) 
    				properties.setProperty("reaction.lsr.home", 		"/data/mechs/submechanisms");
    	if (null == properties.getProperty("reaction.lsr.suffix")) 
    				properties.setProperty("reaction.lsr.suffix", 		".lsr");
        if (null == properties.getProperty("reaction.generation.home")) 
    				properties.setProperty("reaction.generation.home", 	"/data/mechanisms/generated");
        if (null == properties.getProperty("reaction.data.home")) 
    				properties.setProperty("reaction.data.home", 		"/data");  
        if (null == properties.getProperty("reaction.tmp.home")) 
    				properties.setProperty("reaction.tmp.home", 		"/tmp"); 
        if (null == properties.getProperty("reaction.command.home")) 
    				properties.setProperty("reaction.command.home", 	"/command"); 
        if (null == properties.getProperty("reaction.bin.home")) 
    				properties.setProperty("reaction.bin.home",             "/bin"); 
        if (null == properties.getProperty("reaction.stat-inf.home")) 
    				properties.setProperty("reaction.stat-inf.home", 	"/data/stat-inf.dat"); 
        if (null == properties.getProperty("reaction.react.exe")) 
    				properties.setProperty("reaction.react.exe",            "chemdb"); 
	}
	
        public static String verifyPath(String home)
        {
            String fs = System.getProperty("file.separator");
            if ( home.endsWith(fs) )
                home = home.substring(0, home.length() - 1);
            if ( !home.startsWith(fs) )
                home = fs + home;
            home.replaceAll(fs+fs, "");        
	    return home;
        }
    public static String getWebServicesHome() {
        return verifyPath(getProperty("services.home"));
    }
    public static String getHome() {
	return verifyPath(getProperty("reaction.home"));
    }
	
    public static void setHome(String value) {
      	setProperty("reaction.home", value);
    }
	
    public static String getScriptsHome() {
        return verifyPath(getProperty("reaction.scripts.home"));
    }
	
    public static void setScriptsHome(String value)
    {
        setProperty("reaction.scripts.home", value);
    }

    public static String getMechsHome()
    {
      return verifyPath(getProperty("reaction.mechs.home"));
    }
	
    public static void setMechsHome(String value)
    {
	    setProperty("reaction.mechs.home", value);
    }   
	
    public static String getMechsSuffix()
    {
	    return getProperty("reaction.mechs.suffix");
    }
	
    public static void setMechsSuffix(String value)
    {
	    setProperty("reaction.mechs.suffix", value);
    }  

    public static String getLsrHome()
    {
        return verifyPath(getProperty("reaction.lsr.home"));
	}
	
    public static void setLsrHome(String value)
    {
	    setProperty("reaction.lsr.home", value);
	}   
	
	public static String getLsrSuffix()
    {
	    return getProperty("reaction.lsr.suffix");
	}
	
    public static void setLsrSuffix(String value)
    {
	    setProperty("reaction.lsr.suffix", value);
    } 	
    
    public static String getGenerationHome()
    {
        return verifyPath(getProperty("reaction.generation.home"));
	}
	
    public static void setGenerationHome(String value)
    {
	    setProperty("reaction.generation.home", value);
    }   
    
     public static String getTmpHome()
    {
        return verifyPath(getProperty("reaction.tmp.home"));
	}
	
    public static void setTmpHome(String value)
    {
	    setProperty("reaction.tmp.home", value);
    }   
    
    public static String getDataHome()
    {
        return verifyPath(getProperty("reaction.data.home"));
    }
	
    public static void setDataHome(String value)
    {
	    setProperty("reaction.data.home", value);
    }   
}
