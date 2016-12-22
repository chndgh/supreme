package com.bengi.config;

/**
 * Created by Ding Guo Hua on 12/22/2016.
 */

import com.bengi.util.MongoUtils;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMongoRepositories(basePackages = "com.bengi.repository")
public class MongoConfig {

    @Value("${bengi.mongo.db.username}")
    private String mongoUser;
    @Value("${bengi.mongo.db.password}")
    private String mongoPwd;
    @Value("${bengi.mongo.db.name}")
    private String db;
    @Value("${bengi.mongo.replica.set}")
    private String replicaSetString;

    public
    @Bean
    MongoDbFactory mongoDbFactory() throws Exception {
        List<ServerAddress> seeds = MongoUtils.parseReplicaSet(replicaSetString);
        if(StringUtils.isEmpty(mongoUser) && StringUtils.isEmpty(mongoPwd)){
            return new SimpleMongoDbFactory(new MongoClient(seeds),db);
        }else{
            return new SimpleMongoDbFactory(new MongoClient(seeds, Arrays.asList(MongoCredential.createMongoCRCredential(mongoUser, db, mongoPwd.toCharArray()))),db);
        }

    }
}
