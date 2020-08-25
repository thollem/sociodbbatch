package com.artsgard.sociodbbatch.processors;

import com.artsgard.sociodbbatch.socio.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.socio.repository.AssociatedSocioRepository;
import java.sql.Timestamp;
import java.util.Calendar;
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

        if (associated.getAssociatedSocioDate() != null) {
            Timestamp now = new Timestamp(System.currentTimeMillis());
            Calendar calNow = Calendar.getInstance();
            calNow.setTimeInMillis(now.getTime());

            Calendar calPendingDate = Calendar.getInstance();
            Timestamp pendingDate = associated.getAssociatedSocioDate();

            calPendingDate.setTimeInMillis(pendingDate.getTime());
            calPendingDate.add(Calendar.MONTH, 1);

            if (calPendingDate.after(calNow)) {
                associated.setAssociatedSocioState(SocioAssociatedSocio.AssociatedSocioState.PENDING);
            } else {
                associated.setAssociatedSocioState(SocioAssociatedSocio.AssociatedSocioState.EXPIRED);
            }
        }
        return associated;
    }
}
