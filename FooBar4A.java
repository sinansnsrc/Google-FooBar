import java.util.Arrays;

public class FooBar4A {
    public static void main(String[] args) {
        for(int num_buns = 1; num_buns <= 9; num_buns++) {
            for(int num_required = 0; num_required <= num_buns; num_required++) {
                int[][] a = solution(num_buns, num_required);

                System.out.print("(" + num_buns + ", " + num_required + "): ");
                for(int[] i : a) {
                    System.out.print(Arrays.toString(i));
                }
                System.out.println();
            }
            System.out.println();
        }
    }

    public static int[][] solution(int num_buns, int num_required) {
        int[][] bunniesAndKeys;

        //Case 1 (May not be required)
        if(num_required == 0) {
            return new int[num_buns][0];
        }

        //Case 2
        else if(num_required == 1) {
            bunniesAndKeys = new int[num_buns][];
            for(int i = 0; i < bunniesAndKeys.length; i++) {
                bunniesAndKeys[i] = new int[] {0};
            }
            return bunniesAndKeys;
        }

        //Case 3
        else if(num_buns == num_required) {
            bunniesAndKeys = new int[num_buns][];
            for(int i = 0; i < bunniesAndKeys.length; i++) {
                bunniesAndKeys[i] = new int[] {i};
            }
            return bunniesAndKeys;
        }

        //Case 4
        else {
            int totalKeys = factorial(num_buns) / factorial(num_buns - num_required)/ factorial(num_required - 1);
            int numRepeats = num_buns - num_required + 1;
            int currentKey = 0;

            bunniesAndKeys = new int[num_buns][totalKeys / num_buns];

            for(int i = 0; i < bunniesAndKeys.length; i++) {
                Arrays.fill(bunniesAndKeys[i], -1);
            }

            for(int b = numRepeats >= 8 ? 0 : -1; numRepeats >= 8 ? b < num_buns - 7 : b < 0; b++) {
                for(int c = numRepeats >= 7 ? b + 1 : -1; numRepeats >= 7 ? c < num_buns - 6 : c < 0; c++) {
                    for(int d = numRepeats >= 6 ? c + 1 : -1; numRepeats >= 6 ? d < num_buns - 5 : d < 0; d++) {
                        for(int e = numRepeats >= 5 ? d + 1 : -1; numRepeats >= 5 ? e < num_buns - 4 : e < 0; e++) {
                            for(int f = numRepeats >= 4 ? e + 1 : -1; numRepeats >= 4 ? f < num_buns - 3 : f < 0; f++) {
                                for(int g = numRepeats >= 3 ? f + 1 : -1; numRepeats >= 3 ? g < num_buns - 2 : g < 0; g++) {
                                    for(int h = numRepeats >= 2 ? g + 1 : -1; numRepeats >= 2 ? h < num_buns - 1 : h < 0; h++) {
                                        for(int i = h + 1; i < num_buns; i++) {
                                            switch(numRepeats){
                                                case(9):
                                                    bunniesAndKeys[0][availableIndex(bunniesAndKeys[0])] = currentKey;
                                                case(8):
                                                    bunniesAndKeys[b][availableIndex(bunniesAndKeys[b])] = currentKey;
                                                case(7):
                                                    bunniesAndKeys[c][availableIndex(bunniesAndKeys[c])] = currentKey;
                                                case(6):
                                                    bunniesAndKeys[d][availableIndex(bunniesAndKeys[d])] = currentKey;
                                                case(5):
                                                    bunniesAndKeys[e][availableIndex(bunniesAndKeys[e])] = currentKey;
                                                case(4):
                                                    bunniesAndKeys[f][availableIndex(bunniesAndKeys[f])] = currentKey;
                                                case(3):
                                                    bunniesAndKeys[g][availableIndex(bunniesAndKeys[g])] = currentKey;
                                                case(2):
                                                    bunniesAndKeys[h][availableIndex(bunniesAndKeys[h])] = currentKey;
                                                case(1):
                                                    bunniesAndKeys[i][availableIndex(bunniesAndKeys[i])] = currentKey;
                                            }
                                            currentKey++;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }

            return bunniesAndKeys;
        }
    }

    public static int factorial(int n){
        if (n == 0)
            return 1;
        else
            return(n * factorial(n - 1));
    }

    public static int availableIndex(int[] n) {
        for(int i = 0; i < n.length; i++) {
            if (n[i] == -1) { return i; }
        }
        return 0;
    }
}
