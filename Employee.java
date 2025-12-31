import java.util.ArrayList;

public class Employee {

    // static fields
    private static int emID = 0;

    // instance fields
    private String empId;
    private String fName;
    private String lName;
    private ArrayList<Department> depLst;

    // static methods
    public static String getNextEmpID() {
        emID++;
        return String.format("EMP%04d", emID);
    }

    public static void decrementEmpID() {
        if (emID > 0) {
            emID--;
        }
    }

    // constructor
    public Employee(String fName, String lName) {
        this.empId = getNextEmpID();
        this.fName = fName;
        this.lName = lName;
        this.depLst = new ArrayList<>();
    }

    // accessors
    public String get_emp_id() {
        return empId;
    }

    public String get_fName() {
        return fName;
    }

    public String get_lName() {
        return lName;
    }

    public ArrayList<Department> get_depLst() {
        return depLst;
    }

    // mutators
    public void set_fName(String f) {
        this.fName = f;
    }

    public void set_lName(String l) {
        this.lName = l;
    }

    // methods
    public void add_dept(Department d) {
        depLst.add(d);
    }

    @Override
    public String toString() {
        return "Employee[id=" + empId + ", name=" + fName + " " + lName +
               ", #appts=" + depLst.size() + "]";
    }
}
