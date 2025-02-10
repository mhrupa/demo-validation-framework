package com.example.validation.handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
public class MongoDBDatabaseHandler implements DatabaseHandler {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<Map<String, Object>> fetchOriginalData(String schema, String tableName) {
        return fetchData(schema, tableName);
    }

    @Override
    public List<Map<String, Object>> fetchDemoData(String schema, String tableName) {
        return fetchData("demo_" + schema, tableName);
    }

    private List<Map<String, Object>> fetchData(String schema, String tableName) {
        List<?> result = mongoTemplate.findAll(Object.class, schema + "_" + tableName);
        return result.stream()
                     .map(obj -> (Map<String, Object>) obj)
                     .collect(Collectors.toList());
    }
}
