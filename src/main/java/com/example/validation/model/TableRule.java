package com.example.validation.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TableRule {
    private String tableName;
    private List<ColumnRule> columns;
}
