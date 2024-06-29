package com.asessment.demo.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddStocksDTO {

    private String stockName;

    private Long addUnits;
}
