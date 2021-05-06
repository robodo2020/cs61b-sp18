public class SLList {
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode first; 
    private int size;

    public SLList(int x) {
        first = new IntNode(x, null);
    }


    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        first = new IntNode(x, first);
    }    

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return first.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        /* Your Code Here! */
        IntNode pointer = first;

        while(pointer.next != null){
            pointer = pointer.next;
        }
        pointer.next = new IntNode(x, null);
    }

    /** Returns the number of items in the list using recursion. */
    public int size() {
        /* Your Code Here! */
        IntNode pointer = first;
        while(pointer != null){
            size+=1;
            pointer = pointer.next;
        }
        return size;
    }


    public static void main(String[] args){
        SLList x = new SLList(15);
        x.addFirst(5);
        x.addFirst(10);
        System.out.println(x);
        System.out.println(x.size());
    }
}

