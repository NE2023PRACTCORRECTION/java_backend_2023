package pract.oop_java.pms.v1.dto.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import pract.oop_java.pms.v1.enums.ResponseType;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Response<T> {
   private ResponseType type;
   private T payload;
}
