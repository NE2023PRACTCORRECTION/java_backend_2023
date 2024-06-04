package pract.oop_java.pms.v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ResponseStatus;
import pract.oop_java.pms.v1.dto.responses.ErrorResponse;
import pract.oop_java.pms.v1.dto.responses.Response;
import pract.oop_java.pms.v1.enums.ResponseType;
import pract.oop_java.pms.v1.payload.ApiSecondResponse;


import java.util.ArrayList;
import java.util.List;



@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException{

    public NotFoundException(String message){
        super(message);
    }

//    public ResponseEntity<ApiSecondResponse> getResponse(){
//        List<String> details = new ArrayList<>();
//        details.add(super.getMessage());
//        ErrorResponse errorResponse = new ErrorResponse().setMessage("Failed to get a resource").setDetails(details);
//        Response<ErrorResponse> response = new Response<>();
//        response.setType(ResponseType.RESOURCE_NOT_FOUND);
//        response.setPayload(errorResponse);
//        return  ResponseEntity.ok(ApiSecondResponse.fail(this.getMessage()));
//    }
}