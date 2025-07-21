# 🏢 Employee Management System

Hệ thống quản lý nhân viên toàn diện được phát triển bằng Java, cung cấp các chức năng quản lý nhân viên và phòng ban một cách hiệu quả.

[![Java](https://img.shields.io/badge/Java-11+-orange.svg)](https://openjdk.java.net/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![Build Status](https://img.shields.io/badge/Build-Passing-green.svg)]()

## 📋 Mục lục

- [Tổng quan](#tổng-quan)
- [Tính năng](#tính-năng)
- [Cấu trúc dự án](#cấu-trúc-dự-án)
- [Yêu cầu hệ thống](#yêu-cầu-hệ-thống)
- [Cài đặt và chạy](#cài-đặt-và-chạy)
- [Hướng dẫn sử dụng](#hướng-dẫn-sử-dụng)
- [API Documentation](#api-documentation)
- [Đóng góp](#đóng-góp)
- [Giấy phép](#giấy-phép)

## 🎯 Tổng quan

Employee Management System là một ứng dụng console Java được thiết kế để quản lý thông tin nhân viên và phòng ban trong một tổ chức. Hệ thống cung cấp giao diện dòng lệnh thân thiện với người dùng và các chức năng CRUD (Create, Read, Update, Delete) đầy đủ.

## ✨ Tính năng

### 👥 Quản lý nhân viên
- **NV1**: Thêm nhân viên mới
- **NV2**: Hiển thị danh sách nhân viên
- **NV3**: Tìm kiếm nhân viên theo ID
- **NV4**: Tìm kiếm nhân viên theo tên
- **NV5**: Cập nhật thông tin nhân viên
- **NV6**: Xóa nhân viên
- **NV7**: Thống kê nhân viên

### 🏢 Quản lý phòng ban
- **PB1**: Thêm phòng ban mới
- **PB2**: Hiển thị danh sách phòng ban
- **PB3**: Tìm kiếm phòng ban theo tên
- **PB4**: Cập nhật thông tin phòng ban
- **PB5**: Xóa phòng ban
- **PB6**: Thêm nhân viên vào phòng ban
- **PB7**: Hiển thị danh sách nhân viên trong phòng ban
- **PB8**: Xóa nhân viên khỏi phòng ban
- **PB9**: Thống kê nhân viên trong phòng ban

## 📁 Cấu trúc dự án

```
employee_management/
├── src/
│   ├── Main.java                   # Entry point của ứng dụng
│   ├── Employee.java               # Model class cho nhân viên
│   ├── Department.java             # Model class cho phòng ban
│   ├── EmployeeManager.java        # Interface quản lý nhân viên
│   ├── EmployeeManagerImp.java     # Implementation quản lý nhân viên
│   ├── DepartmentManager.java      # Interface quản lý phòng ban
│   └── DepartmentManagerImp.java   # Implementation quản lý phòng ban
├── employee_management.iml         # IntelliJ IDEA module file
└── README.md                       # Tài liệu dự án
```

## 🔧 Yêu cầu hệ thống

- **Java**: JDK 11 hoặc cao hơn
- **IDE**: IntelliJ IDEA, Eclipse, hoặc bất kỳ IDE Java nào
- **Dependencies**: 
  - Lombok (để giảm boilerplate code)
  - Java Time API (cho xử lý ngày tháng)

## 🚀 Cài đặt và chạy

### 1. Clone repository

```bash
git clone https://github.com/ntabodoiqua/VDT2025_EmployeeManagement.git
cd VDT2025_EmployeeManagement/employee_management
```

### 2. Biên dịch dự án

```bash
# Sử dụng javac
javac -cp ".:lib/*" src/*.java

# Hoặc sử dụng IDE
# Mở dự án trong IntelliJ IDEA hoặc Eclipse
```

### 3. Chạy ứng dụng

```bash
# Từ command line
java -cp ".:lib/*" -Dsrc.dir=src Main

# Hoặc chạy từ IDE
# Chạy file Main.java
```

## 📖 Hướng dẫn sử dụng

### Khởi động ứng dụng

Khi chạy ứng dụng, bạn sẽ thấy menu chính:

```
==== Chương trình quản lý nhân viên ====
Chọn chức năng:
NV1. Thêm nhân viên
NV2. Hiển thị danh sách nhân viên
...
0. Thoát
========================================
```

### Ví dụ sử dụng

1. **Thêm nhân viên mới**: Chọn `NV1` và nhập thông tin theo hướng dẫn
2. **Xem danh sách**: Chọn `NV2` để hiển thị tất cả nhân viên
3. **Tìm kiếm**: Sử dụng `NV3` hoặc `NV4` để tìm kiếm theo ID hoặc tên

### Model Classes

#### Employee
```java
public class Employee {
    private int id;
    private String name;
    private LocalDate dob;
    private Department department;
    private double salary;
    private String email;
    private String phone;
}
```

#### Department
```java
public class Department {
    private int id;
    private String name;
    private String address;
    private List<Employee> employees;
}
```

## 📚 API Documentation

### EmployeeManager Interface

| Method | Description |
|--------|-------------|
| `addEmployee(Scanner)` | Thêm nhân viên mới |
| `displayEmployees(Scanner)` | Hiển thị danh sách nhân viên |
| `searchEmployeeById(Scanner)` | Tìm kiếm nhân viên theo ID |
| `searchEmployeeByName(Scanner)` | Tìm kiếm nhân viên theo tên |
| `updateEmployee(Scanner)` | Cập nhật thông tin nhân viên |
| `deleteEmployee(Scanner)` | Xóa nhân viên |
| `statistics(Scanner)` | Thống kê nhân viên |

### DepartmentManager Interface

| Method | Description |
|--------|-------------|
| `addDepartment(Scanner)` | Thêm phòng ban mới |
| `displayDepartments(Scanner)` | Hiển thị danh sách phòng ban |
| `searchDepartmentByName(Scanner)` | Tìm kiếm phòng ban theo tên |
| `updateDepartment(Scanner)` | Cập nhật thông tin phòng ban |
| `deleteDepartment(Scanner)` | Xóa phòng ban |
| `addEmployeeToDepartment(Scanner)` | Thêm nhân viên vào phòng ban |
| `displayEmployeesInDepartment(Scanner)` | Hiển thị nhân viên trong phòng ban |
| `deleteEmployeeFromDepartment(Scanner)` | Xóa nhân viên khỏi phòng ban |
| `statisticsInDepartment(Scanner)` | Thống kê nhân viên trong phòng ban |

## 🔮 Tính năng tương lai

- [ ] Giao diện đồ họa (GUI) với JavaFX
- [ ] Kết nối cơ sở dữ liệu (MySQL/PostgreSQL)
- [ ] Export/Import dữ liệu (CSV, Excel)
- [ ] Báo cáo và biểu đồ thống kê
- [ ] Hệ thống xác thực và phân quyền
- [ ] API REST cho tích hợp với hệ thống khác

## 🤝 Đóng góp

Chúng tôi hoan nghênh mọi đóng góp! Để đóng góp:

1. Fork repository
2. Tạo feature branch (`git checkout -b feature/amazing-feature`)
3. Commit changes (`git commit -m 'Add amazing feature'`)
4. Push to branch (`git push origin feature/amazing-feature`)
5. Mở Pull Request

### Quy tắc đóng góp

- Tuân thủ Java coding conventions
- Viết unit tests cho code mới
- Cập nhật documentation khi cần thiết
- Đảm bảo code không có lỗi compile

## 📝 Giấy phép

Dự án này được phân phối dưới giấy phép MIT. Xem file [LICENSE](LICENSE) để biết thêm chi tiết.

## 📞 Liên hệ

- **Author**: VDT 2025 Team
- **Email**: vdt2025@example.com
- **Repository**: [VDT2025_EmployeeManagement](https://github.com/ntabodoiqua/VDT2025_EmployeeManagement)

---

⭐ Nếu dự án này hữu ích cho bạn, hãy star repository này!

**Made with ❤️ for VDT 2025**
