import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;

public class SortingAlg {
    // ideas, save it to a file
    // sort it by time
    private static boolean withRange = false;
    private static boolean withArray = false;
    private static String[] sortingOptions = { "normal bubble sort", "Efficient bubble sort", "selection sort",
            "insertion sort", "merge sort", "quick sort", "efficient quick sort", "counting sort", "heap sort" };
    private static String[] modes = { "Perform all sorting algorithms", "print list sorted",
            String.format("%-38s %s", "toggle print result with array", "- status: " + withArray),
            String.format("%-38s %s", "toggle range element", "- status: " + withArray) };
    private static Scanner sc = new Scanner(System.in);
    private static Random rd = new Random();

    public static void printMenu() {
        System.out.println("*** Sorting Options: ");
        for (int i = 1; i <= sortingOptions.length; i++) {
            System.out.printf("%-4s %,d - %s%n", " ", i, sortingOptions[i - 1]);
        }

        System.out.println("\n*** Modes: ");
        for (char i = 0; i < modes.length; i++) {
            System.out.printf("%-4s %c - %s%n", " ", i + 65, modes[i]);
        }
        System.out.printf("\n[q]uit\n");
    }

    public static void main(String[] args) {
        String string_input = "";
        printMenu();

        while (true) {
            System.out.print("Enter an option or [P]rint menu again: ");
            string_input = sc.nextLine().toLowerCase();

            if (string_input.equals("p")) {
                printMenu();
                continue;
            }
            if ((string_input.equals("quit")) || (string_input.equals("q"))) {
                break;
            }

            try {
                try {
                    // if it's a number
                    int num = Integer.parseInt(string_input);
                    if (num < 1 || num > sortingOptions.length) {
                        throw new IllegalArgumentException();
                    }
                    performSort(num);
                } catch (NumberFormatException e) {
                    // if it's a char
                    if (string_input.length() != 1) {
                        throw new IllegalArgumentException();
                    }
                    char ch = Character.toLowerCase(string_input.charAt(0));
                    performMode(ch);
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid input");
            }
        }
        System.out.println("Program terminated. Goodbye!");
    }

    private static void performMode(char mode) throws IllegalArgumentException {
        String s;
        switch (mode) {
            case 'a':
                printResult();
                break;
            case 'b':
                printResultSorted();
                break;
            case 'c':
                withArray = !withArray;
                s = withArray ? "ON" : "OFF";
                System.out.println("Printing w. array has been turned " + s + ".");
                break;
            case 'd':
                withRange = !withRange;
                s = withRange ? "ON" : "OFF";
                System.out.println("Ranged element has been turned " + s + ".");
                break;
            default:
                throw new IllegalArgumentException();
        }
    }

    private static void performSort(int option) throws NumberFormatException, IllegalArgumentException {
        long timeBefore, timeAfter;
        System.out.print("Enter no. of elements: ");
        int num = Integer.parseInt(sc.nextLine());
        int range = -1;
        if (withRange) {
            System.out.print("Enter the upper bound of the element: ");
            range = Integer.parseInt(sc.nextLine());
        }
        int[] arr;
        if (withRange)
            arr = randomArrayWithRange(num, range);
        else
            arr = randomArray(num);

        printArr("Unsorted array", arr);
        switch (option) {
            case 1:
                timeBefore = System.nanoTime();
                bubbleSort(arr);
                timeAfter = System.nanoTime();
                break;
            case 2:
                timeBefore = System.nanoTime();
                efficientBubbleSort(arr);
                timeAfter = System.nanoTime();
                break;
            case 3:
                timeBefore = System.nanoTime();
                selectionSort(arr);
                timeAfter = System.nanoTime();
                break;
            case 4:
                timeBefore = System.nanoTime();
                insertionSort(arr);
                timeAfter = System.nanoTime();
                break;
            case 5:
                timeBefore = System.nanoTime();
                mergeSort(arr);
                timeAfter = System.nanoTime();
                break;
            case 6:
                timeBefore = System.nanoTime();
                quickSort(arr);
                timeAfter = System.nanoTime();
                break;
            case 7:
                timeBefore = System.nanoTime();
                efficientQuickSort(arr);
                timeAfter = System.nanoTime();
                break;
            case 8:
                timeBefore = System.nanoTime();
                countSort(arr);
                timeAfter = System.nanoTime();
                break;
            case 9:
                timeBefore = System.nanoTime();
                heapSort(arr);
                timeAfter = System.nanoTime();
                break;
            default:
                throw new IllegalArgumentException();

        }
        printArr(sortingOptions[option - 1], arr, timeAfter - timeBefore);
    }

    private static void printResult() {
        long timeBefore, timeAfter;
        System.out.print("Enter no. of elements: ");
        int num = Integer.parseInt(sc.nextLine());
        int range = -1;
        if (withRange) {
            System.out.print("Enter the upper bound of the element: ");
            range = Integer.parseInt(sc.nextLine());
        }
        int[] arr;
        if (withRange)
            arr = randomArrayWithRange(num, range);
        else
            arr = randomArray(num);

        if (withArray)
            printArr("Unsorted array", arr);

        timeBefore = System.nanoTime();
        bubbleSort(arr);
        timeAfter = System.nanoTime();
        if (withArray)
            printArr("normal bubble sort", arr, timeAfter - timeBefore);
        else
            printArr("normal bubble sort", timeAfter - timeBefore);

        timeBefore = System.nanoTime();
        efficientBubbleSort(arr);
        timeAfter = System.nanoTime();
        if (withArray)
            printArr("Efficient bubble sort", arr, timeAfter - timeBefore);
        else
            printArr("Efficient bubble sort", timeAfter - timeBefore);

        timeBefore = System.nanoTime();
        selectionSort(arr);
        timeAfter = System.nanoTime();
        if (withArray)
            printArr("selection sort", arr, timeAfter - timeBefore);
        else
            printArr("selection sort", timeAfter - timeBefore);

        timeBefore = System.nanoTime();
        insertionSort(arr);
        timeAfter = System.nanoTime();
        if (withArray)
            printArr("insertion sort", arr, timeAfter - timeBefore);
        else
            printArr("insertion sort", timeAfter - timeBefore);

        timeBefore = System.nanoTime();
        mergeSort(arr);
        timeAfter = System.nanoTime();
        if (withArray)
            printArr("merge sort", arr, timeAfter - timeBefore);
        else
            printArr("merge sort", timeAfter - timeBefore);

        timeBefore = System.nanoTime();
        quickSort(arr);
        timeAfter = System.nanoTime();
        if (withArray)
            printArr("quick sort", arr, timeAfter - timeBefore);
        else
            printArr("quick sort", timeAfter - timeBefore);

        timeBefore = System.nanoTime();
        efficientQuickSort(arr);
        timeAfter = System.nanoTime();
        if (withArray)
            printArr("efficient quick sort", arr, timeAfter - timeBefore);
        else
            printArr("efficient quick sort", timeAfter - timeBefore);

        timeBefore = System.nanoTime();
        countSort(arr);
        timeAfter = System.nanoTime();
        if (withArray)
            printArr("counting sort", arr, timeAfter - timeBefore);
        else
            printArr("counting sort", timeAfter - timeBefore);

        timeBefore = System.nanoTime();
        heapSort(arr);
        timeAfter = System.nanoTime();
        if (withArray)
            printArr("heap sort", arr, timeAfter - timeBefore);
        else
            printArr("heap sort", timeAfter - timeBefore);
    }

    private static void printResultSorted() {

    }

    /*
     * Time complexity O(n^2)
     */
    public static void bubbleSort(int[] arr) {
        int s = arr.length;
        for (int i = 0; i < s; i++) {
            for (int j = 0; j < s - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /*
     * Special conditions:
     * - When the array is already sorted O(n)
     * -
     */
    public static void efficientBubbleSort(int[] arr) {
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

    /**
     * Time complexity: O(n^2)
     * 
     * @param arr
     */
    public static void selectionSort(int[] arr) {
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

    public static void insertionSort(int[] arr) {
        int s = arr.length;
        for (int i = 1; i < s; i++) {
            int j = i;
            while (j > 0 && arr[j] < arr[j - 1]) {
                swap(arr, j, j - 1);
                j--;
            }
        }
    }

    public static void mergeSort(int[] arr) {
        mergeSortHelper(arr, 0, arr.length - 1);
    }

    private static void mergeSortHelper(int[] arr, int l, int r) {
        int m = l + (r - l) / 2;

        if (l < r) {
            // calculate the middle index
            mergeSortHelper(arr, l, m);
            mergeSortHelper(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    private static void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        /* Create temp arrays */
        int L[] = new int[n1];
        int R[] = new int[n2];

        /* Copy data to temp arrays */
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        /* Merge the temp arrays */

        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray array
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    static void countSort(int[] arr) {
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;
        int count[] = new int[range];
        int output[] = new int[arr.length];
        for (int item : arr) {
            count[item - min]++;
        }

        for (int i = 1; i < count.length; i++) {
            count[i] += count[i - 1];
        }

        for (int i = arr.length - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = output[i];
        }
    }

    static void quickSort(int[] arr) {
        quickSortHelper(arr, 0, arr.length - 1);
    }

    static void quickSortHelper(int[] arr, int low, int high) {
        if (low < high) {

            // pi is partitioning index, arr[p]
            // is now at right place
            int pi = partition(arr, low, high);

            // Separately sort elements before
            // partition and after partition
            quickSortHelper(arr, low, pi - 1);
            quickSortHelper(arr, pi + 1, high);
        }
    }

    // this partition assumes that the pivots is always the last element
    private static int partition(int[] arr, int low, int high) {
        // pivot
        int pivot = arr[high];

        // Index of smaller element and
        // indicates the right position
        // of pivot found so far
        int i = (low - 1);

        for (int j = low; j <= high - 1; j++) {

            // If current element is smaller
            // than the pivot
            if (arr[j] < pivot) {

                // Increment index of
                // smaller element
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high);
        return (i + 1);
    }

    /**
     * Improvement #1: chooses the middle of the 3 elements
     * Improvement #2: use insertion sort when the array size
     * is "small" enough. In this implementation, I used insertion sort
     * when array size < 8
     */
    public static void efficientQuickSort(int[] arr) {
        efficientQuickSortHelper(arr, 0, arr.length - 1);
    }

    private static void efficientQuickSortHelper(int[] data, int first, int last) {
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
            insertionSort(data);
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

    public static void heapSort(int[] arr) {
        int N = arr.length;

        // Build heap (rearrange array)
        for (int i = N / 2 - 1; i >= 0; i--)
            heapify(arr, N, i);

        // One by one extract an element from heap
        for (int i = N - 1; i > 0; i--) {
            // Move current root to end
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            heapify(arr, i, 0);
        }
    }

    // private static void buildHeap(int[] arr) {

    // }

    private static void heapify(int arr[], int N, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < N && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < N && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            heapify(arr, N, largest);
        }
    }

    private static int[] randomArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i;
        }

        for (int i = 0; i < size; i++) {
            int randIdx1 = returnRandomIndex(size);
            int randIdx2 = returnRandomIndex(size);
            swap(arr, randIdx1, randIdx2);
        }
        return arr;
    }

    private static int[] randomArrayWithRange(int size, int range) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rd.nextInt(range);
        }

        for (int i = 0; i < size; i++) {
            int randIdx1 = returnRandomIndex(size);
            int randIdx2 = returnRandomIndex(size);
            swap(arr, randIdx1, randIdx2);
        }
        return arr;
    }

    private static int returnRandomIndex(int size) {
        return (int) (Math.random() * size);
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    private static void printArr(String msg, long timeDiff) {
        System.out.printf("*** %-25s | %-6s milliseconds \n", msg, (double) (timeDiff) / 1_000_000);
    }

    private static void printArr(String msg, int[] arr) {
        System.out.printf("*** %s *** \n%s\n", msg, Arrays.toString(arr));
    }

    private static void printArr(String msg, int[] arr, long timeDiff) {
        System.out.printf("\n%s\n *** %-25s | %-6s milliseconds\n",
                Arrays.toString(arr),
                msg,
                (double) (timeDiff) / 1_000_000);
    }
}
