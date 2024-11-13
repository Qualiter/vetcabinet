package org.vetcabinet.user.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginDto {
    @NotBlank
    @Size(min = 3, max = 20)
    private String login;

    @NotBlank
    @Size(min = 8, max = 64)
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()_+-=\\[\\]{}\\|;:'\\\",.<>?/])" +
            "[A-Za-z\\d!@#$%^&*()_+-=\\[\\]{}\\|;:'\\\",.<>?/]{8,64}$",
            message = "Пароль должен содержать от 8 до 64 символов, включая по крайней мере одну заглавную букву, " +
                    "одну строчную букву, одну цифру и один специальный символ.")
    private String password;
}