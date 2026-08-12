class Employee {
    int id; String name; float salary;

    Employee(int id, String name, float salary) {
        this.id = id; this.name = name; this.salary = salary;
    }

    void calculateSalary() {
        System.out.println(id + " " + name + " Salary: " + salary);
    }
}

class Manager extends Employee {
    Manager(int id, String name, float salary) {
        super(id, name, salary);
    }

    @Override
    void calculateSalary() {
        System.out.println(id + " " + name + " Total Salary: " + (salary + 10000));
    }
}

class Programmer extends Employee {
    Programmer(int id, String name, float salary) {
        super(id, name, salary);
    }

    @Override
    void calculateSalary() {
        System.out.println(id + " " + name + " Total Salary: " + (salary + 5000));
    }
}

public class EmployeeInfo {
    public static void main(String[] args) {
        Employee e1 = new Manager(101, "ABC", 50000);
        Employee e2 = new Programmer(102, "XYZ", 30000);

        e1.calculateSalary();
        e2.calculateSalary();
    }
}
