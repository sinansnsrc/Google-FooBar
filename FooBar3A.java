public class FooBar3A {
    public static void main(String[] args) {
        int start = 0;
        int length = 4;

        System.out.println(solution(start, length));
    }

    public static int solution(int start, int length) {
        int checksum = 0;
        long value = start;

        for(int i = 0, remove = 0; i < length; i++, remove++) {
            for(int j = 0; j < length - remove; j++, value++) {
                checksum ^= value;
            }

            value += remove;
        }

        return checksum;
    }
}
