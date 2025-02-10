package com.example.validation.handler;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Component;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import java.util.List;
import java.util.Map;

@Component
public class MySQLDatabaseHandler implements DatabaseHandler {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Map<String, Object>> fetchOriginalData(String schema, String tableName) {
        String query = "SELECT * FROM " + schema + "." + tableName;
        return entityManager.createNativeQuery(query)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
                .list();
    }

    @Override
    public List<Map<String, Object>> fetchDemoData(String schema, String tableName) {
        String query = "SELECT * FROM demo_" + schema + "." + tableName;
        return entityManager.createNativeQuery(query)
                .unwrap(org.hibernate.query.NativeQuery.class)
                .setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE)
                .list();
    }
}
