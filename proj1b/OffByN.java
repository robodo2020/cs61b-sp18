public class OffByN implements CharacterComparator {
    private int difNum;
    public OffByN(int N) {
        difNum = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return (diff == difNum || diff == -difNum);
    }
}
