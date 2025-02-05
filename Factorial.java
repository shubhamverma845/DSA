public class Factorial {

    public static int factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (n == 1) {
            return 1;
        } else {
            return factorial(n - 1) * n;
        }
    }
}
