package pract.oop_java.pms.v1.services;

import pract.oop_java.pms.v1.dto.requests.CreateProductDTO;
import pract.oop_java.pms.v1.dto.requests.CreateQuantityDTO;
import pract.oop_java.pms.v1.models.Product;
import pract.oop_java.pms.v1.models.Purchase;
import pract.oop_java.pms.v1.models.Quantity;

import java.util.List;
import java.util.UUID;

public interface QuantityService {

    Quantity create(CreateQuantityDTO quantityDTO);
    List<Quantity> getAllQuantities ();

    Quantity getQuantityById(UUID id);

    Quantity updateQuantity(CreateQuantityDTO quantityDTO,UUID id);

    Quantity deleteQuantityById(UUID id);

    Quantity getQuantityByproductCode(String code);

    Quantity findQuantityByProductCode(String code);
}
