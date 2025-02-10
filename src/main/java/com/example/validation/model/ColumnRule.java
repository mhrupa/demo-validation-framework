package com.example.validation.model;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ColumnRule {
    private String column;
    private String algo;
    private String analyticssolumnname;
}
