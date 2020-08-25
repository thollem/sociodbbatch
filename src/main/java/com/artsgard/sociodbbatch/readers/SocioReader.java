package com.artsgard.sociodbbatch.readers;

import com.artsgard.sociodbbatch.socio.model.SocioModel;
import com.artsgard.sociodbbatch.socio.repository.SocioRepository;
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
public class SocioReader implements ItemReader<SocioModel> {
   
    @Autowired
    private SocioRepository repo;

    private Iterator<SocioModel> socioIterator;

    @BeforeStep
    public void before(StepExecution stepExecution) {
        socioIterator = repo.findAll().iterator();
    }

    @Override
    public SocioModel read() {
        if (socioIterator != null && socioIterator.hasNext()) {
            return socioIterator.next();
        } else {
            return null;
        }
    }
}
