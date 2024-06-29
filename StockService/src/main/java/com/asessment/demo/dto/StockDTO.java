package com.asessment.demo.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class StockDTO {
    private String stockName;
    private String category;

}
