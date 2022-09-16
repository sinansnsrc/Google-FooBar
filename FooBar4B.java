import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;
import java.util.Arrays;

public class FooBar4B {
    public static void main(String[] args) {


        int[][] case1 = {{0, 2, 2, 2, -1}, {9, 0, 2, 2, -1}, {9, 3, 0, 2, -1}, {9, 3, 2, 0, -1}, {9, 3, 2, 2, 0}};
        int[] a = solution(case1, 1);
        System.out.println("Test Case 1: " + Arrays.toString(a) + "\n");

        int[][] case2 = {{0, 1, 1, 1, 1}, {1, 0, 1, 1, 1}, {1, 1, 0, 1, 1}, {1, 1, 1, 0, 1}, {1, 1, 1, 1, 0}};
        int[] b = solution(case2, 3);
        System.out.println("Test Case 2: " + Arrays.toString(b) + "\n");


        int[][] myCase1 = {{0, 97, 12, -123, 83, 77}, {13, 0, -12, 743, 2, 1}, {9908, 12, 0, 14, -2, 745}, {873, 45, 84, 0, 1, 984}, {-12, 54, 98, 1, 0, 17}, {123, 34, 8, -2, 91, 0}};
        int[] c = solution(myCase1, 3);
        System.out.println("My Case 1: " + Arrays.toString(c) + "\n");


        int[][] myCase2 = {{0, 15, 1, 2, 2}, {1, 0, -1000, 2, 2}, {3, 3, 0, 2, 3}, {4, 3, 2, 0, 1}, {4, 4, 5, 4, 0}};
        int[] d = solution(myCase2, 3);
        System.out.println("My Case 2: " + Arrays.toString(d) + "\n");

        int[][] myCase3 = {{0, 2, 1, 3, 2}, {1, 0, 2, 2, 2}, {-2, 3, 0, 2, 2}, {4, 3, 2, 0, 1}, {4, 4, 5, 4, 0}};
        int[] e = solution(myCase3, 3);
        System.out.println("My Case 3: " + Arrays.toString(e) + "\n");
    }

    public static int[] solution(int[][] times, int times_limit) {
        int[][] graph = generateGraph(times);
        int vertices = times.length;
        int edges = graph.length;

        int[][] distances = bellmanFordAlgorithm(graph, vertices, edges);

        if(distances == null) { //infinite loop found, return save bunnies
            int[] savedBunnies = new int[times.length - 2];

            for(int i = 0; i < savedBunnies.length; i++) {
                savedBunnies[i] = i;
            }

            return savedBunnies;
        }
        else { //no infinite loop, process normally
            String bunnyString = "";

            for(int i = 1; i < times.length - 1; i++) {
                bunnyString += i;
            }

            for(int bunniesToSave = times.length - 2; bunniesToSave > 0; bunniesToSave--) {
                ArrayList<String> permutations = permutation(new char[bunniesToSave], 0, bunnyString);

                for(int i = 0; i < permutations.size(); i++) {
                    if(hasRepeat(permutations.get(i))) {
                        permutations.remove(i);
                        i--;
                    }
                }

                for(String path : permutations) {
                    if(generateTimeForPath("0" + path + Integer.toString(times.length - 1), distances) <= times_limit) {
                        int[] savedBunnies = new int[bunniesToSave];

                        int index = 0;
                        for(int i = 1; i < times.length - 1; i++) {
                            if(path.contains(Integer.toString(i))) {
                                savedBunnies[index] = i - 1;
                                index++;
                            }
                        }

                        return savedBunnies;
                    }
                }
            }
        }

        return new int[0];
    }

    public static int[][] generateGraph(int[][] times) {
        int[][] graph = new int[(times.length - 1) * times.length][3];

        for(int index = 0, source = 0, target = 0; index < graph.length; index++) {
            if(target == times.length) {
                target = 0;
                source++;
                index--;
            }
            else if(target == source) {
                target++;
                index--;
            }
            else {
                graph[index][0] = source;
                graph[index][1] = target;
                graph[index][2] = times[source][target];
                target++;
            }
        }

        return graph;
    } //generates the input values for the Bellman-Ford Algorithm

    public static int[][] bellmanFordAlgorithm(int[][] graph, int vertices, int edges) {
        int[][] distances = new int[vertices][];
        for(int position = 0; position < vertices; position++) {
            int[] distance = new int[vertices];
            Arrays.fill(distance, Integer.MAX_VALUE);
            distance[position] = 0;

            for (int i = 0; i < vertices - 1; i++) {
                for (int j = 0; j < edges; j++) {
                    if (distance[graph[j][0]] != Integer.MAX_VALUE && distance[graph[j][0]] + graph[j][2] < distance[graph[j][1]]) {
                        distance[graph[j][1]] = distance[graph[j][0]] + graph[j][2];
                    }
                }
            }

            for (int i = 0; i < edges; i++) {
                if (distance[graph[i][0]] != Integer.MAX_VALUE && distance[graph[i][0]] + graph[i][2] < distance[graph[i][1]]) {
                    return null;
                }
            }
            distances[position] = distance;
        }
        return distances;
    }

    public static int generateTimeForPath(String path, int[][] distances) {
        int time = 0;
        for(int i = 0; i < path.length() - 1; i++) {
            time += distances[Integer.parseInt(path.substring(i, i + 1))][Integer.parseInt(path.substring(i + 1, i + 2))];
        }
        return time;
    }

    public static ArrayList<String> permutation(char[] perm, int pos, String str) {
        ArrayList<String> permutations = new ArrayList<>();

        if (pos == perm.length) {
            permutations.add(new String(perm));
        }
        else {
            for (int i = 0 ; i < str.length() ; i++) {
                perm[pos] = str.charAt(i);
                permutations.addAll(permutation(perm, pos+1, str));
            }
        }
        return permutations;
    }

    public static boolean hasRepeat(String str) {
        for(int i = 0; i < str.length(); i++) {
            String temp = str.substring(0,i) + str.substring(i + 1);
            if(temp.contains(str.substring(i,i + 1))) {
                return true;
            }
        }
        return false;
    }
}

