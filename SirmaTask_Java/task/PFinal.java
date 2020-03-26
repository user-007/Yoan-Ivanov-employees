import java.time.LocalDate;

public class PFinal implements Comparable<PFinal> {
    public PFinal(int femp1, int femp2, long final_days) {
        this.femp1 = femp1;
        this.femp2 = femp2;
        this.final_days = final_days;
    }

    public int getemp1() {
        return femp1;
    }

    public int getemp2() {
        return femp2;
    }

    private int femp1;
    private int femp2;
    private long final_days;

    public long getFinal_days() {
        return final_days;
    }

    @Override
    public int compareTo(PFinal o) {
        return Long.compare(getFinal_days(), o.getFinal_days());
    }

}
