package com.artsgard.sociodbbatch.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author artsgard
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "address")
public class AddressModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @NotNull
    @Column(name = "street", length = 100)
    private String street;
    
    @NotNull
    @Column(name = "city", length = 100)
    private String city;

    @Column(name = "postalcode", length = 100)
    private String postalcode;
    
    @Column(name = "province", length = 100)
    private String province;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private CountryModel country;
    
    @Column(name = "description", length = 500)
    private String description;
    
    
    public enum AddressType {
        HOME, SECOND, VACATION, BILLING
    }
    
    @NotNull
    @Column(name = "address_type", length = 100)
    @Enumerated(EnumType.STRING)
    private AddressType addressType;
    
    @JsonIgnoreProperties("socioAddresses")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "socio_id", nullable = true)
    private SocioModel socio;
}
