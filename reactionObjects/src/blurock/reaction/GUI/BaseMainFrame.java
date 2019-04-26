/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package blurock.reaction.GUI;

import javax.swing.JFrame;

/**
 *
 * @author blurock
 */
public class BaseMainFrame extends JFrame {
    public CMLFrame cmlFrame;
    public BaseMainFrame() {
        cmlFrame = new CMLFrame();
    }
    
    
    public  void startWait() {
        System.out.println("==== startWait");
    }

    public  void stopWait() {
        System.out.println("==== stopWait");
    }

}
