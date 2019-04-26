/*
 * SingleFlow.java
 *
 * Created on October 24, 2002, 5:19 PM
 */

package react.mechanisms.flow;
import utilities.LabeledBarGraph;
import javax.swing.JPanel;
/**
 *
 * @author  reaction
 */
public class SingleFlow {
    
    public String parentSpecies = null;
    
    public String sonSpecies = null;
    
    public double absoluteFlow = 0.0;
    
    public double relativeFlow = 0.0;
    
    /** Creates a new instance of SingleFlow */
    public SingleFlow(String p, String s, double a, double r) {
        parentSpecies = p;
        sonSpecies = s;
        absoluteFlow = a;
        relativeFlow = r;
    }
    
    public JPanel objectAsPanel() {
        LabeledBarGraph bar = new LabeledBarGraph();
        bar.set(sonSpecies,relativeFlow);
        return bar;
    }
    public String toString() {
        return new String(parentSpecies + " -> " + sonSpecies + ": " + relativeFlow);
    }
}
