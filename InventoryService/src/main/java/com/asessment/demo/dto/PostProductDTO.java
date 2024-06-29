package com.asessment.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class PostProductDTO {

    private String productName;

    private String category;
}
