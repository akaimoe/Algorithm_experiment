public class sfsy_2_1 {
    public static double func(double num){
        return num*num*num - 3.0*num - 1.0;
    }
    public static double funcD(double num){
        return 3.0*num*num - 3.0;
    }
    public static double Dichotomy(double up, double down){
        double num = (up+down)/2.0;
        while(up-down >= 1e-7){
            if(func(num) < 0){
                down = num;
                num = (up+down)/2.0;
            }
            else {
                up = num;
                num = (up+down)/2.0;
            }
        }
        return num;
    }
    public static double Newton(double num){
        while (Math.abs(func(num)) >= 1e-7){
            num -= func(num)/funcD(num);
        }
        return num;
    }

    public static void main(String[] args) {
        double up=3.0, down=1.0, num=4.0;
        double ans = 2.0 * Math.cos(Math.toRadians(20));
        double dichotomy = Dichotomy(up,down);   //二分法
        double newton = Newton(num);
        System.out.println("二分法结果为:"+dichotomy+" 误差为:"+Math.abs(dichotomy-ans));
        System.out.println("牛顿迭代法结果为:"+newton+" 误差为:"+Math.abs(newton-ans));
    }
}
