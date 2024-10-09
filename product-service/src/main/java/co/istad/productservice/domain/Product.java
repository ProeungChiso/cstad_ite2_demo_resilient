package co.istad.productservice.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "pro_uuid")
    private String uuid;

    @Column(name = "pro_name")
    private String productName;

    @Column(name = "pro_desc")
    private String productDescription;

    @Column(name = "pro_category")
    private String productCategory;

    @Column(name = "pro_price")
    private Double productPrice;

    @Column(name = "pro_qty")
    private Integer productQuantity;
}
