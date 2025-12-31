public class Main {

    public static void main(String[] args) {

        // Ensure filename is provided
        if (args.length == 0) {
            System.out.println("Usage: java Main <employeeInfo.csv>");
            return;
        }

        String filename = args[0];

        // Create database
        EmployeeDB db = new EmployeeDB(filename);

        // Read CSV and build database
        db.read_file();
        db.add_emp_data();

        System.out.println("\n--- Cross-Appointed Employees ---");
        db.print_cross_emp();

        // Sample queries
        System.out.println("\n--- Sample Queries ---");

        String empId = db.findEmpIdByName("Maria", "Hicks");
        if (empId != null) {
            System.out.println("Employee ID for Maria Hicks: " + empId);
            db.date_diff(empId);
        }

        // Remove example
        System.out.println("\n--- Remove Employee ---");
        db.remove_emp(empId);

        // Verify removal
        db.date_diff(empId);

        System.out.println("\nFinal employee count: " + db.get_obj_lst().size());
    }
}
