import java.util.Arrays;

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = new int[]{5,8,4,6,97,1,2,59,8,4,566,2};
        insertionSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void insertionSort(int[] arr) {
        for(int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            int k = i;
            while(k > 0 && curr < arr[k - 1]) {
                arr[k] = arr[k - 1];
                k--;
            }
            arr[k]  = curr;
        }
    }
}
