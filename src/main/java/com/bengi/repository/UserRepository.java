package com.bengi.repository;

import com.bengi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by Ding Guo Hua on 12/22/2016.
 */
public interface UserRepository extends MongoRepository<User,String>{

        User findByUsername(String username);
}
