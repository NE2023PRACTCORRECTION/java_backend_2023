package pract.oop_java.pms.v1.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pract.oop_java.pms.v1.dto.requests.CreateProductDTO;
import pract.oop_java.pms.v1.dto.requests.CreateQuantityDTO;
import pract.oop_java.pms.v1.payload.ApiResponse;
import pract.oop_java.pms.v1.services.QuantityService;
import pract.oop_java.pms.v1.utils.ExceptionUtils;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/quantity")
@RequiredArgsConstructor
public class QuantityController {

    private  final QuantityService quantityService;

    @PostMapping("/create")
    ResponseEntity<ApiResponse> create(@Validated @RequestBody CreateQuantityDTO quantityDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The quantity  created successfully", this.quantityService.create(quantityDTO)));
        }catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }

    /**
     *
     * @return
     */
    @GetMapping("/all")
    ResponseEntity<ApiResponse> getAllQuantities(){
        try{

            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "All quantities retrieved successfully", this.quantityService.getAllQuantities()));
        }catch (Exception exception){
//            log.error("Error retrieving consumables", exception);
            return  ExceptionUtils.handleControllerExceptions(exception);
        }
    }

    /**
     *
     * @param id
     * @return
     */
    @GetMapping("/id/{uuid}")
    ResponseEntity<ApiResponse> getQuantityById(@PathVariable("uuid") String id){
        try{
//            log.info(" Consumable retrieved successfully by id {}" , id);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "found", this.quantityService.getQuantityById(UUID.fromString(id))));
        }catch (Exception exception){
//            log.error("Error retrieving consumable", exception);
            return  ExceptionUtils.handleControllerExceptions(exception);
        }
    }

    /**
     *
     * @param id
     * @param dto
     * @return
     */

    @PutMapping("/update/{uuid}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    ResponseEntity<ApiResponse> update(@PathVariable("uuid") String id, @Validated @RequestBody CreateQuantityDTO dto) {
        try {
//            log.info("Updated an entity of type {} with uuid {}", ENTITY, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The quantity updated successfully", this.quantityService.updateQuantity(dto, UUID.fromString(id))));

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

            this.quantityService.deleteQuantityById(UUID.fromString(id));
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The quantity is deleted successfully"));
        }catch (Exception exception) {
//            log.error("Error deleting consumable", exception);
            return  ExceptionUtils.handleControllerExceptions(exception);
        }
    }
}

