package pract.oop_java.pms.v1.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
public class ApiResponse {
    public boolean success;
    public String message;

    public Object data;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

