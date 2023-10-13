import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class sfsy_1_5 {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = 6;
//        n = sc.nextInt();
        List<List<Integer>> partitions = partition(n);

        for (List<Integer> partition : partitions
        ) {
            System.out.println(partition);
        }
    }

    public static List<List<Integer>> partition(int n) {
        List<List<Integer>> result = new ArrayList<>();
        DFS(result, new ArrayList<>(), n, n);
        return result;
    }

    private static void DFS(List<List<Integer>> result, List<Integer> current, int n, int remaining) {
        if (remaining == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = Math.min(n, remaining); i >= 1; i--) {
            current.add(i);
            DFS(result, current, i, remaining - i);
            current.remove(current.size() - 1);
        }
    }
}
