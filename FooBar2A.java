import java.util.Arrays;

public class FooBar2A {

    public static void main(String[] args) {
        //String[] exampleData = {"7.7.7", "7.0.7", "7.7.0", "7.7", "7"};
        String[] exampleData = {"1.11", "2.0.0", "1.2", "2", "0.1", "1.2.1", "1.1.1", "2.0"};
        //String[] exampleData = {"1.1.2", "1.0", "1.3.3", "1.0.12", "1.0.2"};

        String[] answer = solution(exampleData);

        System.out.println(Arrays.toString(answer));
    }

    public static String[] solution(String[] l) {
        for (int i = 0; i < l.length - 1; i++)
        {
            int index = i;
            for (int j = i + 1; j < l.length; j++){
                String[] a;
                String[] b;
                if (l[j].indexOf(".") == -1) { a = new String[]{l[j]}; }
                else { a = (l[j] + "").split("\\."); }

                if (l[index].indexOf(".") == -1) { b = new String[]{l[index]}; }
                else { b = (l[index] + "").split("\\."); }

                if (Integer.parseInt(a[0]) < Integer.parseInt(b[0])) {
                    index = j;
                }
                else if (Integer.parseInt(a[0]) == Integer.parseInt(b[0])) {
                    if ((a.length > 1 && b.length > 1) && Integer.parseInt(a[1]) < Integer.parseInt(b[1])) {
                        index = j;
                    }
                    else if ((a.length > 1 && b.length > 1) && Integer.parseInt(a[1]) == Integer.parseInt(b[1])) {
                        if ((a.length > 2 && b.length > 2) && Integer.parseInt(a[2]) < Integer.parseInt(b[2])) {
                            index = j;
                        }
                        else if (!(a.length > 2) && b.length > 2) {
                            index = j;
                        }
                    }
                    else if (!(a.length > 1) && b.length > 1) {
                        index = j;
                    }
                }
            }

            String smallerElevator = l[index];
            l[index] = l[i];
            l[i] = smallerElevator;
        }

        return l;
    }
}
