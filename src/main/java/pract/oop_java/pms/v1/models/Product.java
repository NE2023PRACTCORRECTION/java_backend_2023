package pract.oop_java.pms.v1.models;


import lombok.*;
import pract.oop_java.pms.v1.fileHandling.File;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(unique = true)
    private  String productCode ;


    private String type;
    private float price;
    private Date date;
    private String name;
    @OneToOne
    @JoinColumn(referencedColumnName = "path")
    File path ;
}
