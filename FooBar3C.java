public class FooBar3C {
    public static void main(String[] args) {
        int[] case1 = new int[] {1, 2, 3, 4, 5, 6};
        int[] case2 = new int[] {1, 1, 1};

        System.out.println(solution(case1));
        System.out.println(solution(case2));
    }

    public static int solution(int[] l) {
        int numberOfSolutions = 0;
        for(int i = 0; i < l.length; i++) {
            for(int j = i + 1; j < l.length; j++) {
                if(l[j] % l[i] == 0) {
                    for(int k = j + 1; k < l.length; k++) {
                        if(l[k] % l[j] == 0) {
                            numberOfSolutions++;
                        }
                    }
                }
            }
        }

        return numberOfSolutions;
    }
}
