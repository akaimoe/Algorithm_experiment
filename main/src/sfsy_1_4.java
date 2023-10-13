import java.util.Scanner;

public class sfsy_1_4 {
    public static int add(int x, int y){
        int sum = 0;
        for (int i = x; i <=y; i++)
            sum += i;
        return sum;
    }

    public static void output(int x, int y, int n){
        int i;
        for (i = x; i < y; i++)
            System.out.print(i+"+");
        System.out.println(i);
    }

    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = 27;
//        n = sc.nextInt();
        int x=1, y=2;
        while(y <= n/2 + 1){
            int sum = add(x,y);
            if(sum == n){
                output(x, y, n);
                y++;
            }
            else if(sum < n)
                y++;
            else
                x++;
        }

    }
}
