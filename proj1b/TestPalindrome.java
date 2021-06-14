import org.junit.Test;
import static org.junit.Assert.*;

public class TestPalindrome {
    // You must use this palindrome, and not instantiate
    // new Palindromes, or the autograder might be upset.
    static Palindrome palindrome = new Palindrome();

    @Test
    public void testWordToDeque() {
        Deque d = palindrome.wordToDeque("persiflage");
        String actual = "";
        for (int i = 0; i < "persiflage".length(); i++) {
            actual += d.removeFirst();
        }
        assertEquals("persiflage", actual);
    }


    @Test
    public void testIsPalindrome(){
        assertFalse(palindrome.isPalindrome("cat"));
        assertFalse(palindrome.isPalindrome("Wow"));
        assertTrue(palindrome.isPalindrome("cabac"));
        assertTrue(palindrome.isPalindrome("X"));
        assertTrue(palindrome.isPalindrome("qwerrewq"));
        assertFalse((palindrome.isPalindrome(" xcGwiIiwGcx")));
        assertTrue((palindrome.isPalindrome(" xcGwiIiwGcx ")));
    }

    @Test
    public void testisPalindromeOffByOne(){
        CharacterComparator cc = new OffByOne();
        // Get understand about cc whether the object is OffByOne or CharacterComparator?
        assertTrue(palindrome.isPalindrome("flake", cc));
        assertTrue(palindrome.isPalindrome("acb", cc));
        assertTrue(palindrome.isPalindrome("x", cc));
        assertTrue(palindrome.isPalindrome("%&", cc));
        assertFalse(palindrome.isPalindrome("aaaa", cc));
        assertFalse(palindrome.isPalindrome("abcd", cc));
    }
    @Test
    public void testisPalindromeOffByN(){
        CharacterComparator cc = new OffByN(5);
        assertTrue(palindrome.isPalindrome("toity", cc));
        assertTrue(palindrome.isPalindrome("augylpf", cc));
        assertTrue(palindrome.isPalindrome("liang", cc));
        assertFalse(palindrome.isPalindrome("aaaa", cc));
        assertFalse(palindrome.isPalindrome("flake", cc));
        assertFalse(palindrome.isPalindrome("acb", cc));


    }
}
