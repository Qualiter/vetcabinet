package org.vetcabinet.branches.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.vetcabinet.clinic.model.Clinic;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "clinic_branches")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "code", nullable = false, unique = true)
    private String code;
    @ManyToOne
    @JoinColumn(name = "clinic_uuid")
    private Clinic clinic;
    @Column(name = "is_main", nullable = false)
    private Boolean isMain;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "short_name", nullable = false)
    private String shortName;
    @Column(name = "full_address", nullable = false)
    private String fullAddress;
    @Column(name = "address", nullable = false)
    private String address;
    @JdbcTypeCode(SqlTypes.DECIMAL)
    @Column(precision = 19, scale = 7)
    private BigDecimal latitude;
    @JdbcTypeCode(SqlTypes.DECIMAL)
    @Column(precision = 19, scale = 7)
    private BigDecimal longitude;
    @Column(name = "is_storeyed")
    private Boolean isStoreyed;
    @Column(name = "floor", unique = true)
    private Integer floor;
}
