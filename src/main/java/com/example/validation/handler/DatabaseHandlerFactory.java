package com.example.validation.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseHandlerFactory {

    @Autowired
    private MySQLDatabaseHandler mySQLDatabaseHandler;

    @Autowired
    private MongoDBDatabaseHandler mongoDBDatabaseHandler;

    public DatabaseHandler getHandler(String databaseType) {
        switch (databaseType.toLowerCase()) {
            case "mysql":
                return mySQLDatabaseHandler;
            case "mongodb":
                return mongoDBDatabaseHandler;
            default:
                throw new IllegalArgumentException("Unsupported database type: " + databaseType);
        }
    }
}
