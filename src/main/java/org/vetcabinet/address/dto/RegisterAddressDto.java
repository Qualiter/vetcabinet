package org.vetcabinet.address.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegisterAddressDto {
    @NotBlank
    private String postcode;

    @NotBlank
    private String fullAddress;

    @NotBlank
    private BigDecimal latitude;

    @NotBlank
    private BigDecimal longitude;

    @NotBlank
    private Boolean isStoreyed;

    @NotBlank
    private Long floor;
    private String additionalData;
}