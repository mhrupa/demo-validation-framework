package com.example.validation.controller;

import com.example.validation.service.ValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/validation")
public class ValidationController {

    @Autowired
    private ValidationService validationService;

    @PostMapping("/execute")
    public ResponseEntity<String> executeValidation() {
        validationService.validateAllDatabases();
        return ResponseEntity.ok("Validation started for all configured databases.");
    }
}
