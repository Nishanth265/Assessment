package com.asessment.demo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Optional;

@Builder
@Data
public class SellDTO {

    private String stocksName;

    private String CategoryName;

    private Long unitsToSold;

}
