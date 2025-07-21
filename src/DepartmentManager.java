import java.util.Scanner;

public interface DepartmentManager {
    void addDepartment(Scanner scanner);

    void displayDepartments(Scanner scanner);

    void searchDepartmentByName(Scanner scanner);

    void updateDepartment(Scanner scanner);

    void deleteDepartment(Scanner scanner);

    void addEmployeeToDepartment(Scanner scanner);

    void displayEmployeesInDepartment(Scanner scanner);

    void deleteEmployeeFromDepartment(Scanner scanner);

    void statisticsInDepartment(Scanner scanner);
}
