package com.artsgard.sociodbbatch.repository;

import com.artsgard.sociodbbatch.model.SocioAssociatedSocio;
import com.artsgard.sociodbbatch.model.SocioAssociatedSocioId;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AssociatedSocioRepository extends JpaRepository<SocioAssociatedSocio, SocioAssociatedSocioId> {
    
    static final String ASSOCIATED_SOCIO_DELETE_QUERY = 
             "DELETE FROM socio_associated_socio WHERE socio_id=:socioId and associated_socio_id=:associatedSocioId";
    
    static final String ASSOCIATED_SOCIO_BY_IDS = 
             "SELECT * FROM socio_associated_socio WHERE socio_id=:socioId and associated_socio_id=:associatedSocioId";
    
    @Modifying
    @Transactional
    @Query(value = ASSOCIATED_SOCIO_DELETE_QUERY, nativeQuery = true)
    public void deleteAssociatedSocio(@Param("socioId") Long socioId, @Param("associatedSocioId") Long associatedSocioId);
    
    @Query(value = ASSOCIATED_SOCIO_BY_IDS, nativeQuery = true)
    public SocioAssociatedSocio getAssociatedSocioBySocioIdAndAssociatedSocioId(@Param("socioId") Long socioId, @Param("associatedSocioId") Long associatedSocioId);
}
