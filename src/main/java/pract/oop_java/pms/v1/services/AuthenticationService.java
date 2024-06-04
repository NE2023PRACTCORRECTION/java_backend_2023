package pract.oop_java.pms.v1.services;

import pract.oop_java.pms.v1.dto.requests.LoginDTO;
import pract.oop_java.pms.v1.dto.responses.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginDTO dto);

    // other methods

}
