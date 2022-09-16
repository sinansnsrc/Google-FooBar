import java.math.BigInteger;

public class FooBar3B {
    public static void main(String[] args) {
        //System.out.println(solution("2","17"));
        //System.out.println(solution("2","1"));
        //System.out.println(solution("4","7"));
        //System.out.println(solution("1","1"));
        //System.out.println(solution("3","100000000000000007629769841091887003294964970946560"));
    }

    public static String solution(String x, String y) {
        BigInteger generations = new BigInteger("0");
        BigInteger m = new BigInteger(x);
        BigInteger f = new BigInteger(y);

        while(!m.equals(BigInteger.ONE) || !f.equals(BigInteger.ONE)) {
            BigInteger gcd = f.gcd(m);

            if(gcd.equals(BigInteger.ZERO) || (gcd.equals(m) && !m.equals(BigInteger.ONE)) || (gcd.equals(f) && !f.equals(BigInteger.ONE)) || m.compareTo(f) == 0) {
                return "impossible";
            }

            if(!m.equals(BigInteger.ONE) && !f.equals(BigInteger.ONE)) {
                if(m.compareTo(f) == 1) {
                    generations = generations.add((m.subtract(m.mod(f)).divide(f)));
                    m = m.mod(f);
                }
                else if (m.compareTo(f) == -1) {
                    generations = generations.add((f.subtract(f.mod(m)).divide(m)));
                    f = f.mod(m);
                }
            }
            else {
                if(m.compareTo(f) == 1) {
                    m = m.subtract(f);
                    generations = generations.add(BigInteger.ONE);
                }
                else if (m.compareTo(f) == -1) {
                    f = f.subtract(m);
                    generations = generations.add(BigInteger.ONE);
                }
            }
        }

        return generations.toString();
    }
}
