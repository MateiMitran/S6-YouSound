//package com.backend.userservice.config;
//import com.mongodb.ConnectionString;
//import com.mongodb.MongoClientSettings;
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoClients;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;
//import org.springframework.data.mongodb.core.MongoTemplate;
//import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
//
//import java.net.UnknownHostException;
//import java.util.Collection;
//import java.util.Collections;
//
//@Configuration
//public class MongoConfig extends AbstractMongoClientConfiguration {
//
//    @Override
//    protected String getDatabaseName() {
//        return "users";
//    }
//
//    @Override
//    public MongoClient mongoClient() {
//        ConnectionString connectionString = new ConnectionString("mongodb+srv://techwaves6g4:RNTVlJRbgemNZyoW@user.eardllh.mongodb.net/users?retryWrites=true&w=majority");
//        MongoClientSettings mongoClientSettings = MongoClientSettings.builder()
//                .applyConnectionString(connectionString)
//                .build();
//
//        return MongoClients.create(mongoClientSettings);
//    }
//
//    @Bean
//    public MongoTemplate mongoTemplate() throws UnknownHostException {
//        return new MongoTemplate(
//                new SimpleMongoClientDatabaseFactory(
//                        mongoClient(),
//                        getDatabaseName()
//                )
//        );
//    }
//
//    @Override
//    public Collection getMappingBasePackages() {
//        return Collections.singleton("com.backend.userservice");
//    }
//}