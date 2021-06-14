public class OffByN implements CharacterComparator{
    private int dif_num;
    public OffByN(int N){
        dif_num = N;
    }
    @Override
    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return (diff == dif_num || diff == -dif_num);
    }
}
