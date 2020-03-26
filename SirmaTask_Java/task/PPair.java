import java.util.*;
public class PPair implements Comparable<PPair> {

    public PPair(int emp1_id, int emp2_id, long dys) {
        this.emp1_id = emp1_id;
        this.emp2_id = emp2_id;
        this.dys = dys;
    }

    public int getemp1_id() {
        return emp1_id;
    }

    public int getemp2_id() {
        return emp2_id;
    }

    public long getDys() {
        return dys;
    }

    private int emp1_id; // long
    private int emp2_id;
    private long dys;

    @Override
    public int compareTo(PPair pp) {
        return Comparator.comparingInt(PPair::getemp1_id)
        .thenComparingInt(PPair::getemp2_id)
        .compare(this,pp);
    }
}
