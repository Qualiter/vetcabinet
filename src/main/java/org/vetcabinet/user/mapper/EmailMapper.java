package org.vetcabinet.user.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.user.dto.EmailDto;
import org.vetcabinet.user.model.Email;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    Email toEmail(EmailDto email);

    List<Email> toListEmail(List<EmailDto> email);

    EmailDto toEmailDto(Email email);

    List<EmailDto> toListEmailDto(List<Email> email);
}
