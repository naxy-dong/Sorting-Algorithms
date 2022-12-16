import java.util.Arrays;
import java.util.Scanner;

public class SortingAlgRunner {
    // ideas, save it to a file
    // sort it by time
    private static Scanner sc = new Scanner(System.in);
    private static boolean withRange = false;
    private static boolean withArray = false;
    private static SortingAlgorithm[] sortingOptions = { new BubbleSort(), new EfficientBubbleSort(),
            new SelectionSort(), new InsertionSort(), new MergeSort() ,
            new QuickSort(), new EfficientQuickSort(),  new HeapSort(),new CountingSort()};

    private static String[] modes = { "Perform all sorting algorithms", "print list sorted",
            String.format("%-38s %s", "toggle print result with array", "- status: " + withArray),
            String.format("%-38s %s", "toggle range element", "- status: " + withRange) };


    public static void printMenu() {
        System.out.println("*** Sorting Options: ");
        for (int i = 1; i <= sortingOptions.length; i++) {
            System.out.printf("%-4s %,d - %s%n", " ", i, sortingOptions[i - 1].getName());
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
        // Option is 1 - 9
        if (option <= 0 || option > sortingOptions.length)
            throw new IllegalArgumentException();
        System.out.print("Enter no. of elements: ");
        int num = Integer.parseInt(sc.nextLine());
        int range;
        int[] arr;
        if (withRange) {
            System.out.print("Enter the upper bound of the element: ");
            range = Integer.parseInt(sc.nextLine());
            arr = ArrayMaker.randomArrayWithRange(num, range);
        } else
            arr = ArrayMaker.randomArray(num);
        printArr("Unsorted array", arr);
        sortingOptions[option - 1].sort_array(arr);
        printArr(sortingOptions[option - 1].getName(), arr, sortingOptions[option - 1].getTime());
    }

    private static void printResult() {
        System.out.print("Enter no. of elements: ");
        int num = Integer.parseInt(sc.nextLine());
        int range;
        int[] arr;

        if (withRange) {
            System.out.print("Enter the upper bound of the element: ");
            range = Integer.parseInt(sc.nextLine());
            arr = ArrayMaker.randomArrayWithRange(num, range);
        } else
            arr = ArrayMaker.randomArray(num);
        if (withArray)
            printArr("Unsorted array", arr);

        for (SortingAlgorithm alg : sortingOptions) {
            alg.sort_array(arr);
            if (withArray)
                printArr(alg.getName(), arr, alg.getTime());
            else
                printArr(alg.getName(), alg.getTime());
        }
    }

    private static void printResultSorted() {

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
