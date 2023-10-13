import java.util.Scanner;

public class sfsy_1_2 {
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = 6;
//        n = sc.nextInt();
        int[][] arr = new int[n][n];
        int x = 0, y = 0;
        int num = 1;
        while(num <= n*n){
            while(x != 0 && y != n-1){
                arr[x][y] = num;
                num++;
                x--;
                y++;
            }
            arr[x][y] = num;
            num++;
            if(num > n*n)
                break;
            if(y < n-1)
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
            if(num > n*n)
                break;
            if(x < n-1)
                x++;
            else
                y++;
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.printf("%-3d",arr[i][j]);
            }
            System.out.println();
        }
    }



}
