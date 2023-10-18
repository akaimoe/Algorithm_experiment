import java.util.*;
import java.text.DecimalFormat;
import java.util.function.DoubleToIntFunction;

public class sfsy_3_3 {
    public static int m, n, mode = 0;
    public static int[][] pd = new int[3][4];
    public static int found = 0;
    public static Stack<int[]> changePlace = new Stack<int[]>();
    public static double min = 999;
    private static void changeRow(int n, int k, double[][] Mat) {
        double[] temp = new double[n + 1];
        for (int i = k; i < n; i++) {
            if (i + 1 == n && Mat[k][k] == 0) {
                System.out.println("无解");
                System.exit(1);
            }

            for (int j = 0; j < n + 1; j++) {
                temp[j] = Mat[k][j];
                Mat[k][j] = Mat[i + 1][j];
                Mat[i + 1][j] = temp[j];
            }
            if (Mat[k][k] != 0)
                return;

        }
    }
    private static void simple(double[][] Mat) {
        int n = Mat.length;
        for (int k = 0; k < n; k++) {
            if (Mat[k][k] == 0) {
                changeRow(n, k, Mat);
            }

            for (int i = 0; i < n; i++) {
                double temp = Mat[i][k];
                for (int j = 0; j < n + 1; j++) {
                    if (i < k)
                        break;
                    if (temp == 0)
                        continue;
                    if (temp != 1) {
                        Mat[i][j] /= temp;
                    }

                    if (i > k)
                        Mat[i][j] -= Mat[k][j];
                }
            }
        }

    }
    private static double[] solving(double[][] coeMat) {
        double[] resMat = new double[coeMat.length];
        for (int i = coeMat.length - 1; i > -1; i--) {
            double temp = 0;
            for (int j = coeMat[i].length; j > 0; j--) {
                if (coeMat[i][j - 1] != 0) {
                    if (j == coeMat[i].length) {
                        temp = coeMat[i][j - 1];
                    } else if (j - 1 > -1 && resMat[j - 1] != 0) {
                        temp -= coeMat[i][j - 1] * resMat[j - 1];
                    } else {
                        resMat[i] = temp / coeMat[i][j - 1];
                        continue;
                    }
                }
            }
        }
        return resMat;
    }
    private static double SumPrice(double priceMat[][], double NEMat[][]){
        double sum = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(NEMat[i][j] != 0){
                    sum += NEMat[i][j] * priceMat[i][j];
//                    System.out.print(NEMat[i][j]+"*"+priceMat[i][j]+"+");
                }
            }
        }
        return sum;
    }
    private static void getMat(int Mat[][], int m, int n){
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                System.out.print(Mat[i][j]+" ");
            }
            System.out.println();
        }
    }
    private static void getMat(double Mat[][]){
        for (int i = 0; i < Mat.length; i++){
            for (double o:Mat[i]
                 ) {
                System.out.printf("%7.1f",o);
            }
            System.out.println();
        }
    }
    private static void NorthEast(double priceMat[][], double NEMat[][]){
        double PriceMat[][] = new double[m+1][n+1];
        for (int i = 0; i <= m; i++){
            for (int j = 0; j <= n; j++){
                PriceMat[i][j] = priceMat[i][j];
            }
        }
        int i=0, j=0;
        while (i<m || j<n){
            if(PriceMat[i][n] > PriceMat[m][j]){
                NEMat[i][j] = PriceMat[m][j];
                PriceMat[i][n] -= PriceMat[m][j];
                PriceMat[m][j] -= PriceMat[m][j];
                j++;
            }
            else if(PriceMat[i][n] < PriceMat[m][j]){
                NEMat[i][j] = PriceMat[i][n];
                PriceMat[m][j] -= PriceMat[i][n];
                PriceMat[i][n] -= PriceMat[i][n];
                i++;
            }
            else {
                NEMat[i][j] = PriceMat[i][n];
                PriceMat[m][j] -= PriceMat[i][n];
                PriceMat[i][n] -= PriceMat[i][n];
                i++;
                j++;
            }
        }
    }

    private static  boolean Poten(double coeMat[][], double priceMat[][], double NEMat[][]){
        int l = 0;
        int emptysize = 0;
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(NEMat[i][j] != 0){
                    if(l>=m+n){
                        return false;
                    }
//                    System.out.println("l="+l+" i="+i+" j="+j+" NEMat[i][j]="+NEMat[i][j]);
                    coeMat[l][j] = 1;
                    coeMat[l][n+i] = 1;
                    coeMat[l][7] = priceMat[i][j];
                    l++;
                }
            }
        }
        while(l < m+n){
            coeMat[l][emptysize] = 1;
            emptysize++;
            l++;
        }
        return true;
    }

    private static double[][] setTestArr(double resMat[], double priceMat[][], double NEMat[][]) {
        double[][] arr = new double[3][4];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(NEMat[i][j] == 0){
                    arr[i][j] = priceMat[i][j] - resMat[j] - resMat[i+n];
                }
            }
        }
        return arr;
    }
    private static int Search(int x, int y, int x_n, int y_n, double NEMat[][], int mod, int step){   //1上 2右 3下 4左
        switch (mod){
            case 1:{
                while(x_n > 0){
                    x_n--;
                    if(NEMat[x_n][y_n] != 0 && pd[x_n][y_n] == 1)
                        break;
                    if((NEMat[x_n][y_n] != 0 || (x == x_n && y == y_n && step > 2)) && pd[x_n][y_n] == 0)
                        return x_n;
                }
                return -1;
            }
            case 2:{
                while(y_n < n-1){
                    y_n++;
                    if(NEMat[x_n][y_n] != 0 && pd[x_n][y_n] == 1)
                        break;
                    if((NEMat[x_n][y_n] != 0 || (x == x_n && y == y_n && step > 2)) && pd[x_n][y_n] == 0)
                        return y_n;
                }
                return -1;
            }
            case 3:{
                while(x_n < m-1){
                    x_n++;
                    if(NEMat[x_n][y_n] != 0 && pd[x_n][y_n] == 1)
                        break;
                    if((NEMat[x_n][y_n] != 0 || (x == x_n && y == y_n && step > 2)) && pd[x_n][y_n] == 0)
                        return x_n;
                }
                return -1;
            }
            case 4:{
                while(y_n > 0){
                    y_n--;
                    if(NEMat[x_n][y_n] != 0 && pd[x_n][y_n] == 1)
                        break;
//                    System.out.println("搜索结果x-n="+x_n+" y_n="+y_n+" pd="+pd[x_n][y_n]);
                    if((NEMat[x_n][y_n] != 0 || (x == x_n && y == y_n && step > 2)) && pd[x_n][y_n] == 0)
                        return y_n;
                }
                return -1;
            }
        }
        return -2;
    }
    private static void DFS(int x, int y, int x_n, int y_n, double NEMat[][], int step, int type){
        if(x == x_n && y == y_n && step > 1){
            if(found == 0){
                Stack<int[]> saveChangePlace = new Stack<int[]>();
                int[] save = {-1,-1};
                int pr = 0;     //1上下 2左右
                int ppr = 0;
//                System.out.println("test1 "+step);
                double add = 999;
                int cs = 0;
//                System.out.println("change:");
                for (int o[]:changePlace
                ) {
//                    System.out.println(o[0]+" "+o[1]);
                    if(save[1] == o[1])
                        pr = 2;
                    else
                        pr = 1;
                    if(ppr == pr)
                        saveChangePlace.pop();

                    saveChangePlace.push(o);
//                    NEMat[o[0]][o[1]] += add;
//                    add = -add;
                    save[0] = o[0];
                    save[1] = o[1];
                    ppr = pr;
                }
                for (int o[]:saveChangePlace
                ) {
                    cs++;
                    if(cs%2!=0)
                        continue;

//                    System.out.print(NEMat[o[0]][o[1]]+" ");
                    if(add > NEMat[o[0]][o[1]] && NEMat[o[0]][o[1]] != 0)
                        add = NEMat[o[0]][o[1]];
                }
//                System.out.println("add="+add);
//                System.out.println("SaveChange:");
                for (int o[]:saveChangePlace
                     ) {
//                    System.out.println(o[0]+" "+o[1]);
                    NEMat[o[0]][o[1]] += add;
                    add = -add;
                }
            }
            found++;
            return;
        }
        if(step > 0)
            pd[x_n][y_n] = 1;
        step++;
        for (int i = 1; i <= 4; i++){
//            System.out.println("x="+x_n+" y="+y_n);
            if((type-2>0 && type - 2 == i) || (type-2<=0 && type + 2 == i)){
                continue;
            }
            if(Search(x, y, x_n, y_n, NEMat, i, step) >= 0){
                int[] item = {x_n, y_n};
//                System.out.println(item[0]+" "+item[1]+" "+step);
                changePlace.push(item);
                if(i == 2 || i == 4){
                    DFS(x, y, x_n, Search(x, y, x_n, y_n, NEMat, i, step), NEMat, step, i);
                }
                else {
                    DFS(x, y, Search(x, y, x_n, y_n, NEMat, i, step), y_n,  NEMat, step, i);
                }


                changePlace.pop();
            }
        }
        pd[x_n][y_n] = 0;
    }
    private static int[] FoundMin(double testMat[][]){

        int[] out = new int[2];
        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                if(testMat[i][j] < min){
                    min = testMat[i][j];
                    out[0] = i;
                    out[1] = j;
                }
            }
        }

        return out;
    }
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("自动输入模式");
        m = 3;
        n = 4;
        int l = 7;
        double[][] priceMat = {
                {3, 11, 3,  10, 7},
                {1, 9,  2,  8,  4},
                {7, 4,  10, 5,  9},
                {3, 6,  5,  6,  0}};

        double[][] NEMat = new double[m][n];
        NorthEast(priceMat, NEMat);
