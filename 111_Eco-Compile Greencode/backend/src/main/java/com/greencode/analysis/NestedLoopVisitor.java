package com.greencode.analysis;

import com.github.javaparser.ast.stmt.DoStmt;
import com.github.javaparser.ast.stmt.ForEachStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.greencode.model.SmellResult;
import com.greencode.model.SmellType;

import java.util.ArrayList;
import java.util.List;

public class NestedLoopVisitor extends VoidVisitorAdapter<List<SmellResult>> {

    @Override
    public void visit(ForStmt n, List<SmellResult> smells) {
        checkNesting(n, smells);
        super.visit(n, smells);
    }

    @Override
    public void visit(WhileStmt n, List<SmellResult> smells) {
        checkNesting(n, smells);
        super.visit(n, smells);
    }

    @Override
    public void visit(ForEachStmt n, List<SmellResult> smells) {
        checkNesting(n, smells);
        super.visit(n, smells);
    }

    @Override
    public void visit(DoStmt n, List<SmellResult> smells) {
        checkNesting(n, smells);
        super.visit(n, smells);
    }

    private void checkNesting(Statement loopStmt, List<SmellResult> smells) {
        // Simple heuristic: check if the body contains any other loop
        boolean hasNested = loopStmt.findAll(Statement.class, s -> 
            s instanceof ForStmt || s instanceof WhileStmt || 
            s instanceof ForEachStmt || s instanceof DoStmt)
            .stream()
            .anyMatch(s -> s != loopStmt); // Ensure it's not the current one

        if (hasNested) {
            smells.add(new SmellResult(
                SmellType.NESTED_LOOP,
                loopStmt.getBegin().map(p -> p.line).orElse(-1),
                8, // High severity
                "NESTED_LOOP"
            ));
        }
    }
}
