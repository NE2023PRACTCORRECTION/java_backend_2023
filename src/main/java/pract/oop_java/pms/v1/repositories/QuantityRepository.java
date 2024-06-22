package pract.oop_java.pms.v1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pract.oop_java.pms.v1.models.Product;
import pract.oop_java.pms.v1.models.Quantity;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface QuantityRepository extends JpaRepository<Quantity, UUID> {

}
