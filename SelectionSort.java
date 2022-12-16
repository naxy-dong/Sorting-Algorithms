public class SelectionSort extends SortingAlgorithm {
    public SelectionSort() {
        super("Selection Sort", true);
    }

    /**
     * Time complexity: O(n^2)
     * 
     * @param arr
     */
    @Override
    public void sort(int[] arr) {
        int min_idx, s = arr.length;
        for (int i = 0; i < s - 1; i++) {
            min_idx = i;
            for (int j = i + 1; j < s; j++) {
                if (arr[min_idx] > arr[j]) {
                    min_idx = j;
                }
            }
            swap(arr, i, min_idx);
        }
    }
}
