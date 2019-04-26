/*
 * CatalogAlgorithms.java
 *
 * Created on March 4, 2001, 8:08 AM
 */

package blurock.opandalgs.register;
    import react.common.*;
    import utilities.*;
    import javax.swing.*;
    import javax.swing.tree.*;
    import java.awt.Dimension;
/**
 *
 * @author  reaction
 * @version 
 */
public class CatalogAlgorithms extends blurock.core.TopTreeInfoPanel {

    private FrameSet algorithmFrames;
    
    private TopReactionMenu Top;
    
    /** Creates new CatalogAlgorithms */
    public CatalogAlgorithms(TopReactionMenu top) {
        super("Algorithms",
                new FrameSet(),
                top.SystemInfo.frameTextDirectory.getValue(),
                new Dimension(top.Defaults.panelSizeX.getValue(),
                                      top.Defaults.panelSizeY.getValue()));
        Top = top;
        initializeTreeHierarchy(topNode);
        updateUI();
    }

    public void initializeTreeHierarchy(DefaultMutableTreeNode topnode) {
        addSimpleNode("Base",topnode);
    }
}
