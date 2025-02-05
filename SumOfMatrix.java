public class SumOfMatrix {

    public static void main(String[] args) {
        int[][] data = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        System.out.println(sumRecursion(data, 2));
    }

    public static int sumRecursion(int[][] data, int n) {
        //sum of n*n = sum(n-1*n-1) + sum(row) + sum(column)
        if (n == 0) {
            return data[n][n];
        }

        int sum = 0;

        for (int i = 0; i <= n; i++) {
            sum = sum + data[n][i] + data[i][n];
        }
        sum -= data[n][n];

        return sum + sumRecursion(data, n - 1);

    }
}
