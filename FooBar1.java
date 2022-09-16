public class FooBar1 {

    public static void main(String[] args) {
        System.out.println(solution(0));
    }

    public static String solution(int i) {
        return getPrimeNumbers().substring(i, i + 5);
    }

    public static String getPrimeNumbers() {
            String primeNumbers = "";
            boolean notPrime = false;

            for (int j = 0; j < 20240; j++) {
                notPrime = false;
                for (int i = 2; i <= j / 2; ++i) {
                    if (j % i == 0) {
                        notPrime = true;
                        break;
                    }
                }
                if (!notPrime && !(j < 2)) {
                    primeNumbers += j + "";
                }
            }

            return primeNumbers;
        }
}
