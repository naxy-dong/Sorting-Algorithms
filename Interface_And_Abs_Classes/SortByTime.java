package Interface_And_Abs_Classes;

import java.util.Comparator;

public class SortByTime implements Comparator<SortingAlgorithm>{
    @Override
    public int compare(SortingAlgorithm o1, SortingAlgorithm o2) {
        return (int) (o1.getTime()-o2.getTime());
    }
}
