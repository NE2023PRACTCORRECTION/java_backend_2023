package pract.oop_java.pms.v1.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pract.oop_java.pms.v1.models.Role;
import pract.oop_java.pms.v1.models.User;

import java.util.Set;

@AllArgsConstructor
@Getter
@Setter
public class LoginResponse {

    public String token;
    public User userData;
    private Set<Role> userRoles;
}
