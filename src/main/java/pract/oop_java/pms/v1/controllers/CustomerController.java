package pract.oop_java.pms.v1.controllers;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pract.oop_java.pms.v1.dto.requests.CreateCustomerDTO;
import pract.oop_java.pms.v1.dto.requests.LoginRequest;
import pract.oop_java.pms.v1.models.Customer;
import pract.oop_java.pms.v1.payload.ApiResponse;
import pract.oop_java.pms.v1.serviceImpl.CustomerServiceImpl;
import pract.oop_java.pms.v1.utils.ExceptionUtils;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

    private  final CustomerServiceImpl  customerService;


    /**
     *
     * @param customerDTO
     * @return
     */

    @PostMapping("/create")
    ResponseEntity<ApiResponse> create(@Validated @RequestBody CreateCustomerDTO customerDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The customer  created successfully", this.customerService.createCustomer(customerDTO)));
        }catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
    }

    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The customer  loggedin successfully", this.customerService.login(loginRequest)));
        } catch (Exception e) {
            return ExceptionUtils.handleControllerExceptions(e);
        }

    }
    /**
     *
     * @return
     */
    @GetMapping("/all")
    ResponseEntity<ApiResponse> getAllCustomers(){
        try{

            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "All customers retrieved successfully", this.customerService.getAllCustomers()));
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
    ResponseEntity<ApiResponse> getCustomerById(@PathVariable("uuid") String id){
        try{
//            log.info(" Consumable retrieved successfully by id {}" , id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "found", this.customerService.getCustomerById(UUID.fromString(id))));
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
    ResponseEntity<ApiResponse> update(@PathVariable("uuid") String id, @Validated @RequestBody CreateCustomerDTO dto) {
        try {
//            log.info("Updated an entity of type {} with uuid {}", ENTITY, id);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The consumable updated successfully", this.customerService.updateCustomer(dto, UUID.fromString(id))));

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

            this.customerService.deleteCustomerById(UUID.fromString(id));
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The consumable is deleted successfully"));
        }catch (Exception exception) {
//            log.error("Error deleting consumable", exception);
            return  ExceptionUtils.handleControllerExceptions(exception);
        }
    }


}
