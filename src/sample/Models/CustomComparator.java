package sample.Models;

import java.util.Comparator;

public class CustomComparator implements Comparator<Record> {
    @Override
    public int compare(Record o1, Record o2) {
        return o1.getTime().compareTo(o2.getTime());
    }
}
