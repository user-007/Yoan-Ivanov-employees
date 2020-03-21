public class Employee {
    private String employeeid;
    private String projectid;
    private String date_from;
    private String date_to;

    public String getEmployeeid() {
        return employeeid;
    }

    public String getProjectid() {
        return projectid;
    }

    public String getDate_from() {
        return date_from;
    }

    public String getDate_to() {
        return date_to;
    }

    public Employee(String eid, String projid, String from, String to) {
        employeeid = eid;
        projectid = projid;
        date_from = from;
        date_to = to;
    }

}

