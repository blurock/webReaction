/*
 * SServer.java
 *
 * Created on March 1, 2004, 2:41 PM
 */

package blurock.reactions.common;
import java.util.*;
/**
 *
 * @author  moliate
 */
public class SServer extends SProperties 
{
	static
	{
    	if (null == properties.getProperty("server.host")) 
    				properties.setProperty("server.host", 		"blurock.forbrf.lth.se");
    	if (null == properties.getProperty("server.protocol")) 
    				properties.setProperty("server.protocol", 	"http");
    	if (null == properties.getProperty("server.axispath")) 	
    				properties.setProperty("server.axispath", 	"/axis/servlet/AxisServlet");
    	if (null == properties.getProperty("server.port")) 	
    				properties.setProperty("server.port", 		"8080");
    	if (null == properties.getProperty("server.service")) 	
    				properties.setProperty("server.service", 	"Reaction");
    	if (null == properties.getProperty("server.username")) 	
    				properties.setProperty("server.username", 	"trial");
    	if (null == properties.getProperty("server.password")) 	
    				properties.setProperty("server.password", 	"laguna");
    	if (null == properties.getProperty("server.database")) 	
    				properties.setProperty("server.database",       "RemoteDatabase");

        }
    public static String serverDirParens(String str) {
            StringTokenizer tok = new StringTokenizer(str,"\\");
            StringBuffer buf = new StringBuffer();
            //Log.println("Number of slashes:  " + tok.countTokens());
            while(tok.countTokens() > 1) {
                String next = tok.nextToken();
                //Log.println("Token: " + next);
                //Log.println("EndToken:");
                buf.append(next);
                buf.append("/");
            }
            if(tok.countTokens() == 1) {
                String last = tok.nextToken();
                //Log.println("Last Token: " + last);
                //Log.println("Last EndToken:");
                buf.append(last);
            }
            return buf.toString();
    }
    public static String getHost()
    {
	    return getProperty("server.host");
	}
	
    public static void setHost(String host)
    {
	    setProperty("server.host", host);
	}

    public static String getProtocol()
    {
	    return getProperty("server.protocol");
	}
	
    public static void setProtocol(String protocol)
    {
	    setProperty("server.protocol", protocol);
	}
		
    public static String getAxisPath()
    {
	    return getProperty("server.axispath");
	}
	
    public static void setAxisPath(String axispath)
    {
	    setProperty("server.axispath", axispath);
	}	
	
    public static String getPort()
    {
	    return getProperty("server.port");
	}
	
    public static void setPort(String port)
    {
	    setProperty("server.port", port);
	}	
    public static String getService()
    {
	    return getProperty("server.service");
	}
	
    public static void setService(String service)
    {
	    setProperty("server.service", service);
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
					    
    public static String getURL()
    {
        StringBuffer sb = new StringBuffer();
        if (properties.getProperty("server.protocol").equals("http"))
        {
            sb.append("http://");
        }
        sb.append(properties.getProperty("server.host"));
        sb.append(":" + properties.getProperty("server.port"));
        if (!properties.getProperty("server.axispath").startsWith("/"))
            sb.append("/");
        sb.append(properties.getProperty("server.axispath"));
        
        return sb.toString();
    }
    
}
