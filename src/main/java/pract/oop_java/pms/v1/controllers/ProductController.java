package pract.oop_java.pms.v1.controllers;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pract.oop_java.pms.v1.dto.requests.CreateCustomerDTO;
import pract.oop_java.pms.v1.dto.requests.CreateProductDTO;
import pract.oop_java.pms.v1.fileHandling.File;
import pract.oop_java.pms.v1.payload.ApiResponse;
import pract.oop_java.pms.v1.serviceImpl.FileServiceImpl;
import pract.oop_java.pms.v1.serviceImpl.ProductServiceImpl;
import pract.oop_java.pms.v1.utils.ExceptionUtils;

import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor

public class ProductController {

    private  final ProductServiceImpl productService;
    private final FileServiceImpl fileService;

    @Value("${uploads.directory}")
    private  String directory;




    @PostMapping("/create")
    ResponseEntity<ApiResponse> create(@RequestParam("path") MultipartFile document , @ModelAttribute() CreateProductDTO createProductDTO) {
        try {
        System.out.println("File has  been created");
          CreateProductDTO dto = new CreateProductDTO();
          dto.setName(createProductDTO.getName());
          dto.setCode(createProductDTO.getCode());
          dto.setType(createProductDTO.getType());
          dto.setPrice(createProductDTO.getPrice());
        System.out.println("File has  been created");
          File file  = fileService.create(document,directory);
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The product  created successfully", this.productService.createProduct(dto,file)));
        }catch (Exception e) {
            e.printStackTrace();
            return ExceptionUtils.handleControllerExceptions(e);
        }
    }

    /**
     *
     * @return
     */
    @GetMapping("/all")
    ResponseEntity<ApiResponse> getAllProducts(){
        try{

            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "All customers retrieved successfully", this.productService.getAllProducts()));
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
    ResponseEntity<ApiResponse> getProductById(@PathVariable("uuid") String id){
        try{
//            log.info(" Consumable retrieved successfully by id {}" , id);
            return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse(true, "found", this.productService.getProductById(UUID.fromString(id))));
        }catch (Exception exception){
//            log.error("Error retrieving consumable", exception);
            return  ExceptionUtils.handleControllerExceptions(exception);
        }
    }



//    @PutMapping("/update/{uuid}")
////    @PreAuthorize("hasAnyAuthority('ADMIN')")
//    ResponseEntity<ApiResponse> update(@PathVariable("uuid") String id, @Validated @RequestBody CreateProductDTO dto) {
//        try {
////            log.info("Updated an entity of type {} with uuid {}", ENTITY, id);
//            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The product updated successfully", this.productService.updateProduct(dto, UUID.fromString(id))));
//
//        } catch (Exception exception) {
////            log.error("Error updating consumable", exception);
//            return ExceptionUtils.handleControllerExceptions(exception);
//        }
//    }

    @DeleteMapping("delete/{uuid}")
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<ApiResponse> delete(@PathVariable @Validated String id) {
        try {
//            log.info("deleted an entity of type {} with uuid {}", ENTITY, id);

            this.productService.deleteProductById(UUID.fromString(id));
            return ResponseEntity.status(HttpStatus.CREATED).body(new ApiResponse(true, "The product is deleted successfully"));
        }catch (Exception exception) {
//            log.error("Error deleting consumable", exception);
            return  ExceptionUtils.handleControllerExceptions(exception);
        }
    }

}
