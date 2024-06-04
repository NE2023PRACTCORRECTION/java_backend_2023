package pract.oop_java.pms.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pract.oop_java.pms.v1.models.Purchase;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, UUID> {
    Optional<Purchase> findByProductCode(String productCode);
    Purchase getByProductCode(String productCode);
}
