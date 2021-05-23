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
    }

    // what resize have to do:  extend Array & organize the list
    private void resize(int capacity) {
        T[] tmp = (T[]) new Object[capacity];
        
        int reorganizePointer = (nextFirst + 1) % array.length;
        for (int i = 0; i < size; i++){
            tmp[i] = array[reorganizePointer];
            reorganizePointer = (reorganizePointer + 1) % array.length;
        }
        array = tmp; 
        arrayLast = capacity - 1;
        nextFirst = arrayLast;
        nextLast = size;
    }


    public void addFirst(T item) {
        array[nextFirst] = item;
        size += 1;
        addNextPointer(true,false);

        if (size == array.length) { 
            resize(size * RFACTOR);
        }
    }
  
    public void addLast(T item) {
        array[nextLast] = item;
        size += 1;
        addNextPointer(false, true);

        if (size == array.length) { 
            resize(size * RFACTOR);
        }
    }
   
    private void removeNextPointer(boolean nextFirstUse, boolean nextLastUse) {
        if (nextFirstUse) {
            nextFirst = (nextFirst + 1) % array.length;
        } 
        if (nextLastUse) {
            nextLast -= 1;
            if (nextLast < 0){
                nextLast += array.length;
            }
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        removeNextPointer(true, false);
        T FirstNode = array[nextFirst];
        array[nextFirst] = null;
        size -= 1;
        
        //resize & reorganize part
        if (array.length > INITIAL_SIZE && size == (array.length / 4)) {
            resize(array.length / RFACTOR); 
        }
        // System.out.println(FirstNode);
        return FirstNode;

    }
    
    public T removeLast() {
        if (size == 0) {
            return null;
        }
       
        removeNextPointer(false, true);
        T LastNode = array[nextLast];
        array[nextLast] = null;
        size -= 1;

        //resize & reorganize part
        if (array.length > INITIAL_SIZE && size < (array.length / 4)){
            resize((array.length / RFACTOR)); 
        }
        // System.out.println(LastNode);
        return LastNode;

    }
    
    
    public int size() {
        return size;
    }

    public void printDeque() {
        int printPointer = (nextFirst + 1) % array.length;
        for (int i = 0; i < size; i++){
            System.out.println(array[printPointer] + ", ");
            printPointer = (printPointer + 1) % array.length;
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
}