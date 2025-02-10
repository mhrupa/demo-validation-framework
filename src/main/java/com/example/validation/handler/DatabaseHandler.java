package com.example.validation.handler;

import java.util.List;
import java.util.Map;

public interface DatabaseHandler {
    List<Map<String, Object>> fetchOriginalData(String schema, String tableName);
    List<Map<String, Object>> fetchDemoData(String schema, String tableName);
}
