package com.artsgard.sociodbbatch.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "socio") //, catalog = "socio_db")
public class SocioModel implements Serializable { // UserDetails

    public SocioModel(Long id, String username, String password, String firstName, String lastName, String email, 
            Boolean active, List<LanguageModel> socioLanguages, List<AddressModel> socioAddresses) {
        this.id = id;
        this.password = password;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.active = active;
        this.socioLanguages = socioLanguages;
        this.socioAddresses = socioAddresses;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "first_name", nullable = true)
    private String firstName;

    @NotNull
    @Column(name = "last_name", nullable = true)
    private String lastName;

    @NotNull
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotNull
    @Column(name = "register_date", nullable = false)
    private Timestamp registerDate;
    
    @NotNull
    @Column(name = "last_checkin_date", nullable = false)
    private Timestamp lastCheckinDate;

    @NotNull
    @Column(name = "active", nullable = false)
    private Boolean active;
 
    @JsonIgnore
    @OneToMany(targetEntity=SocioAssociatedSocio.class, mappedBy="socio")
    private List<SocioAssociatedSocio> socios;
    
    @JsonIgnore
    @OneToMany(targetEntity=SocioAssociatedSocio.class, mappedBy="socio") //associatedSocio
    private List<SocioAssociatedSocio> associatedSocios;
    
    @ManyToMany
    @JoinTable(name = "socio_role", joinColumns = @JoinColumn(name = "socio_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<RoleModel> socioRoles;

    @NotNull
    @ManyToMany()
    @JoinTable(name = "socio_language", joinColumns = @JoinColumn(name = "socio_id"),
            inverseJoinColumns = @JoinColumn(name = "language_id"))
    private List<LanguageModel> socioLanguages;

    @OneToMany(mappedBy = "socio", cascade = CascadeType.REMOVE)
    private List<AddressModel> socioAddresses;
}
