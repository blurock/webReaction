/*
 * SGeneral.java
 *
 * Created on February 24, 2004, 12:35 PM
 */

package blurock.reactions.common;

/**
 * static (global) class that contains color information and manipulation members
 * @author  moliate
 */
public class SGeneral extends SProperties
{   
  
    static
    {
        if (null == properties.getProperty("graphics.useantialias")) 
              setAntialias(true);
    }
    
    public static boolean getAntialias()
    {
        return  ( Boolean.valueOf(getProperty("graphics.useantialias")) ).booleanValue() ;
    }
	
    public static boolean setAntialias(boolean value)
    {
        return ( Boolean.valueOf( (String)setProperty("graphics.useantialias", Boolean.toString(value))) ).booleanValue();
    }

}
