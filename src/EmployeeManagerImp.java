import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class EmployeeManagerImp implements EmployeeManager{
    @Getter
    private static final List<Employee> employees = new ArrayList<>();
    private static int nextId = 1;

    @Override
    public void addEmployee(Scanner scanner) {
        System.out.print("Nhập tên nhân viên: ");
        String name = scanner.nextLine();
        System.out.print("Nhập ngày sinh (yyyy-MM-dd): ");
        LocalDate dob = LocalDate.parse(scanner.nextLine());
        System.out.print("Nhập lương: ");
        double salary = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Nhập email: ");
        String email = scanner.nextLine();
        System.out.print("Nhập số điện thoại: ");
        String phone = scanner.nextLine();

        // Kiểm tra email và số điện thoại có hợp lệ hay không
        if (!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            System.out.println("Email không hợp lệ. Vui lòng nhập lại.");
            return;
        }
        if (!phone.matches("^\\d{10,15}$")) {
            System.out.println("Số điện thoại không hợp lệ. Vui lòng nhập lại.");
            return;
        }
        // Kiểm tra trùng lặp email và số điện thoại
        for (Employee employee : employees) {
            if (employee.getEmail().equalsIgnoreCase(email)) {
                System.out.println("Email đã tồn tại. Vui lòng nhập email khác.");
                return;
            }
            if (employee.getPhone().equals(phone)) {
                System.out.println("Số điện thoại đã tồn tại. Vui lòng nhập số điện thoại khác.");
                return;
            }
        }
        Employee employee = new Employee(nextId++, name, dob,null, salary, email, phone);
        employees.add(employee);
        System.out.println("Thêm nhân viên thành công!");
    }

    @Override
    public void displayEmployees(Scanner scanner) {
        if (employees.isEmpty()) {
            System.out.println("Danh sách nhân viên trống.");
            return;
        }
        System.out.printf("%-5s %-20s %-15s %-35s %-10s %-35s %-15s%n", "ID", "Tên", "Ngày sinh", "Phòng ban", "Lương", "Email", "Số điện thoại");
        employees.forEach(employee -> {
            System.out.printf("%-5d %-20s %-15s %-35s %-10.2f %-35s %-15s%n",
                    employee.getId(),
                    employee.getName(),
                    employee.getDob(),
                    // Nếu nhân viên không có phòng ban, hiển thị "Chưa phân công"
                    (employee.getDepartment() != null ? employee.getDepartment().getName() : "Chưa phân công"),
                    employee.getSalary(),
                    employee.getEmail(),
                    employee.getPhone());
        });
    }

    @Override
    public void searchEmployeeById(Scanner scanner) {
        System.out.print("Nhập ID nhân viên cần tìm: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        Employee employeeToFind = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employeeToFind = employee;
                break;
            }
        }
        if (employeeToFind == null) {
            System.out.println("Không tìm thấy nhân viên với ID: " + id);
        } else {
            System.out.printf("Thông tin nhân viên ID %d: %n", id);
            System.out.printf("Tên: %s%n", employeeToFind.getName());
            System.out.printf("Ngày sinh: %s%n", employeeToFind.getDob());
            // Nếu nhân viên không có phòng ban, hiển thị "Chưa phân công"
            System.out.println(employeeToFind.getDepartment() != null ? employeeToFind.getDepartment().getName() : "Chưa phân công");
            System.out.printf("Lương: %.2f%n", employeeToFind.getSalary());
            System.out.printf("Email: %s%n", employeeToFind.getEmail());
            System.out.printf("Số điện thoại: %s%n", employeeToFind.getPhone());
        }
    }

    @Override
    public void searchEmployeeByName(Scanner scanner) {
        System.out.println("Nhập tên nhân viên cần tìm: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (Employee employee : employees) {
            if (employee.getName().equalsIgnoreCase(name)) {
                System.out.printf("Thông tin nhân viên: %n");
                System.out.printf("ID: %d%n", employee.getId());
                System.out.printf("Tên: %s%n", employee.getName());
                System.out.printf("Ngày sinh: %s%n", employee.getDob());
                // Nếu nhân viên không có phòng ban, hiển thị "Chưa phân công"
                System.out.printf("Phòng ban: %s%n",
                        (employee.getDepartment() != null ? employee.getDepartment().getName() : "Chưa phân công"));
                System.out.printf("Lương: %.2f%n", employee.getSalary());
                System.out.printf("Email: %s%n", employee.getEmail());
                System.out.printf("Số điện thoại: %s%n", employee.getPhone());
                found = true;
            }
        }
        if (!found) {
            System.out.println("Không tìm thấy nhân viên với tên: " + name);
        }
    }

    @Override
    public void updateEmployee(Scanner scanner) {
        System.out.print("Nhập ID nhân viên cần cập nhật: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự newline sau khi nhập số
        Employee employeeToUpdate = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employeeToUpdate = employee;
                break;
            }
        }
        if (employeeToUpdate == null) {
            System.out.println("Không tìm thấy nhân viên với ID: " + id);
        } else {
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
            employee.setSalary(salary);
            employee.setEmail(email);
            employee.setPhone(phone);

            System.out.println("Cập nhật thông tin nhân viên thành công!");
        }
    }

    @Override
    public void deleteEmployee(Scanner scanner) {
        System.out.print("Nhập ID nhân viên cần xóa: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc ký tự newline sau khi nhập số
        Employee employeeToDelete = null;
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employeeToDelete = employee;
                break;
            }
        }
        if (employeeToDelete == null) {
            System.out.println("Không tìm thấy nhân viên với ID: " + id);
        } else {
            employees.remove(employeeToDelete);
            // Cập nhật danh sách nhân viên trong phòng ban
            if (employeeToDelete.getDepartment() != null) {
                employeeToDelete.getDepartment().getEmployees().remove(employeeToDelete);
            }
            System.out.println("Xóa nhân viên thành công!");
        }
    }

    @Override
    public void statistics(Scanner scanner) {
        int count = 0;
        double totalSalary = 0.0;
        Employee highestSalaryEmployee = null;
        for (Employee employee : employees) {
            count++;
            totalSalary += employee.getSalary();
            if (highestSalaryEmployee == null || employee.getSalary() > highestSalaryEmployee.getSalary()) {
                highestSalaryEmployee = employee;
            }
        }
        if (count == 0) {
            System.out.println("Không có nhân viên nào để thống kê.");
        } else {
            System.out.printf("Tổng số nhân viên: %d%n", count);
            System.out.printf("Tổng lương: %.2f%n", totalSalary);
            System.out.printf("Lương trung bình: %.2f%n", totalSalary / count);
            if (highestSalaryEmployee != null) {
                System.out.printf("Nhân viên có lương cao nhất: %s (%.2f)%n",
                        highestSalaryEmployee.getName(), highestSalaryEmployee.getSalary());
            }
        }
    }

}
