import java.io.*;
import java.util.*;

public class GreenCodeTest {

    public void collectionEfficiency() {
        // Smell 7: LinkedList Usage
        List<String> linkedList = new LinkedList<>();
        linkedList.add("Poor cache localty");

        // Smell 8: Legacy Synchronized Vector
        Vector<Integer> vector = new Vector<>();
        vector.add(100);

        // Smell 9: Legacy Synchronized Stack
        Stack<String> stack = new Stack<>();
        stack.push("Legacy");

        // Smell 10: Inefficient TreeMap (when sorting not needed)
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("Key", "Value");
    }

    public void runFullAnalysis() throws IOException {
        // 1. NESTED_LOOP
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                System.out.println(i + j);
            }
        }

        // 2. BUSY_WAIT
        boolean condition = false;
        while (!condition) { }

        // 3. STRING_CONCAT_IN_LOOP
        String report = "";
        for (int i = 0; i < 100; i++) {
            report += "Item " + i;
        }

        // 4. PRIMITIVE_BOXING
        Integer count = 0;
        for (int i = 0; i < 100; i++) {
            count++;
        }

        // 5. LOOP_INVARIANT
        for (int i = 0; i < 100; i++) {
            double pi = Math.atan(1.0) * 4;
            System.out.println(pi);
        }

        // 6. UNCLOSED_RESOURCE
        FileInputStream fis = new FileInputStream("data.txt");
        fis.read();
    }
}
