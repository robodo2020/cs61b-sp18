public class Palindrome {


    public Deque<Character> wordToDeque(String word){
       // 這行意思？
        Deque D = new LinkedListDeque(); //這個class到底是算Deque還是算LinkedListDeque?
        for (int i = 0; i < word.length(); i++){
            D.addLast(word.charAt(i));
        }
        return D;
    }
    public boolean isPalindrome(String word){
        Deque checkWord = wordToDeque(word);

        return recurPalindrome(checkWord);
//  -------- iteration solution  --------
//        int theWordLastIndex = checkWord.size() -1;
//        for (int i = 0; i < checkWord.size()/2 + 1; i++){
//            Object a = checkWord.get(i);
//            Object b = checkWord.get(theWordLastIndex - i);
//            if (a != b){
//                return false;
//            }
//        }
//        return true;
    }
    private boolean recurPalindrome(Deque checkWord){
        // base case
        if (checkWord.size() < 2) {
            return true;
        }

        Object front = checkWord.removeFirst();
        Object last = checkWord.removeLast();

        return (front == last) && recurPalindrome(checkWord);
    }


}
