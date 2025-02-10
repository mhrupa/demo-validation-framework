package com.example.validation.repository;

import com.example.validation.report.ValidationError;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationErrorRepository extends MongoRepository<ValidationError, String> {
}
