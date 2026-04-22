package com.greencode.analysis;

import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.expr.BinaryExpr;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.greencode.model.SmellResult;
import com.greencode.model.SmellType;

import java.util.List;

public class StringConcatVisitor extends VoidVisitorAdapter<List<SmellResult>> {

    @Override
    public void visit(AssignExpr n, List<SmellResult> smells) {
        if (n.getOperator() == AssignExpr.Operator.PLUS && isInsideLoop(n)) {
            // Check if it's likely a string (simplified pattern)
            smells.add(new SmellResult(
                SmellType.STRING_CONCAT_IN_LOOP,
                n.getBegin().map(p -> p.line).orElse(-1),
                7,
                "STRING_CONCAT_IN_LOOP"
            ));
        }
        super.visit(n, smells);
    }

    private boolean isInsideLoop(com.github.javaparser.ast.Node node) {
        return node.findAncestor(ForStmt.class).isPresent() || 
               node.findAncestor(WhileStmt.class).isPresent() ||
               node.findAncestor(ForEachStmt.class).isPresent() ||
               node.findAncestor(DoStmt.class).isPresent();
    }
}
