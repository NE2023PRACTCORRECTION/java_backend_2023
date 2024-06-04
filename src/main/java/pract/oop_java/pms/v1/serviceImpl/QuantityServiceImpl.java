package pract.oop_java.pms.v1.serviceImpl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pract.oop_java.pms.v1.dto.requests.CreateQuantityDTO;
import pract.oop_java.pms.v1.exceptions.BadRequestException;
import pract.oop_java.pms.v1.exceptions.NotFoundException;
import pract.oop_java.pms.v1.exceptions.ResourceNotFoundException;
import pract.oop_java.pms.v1.models.Purchase;
import pract.oop_java.pms.v1.models.Quantity;
import pract.oop_java.pms.v1.repositories.QuantityRepository;
import pract.oop_java.pms.v1.services.QuantityService;
import pract.oop_java.pms.v1.utils.ExceptionUtils;

import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class QuantityServiceImpl implements QuantityService {
    private  final QuantityRepository quantityRepository;
    @Override
    public Quantity create(CreateQuantityDTO quantityDTO) {

        try {
            Quantity existingQuantity = this.getQuantityByproductCode(quantityDTO.getProductCode());
            if (existingQuantity != null) throw new BadRequestException("A quantity with this code already exists!");
            Quantity quantityEntity = new Quantity();
            quantityEntity.setOperation(quantityDTO.getOperation());
            quantityEntity.setDate(quantityDTO.getDate());
            quantityEntity.setProductCode(quantityDTO.getProductCode());
            quantityEntity.setQuantity(quantityDTO.getQuantity());
            return  quantityRepository.save(quantityEntity);


        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public List<Quantity> getAllQuantities() {

        try {
            return   quantityRepository.findAll();

        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public Quantity getQuantityById(UUID id) {
        try {
            Quantity quantity  =  quantityRepository.findById(id)
                    .orElseThrow(() -> new NotFoundException("The quantity with the given id was not found"));




        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
        return null;
    }

    @Override
    public Quantity updateQuantity(CreateQuantityDTO quantityDTO, UUID id) {
        try {
            Quantity existingQuantity = quantityRepository.findById(id).orElseThrow(() -> new NotFoundException("The purchase with the given id was not found"));
            Quantity quantityEntity = new Quantity();
            quantityEntity.setOperation(quantityDTO.getOperation());
            quantityEntity.setDate(quantityDTO.getDate());
            quantityEntity.setOperation(quantityDTO.getOperation());
            return  quantityRepository.save(quantityEntity);


        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
        }
        return null;
    }

    @Override
    public Quantity deleteQuantityById(UUID id) {
        try {
            Quantity quantity =  quantityRepository
                    .findById(id)
                    .orElseThrow(() -> new NotFoundException("The quantity with the given id was not found"));
            quantityRepository.delete(quantity);
            return quantity;
        }catch (Exception e){
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Quantity getQuantityByproductCode(String code) {
        try {
            return quantityRepository.getByProductCode(code);
        } catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return null;
        }
    }

    @Override
    public Quantity findQuantityByProductCode(String code) {
        try {
            return quantityRepository.findByProductCode(code)
                    .orElseThrow(() -> new ResourceNotFoundException("quantity with code " + code + " not found"));
        }catch (Exception e) {
            ExceptionUtils.handleServiceExceptions(e);
            return  null;
        }
    }
}
