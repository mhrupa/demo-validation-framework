package com.example.validation.model;

import org.springframework.data.mongodb.core.mapping.Document;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "validation_rules")
public class ValidationRuleDocument {
    private String database;
    private String databaseType;
    private ConnectionDetails connectionDetails;
    private List<SchemaRule> schemas;
}
