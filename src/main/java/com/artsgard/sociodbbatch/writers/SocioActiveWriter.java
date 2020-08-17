package com.artsgard.sociodbbatch.writers;

import com.artsgard.sociodbbatch.model.SocioModel;
import com.artsgard.sociodbbatch.repository.SocioRepository;
import java.util.List;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SocioActiveWriter extends JpaItemWriter<SocioModel> {

    @Autowired
    private SocioRepository repo;
/*
    @Autowired
    @Qualifier("dbEntityManagerFactory")
    private EntityManagerFactory entityManagerFactory;
*/
    @Override
    public void write(List<? extends SocioModel> socios) {
        repo.saveAll(socios);
    }
}
