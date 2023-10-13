import java.util.Scanner;

public class sfsy_2_3 {

    public static double bestChance(double avePrice[], double dp[], int year){
        double lowerPrice = avePrice[1];
        int y=1;
        if(year == 0)
            return 0;
        if(year == 1){
            return avePrice[1];
        }
        else {
            for (int i = 1; i < year; i++){
                if(avePrice[i] < lowerPrice){
                    lowerPrice = avePrice[i];
                    y = i;
                }

            }
            return dp[y] + bestChance(avePrice, dp, year - y );
        }

    }
    public static void Sell(int upkeep[], int price[], int year, int newPrice){
        int upkeepSum = 0;
        double[] dp = new double[100];
        double[] avePrice = new double[100];
        int cost = 0;
        for (int i = 0; i <= year; i++){
            dp[i] = newPrice - price[i] + upkeepSum;
            upkeepSum += upkeep[i];
        }
        for (int i = 1; i < year; i++){
            avePrice[i] = dp[i] / i;
        }
        System.out.println(year+"年最低价格为："+bestChance(avePrice, dp, year)+"万元");

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n;
        int[] upkeep = new int[100];
        int[] price = new int[100];
        int newPrice;
        int year;
        System.out.print("输入表格长度：");
        n = sc.nextInt();
        System.out.print("输入新车价格：");
        newPrice = sc.nextInt();
        System.out.print("输入使用年限：");
        year = sc.nextInt();
        System.out.print("输入保养价格表：");
        for (int i=0; i < n; i++){
            upkeep[i] = sc.nextInt();
        }
        System.out.print("输入二手售价表：");
        for (int i=1; i <= n; i++){
            price[i] = sc.nextInt();
        }
        Sell(upkeep,price,year,newPrice);

    }
}
