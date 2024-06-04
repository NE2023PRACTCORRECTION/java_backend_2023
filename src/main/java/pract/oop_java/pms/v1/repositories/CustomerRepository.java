package pract.oop_java.pms.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pract.oop_java.pms.v1.models.Customer;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer , UUID> {
    Optional<Customer> findCustomerByEmail(String email);
}
