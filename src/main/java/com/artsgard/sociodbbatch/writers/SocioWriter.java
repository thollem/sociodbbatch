package com.artsgard.sociodbbatch.writers;

import com.artsgard.sociodbbatch.model.SocioModel;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.artsgard.sociodbbatch.repository.SocioRepository;

/**
 *
 * @author artsgard
 */

@Component
public class SocioWriter implements ItemWriter<SocioModel> {
    
    @Autowired
    private SocioRepository repo;
    
    @Override
    public void write(List<? extends SocioModel> socios) throws Exception {
        repo.saveAll(socios);
    }
}
