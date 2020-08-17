package com.artsgard.sociodbbatch.writers;

import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.repository.AssociatedSocioRepository;
import org.springframework.batch.item.ItemWriter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class SocioPendingWriter implements ItemWriter<SocioAssociatedSocio> {

    @Autowired
    private AssociatedSocioRepository repo;
/*    
    @Autowired
    @Qualifier("dbEntityManagerFactory")
    private EntityManagerFactory entityManagerFactory;
*/
    @Override
    @Transactional
    public void write(List<? extends SocioAssociatedSocio> asociatedSocios) {
        repo.saveAll(asociatedSocios);
    }
}
