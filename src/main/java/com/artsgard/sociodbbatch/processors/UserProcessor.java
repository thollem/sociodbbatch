package com.artsgard.sociodbbatch.processors;

import com.artsgard.sociodbbatch.model.User;
import com.artsgard.sociodbbatch.repository.UserRepository;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 * @author artsgard
 */
@Component
public class UserProcessor implements ItemProcessor<User, User> {

    @Autowired
    private UserRepository repo;

    @Override
    public User process(User user) throws Exception {
        System.out.println(user.getUsername());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        user.setUsername(user.getUsername() + " / " +  dateFormat.format(new Date()));
        System.out.println(user.getUsername());
        return user;
    }
}