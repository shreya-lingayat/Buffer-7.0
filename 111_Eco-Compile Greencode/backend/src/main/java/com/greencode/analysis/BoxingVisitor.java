package com.greencode.analysis;

import com.github.javaparser.ast.body.VariableDeclarator;
import com.github.javaparser.ast.stmt.ForStmt;
import com.github.javaparser.ast.stmt.WhileStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.greencode.model.SmellResult;
import com.greencode.model.SmellType;

import java.util.List;

public class BoxingVisitor extends VoidVisitorAdapter<List<SmellResult>> {

    @Override
    public void visit(VariableDeclarator n, List<SmellResult> smells) {
        String type = n.getType().asString();
        if ((type.equals("Integer") || type.equals("Double") || type.equals("Long") || type.equals("Float")) && 
            isInsideLoop(n)) {
            smells.add(new SmellResult(
                SmellType.PRIMITIVE_BOXING,
                n.getBegin().map(p -> p.line).orElse(-1),
                5,
                "PRIMITIVE_BOXING"
            ));
        }
        super.visit(n, smells);
    }

    private boolean isInsideLoop(com.github.javaparser.ast.Node node) {
        return node.findAncestor(ForStmt.class).isPresent() || 
               node.findAncestor(WhileStmt.class).isPresent();
    }
}
