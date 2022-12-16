public class EfficientBubbleSort extends SortingAlgorithm{
    public EfficientBubbleSort() {
        super("Efficient Bubble Sort", true);
    }

    /*
     * Worse case: Time complexity O(n^2)
     * Special Best case:
     * - When the array is already sorted O(n)
     */
    @Override
    public void sort(int[] arr) {
        int s = arr.length;
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }
}
