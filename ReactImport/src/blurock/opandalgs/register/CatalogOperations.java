/*
 * CatalogOperations.java
 *
 * Created on March 4, 2001, 8:06 AM
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
public class CatalogOperations extends blurock.core.TopTreeInfoPanel {

    private FrameSet operationFrames;
    
    private TopReactionMenu Top;
    
    /** Creates new CatalogOperations */
    public CatalogOperations(TopReactionMenu top) {
        super("Operations",
                new FrameSet(),
                top.SystemInfo.frameTextDirectory.getValue(),
                new Dimension(top.Defaults.panelSizeX.getValue(),
                                      top.Defaults.panelSizeY.getValue()));
        Top = top;
        initializeTreeHierarchy(topNode);        
        updateUI();
    }
        
    public void initializeTreeHierarchy(DefaultMutableTreeNode topnode) {
        initializeGraphOperations(topnode);
        initializeVerifyOperations(topnode);
        initializeVectorOperations(topnode);
        initializeParameterizedFunctionOperations(topnode);
    }
    public void initializeGraphOperations(DefaultMutableTreeNode topnode) {
        DefaultMutableTreeNode grh = addSimpleNode("Graph Operations",topnode);
        SimpleOperationPanel pnl1 = new SimpleOperationPanel("NodeEdgeOperation","Operation");
        BaseFrame fr1 = new BaseFrame(Top,pnl1,
                             "NodeEdgeOperation",
                             "Graph: NodeEdgeOperation Operation",
                             "opandalgs/register/graph/NodeEdgeOperation.txt");
        addNode(fr1,grh);
        
        SimpleOperationPanel pnl2 = new SimpleOperationPanel("GraphElementMatch","Operation");
        BaseFrame fr2 = new BaseFrame(Top,pnl2,
                             "GraphElementMatch",
                             "Graph: GraphElementMatch Operation",
                             "opandalgs/register/graph/GraphElementMatch.txt");
        addNode(fr2,grh);
        
        SimpleOperationPanel pnl3 = new SimpleOperationPanel("GraphNeighbors","Operation");
        BaseFrame fr3 = new BaseFrame(Top,pnl3,
                             "GraphNeighbors",
                             "Graph: GraphNeighbors Operation",
                             "opandalgs/register/graph/GraphNeighbors.txt");
        addNode(fr3,grh);
        
        SimpleOperationPanel pnl4 = new SimpleOperationPanel("SimpleGraphCounts","Operation");
        BaseFrame fr4 = new BaseFrame(Top,pnl4,
                             "SimpleGraphCounts",
                             "Graph: SimpleGraphCounts Operation",
                             "opandalgs/register/graph/SimpleGraphCounts.txt");
        addNode(fr4,grh);
        
        SimpleOperationPanel pnl5 = new SimpleOperationPanel("GraphNodePairsOperation","Operation");
        BaseFrame fr5 = new BaseFrame(Top,pnl5,
                             "GraphNodePairsOperation",
                             "Graph: GraphNodePairsOperation Operation",
                             "opandalgs/register/graph/GraphNodePairsOperation.txt");
        addNode(fr5,grh);
        
    }
    public void initializeVerifyOperations(DefaultMutableTreeNode topnode) {
        DefaultMutableTreeNode verify = addSimpleNode("Verify Environment",topnode);
        SimpleOperationPanel pnl1 = new SimpleOperationPanel("VerifyErrorOperation","Operation");
        BaseFrame fr1 = new BaseFrame(Top,pnl1,
                             "ParameterizedFunction",
                             "Verify: ParameterizedFunction Operation",
                             "opandalgs/register/verify/ParameterizedFunction.txt");
        addNode(fr1,verify);
        SimpleOperationPanel pnl2 = new SimpleOperationPanel("GraphNodePairsOperation","Operation");
        BaseFrame fr2 = new BaseFrame(Top,pnl2,
                             "VerifyOperation",
                             "Verify: VerifyOperation Operation",
                             "opandalgs/register/verify/VerifyOperation.txt");
        addNode(fr2,verify);
    }        
    public void initializeVectorOperations(DefaultMutableTreeNode topnode) {
        DefaultMutableTreeNode vec = addSimpleNode("Vector Operations",topnode);
        SimpleOperationPanel pnl1 = new SimpleOperationPanel("FormVector","Operation");
        BaseFrame fr1 = new BaseFrame(Top,pnl1,
                             "FormVector",
                             "Vector: FormVector Operation",
                             "opandalgs/register/vector/FormVector.txt");
        addNode(fr1,vec);
        SimpleOperationPanel pnl2 = new SimpleOperationPanel("VectorParameterizedFunction","Operation");
        BaseFrame fr2 = new BaseFrame(Top,pnl2,
                             "VectorParameterizedFunction",
                             "Vector: VectorParameterizedFunction Operation",
                             "opandalgs/register/vector/VectorParameterizedFunction.txt");
        addNode(fr2,vec);
    }
    
    public void initializeParameterizedFunctionOperations(DefaultMutableTreeNode topnode) {
        DefaultMutableTreeNode param = addSimpleNode("Parameterized Function Operations",topnode);
        SimpleOperationPanel pnl1 = new SimpleOperationPanel("FormVector","Operation");
        BaseFrame fr1 = new BaseFrame(Top,pnl1,
                             "ParameterizedFunction",
                             "Parameterized Function: ParameterizedFunction Operation",
                             "opandalgs/register/parameterized/ParameterizedFunction.txt");
        addNode(fr1,param);
        SimpleOperationPanel pnl2 = new SimpleOperationPanel("RealFunctionParameterized","Operation");
        BaseFrame fr2 = new BaseFrame(Top,pnl2,
                             "RealFunctionParameterized",
                             "Parameterized Function: RealFunctionParameterized Operation",
                             "opandalgs/register/parameterized/FormVector.txt");
        addNode(fr2,param);
        SimpleOperationPanel pnl3 = new SimpleOperationPanel("ParameterSetOperation","Operation");
        BaseFrame fr3 = new BaseFrame(Top,pnl3,
                             "ParameterSetOperation",
                             "Parameterized Function: ParameterSetOperation Operation",
                             "opandalgs/register/parameterized/ParameterSetOperation.txt");
        addNode(fr3,param);
        SimpleOperationPanel pnl4 = new SimpleOperationPanel("ParameterExtract","Operation");
        BaseFrame fr4 = new BaseFrame(Top,pnl4,
                             "ParameterExtract",
                             "Parameterized Function: ParameterExtract Operation",
                             "opandalgs/register/parameterized/ParameterExtract.txt");
        addNode(fr4,param);
        SimpleOperationPanel pnl5 = new SimpleOperationPanel("DoOperation","Operation");
        BaseFrame fr5 = new BaseFrame(Top,pnl5,
                             "DoOperation",
                             "Parameterized Function: DoOperation Operation",
                             "opandalgs/register/parameterized/DoOperation.txt");
        addNode(fr5,param);
        SimpleOperationPanel pnl6 = new SimpleOperationPanel("ParametersInFunction","Operation");
        BaseFrame fr6 = new BaseFrame(Top,pnl6,
                             "ParametersInFunction",
                             "Parameterized Function: ParametersInFunction Operation",
                             "opandalgs/register/parameterized/ParametersInFunction.txt");
        addNode(fr6,param);
    }
    void initializeLogicalOperations(DefaultMutableTreeNode topnode) {
        DefaultMutableTreeNode log = addSimpleNode("Logical Function Operations",topnode);
        SimpleOperationPanel pnl1 = new SimpleOperationPanel("LogicalOperation","Operation");
        BaseFrame fr1 = new BaseFrame(Top,pnl1,
                             "LogicalOperation",
                             "Logical Operations: LogicalOperation Operation",
                             "opandalgs/register/logical/LogicalOperation.txt");
        addNode(fr1,log);
        SimpleOperationPanel pnl2 = new SimpleOperationPanel("NumericPredicate","Operation");
        BaseFrame fr2 = new BaseFrame(Top,pnl2,
                             "NumericPredicate",
                             "Logical Operations: NumericPredicate Operation",
                             "opandalgs/register/logical/NumericPredicate.txt");
        addNode(fr2,log);
        SimpleOperationPanel pnl3 = new SimpleOperationPanel("BaseDataExactlyEqualPredicate","Operation");
        BaseFrame fr3 = new BaseFrame(Top,pnl3,
                             "BaseDataExactlyEqualPredicate",
                             "Logical Operations: BaseDataExactlyEqualPredicate Operation",
                             "opandalgs/register/logical/BaseDataExactlyEqualPredicate.txt");
        addNode(fr3,log);
    }
    void initializeNumericOperations(DefaultMutableTreeNode topnode) {
        DefaultMutableTreeNode num = addSimpleNode("Numeric Function Operations",topnode);
        SimpleOperationPanel pnl1 = new SimpleOperationPanel("NumericOperation","Operation");
        BaseFrame fr1 = new BaseFrame(Top,pnl1,
                             "NumericOperation",
                             "Numeric Operations: NumericOperation Operation",
                             "opandalgs/register/numeric/NumericOperation.txt");
        addNode(fr1,num);
        SimpleOperationPanel pnl2 = new SimpleOperationPanel("BaseDataNumOpMinus","Operation");
        BaseFrame fr2 = new BaseFrame(Top,pnl2,
                             "BaseDataNumOpMinus",
                             "Numeric Operations: BaseDataNumOpMinus Operation",
                             "opandalgs/register/numeric/BaseDataNumOpMinus.txt");
        addNode(fr2,num);
        SimpleOperationPanel pnl3 = new SimpleOperationPanel("BaseDataNumOpPlus","Operation");
        BaseFrame fr3 = new BaseFrame(Top,pnl3,
                             "BaseDataNumOpPlus",
                             "Numeric Operations: BaseDataNumOpPlus Operation",
                             "opandalgs/register/numeric/BaseDataNumOpPlus.txt");
        addNode(fr3,num);
        SimpleOperationPanel pnl4 = new SimpleOperationPanel("BaseDataNumOpMultiply","Operation");
        BaseFrame fr4 = new BaseFrame(Top,pnl4,
                             "BaseDataNumOpMultiply",
                             "Numeric Operations: BaseDataNumOpMultiply Operation",
                             "opandalgs/register/numeric/BaseDataNumOpMultiply.txt");
        addNode(fr4,num);
        SimpleOperationPanel pnl5 = new SimpleOperationPanel("NumericOperation","Operation");
        BaseFrame fr5 = new BaseFrame(Top,pnl5,
                             "BaseDataNumOpDivide",
                             "Numeric Operations: BaseDataNumOpDivide Operation",
                             "opandalgs/register/numeric/BaseDataNumOpDivide.txt");
        addNode(fr5,num);
    }
    
}
