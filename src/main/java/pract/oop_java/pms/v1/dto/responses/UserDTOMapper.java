package pract.oop_java.pms.v1.dto.responses;

import org.springframework.stereotype.Component;
import pract.oop_java.pms.v1.models.User;

import java.util.function.Function;

@Component
public class UserDTOMapper implements Function<User , UserDTO> {
    @Override
    public UserDTO apply(User user) {
//        return new UserDTO(
//                user.getId(),
//                user.getEmail(),
//                user.getUsername(),
//                user.getRoles(),
//
//
//                user.getLastLogin()
//        );

        return  null ;
    }
}
