/*
 * SProperties.java
 *
 * Created on February 24, 2004, 12:35 PM
 */

package blurock.reactions.common;
import java.util.*;
import java.beans.*;
/**
 * static (global) class that is the base of the specialized property classes
 * @author  moliate
 */
public class SProperties
{   
	protected static Properties properties = new Properties();
	protected static Vector listeners = new Vector();
        
        static {
            load();
        }
	
	public static void addPropertyChangeListener(PropertyChangeListener pcl)
	{
		listeners.add(pcl);
	}
	
	public static void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		listeners.remove(pcl);
	}
		
	public static String setProperty(String name, String value)
	{
		String old = (String)properties.setProperty(name, value);
			
		PropertyChangeEvent pce = new PropertyChangeEvent(new Object(), name, old, value);
                for (Enumeration e = listeners.elements() ; e.hasMoreElements() ;) 
                {
                    ((PropertyChangeListener)e.nextElement()).propertyChange(pce);
                }
     	
		return old;
	}
	
	public static String getProperty(String name)
	{
		return properties.getProperty(name);
	}
        
        public static void save()
        {
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
        
        public static void load()
        {
            try
            {
                java.io.FileInputStream fis = new java.io.FileInputStream("terminus.properties");
                properties.load(fis);
            }
            catch(Exception e)
            {
	        // -- try to load on classpath (if running as server /WEB-INF/lib would be root)
	        // loadOnClassPath();
            }
        }
	
	public static void loadOnClassPath()
	{
	    try
            {
	        java.io.InputStream in = new Object().getClass().getResourceAsStream("/terminus.properties");
                properties.load(in);
            }
            catch(java.io.IOException e)
            {
                //Interact.report("Failed to load settings.", e);
            }
	}

}

