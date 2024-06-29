package com.asessment.demo.service;


import com.asessment.demo.dto.PostProductDTO;
import com.asessment.demo.exception.ResourceNotFoundException;
import com.asessment.demo.model.ProductDetails;
import com.asessment.demo.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    @Transactional
    public ProductDetails insertNewProduct(PostProductDTO postProductDTO) {

        ProductDetails productDetails = new ProductDetails(postProductDTO.getProductName(),postProductDTO.getCategory(),
                                                            Instant.now());
        return productRepository.save(productDetails);
    }

    public List<ProductDetails> getProducts() {

        List<ProductDetails> productDetails=productRepository.findAll();
        if (productDetails.size()==0)
        {
            throw new ResourceNotFoundException("Products are not available");
        }
        return productDetails;


    }

    public List<ProductDetails> getProductsByName(String name) {

        List<ProductDetails> productDetails=productRepository.findByProductName(name);
        if (productDetails.size()==0)
        {
            throw new ResourceNotFoundException("Product Named "+name+" is not present in Inventory");
        }
        return productDetails;
    }

    public List<ProductDetails> getProductsByCategory(String category) {

        List<ProductDetails> productDetails = productRepository.findByProductCategory(category);
        if (productDetails.size()==0)
        {
            throw new ResourceNotFoundException("Category Named "+category+" is not present in Inventory");
        }
        return  productDetails;
    }

    public List<ProductDetails> getProductsByNameAndCategory(String category, String name) {
       List<ProductDetails> productDetails = productRepository.getProductsByNameAndCategory(category,name);
        if (productDetails.size()==0)
        {
            throw new ResourceNotFoundException("Product with "+name+" and "+category+" is not present");
        }
        return productDetails;
    }

    @Transactional
    public ProductDetails updateProductName(ProductDetails productDetails) {
        productDetails.setCreatedDate(Instant.now());
        return productRepository.save(productDetails);
    }

    public void deleteProductsByName(String deleteName) {

        List<ProductDetails> productPresent= productRepository.findByProductName(deleteName);
        if (productPresent.size()==0)
        {
            throw new ResourceNotFoundException("Given product is not present");
        }
        productRepository.deleteProductName(deleteName);

    }
}
