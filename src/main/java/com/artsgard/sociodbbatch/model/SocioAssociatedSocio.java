	package com.artsgard.sociodbbatch.model;

import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name = "SocioAssociatedSocio")
@Table(name = "socio_associated_socio") //, catalog = "socio_db") , schema = "socio_db")
@IdClass(SocioAssociatedSocioId.class)
public class SocioAssociatedSocio implements Serializable {

    private SocioAssociatedSocio() { }

    public SocioAssociatedSocio(Long socioId, Long associatedSocioId, SocioModel socio, SocioModel associatedSocio, AssociatedSocioState associatedSocioState, Timestamp associatedSocioDate) {
        this.socioId = socioId;
        this.associatedSocioId = associatedSocioId;
        this.socio = socio;
        this.associatedSocio = associatedSocio;
        this.associatedSocioState = associatedSocioState;
        this.associatedSocioDate = associatedSocioDate;
    }
    
    @Id
    private Long socioId;
    
    @Id
    private Long associatedSocioId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socioId", updatable = false, insertable = false,
            referencedColumnName = "id")
    private SocioModel socio;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "associatedSocioId", updatable = false, insertable = false,
            referencedColumnName = "id")
    private SocioModel associatedSocio;
    
    public enum AssociatedSocioState {
        PENDING, EXPIRED, ACCEPTED, DENIED
    }

    @Column(name = "associated_socio_state", length = 100)
    @Enumerated(EnumType.STRING)
    private AssociatedSocioState associatedSocioState;
    
    @Column(name = "associated_socio_date", nullable = true)
    private Timestamp associatedSocioDate;

    public long getSocioId() {
        return socioId;
    }

    public void setSocioId(long socioId) {
        this.socioId = socioId;
    }

    public long getAssociatedSocioId() {
        return associatedSocioId;
    }

    public void setAssociatedSocioId(long associatedSocioId) {
        this.associatedSocioId = associatedSocioId;
    }

    public SocioModel getSocio() {
        return socio;
    }

    public void setSocio(SocioModel socio) {
        this.socio = socio;
    }

    public SocioModel getAssociatedSocio() {
        return associatedSocio;
    }

    public void setAssociatedSocio(SocioModel associatedSocio) {
        this.associatedSocio = associatedSocio;
    }

    public AssociatedSocioState getAssociatedSocioState() {
        return associatedSocioState;
    }

    public void setAssociatedSocioState(AssociatedSocioState associatedSocioState) {
        this.associatedSocioState = associatedSocioState;
    }

    public Timestamp getAssociatedSocioDate() {
        return associatedSocioDate;
    }

    public void setAssociatedSocioDate(Timestamp associatedSocioDate) {
        this.associatedSocioDate = associatedSocioDate;
    }
}