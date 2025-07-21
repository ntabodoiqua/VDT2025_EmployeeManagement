import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==== Chương trình quản lý nhân viên ====");
        System.out.println("Chọn chức năng:");
        System.out.println("1. Thêm nhân viên");
        System.out.println("2. Hiển thị danh sách nhân viên");
        System.out.println("3. Tìm kiếm nhân viên theo ID");
        System.out.println("4. Cập nhật thông tin nhân viên");
        System.out.println("5. Xóa nhân viên");
        System.out.println("6. Thoát");
        System.out.println("========================================");
        while (true) {
            EmployeeManagerImp employeeManagerImp = new EmployeeManagerImp();
            System.out.print("Nhập lựa chọn của bạn: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Đọc ký tự newline sau khi nhập số
            switch (choice) {
                case 1:
                    employeeManagerImp.addEmployee(scanner);
                    break;
                case 2:
                    employeeManagerImp.displayEmployees();
                    break;
                case 3:
                    employeeManagerImp.searchEmployeeById(scanner);
                    break;
                case 4:
                    employeeManagerImp.updateEmployee(scanner);
                    break;
                case 5:
                    employeeManagerImp.deleteEmployee(scanner);
                    break;
                case 6:
                    System.out.println("Thoát chương trình.");
                    scanner.close();
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng thử lại.");
            }
            System.out.println("========================================");
        }
    }
}
