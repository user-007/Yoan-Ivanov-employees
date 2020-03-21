import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class StartApp {


    public static ArrayList<Employee> employees = new ArrayList<Employee>();
    public static ArrayList<PPair> pairs = new ArrayList<PPair>();

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
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
                    Date date = new Date();
                    String ldt = dateFormat.format(date);
                    Employee empp = new Employee(str[0], str[1], str[2], ldt);
                    employees.add(empp);
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
        Collections.reverse(pairs);
        try {
            System.out.println(pairs.get(0).getemp1_id());
            System.out.println(pairs.get(0).getemp2_id());
        } catch (Exception exp) {
            System.out.println(exp.getMessage());
        }

    }
}


