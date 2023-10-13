import java.util.Scanner;
import java.util.Stack;

public class sfsy_3_2 {
    public static int[][] mat = new int[9][9];
    public static void setMat(){
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                mat[i][j]=sc.nextInt();
            }
        }
    }
    public static void getMat(){
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                System.out.print(mat[i][j]+"  ");
            }
            System.out.println();
        }
    }
    public static int Empty(){
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < 9; i++){
            for (int j = 0; j < 9; j++){
                if(mat[i][j] == 0)
                    return i*9+j;
            }
        }
        return -1;
    }
    public static boolean Check(int x, int y, int num){
        int[] dx= {1,1,1,0,0,0,-1,-1,-1},dy={1,0,-1,1,0,-1,1,0,-1};
        for (int i = 0; i < 9; i++){
            if(mat[x][i]==num || mat[i][y]==num)
                return false;
        }
        int blockX = x/3*3+1, blockY = y/3*3+1;
        for (int i = 0; i < 9; i++){
            if(mat[blockX+dx[i]][blockY+dy[i]]==num)
                return false;
        }
        return true;
    }
    public static boolean Process(){
        Stack<Integer> st = new Stack<Integer>();
        st.push(Empty());
        int num;
        while (!st.empty() && st.peek()>-1){
            int x = st.peek()/9, y = st.peek()%9;
            for (num = mat[x][y] + 1; num <=9 && !Check(x, y, num); num++);
            if(num > 9){
                mat[x][y] = 0;
                st.pop();
            }
            else{
                mat[x][y] = num;
                st.push(Empty());
            }
        }
        return !st.empty();
    }

    public static void main(String[] args) {
        System.out.println("请输入矩阵：");
        setMat();
        System.out.println("结果为：");
        if(Process())
            getMat();
        else
            System.out.println("None");
    }


}
