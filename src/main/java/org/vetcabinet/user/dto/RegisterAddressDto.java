package org.vetcabinet.user.dto;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegisterAddressDto {
    private String postcode;

    private String fullAddress;

    private BigDecimal latitude;

    private BigDecimal longitude;

    private Boolean isStoreyed;

    private Long floor;

    private String additionalData;
}