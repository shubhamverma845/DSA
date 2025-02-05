public class ProductRecursion {
    public static void main(String[] args) {
        int m = 45, n = 31;
        System.out.println(productRecursion(m, n));
    }

    public static int productRecursion(int m, int n) {
        if (n == 0) {
            return 0;
        }

        return m + productRecursion(m, n - 1);
    }
}
