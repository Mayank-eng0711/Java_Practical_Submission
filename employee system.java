import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

// Model class representing an Employee
class Employee {
    private final int id;
    private String name;
    private String designation;
    private double salary;

    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-20s | Designation: %-18s | Salary: $%.2f",
                id, name, designation, salary);
    }
}

// Controller managing records via java.util collections
class EmployeeManager {
    private final Map<Integer, Employee> employeeRecords = new HashMap<>();

    public boolean addEmployee(Employee emp) {
        if (employeeRecords.containsKey(emp.getId())) {
            return false;
        }
        employeeRecords.put(emp.getId(), emp);
        return true;
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employeeRecords.values());
    }

    public Employee getEmployeeById(int id) {
        return employeeRecords.get(id);
    }

    public boolean updateEmployee(int id, String name, String designation, double salary) {
        Employee emp = employeeRecords.get(id);
        if (emp != null) {
            emp.setName(name);
            emp.setDesignation(designation);
            emp.setSalary(salary);
            return true;
        }
        return false;
    }

    public boolean deleteEmployee(int id) {
        return employeeRecords.remove(id) != null;
    }
}

// Main interactive CLI application
public class EmployeeManagementSystem {
    public static void main(String[] args) {
        EmployeeManager manager = new EmployeeManager();
        Scanner scanner = new Scanner(System.in);

        // Pre-populating sample records
        manager.addEmployee(new Employee(101, "Alice Smith", "Software Engineer", 85000.00));
        manager.addEmployee(new Employee(102, "Bob Jones", "Project Manager", 95000.00));
        manager.addEmployee(new Employee(103, "Charlie Brown", "QA Specialist", 65000.00));

        while (true) {
            System.out.println("\n==========================================");
            System.out.println("   EMPLOYEE RECORD MANAGEMENT SYSTEM     ");
            System.out.println("==========================================");
            System.out.println("1. Add New Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Search Employee by ID");
            System.out.println("4. Update Employee Details");
            System.out.println("5. Delete Employee Record");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                continue;
            }

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Employee ID: ");
                        int id = Integer.parseInt(scanner.nextLine().trim());
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter Designation: ");
                        String designation = scanner.nextLine().trim();
                        System.out.print("Enter Salary: ");
                        double salary = Double.parseDouble(scanner.nextLine().trim());

                        if (manager.addEmployee(new Employee(id, name, designation, salary))) {
                            System.out.println("Success: Employee record created successfully.");
                        } else {
                            System.out.println("Error: Employee with ID " + id + " already exists.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid input format for numeric fields.");
                    }
                    break;

                case 2:
                    List<Employee> employees = manager.getAllEmployees();
                    if (employees.isEmpty()) {
                        System.out.println("No employee records found.");
                    } else {
                        System.out.println("\n--- Current Employee Records ---");
                        for (Employee emp : employees) {
                            System.out.println(emp);
                        }
                    }
                    break;

                case 3:
                    try {
                        System.out.print("Enter Employee ID to search: ");
                        int searchId = Integer.parseInt(scanner.nextLine().trim());
                        Employee emp = manager.getEmployeeById(searchId);
                        if (emp != null) {
                            System.out.println("\n--- Record Details ---");
                            System.out.println(emp);
                        } else {
                            System.out.println("Error: Employee ID " + searchId + " not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID must be an integer.");
                    }
                    break;

                case 4:
                    try {
                        System.out.print("Enter Employee ID to update: ");
                        int updateId = Integer.parseInt(scanner.nextLine().trim());
                        if (manager.getEmployeeById(updateId) == null) {
                            System.out.println("Error: Employee ID " + updateId + " not found.");
                            break;
                        }
                        System.out.print("Enter New Name: ");
                        String name = scanner.nextLine().trim();
                        System.out.print("Enter New Designation: ");
                        String designation = scanner.nextLine().trim();
                        System.out.print("Enter New Salary: ");
                        double salary = Double.parseDouble(scanner.nextLine().trim());

                        if (manager.updateEmployee(updateId, name, designation, salary)) {
                            System.out.println("Success: Employee details updated.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Invalid numeric input.");
                    }
                    break;

                case 5:
                    try {
                        System.out.print("Enter Employee ID to delete: ");
                        int deleteId = Integer.parseInt(scanner.nextLine().trim());
                        if (manager.deleteEmployee(deleteId)) {
                            System.out.println("Success: Employee record removed.");
                        } else {
                            System.out.println("Error: Employee ID " + deleteId + " not found.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID must be an integer.");
                    }
                    break;

                case 6:
                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid selection! Enter a number between 1 and 6.");
            }
        }
    }
}
