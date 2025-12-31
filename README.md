Employee Database (Java)
Overview

This project is a Java-based employee database system that reads employee records from a CSV file and builds an object-oriented in-memory database. It assigns unique employee and department IDs, supports employees with multiple departmental appointments (cross-appointments), and provides various query and management operations.

The implementation strictly follows a predefined API so it can be tested using an automated grading harness.

Features

Reads employee data from a CSV file

Skips header rows automatically

Generates unique employee IDs (EMP0001, EMP0002, …)

Assigns shared department IDs across employees

Supports cross-appointed employees (multiple departments)

Computes date differences between appointments

Removes employees by ID

Prints reports for cross-appointed employees

Dataset

The program expects a CSV file named employeeInfo.csv with the following columns:

SNo, FirstName, LastName, DepartmentName, DateOfJoining


The first row is a header and is skipped

Dates are formatted as YYYY-MM-DD

The same employee (same first and last name) may appear multiple times for different departments

Project Structure
employee-database-java/
│
├── Department.java
├── Employee.java
├── EmployeeDB.java
├── employeeInfo.csv   (provided externally)
└── README.md

Class Summary
Department

Maintains static department IDs shared across employees

Ensures the same department name always maps to the same ID

Stores department name and joining date

Employee

Generates unique employee IDs

Stores first and last names

Maintains a list of department appointments

EmployeeDB

Reads and parses the CSV file

Builds a list of unique employees

Supports:

Cross-appointment detection

Date difference calculation

Employee removal

Reporting and queries

How to Run

Place employeeInfo.csv in the project directory

Compile the project:

javac *.java


Use the provided harness or your own driver program to interact with EmployeeDB

Example:

EmployeeDB db = new EmployeeDB("employeeInfo.csv");
db.read_file();
db.add_emp_data();
db.print_cross_emp();

Example Output
1000 records added to the dictionary.
689 unique employees added to the database.
Record 01 found! Employee[id=EMP0004, name=Maria Hicks, #appts=2] Appointments found:
Department[id=3, name=Biology, joinDate=2005-09-01]
Department[id=7, name=Chemistry, joinDate=2023-01-15]

Notes

Employee uniqueness is determined by first name + last name

Employee IDs are rolled back if a duplicate employee is detected

Department IDs are shared globally using static tracking

The implementation matches the required API exactly for autograder compatibility

Author

Anagha kulkarni
Computer Science Student
