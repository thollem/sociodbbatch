package com.artsgard.sociodbbatch.writers;

import com.artsgard.sociodbbatch.model.User;
import com.artsgard.sociodbbatch.repository.UserRepository;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author artsgard
 */

@Component
public class UserWriter implements ItemWriter<User> {
    
    @Autowired
    private UserRepository repo;
    
    @Override
    public void write(List<? extends User> users) throws Exception {
        repo.saveAll(users);
    }
}
