package com.example.validation.report;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "validation_errors")
public class ValidationError {
    private String tableName;
    private String columnName;
    private Object transformedValue;
    private Object demoValue;
}
