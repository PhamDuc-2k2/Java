package com.ducpham.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductResponse {
    private String code;
    private String name;
    private int quantity;
    private BigDecimal price;
    private String description;
    private List<Integer> productImageIds;
}
