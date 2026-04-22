package com.greencode.analysis;

import com.github.javaparser.ast.expr.AssignExpr;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.greencode.model.SmellResult;
import com.greencode.model.SmellType;

import java.util.List;

public class LoopInvariantVisitor extends VoidVisitorAdapter<List<SmellResult>> {

    @Override
    public void visit(AssignExpr n, List<SmellResult> smells) {
        // Find if this assignment is inside a loop
        n.getParentNode().ifPresent(parent -> {
            if (isInsideLoop(n)) {
                // Simplified: if value is a literal or simple calculation with literals
                if (n.getValue().isLiteralExpr()) {
                    smells.add(new SmellResult(
                        SmellType.LOOP_INVARIANT,
                        n.getBegin().map(p -> p.line).orElse(-1),
                        4, // Medium severity
                        "LOOP_INVARIANT"
                    ));
                }
            }
        });
        super.visit(n, smells);
    }

    private boolean isInsideLoop(AssignExpr expr) {
        return expr.findAncestor(ForStmt.class).isPresent() || 
               expr.findAncestor(WhileStmt.class).isPresent();
    }
}
