package com.example.validation.model;

import lombok.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchemaRule {
    private String schemaName;
    private List<TableRule> tables;
}
