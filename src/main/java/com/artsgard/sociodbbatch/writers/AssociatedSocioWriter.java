package com.artsgard.sociodbbatch.writers;

import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.repository.AssociatedSocioRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author artsgard
 */

@Component
public class AssociatedSocioWriter implements ItemWriter<SocioAssociatedSocio> {
    
    @Autowired
    private AssociatedSocioRepository repo;
    
    @Override
    public void write(List<? extends SocioAssociatedSocio> associated) throws Exception {
        List<SocioAssociatedSocio> list = new ArrayList();
        
        associated.stream().filter(scs -> (scs != null)).forEachOrdered(scs -> {
            list.add(scs);
        });
        repo.saveAll(list);
    }
}
