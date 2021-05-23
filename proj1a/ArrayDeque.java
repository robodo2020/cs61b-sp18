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
                nextLast = (nextLast + 1) % array.length;
            }
        if (nextFirstUse) {
                nextFirst -= 1;
                if (nextFirst < 0) {
                    nextFirst += array.length;
                }
            } 
        // need resize
        if (size >= array.length - 1) { 
            resize(size * RFACTOR, true);
            nextFirst = arrayLast;
            nextLast = size;
        }
        
    }
    // what resize have to do
    // 1. extend Array 
    // 2. organize former list
    private void resize(int capacity, boolean UseAddOrRemove) {
        T[] tmp = (T[]) new Object[capacity];
        // if (UseAddOrRemove){
        reorganize();
        // }
        
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
            resize(array.length / RFACTOR, false); 
            nextFirst = arrayLast;
            nextLast = arrayLast;
        }
        System.out.println(FirstNode);
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
            resize((array.length / RFACTOR) + 1, false); 
            nextFirst = arrayLast;
            nextLast = arrayLast - 1;
        }
        System.out.println(LastNode);
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
        System.out.println(array[(nextFirst + 1 + index) % array.length]);
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
   
}