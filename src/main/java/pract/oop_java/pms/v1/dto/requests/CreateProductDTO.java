package pract.oop_java.pms.v1.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateProductDTO {

    private String code;
    private String type;
    private float price;
    private String name;
}
