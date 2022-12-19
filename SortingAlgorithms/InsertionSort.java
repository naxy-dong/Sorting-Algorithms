package SortingAlgorithms;
import Interface_And_Abs_Classes.SortingAlgorithm;

public class InsertionSort extends SortingAlgorithm {
    public InsertionSort() {
        super("Insertion Sort", true);
    }

    @Override
    public void sort(int[] arr) {
        int s = arr.length;
        for (int i = 1; i < s; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }
}
