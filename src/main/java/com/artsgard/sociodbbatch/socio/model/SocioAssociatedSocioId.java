package com.artsgard.sociodbbatch.socio.model;

import java.io.Serializable;

public class SocioAssociatedSocioId implements Serializable {
    private SocioAssociatedSocioId() { }
    private Long socioId;
    private Long associatedSocioId;

    @Override
    public int hashCode() {
        return (int) (socioId + associatedSocioId);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof SocioAssociatedSocioId) {
            SocioAssociatedSocioId otherId = (SocioAssociatedSocioId) object;
            return (otherId.socioId == this.socioId)
                    && (otherId.associatedSocioId == this.associatedSocioId);
        }
        return false;
    }
}