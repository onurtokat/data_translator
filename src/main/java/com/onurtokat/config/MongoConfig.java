package com.onurtokat.config;

import java.io.IOException;

import com.onurtokat.domain.DataDomain;
import cz.jirutka.spring.embedmongo.EmbeddedMongoFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.*;
import com.mongodb.MongoClient;

/**
 * MongoConfig is used for integration embedded mongo db
 *
 * @author onurtokat
 */
@Configuration
public class MongoConfig {

    private static final String MONGO_DB_URL = "localhost";
    private static final String MONGO_DB_NAME = "embeded_db";
    public static MongoTemplate mongoTemplate;
    /**
     * mongoTemplate method provides template with URL and port configuration
     *
     * @return configured mongo template
     * @throws IOException it is used temp mongo db exe creation, if file
     *                     cannot be created or deleted
     */
    @Bean
    public MongoTemplate mongoTemplate() throws IOException {
        EmbeddedMongoFactoryBean mongo = new EmbeddedMongoFactoryBean();
        mongo.setBindIp(MONGO_DB_URL);
        MongoClient mongoClient = mongo.getObject();
        mongoTemplate = new MongoTemplate(mongoClient, MONGO_DB_NAME);
        return mongoTemplate;
    }

    /** dropCollection static method provides drop operation when new data loading
     * to mongo db
     *
     */
    public static void dropCollection(){
        mongoTemplate.dropCollection("dataDomain");
    }
}