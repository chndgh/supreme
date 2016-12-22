package com.bengi.config;

import com.bengi.model.User;
import com.bengi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * Created by Ding Guo Hua on 12/22/2016.
 */
@Service
public class DataInit {

    @Autowired
    UserRepository userRepository;

    @PostConstruct
    public void dataInit(){
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setRole("admin");
        userRepository.save(admin);

        User user = new User();
        user.setUsername("user");
        user.setPassword("user");
        user.setRole("user");
        userRepository.save(user);
    }
}
