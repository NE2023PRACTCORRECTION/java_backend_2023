package pract.oop_java.pms.v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import pract.oop_java.pms.v1.dto.responses.ErrorResponse;
import pract.oop_java.pms.v1.dto.responses.Response;
import pract.oop_java.pms.v1.enums.ResponseType;

import java.util.ArrayList;
import java.util.List;

public class TokenException extends Exception{
    private final HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;

    public TokenException(String message){
        super(message);
    }

    public ResponseEntity<Response> getResponseEntity() {
        List<String> details = new ArrayList<>();
        details.add(super.getMessage());
        ErrorResponse errorResponse = new ErrorResponse().setMessage("You do not have authority to access this resources").setDetails(details);
        Response<ErrorResponse> response = new Response<>();
        response.setPayload(errorResponse);
        response.setType(ResponseType.UNAUTHORIZED);
        return new ResponseEntity<Response>(response , httpStatus);
    }
}