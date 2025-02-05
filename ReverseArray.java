public class ReverseArray {
    public static void main(String[] args) {
        int[] data = new int[]{2, 4, 5, 7, 8, 9, 12, 14, 17, 19, 22, 25, 27, 28, 33, 37};

        reverse(data, 0, data.length - 1);

        for(int i : data) {
            System.out.println(i);
        }
    }

    public static void reverse(int[] data, int start, int end) {

        if (start < end) {
            int temp = data[start];
            data[start] = data[end];
            data[end] = temp;

            reverse(data, start + 1, end - 1);
        }
    }
}
