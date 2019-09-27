package sample.Models;

public class Record {
    final private Double time;
    final private String name;

    public Record(String name, Double time){
        this.name = name;
        this.time = time;
    }

    public Double getTime() {
        return time;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("Hrac: ");
        sb.append(this.name);
        sb.append(", cas: ");
        sb.append(this.time);

        return sb.toString();
    }
}
