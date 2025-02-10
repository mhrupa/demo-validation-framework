package com.example.validation.repository;

import com.example.validation.model.ValidationRuleDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RuleRepository extends MongoRepository<ValidationRuleDocument, String> {
    ValidationRuleDocument findByDatabase(String database);
}
