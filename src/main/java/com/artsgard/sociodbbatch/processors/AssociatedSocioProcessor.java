package com.artsgard.sociodbbatch.processors;

import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.repository.AssociatedSocioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 * @author artsgard
 */
@Component
public class AssociatedSocioProcessor implements ItemProcessor<SocioAssociatedSocio, SocioAssociatedSocio> {

    @Autowired
    private AssociatedSocioRepository repo;

    @Override
    public SocioAssociatedSocio process(SocioAssociatedSocio associated) throws Exception {
        
        System.out.println(associated.getAssociatedSocioState());
        associated.setAssociatedSocioState(SocioAssociatedSocio.AssociatedSocioState.EXPIRED);
        return associated;
    }
}