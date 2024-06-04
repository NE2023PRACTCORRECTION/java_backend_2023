package pract.oop_java.pms.v1.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import pract.oop_java.pms.v1.dto.requests.CreateUserDTO;
import pract.oop_java.pms.v1.dto.requests.UpdateUserDTO;
import pract.oop_java.pms.v1.enums.EUserStatus;
import pract.oop_java.pms.v1.models.Person;
import pract.oop_java.pms.v1.models.User;

import java.util.List;
import java.util.UUID;

public interface UserService {

    // CRUD OPERATIONS
    List<User> getAllUsers();

    Page<User> getAllUsers(Pageable pageable);

    User getUserById(UUID uuid);

    User getUserByEmail(String email);

    User createUser(CreateUserDTO user);

    User updateUser(UUID uuid, UpdateUserDTO user);

    User voidUser(UUID uuid);

    User deleteUser(UUID uuid);

    // OTHER METHODS

    boolean isUserLoggedIn();

    User getLoggedInUser();

    boolean isNotUnique(Person user);

    boolean validateUserEntry(Person user);






    // getting users by other parameters
    List<User> getUsersByRole(UUID role);

    Page<User> getUsersByRole(UUID role, Pageable pageable);



    List<User> getUsersByAccountStatus(EUserStatus userStatus);

    Page<User> getUsersByAccountStatus(EUserStatus userStatus, Pageable pageable);














}
