import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class EmployeeDB {

    private String filename;
    private ArrayList<ArrayList<String>> emp_rows;
    private ArrayList<Employee> emp_obj_list;

    public EmployeeDB(String filename) {
        this.filename = filename;
        emp_rows = new ArrayList<>();
        emp_obj_list = new ArrayList<>();
    }

    // reads CSV
    public void read_file() {
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean header = true;
            while ((line = br.readLine()) != null) {
                if (header) {
                    header = false;
                    continue;
                }
                String[] parts = line.split(",");
                ArrayList<String> row = new ArrayList<>();
                row.add(parts[1]); // FirstName
                row.add(parts[2]); // LastName
                row.add(parts[3]); // DepartmentName
                row.add(parts[4]); // DateOfJoining
                emp_rows.add(row);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(emp_rows.size() + " records added to the dictionary.");
    }

    public ArrayList<Employee> get_obj_lst() {
        return emp_obj_list;
    }

    public int dict_size() {
        return emp_rows.size();
    }

    public int emp_exists(Employee e) {
        for (int i = 0; i < emp_obj_list.size(); i++) {
            Employee cur = emp_obj_list.get(i);
            if (cur.get_fName().equals(e.get_fName()) &&
                cur.get_lName().equals(e.get_lName())) {
                return i;
            }
        }
        return -1;
    }

    public void add_emp_data() {
        for (int i = 0; i < emp_rows.size(); i++) {
            ArrayList<String> r = emp_rows.get(i);
            String f = r.get(0);
            String l = r.get(1);
            String dep = r.get(2);
            String date = r.get(3);

            Employee temp = new Employee(f, l);
            int idx = emp_exists(temp);

            Employee target;
            if (idx == -1) {
                emp_obj_list.add(temp);
                target = temp;
            } else {
                Employee.decrementEmpID();
                target = emp_obj_list.get(idx);
            }

            Department d = new Department(dep, date);
            target.add_dept(d);
        }
        System.out.println(emp_obj_list.size() + " unique employees added to the database.");
    }

    public void print_cross_emp() {
        int count = 1;
        for (Employee e : emp_obj_list) {
            if (e.get_depLst().size() > 1) {
                System.out.printf("Record %02d found! %s Appointments found:\n",
                        count++, e.toString());
                for (Department d : e.get_depLst()) {
                    System.out.println(d.toString());
                }
            }
        }
    }

    public void remove_emp(String empId) {
        for (int i = 0; i < emp_obj_list.size(); i++) {
            if (emp_obj_list.get(i).get_emp_id().equals(empId)) {
                emp_obj_list.remove(i);
                System.out.println("Successfully removed employee with employee id: " + empId);
                return;
            }
        }
        System.out.println("Employee not found!");
    }

    public void date_diff(String empId) {
        long v = date_diff_value(empId);
        if (v == -2) {
            System.out.println("Employee not found!");
        } else if (v == -1) {
            System.out.println("Employee is not cross-appointed.");
        } else {
            System.out.println("Difference between cross-appointments (in days) is: " + v);
        }
    }

    // ----- Given methods (do not modify) -----

    public String findEmpIdByName(String f, String l) {
        for (Employee e : emp_obj_list) {
            if (e.get_fName().equals(f) && e.get_lName().equals(l)) {
                return e.get_emp_id();
            }
        }
        return null;
    }

    public long date_diff_value(String empId) {
        Employee target = null;
        for (Employee e : emp_obj_list) {
            if (e.get_emp_id().equals(empId)) {
                target = e;
                break;
            }
        }
        if (target == null) return -2;
        if (target.get_depLst().size() <= 1) return -1;

        LocalDate min = null, max = null;
        for (Department d : target.get_depLst()) {
            LocalDate dt = LocalDate.parse(d.get_join_date());
            if (min == null || dt.isBefore(min)) min = dt;
            if (max == null || dt.isAfter(max)) max = dt;
        }
        return ChronoUnit.DAYS.between(min, max);
    }
}
