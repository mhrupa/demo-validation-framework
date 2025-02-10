package com.example.validation.service;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import com.example.validation.model.ValidationRuleDocument;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class RuleLoaderService {

    private final List<ValidationRuleDocument> rules;

    public RuleLoaderService() {
        this.rules = loadRulesFromFile();
    }

    private List<ValidationRuleDocument> loadRulesFromFile() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(
                    new ClassPathResource("rules.json").getInputStream(),
                    new TypeReference<List<ValidationRuleDocument>>() {}
            );
        } catch (IOException e) {
            throw new RuntimeException("Failed to load rules.json", e);
        }
    }

    public List<ValidationRuleDocument> getRules() {
        return rules;
    }
}
