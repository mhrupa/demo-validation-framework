package com.example.validation.dto;

import java.util.List;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationRuleDocument {
    @Id
    private String id;
    private String database;
    private String databaseType;
    private ConnectionDetails connectionDetails;
    private List<SchemaRule> schemas;
}
