package com.example.validation.service;

import com.example.validation.repository.RuleRepository;
import com.example.validation.handler.DatabaseHandlerFactory;
import com.example.validation.model.ValidationRuleDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private DatabaseHandlerFactory databaseHandlerFactory;

    public void validateAllDatabases() {
        List<ValidationRuleDocument> ruleDocs = ruleRepository.findAll();
        if (ruleDocs.isEmpty()) {
            throw new RuntimeException("No validation rules found.");
        }

        for (ValidationRuleDocument ruleDoc : ruleDocs) {
            String databaseType = ruleDoc.getDatabaseType();
            System.out.println("Validating database: " + ruleDoc.getDatabase() + " (" + databaseType + ")");
            // Implement database connection and validation logic
        }
    }
}
