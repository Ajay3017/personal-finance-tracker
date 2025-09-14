package com.project.finance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CategoryBreakdown {

    private String category;

    private double total;
}
