package com.artsgard.sociodbbatch.processors;

import com.artsgard.sociodbbatch.model.SocioModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;
import com.artsgard.sociodbbatch.repository.SocioRepository;

/**
 *
 * @author artsgard
 */
@Component
public class SocioProcessor implements ItemProcessor<SocioModel, SocioModel> {

    @Autowired
    private SocioRepository repo;

    @Override
    public SocioModel process(SocioModel socio) throws Exception {
        System.out.println(socio.getUsername());
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        socio.setUsername(socio.getUsername() + " / " +  dateFormat.format(new Date()));
        System.out.println(socio.getUsername());
        return socio;
    }
}