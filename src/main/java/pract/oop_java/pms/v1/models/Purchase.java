package pract.oop_java.pms.v1.models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne()
    @JoinColumn(name = "productId")
    private  Product productCode ;

    private  float quantity;

    @Transient
    private  float total;

    private Date date;
}
