package pract.oop_java.pms.v1.serviceImpl;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pract.oop_java.pms.v1.dto.requests.CreateProductDTO;
import pract.oop_java.pms.v1.exceptions.BadRequestException;
import pract.oop_java.pms.v1.exceptions.NotFoundException;
import pract.oop_java.pms.v1.exceptions.ResourceNotFoundException;
import pract.oop_java.pms.v1.fileHandling.File;
import pract.oop_java.pms.v1.models.Customer;
import pract.oop_java.pms.v1.models.Product;
import pract.oop_java.pms.v1.repositories.FileRepository;
import pract.oop_java.pms.v1.repositories.ProductRepository;
import pract.oop_java.pms.v1.services.ProductService;
import pract.oop_java.pms.v1.utils.ExceptionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {
    private  final ProductRepository productRepository;
    private final FileRepository fileRepository;
//    private  final FileServiceImpl fileService ;

    @Override
    public Product createProduct(CreateProductDTO productDTO , File file) {

        try {
            File fileService = fileRepository.save(file);

            Product existingProduct = this.getProductByCode(productDTO.getCode());
            if (existingProduct != null) throw new BadRequestException("A product with this code already exists!");
            Product productEntity = new Product();
            productEntity.setCode(productDTO.getCode());
            productEntity.setDate(new Date());
            productEntity.setName(productDTO.getName());
            productEntity.setType(productDTO.getType());
            productEntity.setPath(file);
            productEntity.setPrice(productDTO.getPrice());

            productRepository.save(productEntity);

            return productEntity;

        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {

        try {
            return productRepository.findAll();
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;

        }

    }

    @Override
    public Product getProductById(UUID id) {

        try {
            Product product  =  productRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("The product with the given id was not found"));


        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
        return null;
    }

    @Override
    public Product updateProduct(CreateProductDTO productDTO, UUID id ,File file) {
        try {
            File fileservice =  fileRepository.save(file);
            Product productEntity = productRepository.findById(id).orElseThrow(() -> new NotFoundException("The product with the provided id was not found"));
            productEntity.setDate(new Date());
            productEntity.setName(productDTO.getName());
//            productEntity.setImage(productDTO.getImage());
            productEntity.setType(productDTO.getType());
//            productDTO.setImage(productDTO.getImage());
            productDTO.setPrice(productEntity.getPrice());
            return productEntity;
        }
        catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Product deleteProductById(UUID id) {
        try {
            Product product =  productRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("The Product with the given id was not found"));
            productRepository.delete(product);
            return product;
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }

    }

    @Override
    public Product getProductByCode(String code) {
        try {
            return productRepository.getByCode(code);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Product findProductByCode(String code) {
        try {
            return productRepository.findByCode(code)
                    .orElseThrow(() -> new ResourceNotFoundException("Product with code " + code + " not found"));
        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return  null;
        }
    }
}
