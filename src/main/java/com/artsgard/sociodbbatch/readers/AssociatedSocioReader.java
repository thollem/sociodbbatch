package com.artsgard.sociodbbatch.readers;

import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.repository.AssociatedSocioRepository;
import java.util.Iterator;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 
 * @author artsgard
 */
@Component
public class AssociatedSocioReader implements ItemReader<SocioAssociatedSocio> {
   
    @Autowired
    private AssociatedSocioRepository repo;

    private Iterator<SocioAssociatedSocio> associatedSocioIterator;

    @BeforeStep
    public void before(StepExecution stepExecution) {
        associatedSocioIterator = repo.findAll().iterator();
    }

    @Override
    public SocioAssociatedSocio read() {
        if (associatedSocioIterator != null && associatedSocioIterator.hasNext()) {
            return associatedSocioIterator.next();
        } else {
            return null;
        }
    }
}
