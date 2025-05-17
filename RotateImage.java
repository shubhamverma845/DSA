public class RotateImage {
    public static void main(String[] args) {
        int[][] matrix = new int[][]{{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};

        printMatrix(matrix);
        rotate(matrix);
        System.out.println("Rotated Matrix");
        printMatrix(matrix);


        System.out.println();
        int[][] matrix2 = new int[][]{{5, 1, 9, 11}, {2, 4, 8, 10}, {13, 3, 6, 7}, {15, 14, 12, 16}};

        printMatrix(matrix2);
        rotate(matrix2);
        System.out.println("Rotated Matrix");
        printMatrix(matrix2);
    }

    public static void rotate(int[][] matrix) {

        int min = 0;
        int max = matrix.length - 1;

        while (min < max) {
            for (int i = max - min; i > 0; i--) {
                shift(matrix, min, max);
            }

            max--;
            min++;
        }
    }

    public static void shift(int[][] matrix, int min, int max) {
        Integer curr = matrix[min + 1][min];

        //top row
        for (int i = min, j = min; j <= max; j++) {
            int temp = matrix[i][j];
            matrix[i][j] = curr;
            curr = temp;
        }

        for (int i = min + 1, j = max; i <= max; i++) {
            int temp = matrix[i][j];
            matrix[i][j] = curr;
            curr = temp;
        }

        for (int j = max - 1, i = max; j >= min; j--) {
            int temp = matrix[i][j];
            matrix[i][j] = curr;
            curr = temp;
        }

        for (int i = max - 1, j = min; i > min; i--) {
            int temp = matrix[i][j];
            matrix[i][j] = curr;
            curr = temp;
        }
    }

    public static void printMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.printf("%3d ", matrix[i][j]);
            }

            System.out.println();
        }
    }
}
