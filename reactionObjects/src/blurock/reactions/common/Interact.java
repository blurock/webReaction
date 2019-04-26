/*
 * Interact.java
 *
 * Created on March 1, 2004, 1:15 PM
 */

package blurock.reactions.common;

import blurock.reaction.GUI.ErrorDialog;

/**
 * Used for interaction with the user. Mostly used to report errors. 
 * 
 * static (global) class
 * @author  moliate
 */
public class Interact 
{   
    protected static boolean reportMessages = true;
    protected static javax.swing.JLabel info = null;
    protected static String info_text = "";
    protected static javax.swing.ImageIcon[] icons = {};
    
    public static void setInfoLabel(javax.swing.JLabel infoLabel)
    {
        info = infoLabel;
        info_text = infoLabel.getText();
    }
    
    /** First icon is default. */    
    public static void setInfoIcons(javax.swing.ImageIcon[] s_icons)
    {
        icons = s_icons;
    }
    
    public static void infoIcon(int icon)
    {
        try
        {
            info.setIcon(icons[icon]);
        }
        catch(Exception e)
        {
            // ignore Array of of bounds
        }
    }
    
    public static void info(String text)
    {
        if (null != info)
        {
            info.setText(text); 
			/*
			java.awt.Graphics g = info.getGraphics();
			
			if (g != null) 
			{
				java.awt.Size s = info.getSize();

				info.paint(g);
			}
			else 
				info.repaint();
				*/
			info.paintImmediately(info.getVisibleRect());
        }
    }
    
    public static void info()
    {
        infoIcon(0);
        info(info_text);
    }  

    public static void setReporting(boolean report)
    {
        reportMessages = report;
    }
    
    public static void report(String message)
    {
        if (!reportMessages) return;
        
        ErrorDialog dlg = new ErrorDialog(null, true);
        dlg.setMessage(message);
        dlg.setVisible(true);
    }
 
    public static void report(String message, String details)
    {
        if (!reportMessages) return;
        
        ErrorDialog dlg = new ErrorDialog(null, true);
        dlg.setMessage(message);
        dlg.setDetails(details);
        dlg.setVisible(true);
    }
     
    public static void report(String message, Throwable throwable)
    {
        if (!reportMessages) return;
        
        ErrorDialog dlg = new ErrorDialog(null, true);
        dlg.setMessage(message);
        StackTraceElement[] el = throwable.getStackTrace();
        String details = "";
        for (int i = 0; i < el.length; i++)
        {
            details += el[i].toString() + "\n";
        }
        dlg.setDetails(details);
        dlg.setVisible(true);
    }
    
    public static void report(Throwable throwable)
    {
        if (!reportMessages) return;
        
        String message = throwable.getLocalizedMessage();
        if (null == message)
            message = "An unknown exception has occurred.";
        
        report(message, throwable);
    }
}
