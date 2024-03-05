import java.util.HashSet;
import java.util.Set;

/**
 * Creates a {@code Node}.
 */
public class Node {
    String data;
    Node next;
    Set<Node> neighbors;

    /**
     * Initializes a {@code node} with a given {@code data}.
     * 
     * @param data The data being entered into a node.
     */
    public Node(String data) {
        this.data = data;
        this.next = null;
        this.neighbors = new HashSet<>();
    } // Node
} // Node