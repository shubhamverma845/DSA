public class BinarySearch {
    public static void main(String[] args) {
        int[] data = new int[]{2, 4, 5, 7, 8, 9, 12, 14, 17, 19, 22, 25, 27, 28, 33, 37};

        System.out.println(binarySearch(data, 22, 0, data.length - 1));
    }

    public static boolean binarySearch(int[] data, int target, int low, int high) {
        if (low > high) {
            return false;
        }

        int mid = low + (high - low) / 2;

        if (data[mid] == target) {
            return true;
        } else if (data[mid] < target) {
            return binarySearch(data, target, mid + 1, high);
        } else {
            return binarySearch(data, target, low, mid - 1);
        }
    }
}

