package com.asessment.demo.stockresponse;

import lombok.*;

import java.time.Instant;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @Getter
    @Setter
    public class ProductDetail {


        private Long id;
        private Instant createdDate;
        private String productCategory;
        private String productName;



    }

