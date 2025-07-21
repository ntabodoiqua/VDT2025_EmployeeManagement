import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Department {
    int id;
    String name;
    String address;
    // Danh sách nhân viên trong phòng ban
    List<Employee> employees;
}
