// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
import java.util.Scanner;

public class sfsy_1_1_1 {

    public static void main(String[] args) {
        int num = 1;

        int[] cs = {9,8,7,6,5,4,3,2,1};
//        Scanner sc = new Scanner(System.in);
//        for (int i = 0; i < 9; i++){
//            cs[i] = sc.nextInt();
//        }
        int i;
        while(true){
            for (i = 10; i >= 2; i--){
                if(num%i != cs[10-i]){
                    break;
                }
            }
            if(i == 1){
                System.out.println("广场上士兵的最少可能人数是："+num);
                System.out.println("结果验证：");
                for (int j = 10; j >= 2; j--){
                    System.out.print(num%j + " ");
                }
                return;
            }
            num++;
        }

    }
}
