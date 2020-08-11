package com.artsgard.sociodbbatch.model.com.artsgard.sociodbbatch.processors;

import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.repository.AssociatedSocioRepository;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 * @author artsgard
 */
@Component

public class SocioPendingProcessor implements ItemProcessor<SocioAssociatedSocio, SocioAssociatedSocio> {

    @Autowired
    private AssociatedSocioRepository repo;

    @Override
    public SocioAssociatedSocio process(SocioAssociatedSocio associatedSocios) throws Exception {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<associatedSocio " + associatedSocios.getAssociatedSocioState());
         Timestamp date = associatedSocios.getAssociatedSocioDate();
        if (date.after(date)) {
            associatedSocios.setAssociatedSocioState(SocioAssociatedSocio.AssociatedSocioState.ACCEPTED);
        } else {
            associatedSocios.setAssociatedSocioState(SocioAssociatedSocio.AssociatedSocioState.ACCEPTED);
        }     
        return associatedSocios;
    }
}