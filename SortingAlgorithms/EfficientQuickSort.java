package SortingAlgorithms;
import Interface_And_Abs_Classes.SortingAlgorithm;

public class EfficientQuickSort extends SortingAlgorithm {
    public EfficientQuickSort() {
        super("Efficient Quick Sort", true);
    }

    /**
     * Improvement #1: chooses the middle of the 3 elements
     * Improvement #2: use insertion sort when the array size
     * is "small" enough. In this implementation, I used insertion sort
     * when array size < 8
     */
    @Override
    public void sort(int[] arr) {
        efficientQuickSortHelper(arr, 0, arr.length - 1);

    }

    private void efficientQuickSortHelper(int[] data, int first, int last) {
        int left, right, pivot;
        if (first >= last)
            return;
        left = first;
        right = last;
        // choose the middle element as the pivot
        pivot = middleOfThree(data[left], data[right], data[left + (right - left) / 2]);
        do {
            while (data[left] < pivot)
                left++;
            while (data[right] > pivot)
                right--;
            if (left <= right)
                swap(data, left++, right--);
        } while (left <= right);
        if (data.length > 8) {
            efficientQuickSortHelper(data, first, right);
            efficientQuickSortHelper(data, left, last);
        } else {
            // insertion sort
            (new InsertionSort()).sort(data);
        }
    }

    public static int middleOfThree(int a, int b,
            int c) {
        if (a > b) {
            if (b > c)
                return b;
            else if (a > c)
                return c;
            else
                return a;
        } else {
            // Decided a is not greater than b.
            if (a > c)
                return a;
            else if (b > c)
                return c;
            else
                return b;
        }
    }

}
