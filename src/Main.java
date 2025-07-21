import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Chương trình quản lý nhân viên ====");
        // Hiển thị menu chức năng
        System.out.println("Chọn chức năng:");
        // Các chức năng quản lý nhân viên
        System.out.println("NV1. Thêm nhân viên");
        System.out.println("NV2. Hiển thị danh sách nhân viên");
        System.out.println("NV3. Tìm kiếm nhân viên theo ID");
        System.out.println("NV4. Tìm kiếm nhân viên theo tên");
        System.out.println("NV5. Cập nhật thông tin nhân viên");
        System.out.println("NV6. Xóa nhân viên");
        System.out.println("NV7. Thống kê nhân viên");
        // Các chức năng quản lý phòng ban
        System.out.println("PB1. Thêm phòng ban");
        System.out.println("PB2. Hiển thị danh sách phòng ban");
        System.out.println("PB3. Tìm kiếm phòng ban theo tên");
        System.out.println("PB4. Cập nhật thông tin phòng ban");
        System.out.println("PB5. Xóa phòng ban");
        System.out.println("PB6. Thêm nhân viên vào phòng ban");
        System.out.println("PB7. Hiển thị danh sách nhân viên trong phòng ban");
        System.out.println("PB8. Xóa nhân viên khỏi phòng ban");
        System.out.println("PB9. Thống kê nhân viên trong phòng ban");
        // Thoát chương trình
        System.out.println("0. Thoát");
        System.out.println("========================================");
        EmployeeManagerImp employeeManagerImp = new EmployeeManagerImp();
        DepartmentManagerImp departmentManagerImp = new DepartmentManagerImp();
        while (true) {
            System.out.print("Nhập lựa chọn của bạn: ");
            String choice = scanner.nextLine().trim().toUpperCase();
            // Xử lý lựa chọn của người dùng
            switch (choice) {
                case "NV1":
                    employeeManagerImp.addEmployee(scanner);
                    break;
                case "NV2":
                    employeeManagerImp.displayEmployees(scanner);
                    break;
                case "NV3":
                    employeeManagerImp.searchEmployeeById(scanner);
                    break;
                case "NV4":
                    employeeManagerImp.searchEmployeeByName(scanner);
                    break;
                case "NV5":
                    employeeManagerImp.updateEmployee(scanner);
                    break;
                case "NV6":
                    employeeManagerImp.deleteEmployee(scanner);
                    break;
                case "NV7":
                    employeeManagerImp.statistics(scanner);
                    break;
                case "PB1":
                    departmentManagerImp.addDepartment(scanner);
                    break;
                case "PB2":
                    departmentManagerImp.displayDepartments(scanner);
                    break;
                case "PB3":
                    departmentManagerImp.searchDepartmentByName(scanner);
                    break;
                case "PB4":
                    departmentManagerImp.updateDepartment(scanner);
                    break;
                case "PB5":
                    departmentManagerImp.deleteDepartment(scanner);
                    break;
                case "PB6":
                    departmentManagerImp.addEmployeeToDepartment(scanner);
                    break;
                case "PB7":
                    departmentManagerImp.displayEmployeesInDepartment(scanner);
                    break;
                case "PB8":
                    departmentManagerImp.deleteEmployeeFromDepartment(scanner);
                    break;
                case "PB9":
                    departmentManagerImp.statisticsInDepartment(scanner);
                    break;
                case "0":
                    System.out.println("Cảm ơn bạn đã sử dụng chương trình. Tạm biệt!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
            System.out.println("========================================");
        }
    }
}
