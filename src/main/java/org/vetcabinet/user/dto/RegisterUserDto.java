package org.vetcabinet.user.dto;

import lombok.*;
import org.vetcabinet.address.dto.RegisterAddressDto;

import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class RegisterUserDto extends BaseUserDto {
    private String login;

    private String password;

    private List<PhoneDto> phone;

    private List<EmailDto> email;

    private String patronymic;

    private LocalDate birthday;

    private List<RegisterAddressDto> addresses;
}