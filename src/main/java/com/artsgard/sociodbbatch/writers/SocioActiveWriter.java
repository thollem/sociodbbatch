package com.artsgard.sociodbbatch.writers;

import com.artsgard.sociodbbatch.model.SocioModel;
import com.artsgard.sociodbbatch.repository.SocioRepository;
import org.springframework.batch.item.ItemWriter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author artsgard
 */
@Component
public class SocioActiveWriter implements ItemWriter<SocioModel> {

    @Autowired
    private SocioRepository repo;

    @Override
    @Transactional
    public void write(List<? extends SocioModel> socios) throws Exception {
        repo.saveAll(socios);
    }
}
