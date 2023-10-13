public class sfsy_1_1_2 {
    public static long InverseElement(long num, long mod) {
        long m0 = mod;
        long x0 = 0;
        long x1 = 1;

        if (mod == 1) {
            return 0;
        }

        while (num > 1) {
            long q = num / mod;
            long t = mod;

            mod = num % mod;
            num = t;

            t = x0;
            x0 = x1 - q * x0;
            x1 = t;
        }

        if (x1 < 0) {
            x1 += m0;
        }

        return x1;
    }

    public static void main(String[] args) {
        long[] cs = {9,8,7,6,5,4,3,2,1};
        long[] mod = {23,19,17,13,11,7,5,3,2};
        long m;
        long M = 1;
        long num = 0;
        long t;
        for (int i = 0; i < cs.length; i++){
            M *= mod[i];
        }
        for (int i = 0; i < cs.length; i++){
            m = M / mod[i];
            t = InverseElement(m, mod[i]);
            if(t < 0){
                System.out.println("出现非互质数，无法使用中国剩余定理");
                return;
            }
            num += m*cs[i]*t;
        }
        System.out.println("广场上士兵的最少可能人数是："+num);
        System.out.println("结果验证：");
        for (int i = 0; i < cs.length; i++){
            System.out.print(num%mod[i] + " ");
        }

    }

}
