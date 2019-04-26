/*
 * DecisionTreeAsJAVA.java
 *
 * Created on September 28, 2004, 6:29 PM
 */

package blurock.DecisionTree;
import java.util.Hashtable;
import java.util.Vector;
import blurock.DirectedTreeObjects.BaseDataDirectedTree;
import blurock.DecisionTree.BaseDataDecisionTree;
import graph.DrawGraph;
import graph.DrawGraphNode;
import blurock.core.ObjectNotFoundException;
import blurock.logic.predicates.BaseDataPredicate;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author  reaction
 */
public class DecisionTreeAsJAVA  {
    DecisionTreeProgramOut programOut;

    /** Creates a new instance of DecisionTreeAsJAVA */
    public DecisionTreeAsJAVA(DecisionTreeProgramOut prg) {
        programOut = prg;
    }
    public String toString() {
        return stringDetermineReactivePhase();
            }

        String stringDetermineReactivePhase() {        
        StringBuffer buf = new StringBuffer();
        buf.append("package blurock.DecisionTree;\n");
        buf.append("\n");
        buf.append("public class DetermineReactivePhase implements DetermineCondition {\n");
        buf.append("//------------------------------------------------------------------------------\n");
        buf.append("//     INPUT DATA\n");
        buf.append("//              1. Concentration                 [mol/m^3]\n");
        buf.append("//-----------------------------------------------------------------------------\n");
        buf.append("   public DetermineReactivePhase() {\n");
        buf.append("   }\n");
        buf.append("\n");
        buf.append("   public ConditionChoice determineCondition(ConditionParameters parameters) {\n");
        buf.append("      ConditionParametersAsDoubles parametersD = (ConditionParametersAsDoubles) parameters;\n");
        buf.append("      double[] Concentration = parametersD.Parameters;\n");
        buf.append("   i  int DetermineReactivePhase = 0;\n");
        buf.append("   \n");
        String prefix = new String("    ");
        String treeS = addSons(programOut.DecisionTree.Tree.rootNode,prefix,false);
        buf.append(treeS);
        buf.append("   ConditionChoiceAsInteger ans = new ConditionChoiceAsInteger(DetermineReactivePhase);\n");

        buf.append("   return ans;\n");
        buf.append("   }\n");

        buf.append("}\n");
        buf.append("");
        buf.append("");
        return buf.toString();
    }
    String addSons(String parent, String prefix, boolean addelse) {
        StringBuffer buf = new StringBuffer();
        BaseDataDirectedTree tree = programOut.DecisionTree.Tree;
        DrawGraph g = tree.Graph;
            int index = g.getNode(parent);
            DrawGraphNode node = (DrawGraphNode) g.Nodes.elementAt(index);
            
            System.out.println("predname " + node.getNameTag());
            BaseDataPredicate pred = (BaseDataPredicate) programOut.predicates.get(node.getNameTag());
            if(pred != null) {
                String predname = pred.Name;
                System.out.println(predname);
                String predtrans     = (String) programOut.PredicateTranslations.get(predname);
                buf.append(prefix);
                if(addelse) buf.append("} else ");
                buf.append("if( ");
                buf.append(predtrans);
                buf.append(") { \n");    
            }
                Vector sons = g.getSons(parent);
                if(sons.size() > 0) {
                  for(int i=0; i< sons.size();i++) {
                    String npref = new String(prefix + "     ");
                    boolean elseif = true;
                    if(i == 0 ) elseif = false;
                    String son = addSons((String) sons.elementAt(i),npref,elseif);
                    buf.append(son);
                    if(i == sons.size() -1 )
                         buf.append(npref + "}\n");
   
                  }
                } else {
                    try {
                    BaseDataDecisionTreeNodeStats nodestat = (BaseDataDecisionTreeNodeStats) 
                        programOut.DecisionTree.Tree.findObject(parent);
                    int ans = programOut.determineNodeGoal(nodestat);
                    buf.append(prefix + "DetermineReactivePhase = " + ans +";\n");
                    } catch(ObjectNotFoundException ex) {
                        buf.append(prefix + "DetermineReactivePhase = -1;\n");
                    }
                }
                
        return buf.toString();
    }  
 
}
