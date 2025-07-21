import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Employee {
    int id;
    String name;
    LocalDate dob;
    // Phòng ban của nhân viên
    Department department;
    double salary;
    String email;
    String phone;
}
