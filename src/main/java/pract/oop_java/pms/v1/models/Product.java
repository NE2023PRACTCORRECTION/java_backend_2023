package pract.oop_java.pms.v1.models;


import lombok.*;
import pract.oop_java.pms.v1.fileHandling.File;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private String code;

    private String type;
    private String price;
    private Date date;
    private String name;
    @OneToOne
    @JoinColumn(referencedColumnName = "path")
    File path ;
}
