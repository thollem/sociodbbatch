package com.artsgard.sociodbbatch.repository;

import com.artsgard.sociodbbatch.model.SocioModel;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SocioRepository extends JpaRepository<SocioModel, Long> {
    static final String SOCIO_PAGENATING_QUERY = "SELECT * FROM socio ORDER BY LAST_NAME ASC LIMIT :rows OFFSET :offset";
    static final String SOCIO_COUNT_QUERY = "SELECT COUNT(*) FROM SOCIO";
    static final String SOCIO_PAGENATING_LANGUAGE_QUERY = "SELECT * FROM socio JOIN socio_language "
            + "ON socio.id = socio_language.socio_id  JOIN language "
            + "ON socio_language.language_id = language.id  WHERE language.code=:lang "
            + "ORDER BY LAST_NAME ASC LIMIT :rows OFFSET :offset";
    static final String SOCIO_PAGENATING_COUNTRY_QUERY = "select * from socio join address "
            + "on socio.id = address.socio_id join country on country.id = address.country_id "
            + "where country.code=:country ORDER BY LAST_NAME ASC LIMIT :rows OFFSET :offset";
    static final String QUERY = "SELECT * FROM socio_db.socio WHERE socio_id IS NULL";
    
    Optional<SocioModel> findByUsername(String username);
     
    @Query(value = SOCIO_PAGENATING_QUERY, nativeQuery = true)
    public List<SocioModel> getSociosBySortedPage(@Param("rows") int rows,
            @Param("offset") int offset);    
            //@Param("sortParam") String sortParam);

    @Query(value = QUERY, nativeQuery = true)
    public SocioModel findregisteredSocioBySocioId(@Param("id") Long id);
    
    @Query(value = SOCIO_COUNT_QUERY, nativeQuery = true)
    public Integer getsOCIOCount();
    
    @Query(value = SOCIO_PAGENATING_LANGUAGE_QUERY, nativeQuery = true)
    public List<SocioModel> getSociosBySortedPageByLanguage(@Param("rows") int rows,
            @Param("offset") int offset, @Param("lang") String lang);  
    
    @Query(value = SOCIO_PAGENATING_COUNTRY_QUERY, nativeQuery = true)
    public List<SocioModel> getSociosBySortedPageByCountry(@Param("rows") int rows,
            @Param("offset") int offset, @Param("country") String country); 
}
