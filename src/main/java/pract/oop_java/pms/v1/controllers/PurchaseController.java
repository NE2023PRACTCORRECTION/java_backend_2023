package pract.oop_java.pms.v1.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pract.oop_java.pms.v1.dto.requests.CreateProductDTO;
import pract.oop_java.pms.v1.dto.requests.CreatePurchaseDTO;
import pract.oop_java.pms.v1.payload.ApiResponse;
import pract.oop_java.pms.v1.serviceImpl.PurchaseServiceImpl;
import pract.oop_java.pms.v1.utils.ExceptionUtils;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/purchase")
@RequiredArgsConstructor
public class PurchaseController {

    private  final PurchaseServiceImpl  purchaseService;

    @PostMapping("/create")
    ResponseEntity<ApiResponse> create(@Validated @RequestBody CreatePurchaseDTO purchaseDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The purchase  created successfully", this.purchaseService.createPurchase(purchaseDTO)));
        }catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }

    @GetMapping("/all")
    ResponseEntity<ApiResponse> getAllPurchases(){
        try{

            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "All purchases retrieved successfully", this.purchaseService.getAllPurchases()));
        }catch (Exception exception){
//            log.error("Error retrieving consumables", exception);
            return  ExceptionUtils.handleControllerExceptions(exception);
        }
    }

    @GetMapping("/id/{uuid}")
    ResponseEntity<ApiResponse> getPurchaseById(@PathVariable("uuid") String id){
        try{
//            log.info(" Consumable retrieved successfully by id {}" , id);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "found", this.purchaseService.getPurchaseById(UUID.fromString(id))));
        }catch (Exception exception){
//            log.error("Error retrieving consumable", exception);
            return  ExceptionUtils.handleControllerExceptions(exception);
        }
    }

    /**
     *
     * @param id
     * @param purchaseDTO
     * @return
     */

    @PutMapping("/update/{uuid}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    ResponseEntity<ApiResponse> update(@PathVariable("uuid") String id, @Validated @RequestBody CreatePurchaseDTO purchaseDTO) {
        try {
//            log.info("Updated an entity of type {} with uuid {}", ENTITY, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The purchase updated successfully", this.purchaseService.updatePurchase(purchaseDTO, UUID.fromString(id))));

        } catch (Exception exception) {
//            log.error("Error updating consumable", exception);
            return ExceptionUtils.handleControllerExceptions(exception);
        }
    }

    @DeleteMapping("delete/{uuid}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> delete(@PathVariable @Validated String id) {
        try {
//            log.info("deleted an entity of type {} with uuid {}", ENTITY, id);

            this.purchaseService.deletePurchaseById(UUID.fromString(id));
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The purchase is deleted successfully"));
        }catch (Exception exception) {
//            log.error("Error deleting consumable", exception);
            return  ExceptionUtils.handleControllerExceptions(exception);
        }
    }

}
