package com.bengi.repository;

import com.bengi.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by edward on 16/12/20.
 */
public interface ParticipantRepository extends MongoRepository<User, String> {
}
