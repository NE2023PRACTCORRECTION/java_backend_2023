package pract.oop_java.pms.v1.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateQuantityDTO {

    private String productCode;
    private  float quantity;
    private  String operation;
    private Date date;

}
