public class BubbleSort extends SortingAlgorithm {
    public BubbleSort() {
        super("Bubble Sort", true);
    }

    /*
     * Worse case: Time complexity O(n^2)
     * Best case: Time complexity O(n^2)
     */
    @Override
    public void sort(int[] arr) {
        int s = arr.length;
        boolean swapped;
        for (int i = 0; i < s; i++) {
            swapped = false; // we have a boolean that indicate if we swapped
            for (int j = 0; j < s - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                    swapped = true;
                }
            }
            if (!swapped)
                break; // if we don't swap anything, then the array is sorted
        }
    }
}