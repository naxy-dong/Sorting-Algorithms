import java.util.Random;

public class ArrayMaker{
    private static Random rd = new Random();

    public static int[] randomArray(int size) {
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

    public static int[] randomArrayWithRange(int size, int range) {
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
}