public class Palindrome {


    public Deque<Character> wordToDeque(String word) {
       // Should get understand about Deque<Character>
        Deque D = new LinkedListDeque();
        // Get understand about whether D object is Deque or LinkedListDeque?
        for (int i = 0; i < word.length(); i++) {
            D.addLast(word.charAt(i));
        }
        return D;
    }
    public boolean isPalindrome(String word) {
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

    private boolean recurPalindrome(Deque checkWord) {
        // base case
        if (checkWord.size() < 2) {
            return true;
        }

        Object front = checkWord.removeFirst();
        Object last = checkWord.removeLast();

        return (front == last) && recurPalindrome(checkWord);
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque checkWord = wordToDeque(word);
        return recurPalindrome(checkWord, cc);

        // flake should return true
    }

    private boolean recurPalindrome(Deque checkWord, CharacterComparator cc) {
        if (checkWord.size() < 2) {
            return true;
        }
        char front = checkWord.removeFirst().toString().charAt(0);
        char last = checkWord.removeLast().toString().charAt(0);
        // the code above transform the class from Object -> String -> char
        // solution from here:
        // https://stackoverflow.com/questions/13628312/conversion-from-object-to-char-in-java
        // should figure out Object and the solution without transforming

        return cc.equalChars(front, last) && recurPalindrome(checkWord, cc);
    }
}
