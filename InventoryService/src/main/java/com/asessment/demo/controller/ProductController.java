package com.asessment.demo.controller;

import com.asessment.demo.dto.PostProductDTO;
import com.asessment.demo.model.ProductDetails;
import com.asessment.demo.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {


    private final ProductService productService;

    @PostMapping("/newProduct")
    public ResponseEntity<?> newProduct(@RequestBody PostProductDTO postProductDTO){
        productService.insertNewProduct(postProductDTO);
        return new ResponseEntity<>("New product created successfully",HttpStatus.CREATED);
    }

    @GetMapping("/getProducts")
    public ResponseEntity<?> getProducts()
    {
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProducts());
    }

    @GetMapping("/getProductByName/{name}")
    public ResponseEntity<?> getProductByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByName(name));
    }

    @GetMapping("/getProductByCategory/{category}")
    public ResponseEntity<?> getProductByCategory(@PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByCategory(category));
    }

    @GetMapping("/getProductsByNameAndCategory")
    public ResponseEntity<?> getProductsByNameAndCategory(@RequestParam String category,@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(productService.getProductsByNameAndCategory(category,name));
    }

    @PutMapping("/updateProductName")
    public ResponseEntity<?> UpdateProductsName(@RequestBody ProductDetails productDetails){
        return ResponseEntity.status(HttpStatus.OK).body(productService.updateProductName(productDetails));
    }

    @DeleteMapping("/delete/{deleteName}")
    public ResponseEntity<?> deleteProductsByName(@PathVariable String deleteName){
        productService.deleteProductsByName(deleteName);
        return new ResponseEntity<>("Product with name "+deleteName+" deleted successfully",HttpStatus.OK);
    }

}