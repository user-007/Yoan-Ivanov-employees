import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class StartApp {


    public static ArrayList<Employee> employees = new ArrayList<Employee>();
    public static ArrayList<PPair> pairs = new ArrayList<>();
    public static ArrayList<PFinal> pfinal = new ArrayList<>();

    public static long getDays(String a, String b, String c, String d) {
        LocalDate i1Start = LocalDate.parse(a);
        LocalDate i1End = LocalDate.parse(b);
        LocalDate i2Start = LocalDate.parse(c);
        LocalDate i2End = LocalDate.parse(d);
        if (i1End.isBefore(i1Start) || i2End.isBefore(i2Start)) {
            return 0;
        } else {
            long numberOfOverlappingDates;
            if (i1End.isBefore(i2Start) || i2End.isBefore(i1Start)) {
                // no overlap
                numberOfOverlappingDates = 0;
            } else {
                LocalDate laterStart = Collections.max(Arrays.asList(i1Start, i2Start));
                LocalDate earlierEnd = Collections.min(Arrays.asList(i1End, i2End));
                numberOfOverlappingDates = ChronoUnit.DAYS.between(laterStart, earlierEnd);
            }
            return numberOfOverlappingDates;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        BufferedReader reader;
        long days = 0;
        try {
            String path = sc.nextLine();
            File fl = new File(path);
            reader = new BufferedReader(new FileReader(fl.getCanonicalPath()));
            String line = reader.readLine();
            while (line != null) {
                String[] str = line.split(",");
                Employee emp = new Employee(str[0], str[1], str[2], str[3]);
                if (emp.getDate_to().equals("NULL")) {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    String ldt = dateFormat.format(new Date());
                    Employee empp = new Employee(str[0], str[1], str[2], ldt);
                    employees.add(empp);
                } else {
                    employees.add(emp);
                }
                line = reader.readLine();
            }
            reader.close();

        } catch (Exception e) {
            System.out.println("Error!");
        }
        for (int i = 0; i < employees.size(); i++) {
            for (int j = i + 1; j < employees.size(); j++) {
                if (employees.get(i).getProjectid().equals((employees.get(j).getProjectid()))) {
                    pairs.add(new PPair((Integer.parseInt(employees.get(i).getEmployeeid())), Integer.parseInt(employees.get(j).getEmployeeid()), getDays(employees.get(i).getDate_from(), employees.get(i).getDate_to(), employees.get(j).getDate_from(), employees.get(j).getDate_to())));
                }
            }
        }
        Collections.sort(pairs);
        for (int i = 0; i < pairs.size(); i++) {
            int suma = 0;
            int emp1 = pairs.get(i).getemp1_id();
            int emp2 = pairs.get(i).getemp2_id();
            while (pairs.get(i).getemp1_id() == emp1 || pairs.get(i).getemp2_id() == emp2) {
                suma += pairs.get(i).getDys();
            }
            pfinal.add(new PFinal(emp1, emp2, suma));
        }

        Collections.sort(pfinal);
        Collections.reverse(pfinal);
        try {
            if (pfinal.get(0).getFinal_days() == 0) {
                System.out.println("No such pair exists.");
            } else {
                System.out.println(pfinal.get(0).getemp1());
                System.out.println(pfinal.get(0).getemp2());
            }
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }
    }
}


