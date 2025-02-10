package com.example.validation.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.validation.handler.DatabaseHandler;
import com.example.validation.handler.DatabaseHandlerFactory;
import com.example.validation.model.ColumnRule;
import com.example.validation.model.SchemaRule;
import com.example.validation.model.TableRule;
import com.example.validation.model.ValidationRuleDocument;
import com.example.validation.report.ValidationError;

@Service
public class ValidationService {

    @Autowired
    private RuleLoaderService ruleLoaderService;

    @Autowired
    private DatabaseHandlerFactory databaseHandlerFactory;

    @Autowired
    private ReportService reportService;

    public void validateAllDatabases() {
        List<ValidationRuleDocument> ruleDocs = ruleLoaderService.getRules();
        if (ruleDocs.isEmpty()) {
            throw new RuntimeException("No validation rules found in rules.json.");
        }

        for (ValidationRuleDocument ruleDoc : ruleDocs) {
            String databaseType = ruleDoc.getDatabaseType();
            DatabaseHandler dbHandler = databaseHandlerFactory.getHandler(databaseType);

            System.out.println("Validating database: " + ruleDoc.getDatabase() + " (" + databaseType + ")");

            for (SchemaRule schemaRule : ruleDoc.getSchemas()) {
                String schema = schemaRule.getSchemaName();
                System.out.println("Processing schema: " + schema);

                for (TableRule tableRule : schemaRule.getTables()) {
                    String tableName = tableRule.getTableName();
                    System.out.println("Validating table: " + schema + "." + tableName);

                    // Fetch original data from the source DB
                    List<Map<String, Object>> originalData = dbHandler.fetchOriginalData(schema, tableName);

                    // Apply transformation rules
                    List<Map<String, Object>> transformedData = applyRules(originalData, tableRule);

                    // Fetch demo data from the destination DB
                    List<Map<String, Object>> demoData = dbHandler.fetchDemoData(schema, tableName);

                    // Compare transformed data with demo data
                    List<ValidationError> errors = compareData(tableRule, transformedData, demoData);

                    // Log validation errors
                    reportService.logErrors(errors);
                }
            }
        }
    }

    private List<Map<String, Object>> applyRules(List<Map<String, Object>> originalData, TableRule tableRule) {
        for (Map<String, Object> row : originalData) {
            for (ColumnRule columnRule : tableRule.getColumns()) {
                String columnName = columnRule.getColumn();
                String algo = columnRule.getAlgo();

                if (row.containsKey(columnName)) {
                    switch (algo) {
                        case "SHA256":
                            row.put(columnName, hashSHA256((String) row.get(columnName)));
                            break;
                        case "NullifyData":
                            row.put(columnName, null);
                            break;
                        case "Static":
                            row.put(columnName, "STATIC_VALUE");
                            break;
                        case "SHA256-UUID":
                            row.put(columnName, hashUUID((String) row.get(columnName)));
                            break;
                        default:
                            // Leave value unchanged
                            break;
                    }
                }
            }
        }
        return originalData;
    }

    private String hashSHA256(String value) {
        try {
            if (value == null) return null;
            java.security.MessageDigest digest = java.security.MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return java.util.Base64.getEncoder().encodeToString(hash);
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing value", e);
        }
    }

    private String hashUUID(String value) {
        if (value == null) return null;
        return java.util.UUID.nameUUIDFromBytes(value.getBytes()).toString();
    }

    private List<ValidationError> compareData(TableRule tableRule, List<Map<String, Object>> transformedData, List<Map<String, Object>> demoData) {
        List<ValidationError> errors = new java.util.ArrayList<>();

        for (int i = 0; i < transformedData.size(); i++) {
            Map<String, Object> transformedRow = transformedData.get(i);
            Map<String, Object> demoRow = demoData.get(i);

            for (ColumnRule columnRule : tableRule.getColumns()) {
                String columnName = columnRule.getColumn();
                if (!Objects.equals(transformedRow.get(columnName), demoRow.get(columnName))) {
                    errors.add(new ValidationError(tableRule.getTableName(), columnName, transformedRow.get(columnName), demoRow.get(columnName)));
                }
            }
        }
        return errors;
    }
}
