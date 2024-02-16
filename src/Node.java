public class Node {
    int x;
    int y;
    Node prev;
    Node next;

    public Node(int x, int y) {// yilanin dugumlerinin x ve y koordinatini tutar
        this.x = x;
        this.y = y;
        this.prev = null;
        this.next = null;
    }
}
