package com.greencode.analysis;

import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.stmt.TryStmt;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.greencode.model.SmellResult;
import com.greencode.model.SmellType;

import java.util.List;

public class ResourceLeakVisitor extends VoidVisitorAdapter<List<SmellResult>> {

    private static final List<String> RESOURCE_TYPES = List.of(
        "FileInputStream", "FileOutputStream", "BufferedReader", "BufferedWriter", 
        "FileReader", "FileWriter", "Socket", "ServerSocket"
    );

    @Override
    public void visit(ObjectCreationExpr n, List<SmellResult> smells) {
        String type = n.getType().asString();
        if (RESOURCE_TYPES.contains(type)) {
            // Check if it's NOT inside a Try-with-resources (resources list of TryStmt)
            if (!isInsideTryWithResources(n)) {
                smells.add(new SmellResult(
                    SmellType.UNCLOSED_RESOURCE,
                    n.getBegin().map(p -> p.line).orElse(-1),
                    9,
                    "UNCLOSED_RESOURCE"
                ));
            }
        }
        super.visit(n, smells);
    }

    private boolean isInsideTryWithResources(ObjectCreationExpr node) {
        return node.findAncestor(TryStmt.class)
                .map(tryStmt -> tryStmt.getResources().stream()
                        .anyMatch(res -> res.containsWithin(node) || node.containsWithin(res)))
                .orElse(false);
    }
}
