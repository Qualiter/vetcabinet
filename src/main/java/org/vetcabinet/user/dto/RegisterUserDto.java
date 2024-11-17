package org.vetcabinet.user.dto;

import lombok.*;

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

    private String phone;

    private String[] additionalPhones;

    private String email;

    private String patronymic;

    private LocalDate birthday;

    private List<RegisterAddressDto> addresses;
}