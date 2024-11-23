package org.vetcabinet.user.mapper;

import org.mapstruct.Mapper;
import org.vetcabinet.user.dto.EmailDto;
import org.vetcabinet.user.model.Email;

@Mapper(componentModel = "spring")
public interface EmailMapper {
    Email toEmail(EmailDto email);

    EmailDto toEmailDto(Email email);
}
