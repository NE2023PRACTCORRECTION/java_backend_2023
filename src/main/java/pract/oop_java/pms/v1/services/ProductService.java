package pract.oop_java.pms.v1.services;

import pract.oop_java.pms.v1.dto.requests.CreateProductDTO;
import pract.oop_java.pms.v1.fileHandling.File;
import pract.oop_java.pms.v1.models.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product createProduct(CreateProductDTO productDTO , File file);
    List<Product> getAllProducts ();

    Product getProductById(UUID id);

    Product updateProduct(CreateProductDTO productDTO,UUID id,File file);

    Product deleteProductById(UUID id);

    Product getProductByCode(String code);
    Product findProductByCode(String code);
}
