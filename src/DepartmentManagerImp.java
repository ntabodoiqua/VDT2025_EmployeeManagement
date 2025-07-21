import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DepartmentManagerImp implements DepartmentManager{
    private static final List<Department> departments = new ArrayList<>();
    private static int nextId = 1;
    @Override
    public void addDepartment(Scanner scanner) {
        System.out.println("Nhập tên phòng ban:");
        String name = scanner.nextLine();
        System.out.println("Nhập địa chỉ phòng ban:");
        String address = scanner.nextLine();
        Department department = new Department(nextId++, name, address, null);
        departments.add(department);
        System.out.println("Phòng ban đã được thêm thành công: " + department.getName());
    }

    @Override
    public void displayDepartments(Scanner scanner) {
        if (departments.isEmpty()) {
            System.out.println("Danh sách phòng ban trống.");
            return;
        }
        System.out.printf("%-5s %-35s %-15s%n", "ID", "Tên Phòng Ban", "Địa Chỉ");
        departments.forEach(department -> {
            System.out.printf("%-5d %-35s %-15s%n",
                    department.getId(),
                    department.getName(),
                    department.getAddress());
        });
    }

    @Override
    public void searchDepartmentByName(Scanner scanner) {
        System.out.print("Nhập tên phòng ban cần tìm: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Department department : departments) {
            if (department.getName().equalsIgnoreCase(name)) {
                System.out.printf("Thông tin phòng ban: ID: %d, Tên: %s, Địa chỉ: %s%n",
                        department.getId(), department.getName(), department.getAddress());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy phòng ban với tên: " + name);
        }
    }

    @Override
    public void updateDepartment(Scanner scanner) {
        System.out.println("Nhập tên phòng ban cần cập nhật: ");
        System.out.println("Danh sách phòng ban hiện tại: ");
        displayDepartments(scanner);
        String name = scanner.nextLine();
        Department departmentToUpdate = null;
        for (Department department : departments) {
            if (department.getName().equalsIgnoreCase(name)) {
                departmentToUpdate = department;
                break;
            }
        }
        if (departmentToUpdate == null) {
            System.out.println("Không tìm thấy phòng ban với tên: " + name);
            return;
        }
        System.out.print("Nhập tên mới cho phòng ban (hiện tại: " + departmentToUpdate.getName() + "): (nếu không thay đổi, để trống)");
        String newName = scanner.nextLine();
        if (newName.isEmpty()) {
            newName = departmentToUpdate.getName(); // Giữ nguyên tên nếu không thay đổi
        }
        System.out.print("Nhập địa chỉ mới cho phòng ban (hiện tại: " + departmentToUpdate.getAddress() + "): (nếu không thay đổi, để trống)");
        String newAddress = scanner.nextLine();
        if (newAddress.isEmpty()) {
            newAddress = departmentToUpdate.getAddress(); // Giữ nguyên địa chỉ nếu không thay đổi
        }
        departmentToUpdate.setName(newName);
        departmentToUpdate.setAddress(newAddress);
        System.out.println("Cập nhật phòng ban thành công!");
    }

    @Override
    public void deleteDepartment(Scanner scanner) {
        System.out.println("Nhập tên phòng ban cần xóa: ");
        System.out.println("Danh sách phòng ban hiện tại: ");
        displayDepartments(scanner);
        String name = scanner.nextLine();
        Department departmentToDelete = null;
        for (Department department : departments) {
            if (department.getName().equalsIgnoreCase(name)) {
                departmentToDelete = department;
                break;
            }
        }
        if (departmentToDelete == null) {
            System.out.println("Không tìm thấy phòng ban với tên: " + name);
            return;
        }
        departments.remove(departmentToDelete);
        // Cập nhật lại danh sách nhân viên trong phòng ban
        for (Employee employee : EmployeeManagerImp.getEmployees()) {
            if (employee.getDepartment() != null && employee.getDepartment().getId() == departmentToDelete.getId()) {
                employee.setDepartment(null); // Xóa phòng ban của nhân viên
            }
        }
        System.out.println("Phòng ban đã được xóa thành công: " + departmentToDelete.getName());
    }

    @Override
    public void addEmployeeToDepartment(Scanner scanner) {
        System.out.println("Nhập ID phòng ban để thêm nhân viên:");
        System.out.println("Danh sách phòng ban hiện tại: ");
        displayDepartments(scanner);
        // Kiểm tra ID nhập vào có hợp lệ không
        // Nếu ID không phải là số nguyên dương, thông báo lỗi và yêu cầu nhập lại
        if (!scanner.hasNextInt()) {
            System.out.println("ID phòng ban phải là một số nguyên dương. Vui lòng nhập lại.");
            scanner.nextLine();
            return;
        }
        int departmentId = scanner.nextInt();
        scanner.nextLine();
        Department department = null;
        for (Department dep : departments) {
            if (dep.getId() == departmentId) {
                department = dep;
                break;
            }
        }

        if (department == null) {
            System.out.println("Không tìm thấy phòng ban với ID: " + departmentId);
            return;
        }

        System.out.println("Nhập ID nhân viên để thêm vào phòng ban " + department.getName() + ":");
        System.out.println("Danh sách nhân viên chưa được phân công vào phòng ban nào:");
        System.out.printf("%-5s %-20s %-15s %-35s %-10s %-35s %-15s%n", "ID", "Tên", "Ngày Sinh", "Phòng Ban", "Lương", "Email", "Số Điện Thoại");
        for (Employee employee : EmployeeManagerImp.getEmployees()) {
            if (employee.getDepartment() == null) {
                System.out.printf("%-5d %-20s %-15s %-35s %.2f %-35s %-15s%n",
                        employee.getId(),
                        employee.getName(),
                        employee.getDob(),
                        "Chưa phân công",
                        employee.getSalary(),
                        employee.getEmail(),
                        employee.getPhone());
            }
        }
        try {
            if (!scanner.hasNextInt()) {
                System.out.println("ID nhân viên phải là một số nguyên dương. Vui lòng nhập lại.");
                scanner.nextLine();
                return;
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi nhập ID nhân viên. Vui lòng thử lại.");
            scanner.nextLine();
            return;
        }
        int employeeId = scanner.nextInt();
        scanner.nextLine();
        Employee employeeToAdd = null;
        for (Employee employee : EmployeeManagerImp.getEmployees()) {
            if (employee.getId() == employeeId && employee.getDepartment() == null) {
                employeeToAdd = employee;
                break;
            }
        }
        if (employeeToAdd == null) {
            System.out.println("Không tìm thấy nhân viên với ID: " + employeeId + " hoặc nhân viên đã được phân công vào phòng ban khác.");
            return;
        }
        employeeToAdd.setDepartment(department);
        if (department.getEmployees() == null) {
            department.setEmployees(new ArrayList<>());
        }
        department.getEmployees().add(employeeToAdd);
        System.out.println("Nhân viên " + employeeToAdd.getName() + " đã được thêm vào phòng ban " + department.getName() + " thành công.");
    }

    @Override
    public void displayEmployeesInDepartment(Scanner scanner) {
        System.out.println("Nhập ID phòng ban để hiển thị danh sách nhân viên:");
        System.out.println("Danh sách phòng ban hiện tại: ");
        displayDepartments(scanner);
        if (!scanner.hasNextInt()) {
            System.out.println("ID phòng ban phải là một số nguyên dương. Vui lòng nhập lại.");
            scanner.nextLine();
            return;
        }
        int departmentId = scanner.nextInt();
        scanner.nextLine();

        Department department = null;
        for (Department dep : departments) {
            if (dep.getId() == departmentId) {
                department = dep;
                break;
            }
        }

        if (department == null || department.getEmployees() == null || department.getEmployees().isEmpty()) {
            System.out.println("Không tìm thấy phòng ban hoặc phòng ban không có nhân viên.");
            return;
        }

        System.out.printf("%-5s %-20s %-15s %-35s %-10s %-35s %-15s%n", "ID", "Tên", "Ngày Sinh", "Phòng Ban", "Lương", "Email", "Số Điện Thoại");
        for (Employee employee : department.getEmployees()) {
            System.out.printf("%-5d %-20s %-15s %-35s %.2f %-35s %-15s%n",
                    employee.getId(),
                    employee.getName(),
                    employee.getDob(),
                    employee.getDepartment().getName(),
                    employee.getSalary(),
                    employee.getEmail(),
                    employee.getPhone());
        }
    }

    @Override
    public void deleteEmployeeFromDepartment(Scanner scanner) {
        System.out.println("Nhập ID phòng ban để xóa nhân viên:");
        System.out.println("Danh sách phòng ban hiện tại: ");
        displayDepartments(scanner);
        if (!scanner.hasNextInt()) {
            System.out.println("ID phòng ban phải là một số nguyên dương. Vui lòng nhập lại.");
            scanner.nextLine();
            return;
        }
        int departmentId = scanner.nextInt();
        scanner.nextLine();

        Department department = null;
        for (Department dep : departments) {
            if (dep.getId() == departmentId) {
                department = dep;
                break;
            }
        }

        if (department == null || department.getEmployees() == null || department.getEmployees().isEmpty()) {
            System.out.println("Không tìm thấy phòng ban hoặc phòng ban không có nhân viên.");
            return;
        }

        System.out.println("Nhập ID nhân viên cần xóa: ");
        System.out.println("Danh sách nhân viên trong phòng ban " + department.getName() + ":");
        System.out.printf("%-5s %-20s %-15s %-35s %-10s %-35s %-15s%n", "ID", "Tên", "Ngày Sinh", "Phòng Ban", "Lương", "Email", "Số Điện Thoại");
        for (Employee employee : department.getEmployees()) {
            System.out.printf("%-5d %-20s %-15s %-35s %.2f %-35s %-15s%n",
                    employee.getId(),
                    employee.getName(),
                    employee.getDob(),
                    employee.getDepartment().getName(),
                    employee.getSalary(),
                    employee.getEmail(),
                    employee.getPhone());
        }
        try {
            if (!scanner.hasNextInt()) {
                System.out.println("ID nhân viên phải là một số nguyên dương. Vui lòng nhập lại.");
                scanner.nextLine();
                return;
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi nhập ID nhân viên. Vui lòng thử lại.");
            scanner.nextLine();
            return;
        }
        int employeeId = scanner.nextInt();
        scanner.nextLine();

        Employee employeeToDelete = null;
        for (Employee employee : department.getEmployees()) {
            if (employee.getId() == employeeId) {
                employeeToDelete = employee;
                break;
            }
        }

        if (employeeToDelete == null) {
            System.out.println("Không tìm thấy nhân viên với ID: " + employeeId);
            return;
        }

        department.getEmployees().remove(employeeToDelete);
        employeeToDelete.setDepartment(null);
        System.out.println("Nhân viên đã được xóa thành công: " + employeeToDelete.getName());
    }

    @Override
    public void statisticsInDepartment(Scanner scanner) {
        // Hiển thị thống kê số lượng các thông số sau:
        // - Số lượng nhân viên trong phòng ban
        // - Tổng lương của nhân viên trong phòng ban
        // - Nhân viên có lương cao nhất trong phòng ban
        System.out.println("Danh sách phòng ban hiện tại: ");
        displayDepartments(scanner);
        System.out.println("Nhập ID phòng ban để thống kê:");
        try {
            if (!scanner.hasNextInt()) {
                System.out.println("ID phòng ban phải là một số nguyên dương. Vui lòng nhập lại.");
                scanner.nextLine();
                return;
            }
        } catch (Exception e) {
            System.out.println("Đã xảy ra lỗi khi nhập ID phòng ban. Vui lòng thử lại.");
            scanner.nextLine();
            return;
        }
        int departmentId = scanner.nextInt();
        scanner.nextLine();
        Department department = null;
        for (Department dep : departments) {
            if (dep.getId() == departmentId) {
                department = dep;
                break;
            }
        }
        if (department == null || department.getEmployees() == null || department.getEmployees().isEmpty()) {
            System.out.println("Không tìm thấy phòng ban hoặc phòng ban không có nhân viên.");
            return;
        }
        int employeeCount = department.getEmployees().size();
        double totalSalary = 0.0;
        Employee highestSalaryEmployee = null;
        for (Employee employee : department.getEmployees()) {
            totalSalary += employee.getSalary();
            if (highestSalaryEmployee == null || employee.getSalary() > highestSalaryEmployee.getSalary()) {
                highestSalaryEmployee = employee;
            }
        }
        System.out.println("Thống kê phòng ban " + department.getName() + ":");
        System.out.printf("Số lượng nhân viên: %d%n", employeeCount);
        System.out.printf("Tổng lương: %.2f%n", totalSalary);
        if (highestSalaryEmployee != null) {
            System.out.printf("Nhân viên có lương cao nhất: %s (%.2f)%n",
                    highestSalaryEmployee.getName(), highestSalaryEmployee.getSalary());
        } else {
            System.out.println("Không có nhân viên nào trong phòng ban này.");
        }
    }
}
