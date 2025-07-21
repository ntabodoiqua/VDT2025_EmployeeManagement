import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public interface EmployeeManager {
    void addEmployee(Scanner scanner);

    void displayEmployees(Scanner scanner);

    void searchEmployeeById(Scanner scanner);

    void searchEmployeeByName(Scanner scanner);

    void updateEmployee(Scanner scanner);

    void deleteEmployee(Scanner scanner);

    void statistics(Scanner scanner);
}
