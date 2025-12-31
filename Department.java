import java.util.ArrayList;

public class Department {

    // static fields
    private static int dpId = 0;
    private static ArrayList<Integer> dpIds = new ArrayList<>();
    private static ArrayList<String> dpNames = new ArrayList<>();

    // instance fields
    private int depId;
    private String depName;
    private String joinDate;

    // static method
    public static int getNextDepID() {
        dpId++;
        return dpId;
    }

    // constructor
    public Department(String depName, String joinDate) {
        int idx = dpNames.indexOf(depName);
        if (idx != -1) {
            this.depId = dpIds.get(idx);
        } else {
            this.depId = getNextDepID();
            dpIds.add(this.depId);
            dpNames.add(depName);
        }
        this.depName = depName;
        this.joinDate = joinDate;
    }

    // accessors
    public int get_dep_id() {
        return depId;
    }

    public String get_dep_name() {
        return depName;
    }

    public String get_join_date() {
        return joinDate;
    }

    @Override
    public String toString() {
        return "Department[id=" + depId + ", name=" + depName + ", joinDate=" + joinDate + "]";
    }
}
