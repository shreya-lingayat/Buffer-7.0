package com.greencode.analysis;

import com.github.javaparser.ast.stmt.BlockStmt;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.Statement;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.greencode.model.SmellResult;
import com.greencode.model.SmellType;

import java.util.List;

public class BusyWaitVisitor extends VoidVisitorAdapter<List<SmellResult>> {

    @Override
    public void visit(WhileStmt n, List<SmellResult> smells) {
        if (isBusyWait(n.getBody())) {
            smells.add(new SmellResult(
                SmellType.BUSY_WAIT,
                n.getBegin().map(p -> p.line).orElse(-1),
                10, // Critical severity
                "BUSY_WAIT"
            ));
        }
        super.visit(n, smells);
    }

    private boolean isBusyWait(Statement body) {
        if (body.isBlockStmt()) {
            BlockStmt block = body.asBlockStmt();
            // Flag if empty or very small with no sleep/delay
            return block.getStatements().isEmpty() || 
                   (!containsDelay(block) && block.getStatements().size() < 3);
        }
        return true; // Single statement with no delay is suspicious
    }

    private boolean containsDelay(BlockStmt block) {
        String content = block.toString();
        return content.contains("Thread.sleep") || 
               content.contains(".wait") || 
               content.contains(".poll") || 
               content.contains("yield()");
    }
}
