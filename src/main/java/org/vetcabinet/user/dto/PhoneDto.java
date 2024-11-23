package org.vetcabinet.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PhoneDto {
    @NotBlank
    @Pattern(regexp = "^\\+7\\s?\\d{3}\\s?\\d{3}\\s?\\d{2}\\s?\\d{2}$")
    private String phone;
}
