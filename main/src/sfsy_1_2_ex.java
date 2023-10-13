import java.util.Scanner;

public class sfsy_1_2_ex {
    public static void main(String[] args) {
        int n, m;
        Scanner sc = new Scanner(System.in);
        n = 3;
        m = 4;
//        n = sc.nextInt();
//        m = sc.nextInt();
        int[][] arr = new int[n][m];
        int x = 0, y = 0;
        int num = 1;
        while(num <= n*m){
            while(x != 0 && y != m-1){
                arr[x][y] = num;
                num++;
                x--;
                y++;
            }
            arr[x][y] = num;
            num++;
            if(num > n*m)
                break;
            if(y < m-1)
                y++;
            else
                x++;
            while(y != 0 && x != n-1){
                arr[x][y] = num;
                num++;
                x++;
                y--;
            }
            arr[x][y] = num;
            num++;
            if(num > n*m)
                break;
            if(x < n-1)
                x++;
            else
                y++;
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < m; j++){
                System.out.printf("%-3d",arr[i][j]);
            }
            System.out.println();
        }
    }



}
