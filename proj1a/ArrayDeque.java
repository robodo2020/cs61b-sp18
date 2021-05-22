public class ArrayDeque<T> {
    private T[] array;
    private int size;

    private int nextFirst;
    private int nextLast;
    private int INITIAL_SIZE = 8;

    private int arrayLast;
    private int arrayStart;
    private int RFACTOR = 2;

    public ArrayDeque() {
        array = (T[]) new Object[INITIAL_SIZE];
        arrayLast = array.length - 1;
        arrayStart = 0;
        size = 0;   
        nextFirst = arrayLast;
        nextLast = arrayStart;
    }

    private void addNextPointer(boolean nextFirstUse, boolean nextLastUse) {
       if (nextLastUse) {
                nextLast += 1;
            }
        if (nextFirstUse) {
                nextFirst -= 1;
            } 
        // need resize
        // if (size == array.length && nextFirst == nextLast - 1){
        if (size == array.length) {
            resize(size * RFACTOR);
            nextFirst = arrayLast;
            nextLast = size;
        }
        
    }
    // what resize have to do
    // 1. extend Array 
    // 2. organize former list
    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        reorganize();
        System.arraycopy(array, 0, tmp, 0, size);
        array = tmp; 
        arrayLast = capacity - 1;

    }
    private void reorganize() {
        T[] tmp = (T[]) new Object[size];
        int counter = 0;
        // organize from NextFirst to arrayLast
        for (int i = nextFirst + 1; i < array.length; i++) {
            tmp[counter] = array[i];
           counter ++;
        }
        // organize from 0 to nextLast
        for (int i = 0; i < nextLast; i++) {
            tmp[counter] = array[i];
           counter ++;
        }
        array = tmp;
    }

    public void addFirst(T item) {
        array[nextFirst] = item;
        size += 1;
        addNextPointer(true,false);
    }
  
    public void addLast(T item) {
        array[nextLast] = item;
        size += 1;
        addNextPointer(false, true);
        
    }
   
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = (nextFirst + 1) % array.length;
        T FirstNode = array[nextFirst];
        array[nextFirst] = null;
        size -= 1;
        if (array.length > INITIAL_SIZE && size < (array.length / RFACTOR)) {
            resize(array.length / RFACTOR); 
            nextFirst = arrayLast;
            nextLast = arrayLast;
        }
        return FirstNode;

    }
    
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (nextLast == 0) {
            nextLast = arrayLast;
        } 
        else {
            nextLast -= 1;
        }
        T LastNode = array[nextLast];
        array[nextLast] = null;
        size -= 1;
        if (array.length > INITIAL_SIZE && size < (array.length / RFACTOR)){
            resize(array.length / RFACTOR); 
            nextFirst = arrayLast;
            nextLast = arrayLast;
        }
        return LastNode;

    }
    
    
    public int size() {
        return size;
    }

    public void printDeque() {
         for (int i = nextFirst + 1; i < array.length; i++) {
            System.out.print(array[i]+ ", ");
        }
        // organize from 0 to nextLast
        for (int i = 0; i < nextLast; i++) {
            System.out.print(array[i]+ ", ");
        }
        System.out.println();
    }

    public T get(int index) {
        return array[(nextFirst + 1 + index) % array.length];
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        else {
            return false;
        }
    }

    public static void main(String args[]){
        ArrayDeque y = new ArrayDeque();
        y.addLast(6);
        y.printDeque();
        y.addLast(7);
        y.addFirst(5);
        y.addFirst(4);
        y.addFirst(3);
        y.addFirst(2);
        y.addFirst(1);
        y.printDeque();
        // y.addLast(8);
        // y.printDeque();
        y.removeFirst();    
        y.removeFirst(); 
        y.removeFirst(); 
        y.removeFirst(); 
        y.removeFirst(); 
        y.removeFirst(); 
        y.removeFirst(); 
        y.removeFirst(); 
        y.printDeque();
        // y.addLast(9);
        // y.addFirst(15);
        // y.addFirst(14);
        // y.addFirst(13);
        // y.addFirst(12);
        // y.addFirst(11);
        // y.addLast(16);
        // y.printDeque();
        // y.addLast(17);
        // y.printDeque();
        // y.addFirst(25);
        // y.printDeque();
        // y.addFirst(24);
        // y.addFirst(23);
        // y.addFirst(22);
        // y.addFirst(21);
        // y.printDeque();

    }

    
}