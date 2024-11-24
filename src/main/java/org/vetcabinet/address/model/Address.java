package org.vetcabinet.address.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Entity
@Table(name = "addresses")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    private String postcode;

    @Column(name = "full_address", nullable = false)
    private String fullAddress;

    private BigDecimal latitude;

    private BigDecimal longitude;

    @Column(name = "is_storeyed")
    private Boolean isStoreyed;

    private Long floor;

    @Column(name = "additional_data")
    private String additionalData;
}