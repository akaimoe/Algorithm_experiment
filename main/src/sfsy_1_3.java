public class sfsy_1_3 {
    public static int[] used = new int[10], pd = new int[10];
    public static void ans_test() {
        if ((used[1] * 10 + used[2]) * (used[3] * 100 + used[4] * 10 + used[5]) == used[6] * 1000 + used[7] * 100 + used[8] * 10 + used[9]) {
            System.out.printf("%d%dx%d%d%d=%d%d%d%d", used[1], used[2], used[3], used[4], used[5], used[6], used[7], used[8], used[9]);
            System.out.println();
        }
    }
    public static void dfs(int k){
        int i;
        if(k == 9){
            ans_test();
        }
        for (i = 1; i <=9; i++){
            if(pd[i] == 0){
                used[k+1] = i;
                pd[i] = 1;
                dfs(k+1);
                pd[i] = 0;
            }
        }
    }
    public static void main(String[] args) {
        dfs(0);
    }
}
