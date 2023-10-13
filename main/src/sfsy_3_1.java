import java.util.Arrays;

public class sfsy_3_1 {
    public static int[] capacity = {10, 7, 3};
    public static int target = capacity[0] / 2;
    public static int steps=100;
    public static int[][] ans = new int[100][3];

    private static void getAns() {
        if(steps == 100){
            System.out.println("None");
            return;
        }
        System.out.print("最优解为操作"+steps+"次，具体操作为：");
        for (int i = 0; i < steps-1; i++) {
            System.out.print(ans[i][0] + "," + ans[i][1] + "," + ans[i][2] + " -> ");
        }
        System.out.println(ans[steps-1][0] + "," + ans[steps-1][1] + "," + ans[steps-1][2]);
    }

    private static void getBucket(int[][] bucket, int x){
        for (int i = 0; i < x-1; i++) {
            System.out.print(bucket[i][0] + "," + bucket[i][1] + "," + bucket[i][2] + " ---> ");
        }
        System.out.println(bucket[x-1][0] + "," + bucket[x-1][1] + "," + bucket[x-1][2]);

    }

    private static void dataSave(int[][] bucket){
        for (int i = 0; i < 100; i++){
            for (int j = 0; j < 3; j++){
                ans[i][j] = bucket[i][j];
            }
        }
    }

    private static boolean isOperable(int[][] bucket, int x, int i, int j) {
        if (bucket[x - 1][i] == 0) {
            return false;
        }
        if (bucket[x - 1][j] == capacity[j]) {
            return false;
        }
        if (i == j) {
            return false;
        }
        pourOil(bucket, x, i, j);
        for (int t = 0; t < x; t++) {
            if (bucket[t][0] == bucket[x][0] && bucket[t][1] == bucket[x][1] && bucket[t][2] == bucket[x][2]) {
                return false;
            }
        }
        return true;
    }
    private static void DFS(int[][] bucket, int x) {
        if (x > 100) {
            return;
        }

        if (bucket[x - 1][0] == target && bucket[x - 1][1] == target) {
            if(steps > x){
                steps = x;
                dataSave(bucket);
            }

            return;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isOperable(bucket, x, i, j)) {
                    DFS(bucket,x+1);
                }
            }
        }
    }

    private static void pourOil(int[][] bucket, int x, int i, int j) {
        bucket[x][0] = bucket[x-1][0];
        bucket[x][1] = bucket[x-1][1];
        bucket[x][2] = bucket[x-1][2];
        if (bucket[x-1][i] > capacity[j] - bucket[x - 1][j]) {
            bucket[x][i] = bucket[x-1][i] - (capacity[j] - bucket[x-1][j]);
            bucket[x][j] = capacity[j];
        } else {
            bucket[x][j] += bucket[x][i];
            bucket[x][i] = 0;
        }
    }

    public static void main(String[] args) {
        int[][] bucket = new int[100][3];
        bucket[0][0] = 10;
        bucket[0][1] = 0;
        bucket[0][2] = 0;
        DFS(bucket, 1);
        getAns();
    }

}

