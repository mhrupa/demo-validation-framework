package com.example.validation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.validation.report.ValidationError;
import com.example.validation.repository.ValidationErrorRepository;

@Service
public class ReportService {

    @Autowired
    private ValidationErrorRepository validationErrorRepository;

    public void logErrors(List<ValidationError> errors) {
        validationErrorRepository.saveAll(errors);
    }
}
