package com.asessment.demo.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProductDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant createdDate;
    private String productName;
    private String productCategory;

    public ProductDetails(String productName, String category,Instant createdDate) {
        this.productName=productName;
        this.productCategory=category;
        this.createdDate=createdDate;

    }

}
