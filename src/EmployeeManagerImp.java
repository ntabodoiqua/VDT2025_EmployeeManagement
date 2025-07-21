import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagerImp implements EmployeeManager{
    private static final List<Employee> employees = new ArrayList<>();
    private static int nextId = 1;

    @Override
    public void addEmployee(Scanner scanner) {
        System.out.print("Nhập tên nhân viên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập ngày sinh (yyyy-MM-dd): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());
        System.out.print("Nhập phòng ban: ");
        String department = scanner.nextLine();
        System.out.print("Nhập lương: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine();

        Employee employee = new Employee(nextId++, name, dob, department, salary, email, phone);
        employees.add(employee);
        System.out.println("Thêm nhân viên thành công!");
    }

    @Override
    public void displayEmployees() {
        if (employees.isEmpty()) {
            System.out.println("Danh sách nhân viên trống.");
            return;
        }
        System.out.printf("%-5s %-20s %-15s %-35s %-10s %-35s %-15s%n", "ID", "Tên", "Ngày sinh", "Phòng ban", "Lương", "Email", "Số điện thoại");
        for (Employee employee : employees) {
            System.out.printf("%-5d %-20s %-15s %-35s %-10.2f %-35s %-15s%n",
                    employee.getId(), employee.getName(), employee.getDob(), employee.getDepartment(),
                    employee.getSalary(), employee.getEmail(), employee.getPhone());
        }
    }

    @Override
    public void searchEmployeeById(Scanner scanner) {
        System.out.print("Nhập ID nhân viên cần tìm: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        try {
            Employee employee = employees.get(id - 1);
            System.out.printf("Thông tin nhân viên ID %d: %n", id);
            System.out.printf("Tên: %s%n", employee.getName());
            System.out.printf("Ngày sinh: %s%n", employee.getDob());
            System.out.printf("Phòng ban: %s%n", employee.getDepartment());
            System.out.printf("Lương: %.2f%n", employee.getSalary());
            System.out.printf("Email: %s%n", employee.getEmail());
            System.out.printf("Số điện thoại: %s%n", employee.getPhone());
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Không tìm thấy nhân viên với ID: " + id);
        }
    }

    @Override
    public void updateEmployee(Scanner scanner) {
        System.out.print("Nhập ID nhân viên cần cập nhật: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự newline sau khi nhập số

        try {
            Employee employee = employees.get(id - 1);
            System.out.print("Nhập tên mới (hiện tại: " + employee.getName() + "): (nếu không thay đổi, để trống) ");
            String name = scanner.nextLine();
            if (name.isEmpty()) {
                name = employee.getName(); // Giữ nguyên tên nếu không thay đổi
            }
            System.out.print("Nhập ngày sinh mới (hiện tại: " + employee.getDob() + ", định dạng yyyy-MM-dd): (nếu không thay đổi, để trống) ");
            String dobInput = scanner.nextLine();
            if (dobInput.isEmpty()) {
                dobInput = employee.getDob().toString(); // Giữ nguyên ngày sinh nếu không thay đổi
            }
            LocalDate dob = LocalDate.parse(dobInput);
            System.out.print("Nhập phòng ban mới (hiện tại: " + employee.getDepartment() + "): (nếu không thay đổi, để trống) ");
            String department = scanner.nextLine();
            if (department.isEmpty()) {
                department = employee.getDepartment(); // Giữ nguyên phòng ban nếu không thay đổi
            }
            System.out.print("Nhập lương mới (hiện tại: " + employee.getSalary() + "): (nếu không thay đổi, để trống) ");
            double salary = scanner.nextDouble();
            if (salary <= 0) {
                salary = employee.getSalary(); // Giữ nguyên lương nếu không thay đổi
            }
            scanner.nextLine();
            System.out.print("Nhập email mới (hiện tại: " + employee.getEmail() + "): (nếu không thay đổi, để trống) ");
            String email = scanner.nextLine();
            if (email.isEmpty()) {
                email = employee.getEmail(); // Giữ nguyên email nếu không thay đổi
            }
            System.out.print("Nhập số điện thoại mới (hiện tại: " + employee.getPhone() + "): (nếu không thay đổi, để trống) ");
            String phone = scanner.nextLine();
            if (phone.isEmpty()) {
                phone = employee.getPhone(); // Giữ nguyên số điện thoại nếu không thay đổi
            }

            employee.setName(name);
            employee.setDob(dob);
            employee.setDepartment(department);
            employee.setSalary(salary);
            employee.setEmail(email);
            employee.setPhone(phone);

            System.out.println("Cập nhật thông tin nhân viên thành công!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Không tìm thấy nhân viên với ID: " + id);
        }
    }

    @Override
    public void deleteEmployee(Scanner scanner) {
        System.out.print("Nhập ID nhân viên cần xóa: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự newline sau khi nhập số

        try {
            employees.remove(id - 1);
            System.out.println("Xóa nhân viên thành công!");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Không tìm thấy nhân viên với ID: " + id);
        }
    }
}
