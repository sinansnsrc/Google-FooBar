import java.util.ArrayList;

public class FooBar2B {

    public static void main(String[] args) {
        System.out.println(solution(1));
    }

    public static int solution(int total_lambs) {
        return Math.abs(getStingyLAMBs(total_lambs).size() - getGenerousLAMBs(total_lambs).size());
    }

    public static ArrayList<Integer> getGenerousLAMBs(int total_lambs){
        ArrayList<Integer> mostGenerous = new ArrayList<>();

        mostGenerous.add(1);

        while(sumArrayList(mostGenerous) <= total_lambs) {
            mostGenerous.add(mostGenerous.get(mostGenerous.size() - 1) * 2);
        }

        if(sumArrayList(mostGenerous) > total_lambs) {
            mostGenerous.remove(mostGenerous.size() - 1);
        }

        return mostGenerous;
    }

    public static ArrayList<Integer> getStingyLAMBs(int total_lambs){
        ArrayList<Integer> mostStingy = new ArrayList<>();

        mostStingy.add(1);
        if (total_lambs > 0){ mostStingy.add(1); }

        while ((mostStingy.size() > 1) && sumArrayList(mostStingy) <= total_lambs) {
            mostStingy.add(mostStingy.get(mostStingy.size()-1) + mostStingy.get(mostStingy.size() - 2));
        }

        if(sumArrayList(mostStingy) > total_lambs) {
            mostStingy.remove(mostStingy.size() - 1);
        }

        return mostStingy;
    }

    public static int sumArrayList(ArrayList<Integer> arr) {
        int total = 0;
        for (int i : arr) {
            total += i;
        }
        return total;
    }
}
