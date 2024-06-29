package com.asessment.demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Stocks {

     @Id
     @GeneratedValue(strategy = GenerationType.IDENTITY)
     private Long id;
     private String stockName;
     private Long stocksAvailable;
     private Long stocksSold;
     private String category;

     public Stocks(String stockName, String category) {

          this.stockName=stockName;
          this.category=category;
     }

     public Stocks(Long id,String stocksName, String categoryName, Long unitsToSold, long l) {
          this.id=id;
          this.stockName=stocksName;
          this.category=categoryName;
          this.stocksSold=unitsToSold;
          this.stocksAvailable=l;
     }

     public Stocks(Long id, String stockName, long l,long s) {
          this.id=id;
          this.stockName=stockName;
          this.stocksAvailable=l;
          this.stocksSold=s;
     }
}
