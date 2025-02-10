package com.example.validation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnRule {
    private String column;
    private String algo;
    private String analyticssolumnname;
}