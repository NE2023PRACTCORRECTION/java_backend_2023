package pract.oop_java.pms.v1.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {

    private  String email;
    private  String password;
}
