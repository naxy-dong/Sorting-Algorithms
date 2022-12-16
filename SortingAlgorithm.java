//Design choice: ultimately I want a list of SortingAlgorithm object that can run in a loop. 
//Option1: For each sorting algorithm, I would have its own class with sort method.
//Option2: Create a giant class that contains all the sorting methods.

//Extra credit: use data visualization tool to simulate sorting process.
public abstract class SortingAlgorithm implements SortingInterface{
    //Private variables, can't be an interface because boolean inplace has to be initialized.
    private String name;
    private long time;  //default to 0
    private boolean inplace;
    //Idea: Best case and the worst case time complexity
    
    public SortingAlgorithm(String name){
        this.name = name;
        this.time = 0;
        this.inplace = false;
    }

    public SortingAlgorithm(String name, boolean inplace){
        this.name = name;
        this.inplace = inplace;
    }

    public void sort_array(int[] arr){
         long startTime = System.nanoTime();
         sort(arr);
         long endTime = System.nanoTime();
         time = endTime - startTime;
    };

    public void swap(int[] lst, int idx1, int idx2){
        int temp = lst[idx1];
        lst[idx1] = lst[idx2];
        lst[idx2] = temp;
    }

    public String toString(){
        //prints out info about this algorithm
        String inplaceStr = inplace ? "inplace" : "not-inplace";
        String timeStr = time == 0 ? "You have no performed any sort" : time/1_000_000 + "milliseconds";
        return String.format("***%-20s|%-10s|%-10s", name, timeStr, inplaceStr);
    }

    public String getName() {
        return name;
    }

    public long getTime() {
        return time;
    }

    public boolean isInplace() {
        return inplace;
    }

}

