public class sfsy_2_2 {
    public static int sum(int num[]){
        return num[0]*1 + num[1]*2 + num[2]*5 + num[3]*10;
    }

    public static void main(String[] args) {
        int[] num = new int[4];
        int plan = 0;
        for (num[3] = 1; num[3] <= 9; num[3]++) {
            for (num[2] = 1; num[2] <= 19; num[2]++) {
                for (num[1] = 1; num[1] <= 42; num[1]++) {
                    for (num[0] = 1; num[0] <= 83; num[0]++) {
                        if (sum(num) <= 98)
                            num[0]++;
                        if (sum(num) == 100) {
                            System.out.printf("1元数量:%d 2元数量:%d 5元数量:%d 10元数量:%d\n", num[0], num[1], num[2], num[3]);
                            plan++;
                        }
                        if (sum(num) > 100) {
                            break;
                        }
                    }
                }
            }
        }
        System.out.println("共"+plan+"种分钱方法");
    }
}
