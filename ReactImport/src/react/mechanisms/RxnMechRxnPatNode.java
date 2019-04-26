/*
 * RxnMechRxnPatNode.java
 *
 * Created on January 14, 2002, 2:55 PM
 */

package react.mechanisms;
import react.common.TopReactionMenu;
import javax.swing.*;

/**
 *
 * @author  reaction
 * @version 
 */
public class RxnMechRxnPatNode extends react.mechanisms.RxnMechNode {

    /** Creates new RxnMechRxnPatNode */
    public RxnMechRxnPatNode(TopReactionMenu top, String name, String title) {
        super(top,name,title);
    }

    public void showNode() {
        react.reactions.ReactRxnPattern CurrentRxnPat = 
                new react.reactions.ReactRxnPattern(Top);
	CurrentRxnPat.getReactionInfo(Name);
        JPanel panel = new JPanel();
        react.reactions.ReactionPatternDraw rdraw = 
                new react.reactions.ReactionPatternDraw(Top,CurrentRxnPat,panel);
        setUpFrame(panel);
    }
 
}
