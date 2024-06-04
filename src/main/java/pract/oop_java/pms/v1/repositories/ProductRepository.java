package pract.oop_java.pms.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pract.oop_java.pms.v1.models.Product;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product  , UUID> {

    Optional<Product> findByCode(String productCode);
    Product getByCode(String code);
}
