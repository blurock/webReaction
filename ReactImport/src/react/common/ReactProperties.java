/*
 * ReactProperties.java
 *
 * Created on April 1, 2005, 2:29 PM
 */

package react.common;
import java.io.File;
import java.util.Properties;
import java.io.IOException;

/**
 *
 * @author  reaction
 */
public class ReactProperties {
    	protected static Properties properties = new Properties();
        protected static File propertyFile;
    /** Creates a new instance of ReactProperties */
        static
        {
            start();
        }
    public static void start() {
        String homedir = System.getProperty("user.home");
        propertyFile = new File(homedir,"reaction.properties");
        load();
    }
    	public static String setProperty(String name, String value)
	{
		String old = (String)properties.setProperty(name, value);     	
		return old;
	}
	
	public static String getProperty(String name)
	{
		return properties.getProperty(name);
	}
        
        public static void save()
        {
            try {
                java.io.FileOutputStream fos = new java.io.FileOutputStream(propertyFile.toString());
                properties.store(fos, "Reaction properties");
            } catch(IOException e) {
                System.out.println("Failed to save settings." + e);
            }
        
        }
        
        public static void load()
        {
            try {
                System.out.println("Read properties from: " + propertyFile.toString());
                java.io.FileInputStream fis = new java.io.FileInputStream(propertyFile.toString());
                properties.load(fis);
            }
            catch(Exception e) {
                properties.setProperty("react.home","./REACT");
                properties.setProperty("analysis.home","./AnalysisStable");
                properties.setProperty("reaction.home","./Reaction");
            }
        }

}
