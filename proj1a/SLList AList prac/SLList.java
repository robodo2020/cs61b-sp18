public class SLList {
    public class IntNode {
        public int item;
        public IntNode next;
        public IntNode(int i, IntNode n) {
            item = i;
            next = n;
        }
    }

    private IntNode sentinel; 
    private int size;

    // 創建empty SLList
    public SLList(){
        sentinel = new IntNode(0,null);
        size = 0;
    }

    public SLList(int x) {
        sentinel = new IntNode(0, null);
        sentinel.next = new IntNode(x, null);
        size = 1;
    }


    /** Adds an item to the front of the list. */
    public void addFirst(int x) {
        sentinel.next = new IntNode(x, sentinel.next);
        size += 1;
    }    

    /** Retrieves the front item from the list. */
    public int getFirst() {
        return sentinel.next.item;
    }

    /** Adds an item to the end of the list. */
    public void addLast(int x) {
        /* Your Code Here! */
        IntNode pointer = sentinel.next;

        while(pointer.next != null){
            pointer = pointer.next;
        }
        pointer.next = new IntNode(x, null);
        size += 1;
    }
    public int getLast() {
        /* Your Code Here! */
        IntNode pointer = sentinel.next;

        while(pointer.next != null){
            pointer = pointer.next;
        }
        return pointer.item;
    }


    /** Returns the number of items in the list using recursion. */
    public int size() {
        return size;
    }


    public static void main(String[] args){
        SLList x = new SLList(15);
        x.addFirst(5);
        x.addFirst(10);
        
        System.out.println(x);
        System.out.println(x.getFirst());
        System.out.println(x.size());

        System.out.println("----------------------------");

        x.addFirst(1);
        System.out.println(x.getFirst());
        System.out.println(x.size());

        System.out.println("----------------------------");
        x.addLast(6);
        System.out.println(x.getLast());
        System.out.println(x.size());
    }
}