//        getMat(NEMat);
//        getMat(priceMat);
//        double[][] NEMat = {{0,0,4,3},{3,0,1,0},{0,6,0,3}};
//        System.out.println("beginNEMat:");
//        getMat(NEMat);
//        System.out.println();
        while (true){
            double[][] coeMat = new double[7][8];
//            System.out.println(SumPrice(priceMat, NEMat));
            if(!Poten(coeMat, priceMat, NEMat))
                return;
//            System.out.println("coeMat:");
//            getMat(coeMat);
//            System.out.println();

            simple(coeMat);
            double[] resMat = solving(coeMat);
//        for (double o:resMat
//        ) {
//            System.out.print(o+" ");
//        }
//        System.out.println();
//        double[][] mathMatrix = mathDeterminantCalculation(transferMatrix(
//                coeMat, valueMat));
            double[][] testMat = setTestArr(resMat,priceMat,NEMat);
//            System.out.println("testMat:");
//            getMat(testMat);
//            System.out.println();
//            System.out.println();
            int[] emptyStart = FoundMin(testMat);
//            System.out.println("min="+min+" x="+emptyStart[0] + " y=" + emptyStart[1]);
            if(min >= 0){
                System.out.print("最少成本为：");
                System.out.println(SumPrice(priceMat, NEMat));
                getMat(NEMat);
                break;
            }

//        System.out.println(emptyStart[0] + " " + emptyStart[1]);
            DFS(emptyStart[0], emptyStart[1], emptyStart[0], emptyStart[1], NEMat, 0, 10);
//            System.out.println();
//            getMat(NEMat);
//            System.out.println("-------------------------------------------------------------");
            min = 999;
            found = 0;
        }

    }
}
