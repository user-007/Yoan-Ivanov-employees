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

    @Override
    public int compareTo(PPair pp) {
        return Long.compare(getDys(), pp.getDys());
    }

    private int emp1_id; // long
    private int emp2_id;
    private long dys;
}
