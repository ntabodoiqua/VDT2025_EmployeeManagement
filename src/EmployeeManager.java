import java.util.Scanner;

public interface EmployeeManager {
    void addEmployee(Scanner scanner);

    void displayEmployees();

    void searchEmployeeById(Scanner scanner);

    void updateEmployee(Scanner scanner);

    void deleteEmployee(Scanner scanner);
}
