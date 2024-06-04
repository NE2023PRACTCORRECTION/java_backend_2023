package pract.oop_java.pms.v1.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pract.oop_java.pms.v1.dto.requests.CreatePurchaseDTO;
import pract.oop_java.pms.v1.exceptions.BadRequestException;
import pract.oop_java.pms.v1.exceptions.NotFoundException;
import pract.oop_java.pms.v1.exceptions.ResourceNotFoundException;
import pract.oop_java.pms.v1.models.Product;
import pract.oop_java.pms.v1.models.Purchase;
import pract.oop_java.pms.v1.repositories.ProductRepository;
import pract.oop_java.pms.v1.repositories.PurchaseRepository;
import pract.oop_java.pms.v1.services.PurchaseService;
import pract.oop_java.pms.v1.utils.ExceptionUtils;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PurchaseServiceImpl implements PurchaseService {
    private  final PurchaseRepository purchaseRepository;
    private  final ProductRepository productRepository;
    @Override
    public Purchase createPurchase(CreatePurchaseDTO purchaseDTO) {

        try {
            Purchase existingPurchase = this.getPurchaseByproduct_code(purchaseDTO.getProductCode());
            if (existingPurchase != null) throw new BadRequestException("A product with this code already exists!");
            Purchase purchaseEntity = new Purchase();
            purchaseEntity.setDate(purchaseDTO.getDate());
            purchaseEntity.setTotal(purchaseDTO.getTotal());
            purchaseEntity.setQuantity(purchaseDTO.getQuantity());
            purchaseEntity.setProductCode(purchaseDTO.getProductCode());
            return  purchaseRepository.save(purchaseEntity);
        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }

    }

    @Override
    public List<Purchase> getAllPurchases() {

        try {
          return   purchaseRepository.findAll();

        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public Purchase getPurchaseById(UUID id) {
        try {
            Purchase purchase  =  purchaseRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("The purchase with the given id was not found"));




        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
        return null;
    }

    @Override
    public Purchase updatePurchase(CreatePurchaseDTO purchaseDTO, UUID id) {
        try {
            Purchase purchase = purchaseRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("The purchase with the given id was not found"));

            Purchase purchaseEntity = new Purchase();
            purchaseEntity.setDate(purchaseDTO.getDate());
            purchaseEntity.setTotal(purchaseDTO.getTotal());
            purchaseEntity.setQuantity(purchaseDTO.getQuantity());
            purchaseEntity.setProductCode(purchaseDTO.getProductCode());
            return purchaseRepository.save(purchaseEntity);
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
        }
       return  null;
    }

    @Override
    public Purchase deletePurchaseById(UUID id) {
        try {
            Purchase purchase =  purchaseRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("The purchase with the given id was not found"));
            purchaseRepository.delete(purchase);
            return purchase;
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Purchase getPurchaseByproduct_code(String code) {
        try {
            return purchaseRepository.getByProductCode(code);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Purchase findPurchaseByProduct_code(String code) {
        try {
            return purchaseRepository.findByProductCode(code)
                    .orElseThrow(() -> new ResourceNotFoundException("purchase with code " + code + " not found"));
        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return  null;
        }
    }
}
