package com.artsgard.sociodbbatch.processors;

import com.artsgard.sociodbbatch.socio.model.SocioModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.batch.item.ItemProcessor;
import com.artsgard.sociodbbatch.socio.repository.SocioRepository;
import java.sql.Timestamp;
import java.util.Calendar;

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
        Timestamp now = new Timestamp(System.currentTimeMillis());
        Calendar calNow = Calendar.getInstance();
        calNow.setTimeInMillis(now.getTime());
        
        Calendar calRegister = Calendar.getInstance();
        Timestamp lastCheckin = socio.getLastCheckinDate();
       
        calRegister.setTimeInMillis(lastCheckin.getTime());
        calRegister.add(Calendar.MONTH, 1);
       
        if(calRegister.after(calNow)) {
            socio.setActive(Boolean.TRUE);
        } else {
            socio.setActive(Boolean.FALSE);
        }
        return socio;
    }
}