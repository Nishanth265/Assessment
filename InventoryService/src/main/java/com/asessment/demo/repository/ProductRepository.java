package com.asessment.demo.repository;

import com.asessment.demo.model.ProductDetails;
import lombok.Builder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductDetails,Long> {

    List<ProductDetails> findByProductName(String name);

    List<ProductDetails> findByProductCategory(String category);

    @Query(value = "Select * from product_details where product_category=?1 and product_name=?2",nativeQuery = true)
    List<ProductDetails> getProductsByNameAndCategory(String category, String name);


    @Transactional
    @Modifying
    @Query(value = "DELETE FROM product_details WHERE product_name=?1",nativeQuery = true)
    void deleteProductName(String deleteName);
}
