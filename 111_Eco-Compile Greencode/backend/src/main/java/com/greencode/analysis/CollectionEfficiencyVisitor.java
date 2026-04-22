package com.greencode.analysis;

import com.github.javaparser.ast.expr.ObjectCreationExpr;
import com.github.javaparser.ast.visitor.VoidVisitorAdapter;
import com.greencode.model.SmellResult;
import com.greencode.model.SmellType;

import java.util.List;
import java.util.Map;

public class CollectionEfficiencyVisitor extends VoidVisitorAdapter<List<SmellResult>> {

    private static final Map<String, SmellType> INEFFICIENT_TYPES = Map.of(
        "LinkedList", SmellType.LINKEDLIST_USAGE,
        "Vector", SmellType.LEGACY_SYNCHRONIZED,
        "Hashtable", SmellType.LEGACY_SYNCHRONIZED,
        "Stack", SmellType.LEGACY_SYNCHRONIZED,
        "TreeMap", SmellType.MAP_EFFICIENCY,
        "TreeSet", SmellType.MAP_EFFICIENCY
    );

    @Override
    public void visit(ObjectCreationExpr n, List<SmellResult> smells) {
        String type = n.getType().asString();
        // Check for simplified type name (e.g., LinkedList or LinkedList<String>)
        String baseType = type.contains("<") ? type.substring(0, type.indexOf("<")) : type;

        if (INEFFICIENT_TYPES.containsKey(baseType)) {
            SmellType smellType = INEFFICIENT_TYPES.get(baseType);
            int severity = (smellType == SmellType.LEGACY_SYNCHRONIZED) ? 8 : 4;
            
            smells.add(new SmellResult(
                smellType,
                n.getBegin().map(p -> p.line).orElse(-1),
                severity,
                smellType.name()
            ));
        }
        super.visit(n, smells);
    }
}
