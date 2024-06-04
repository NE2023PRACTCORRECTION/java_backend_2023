package pract.oop_java.pms.v1.dto.responses;

import java.util.Date;

public record UserDTO(
        Long userId,
        String email,
        String username,
        String national_id,
        String role,
        Date lastLogin
) {
}
