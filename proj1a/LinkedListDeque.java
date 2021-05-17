public class LinkedListDeque<T> {
    
    private LinkedList sentinelF;
    private LinkedList sentinelB;
    private int size;
    
    private class LinkedList {
        public T val;
        public LinkedList prev;
        public LinkedList next;
        public LinkedList(LinkedList p, T i, LinkedList n){
            prev = p;
            val = i;
            next = n;
        }
    }

    public LinkedListDeque(){
        sentinelF = new LinkedList(null, null, null);
        sentinelB = new LinkedList(sentinelF, null, null);
        sentinelF.next = sentinelB;
        size = 0;
    }

    public void addFirst(T item){
        sentinelF.next = new LinkedList(sentinelF, item, sentinelF.next);
        if (isEmpty()){
            sentinelB.prev = sentinelF.next;  
        }
        else{
            sentinelF.next.next.prev = sentinelF.next;
        }
        size += 1;
    }

    // public void addLast(T item){
    public void addLast(T item){
        sentinelB.prev.next = new LinkedList(sentinelB.prev, item, sentinelB);
        if (isEmpty()){
            sentinelB.prev = sentinelF.next;
        }
        else{
            sentinelB.prev = sentinelB.prev.next;
        }
        size += 1;
    }

    public boolean isEmpty(){
        if (size == 0){
            return true;
        }
        else{
            return false;
        }
    }

    // time complexity = constant
    
    public int size(){
        return size;
    }

    public void printDeque(){
        LinkedList cur = sentinelF.next;
        for(int i = 1; i <= size; i++){
            System.out.print(cur.val);
            System.out.print(" ");
            cur = cur.next;
        }
        System.out.println();
    }

   
    public T removeFirst(){

        T value = sentinelF.next.val;
        sentinelF.next.prev = null;
        sentinelF.next = sentinelF.next.next;
        sentinelF.next.prev.next = null;
        sentinelF.next.prev = sentinelF; 
        size -= 1;
        return value;
    }
    
    
    public T removeLast(){
        // LinkedList value = sentinelB.prev;
        T value = sentinelB.prev.val;
        sentinelB.prev.next = null;
        sentinelB.prev = sentinelB.prev.prev;
        sentinelB.prev.next.prev =  null;
        sentinelB.prev.next = sentinelB;
        size -= 1;
        return value;
    }

    // //用iteration 不可用recursion
    // public T get(int index){
    public T get(int index){
        //解決index > size,但是還是會噴出-1 這樣不夠好 研究看能不能純噴error
        if (index > size){
            System.out.println("Error: function get input should be smaller than size");
            return null;
        }

        int i = 1;
        LinkedList cur = sentinelF.next;
        while(i <= size){
            if(i == index){
                break;
            }
            else{
                cur = cur.next;
                i += 1;
            }
        }
        return cur.val;
        

    }

    // 用recursion 寫 get
    public T getRecursive(int index){
        if (index > size || index < 1){
            System.out.println("Error: function get input should be smaller than size");
            return null;
        }
        LinkedList cur = sentinelF.next;
        int i = 1;
        return recur(i, cur, index);
    }

    private T recur(int i, LinkedList cur, int index){
        if (i == index){
            return cur.val;
        }
        else{
            return recur(i+1,cur.next, index);
        }
    }

}