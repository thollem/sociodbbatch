package com.artsgard.sociodbbatch.processors;

import com.artsgard.sociodbbatch.model.SocioModel;
import com.artsgard.sociodbbatch.repository.SocioRepository;
import java.sql.Timestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;

/**
 *
 * @author artsgard
 */
@Component

public class SocioActiveProcessor implements ItemProcessor<SocioModel, SocioModel> {

    @Autowired
    private SocioRepository repo;

    @Override
    public SocioModel process(SocioModel socio) throws Exception {
        System.out.println("<<<<<<<<<<<<<<<<<<<<<<<<socio " + socio.getUsername());
        Timestamp date = socio.getLastCheckinDate();
        if (date.after(date)) {
            socio.setActive(Boolean.TRUE);
        } else {
            socio.setActive(Boolean.TRUE);
        }            
            
        return socio;
    }
}