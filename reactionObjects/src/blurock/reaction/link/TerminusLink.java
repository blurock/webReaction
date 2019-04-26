// ===================================================================================================================================
// 
//    Author: Henrik Springfors (moliate@yahoo.se)
//    Reaction Project
//       Copyright 2003
//          .  .                          .  .
// ===================================================================================================================================

/*
 * ITerminusLink.java
 *
 * Created on January 30, 2004, 9:45 AM
 */

package blurock.reaction.link;

///import org.apache.axis.client.Call;
///import org.apache.axis.client.Service;
///import org.apache.axis.encoding.XMLType;
import blurock.reactions.common.Interact;
import blurock.reactions.common.Log;
import blurock.reactions.common.SServer;
import java.util.Properties;
import javax.xml.namespace.QName;
/**
 *
 * @author  moliate
 */
public class TerminusLink implements ILink {
    
    /** Creates a new instance of ITerminusLink */
    public TerminusLink() {
      
    }
    
    protected QName setService() {
        return null;
    /*
        System.out.println("setService");
        return new QName(SServer.getService(), method);
     */
    }
    public boolean start() {
           /*  
        try{
       
          
            Interact.infoIcon(1);
            String endpointURL = SServer.getURL(); 
            Interact.info("4 connecting..");
            Log.println( "Setting endpoint URL: " + endpointURL);
            Interact.info("5 connecting..");
            call.setTargetEndpointAddress( new java.net.URL(endpointURL) );
            Interact.info("6 connecting..");




            call.setProperty(Call.USERNAME_PROPERTY, SServer.getUsername());
            call.setProperty(Call.PASSWORD_PROPERTY, SServer.getPassword());
	    call.setTimeout(new Integer(60*240*1000));
            call.setOperationName( setService()); 
            Interact.info("7 connecting..");
            Log.println( "Using operation: " + new QName(SServer.getService(), method) );
            Interact.info("8 connecting..");
            call.setReturnType( org.apache.axis.encoding.XMLType.XSD_STRING );
            Interact.info("connecting..");
           
            Log.print( "Creating call to: " + method + " [ ");
            
            for (int i = 0; i < parameters.length; i++) {
                if(parameters[i].toString().length() < 200)
                    Log.print(parameters[i] + " ");
                else
                    Log.print("parameter length: " + parameters[i].toString().length());
                
                QName type = org.apache.axis.encoding.XMLType.XSD_STRING ;
                
                if (parameters[i] instanceof Byte)
                    type = org.apache.axis.encoding.XMLType.XSD_BYTE;                
                if (parameters[i] instanceof Short)
                    type = org.apache.axis.encoding.XMLType.XSD_SHORT;                
                if (parameters[i] instanceof Integer)
                    type = org.apache.axis.encoding.XMLType.XSD_INT;               
                if (parameters[i] instanceof java.math.BigInteger)
                    type = org.apache.axis.encoding.XMLType.XSD_INTEGER;                  
                if (parameters[i] instanceof Float)
                    type = org.apache.axis.encoding.XMLType.XSD_FLOAT;   
                if (parameters[i] instanceof Boolean)
                    type = org.apache.axis.encoding.XMLType.XSD_BOOLEAN;  
                if (parameters[i] instanceof java.math.BigDecimal)
                    type = org.apache.axis.encoding.XMLType.XSD_DECIMAL;   
                if (parameters[i] instanceof java.util.Calendar)
                    type = org.apache.axis.encoding.XMLType.XSD_DATETIME; 
                if (parameters[i] instanceof java.math.BigDecimal)
                    type = org.apache.axis.encoding.XMLType.XSD_DECIMAL;  
                
                call.addParameter( "arg" +i, type, javax.xml.rpc.ParameterMode.IN);
               
            }
            
            Log.println("]");
           
            Log.println( "Invoking call" );
            Interact.info("Invoking call..");
            result = (String) call.invoke( parameters );
            boolean noError = true;
            if(result.indexOf("ERROR") > 0) {
                noError = false;
            }
            Interact.infoIcon(0);
            Interact.info("ready.");
            if(result != null) {
                if(result.length() < 200)
                    Log.println(result, Log.ALL);
                else
                    Log.println("Output Length: " + result.length());
            }
                //Log.println(result, Log.ALL);
            else
                Log.println( "Result null" );
            Log.println( "ready" );
            return noError;
        } 
        catch(java.rmi.RemoteException e)
        {
            Log.println("Failed to call method: " + e.toString(), Log.ERROR);
            Interact.infoIcon(3);
            // 401 - Error: Unauthorized Access?
            if (-1 == e.toString().indexOf("401")) 
                Interact.report("Could not connect to server", e);
            else
                Interact.report("Wrong username/password. Please try again.", e);
            error = e.toString();  
            Interact.info("could not connect to server.");
            return false;
        }
        catch(java.net.MalformedURLException e)
        {
            Log.println("Bad URL: " + e.toString(), Log.ERROR);
            Interact.infoIcon(3);
            Interact.report("The URL to the server was not accepted", e);
            error = e.toString();  
            Interact.info("malformed url.");
            return false;
        }
        catch(javax.xml.rpc.ServiceException e)
        {
            Log.println("Could not create service: " + e.toString(), Log.ERROR);
            Interact.infoIcon(3);
            Interact.report("Could not create service.", e);
            error = e.toString();  
            Interact.info("could not create service.");
            return false;
        }
           * */
          return false;
    } 
    public boolean stop() {
        return false;
    }
    
    public void setCommand(String command)
    {method = command;}
    
   
    public void setParameters(Object[] parameters)
    {this.parameters=parameters;}

    
    public String setProperty(String property, String value)
    {
        Object r = properties.setProperty(property, value);
        if (null==r)
            return "";
        
        return (String)r;
    }
    
    public String getResult()
    {return result;}
    
    public String getError()  
    {return error;}
    
    protected String method="";
    protected String result="";
    protected String error="";
    protected Object[] parameters = {};
    protected Properties properties = new Properties();

   }
