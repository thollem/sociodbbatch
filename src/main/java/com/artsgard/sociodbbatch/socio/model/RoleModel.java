package com.artsgard.sociodbbatch.socio.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
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
@Table(name = "role") //, catalog = "socio_db") , schema = "socio_db")
public class RoleModel implements Serializable {
    
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    
    @NotNull
    @Column(name = "name", length = 100)
    private String name;
}
